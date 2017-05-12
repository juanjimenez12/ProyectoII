package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Archivo;
import co.unicauca.proyectobase.entidades.Congreso;
import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.entidades.Revista;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-04T10:20:41")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, Integer> pubIdentificador;
    public static volatile SingularAttribute<Publicacion, String> pubHash;
    public static volatile SingularAttribute<Publicacion, Date> pubFechaPublicacion;
    public static volatile SingularAttribute<Publicacion, Date> pubFechaRegistro;
    public static volatile SingularAttribute<Publicacion, String> pubEstado;
    public static volatile SingularAttribute<Publicacion, Integer> pubCreditos;
    public static volatile SingularAttribute<Publicacion, Congreso> congreso;
    public static volatile SingularAttribute<Publicacion, Revista> revista;
    public static volatile SingularAttribute<Publicacion, String> pubAutoresSecundarios;
    public static volatile SingularAttribute<Publicacion, String> pubNombreAutor;
    public static volatile SingularAttribute<Publicacion, Estudiante> pubEstIdentificador;
    public static volatile SingularAttribute<Publicacion, Date> pubFechaVisado;
    public static volatile SingularAttribute<Publicacion, String> pubTipoPublicacion;
    public static volatile SingularAttribute<Publicacion, String> pubIsbn;
    public static volatile SingularAttribute<Publicacion, String> pubDoi;
    public static volatile CollectionAttribute<Publicacion, Archivo> archivoCollection;

}