package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by luciano on 31/03/2018.
 */

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_DISPOSITIVO")
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idRef;


    public Dispositivo() {
    }


    public Long getIdRef() {
        return idRef;
    }

    public void setIdRef(Long idRef) {
        this.idRef = idRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispositivo that = (Dispositivo) o;

        return idRef != null ? idRef.equals(that.idRef) : that.idRef == null;
    }

    @Override
    public int hashCode() {
        return idRef != null ? idRef.hashCode() : 0;
    }
}
