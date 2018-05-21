package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.daoGen.ClienteUsuarioDao;
import br.com.geodrone.model.daoGen.RotaTrabalhoDao;
import br.com.geodrone.utils.DateUtils;

/**
 * Created by fernandes on 25/03/2018.
 */

public class RotaTrabalhoRepository extends CrudRepository<RotaTrabalho, Long>{
    public RotaTrabalhoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RotaTrabalho, Long> getCrudDao() {
        return getDaoSession().getRotaTrabalhoDao();
    }

    public List<RotaTrabalho> findAllByUsuario(Long idUsuario, Long idCliente, Date dtRegistro) {
        DateUtils dateUtils = new DateUtils();
        Date dtIncio = dateUtils.trunc(dtRegistro);
        Date dtFim = dateUtils.lastMinute(dtRegistro);

        QueryBuilder<RotaTrabalho> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(RotaTrabalhoDao.Properties.IdUsuarioReg.eq(idUsuario),
                        RotaTrabalhoDao.Properties.IdCliente.eq(idCliente),
                        RotaTrabalhoDao.Properties.DtRegistro.ge(dtIncio),
                        RotaTrabalhoDao.Properties.DtRegistro.le(dtFim));
        return qrBuilder.orderAsc(RotaTrabalhoDao.Properties.DtRegistro).list();
    }
}
