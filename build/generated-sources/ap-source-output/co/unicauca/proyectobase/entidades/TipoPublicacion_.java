package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Publicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-04T08:45:59")
@StaticMetamodel(TipoPublicacion.class)
public class TipoPublicacion_ { 

    public static volatile SingularAttribute<TipoPublicacion, String> tpubDescripcion;
    public static volatile SingularAttribute<TipoPublicacion, Integer> tpubIdentificador;
    public static volatile ListAttribute<TipoPublicacion, Publicacion> publicacionList;

}