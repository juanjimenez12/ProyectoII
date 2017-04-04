package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Doctorado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-04T08:45:59")
@StaticMetamodel(Coordinador.class)
public class Coordinador_ { 

    public static volatile SingularAttribute<Coordinador, String> cooContrasena;
    public static volatile SingularAttribute<Coordinador, String> cooUsuario;
    public static volatile SingularAttribute<Coordinador, String> cooNombre;
    public static volatile SingularAttribute<Coordinador, String> cooCorreo;
    public static volatile ListAttribute<Coordinador, Doctorado> doctoradoList;
    public static volatile SingularAttribute<Coordinador, Integer> cooIdentificador;

}