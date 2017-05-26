package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Coordinador;
import co.unicauca.proyectobase.entidades.DoctoradoPK;
import co.unicauca.proyectobase.entidades.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-25T15:05:00")
@StaticMetamodel(Doctorado.class)
public class Doctorado_ { 

    public static volatile SingularAttribute<Doctorado, Estudiante> estudiante;
    public static volatile SingularAttribute<Doctorado, Coordinador> coordinador;
    public static volatile SingularAttribute<Doctorado, DoctoradoPK> doctoradoPK;

}