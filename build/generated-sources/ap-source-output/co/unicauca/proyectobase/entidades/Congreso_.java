package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Publicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-25T15:05:00")
@StaticMetamodel(Congreso.class)
public class Congreso_ { 

    public static volatile SingularAttribute<Congreso, String> congTipoCongreso;
    public static volatile SingularAttribute<Congreso, Integer> pubIdentificador;
    public static volatile SingularAttribute<Congreso, String> congTituloPonencia;
    public static volatile SingularAttribute<Congreso, String> congNombreEvento;
    public static volatile SingularAttribute<Congreso, Publicacion> publicacion;

}