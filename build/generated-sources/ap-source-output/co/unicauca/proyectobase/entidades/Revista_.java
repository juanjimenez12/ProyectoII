package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Publicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-04T10:20:41")
@StaticMetamodel(Revista.class)
public class Revista_ { 

    public static volatile SingularAttribute<Revista, Integer> pubIdentificador;
    public static volatile SingularAttribute<Revista, String> revNombreArticulo;
    public static volatile SingularAttribute<Revista, String> revNombreRevista;
    public static volatile SingularAttribute<Revista, String> revCategoria;
    public static volatile SingularAttribute<Revista, Publicacion> publicacion;

}