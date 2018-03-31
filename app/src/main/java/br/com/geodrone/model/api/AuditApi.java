package br.com.geodrone.model.api;

import java.util.Date;

/**
 * Created by fernandes on 30/03/2018.
 */

public interface AuditApi {

    public Date getDtInclusao();

    public void setDtInclusao(Date dtInclusao);

    public Date getDtAlteracao();

    public void setDtAlteracao(Date dtAlteracao);

    public Long getIdUsuario();

    public void setIdUsuario(Long idUsuario);

}
