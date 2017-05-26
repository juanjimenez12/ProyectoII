package co.unicauca.proyectobase.entidades;

import co.unicauca.proyectobase.entidades.Doctorado;
import co.unicauca.proyectobase.entidades.Publicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-25T15:05:00")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile SingularAttribute<Estudiante, String> estContrasena;
    public static volatile SingularAttribute<Estudiante, String> estUsuario;
    public static volatile SingularAttribute<Estudiante, String> estCodigo;
    public static volatile SingularAttribute<Estudiante, Integer> estIdentificador;
    public static volatile ListAttribute<Estudiante, Doctorado> doctoradoList;
    public static volatile SingularAttribute<Estudiante, String> estNombre;
    public static volatile ListAttribute<Estudiante, Publicacion> publicacionList;
    public static volatile SingularAttribute<Estudiante, String> estEstado;
    public static volatile SingularAttribute<Estudiante, Integer> estCohorte;
    public static volatile SingularAttribute<Estudiante, Integer> estSemestre;
    public static volatile SingularAttribute<Estudiante, Integer> estCreditos;
    public static volatile SingularAttribute<Estudiante, String> estApellido;
    public static volatile SingularAttribute<Estudiante, String> estCorreo;
    public static volatile SingularAttribute<Estudiante, String> estTutor;

}