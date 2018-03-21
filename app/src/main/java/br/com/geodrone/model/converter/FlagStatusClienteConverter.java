package br.com.geodrone.model.converter;

import org.greenrobot.greendao.converter.PropertyConverter;

import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.model.constantes.FlagStatusCliente;

/**
 * Created by fernandes on 20/03/2018.
 */

public class FlagStatusClienteConverter implements PropertyConverter<FlagStatusCliente, String> {
    @Override
    public FlagStatusCliente convertToEntityProperty(String databaseValue) {
        return FlagStatusCliente.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(FlagStatusCliente flagStatusCliente) {
        return flagStatusCliente.name();
    }
}
