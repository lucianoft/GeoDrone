package br.com.geodrone.model.converter;

import org.greenrobot.greendao.converter.PropertyConverter;

import br.com.geodrone.model.constantes.FlagPerfilUsuario;

/**
 * Created by fernandes on 18/03/2018.
 */

public class FlagPerfilUsuarioConverter implements PropertyConverter<FlagPerfilUsuario, String> {
    @Override
    public FlagPerfilUsuario convertToEntityProperty(String databaseValue) {
        return FlagPerfilUsuario.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(FlagPerfilUsuario entityProperty) {
        return entityProperty.name();
    }
}
