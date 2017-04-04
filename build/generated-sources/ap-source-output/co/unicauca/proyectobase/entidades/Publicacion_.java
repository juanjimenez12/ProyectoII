package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Archivo;
import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.entidades.PalabraClave;
import co.unicauca.proyectobase.entidades.TipoPublicacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-04T08:45:59")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, Integer> pubIdentificador;
    public static volatile SingularAttribute<Publicacion, Date> pubFechaRegistro;
    public static volatile SingularAttribute<Publicacion, String> pubEstado;
    public static volatile SingularAttribute<Publicacion, String> pubTitulo;
    public static volatile SingularAttribute<Publicacion, Integer> pubCreditos;
    public static volatile ListAttribute<Publicacion, PalabraClave> palabraClaveList;
    public static volatile SingularAttribute<Publicacion, String> pubTituloRevista;
    public static volatile SingularAttribute<Publicacion, String> pubCategoria;
    public static volatile SingularAttribute<Publicacion, Estudiante> pubEstIdentificador;
    public static volatile SingularAttribute<Publicacion, TipoPublicacion> pubTpubIdentificador;
    public static volatile SingularAttribute<Publicacion, Date> pubFechaVisado;
    public static volatile SingularAttribute<Publicacion, String> pubLinkRevista;
    public static volatile ListAttribute<Publicacion, Archivo> archivoList;

}