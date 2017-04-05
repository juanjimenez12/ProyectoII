
package co.unicauca.proyectobase.controladores;

import co.unicauca.proyectobase.dao.EstudianteFacade;
import co.unicauca.proyectobase.entidades.Estudiante;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Named(value = "estudianteController")
@ManagedBean
@SessionScoped
public class EstudianteController implements Serializable {
    
    @EJB
    private EstudianteFacade dao;
    
    private Estudiante actual;
    
    private List<Estudiante> listaEstudiantes;    

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    String INICIO = "index";
    String CREAR = "new";
    String EDITAR = "editar";
    
    public EstudianteController() {
    }
    
    public Estudiante getActual(){
        if(actual==null){actual = new Estudiante();}
        if(actual.getEstSemestre() != null)
        {
            System.out.println("");
        }        
        return actual;
    }
    
    /*Obtener lista de estudiantes por estado
    public List<Estudiante> getListado(){
        if(listaEstudiantes==null){
            listaEstudiantes = new ArrayList;
        }
        return listaEstudiantes;
    }*/
    
    /*Listar estudiantes por estado
    public String listado(String estado){
        listaEstudiantes = dao.listarEstudiantesPorEstado(estado);        
        return "index";
    }*/
    
    public String index(){
        return INICIO;
    }
    
    public List<Estudiante> listado(){
        return dao.findAll();
    }    
    
    public void crear(){
        actual = new Estudiante();
    }
        
    public String agregar(){ 
        
        String contraseña = cifrarBase64(actual.getEstCodigo());
        actual.setEstContrasena(contraseña);

        String [] nombreusuario = actual.getEstCorreo().split("@");
        actual.setEstUsuario(nombreusuario[0]);

        actual.setEstEstado("Activo");

        dao.create(actual);
        
        actual = new Estudiante();
        
        return INICIO;        
    }
    
    public void actualizarActual(Estudiante estudiante)
    {
        this.actual = estudiante;
    }
    
    public String editar(int id){
        actual = dao.find(id);
        return EDITAR;
    }
    
    public String guardar(){
        dao.edit(actual);
        return INICIO;
    }
    
    public String delete(int id){
        actual = dao.find(id);
        actual.setEstEstado("Inactivo");
        return INICIO;
    }    
    
    public boolean estudianteRegistrado(String codigo){
        Estudiante estudiante = dao.find(codigo);
        
        if(estudiante!=null)
            return true;
        
        return false;
    }
    
    public String cifrarBase64(String a){
       Base64.Encoder encoder = Base64.getEncoder();
       String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );        
       return b;
    }

   public String descifrarBase64(String a){
       Base64.Decoder decoder = Base64.getDecoder();
       byte[] decodedByteArray = decoder.decode(a);

       String b = new String(decodedByteArray);        
       return b;
   }
}
