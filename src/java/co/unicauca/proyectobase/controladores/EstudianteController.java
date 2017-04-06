
package co.unicauca.proyectobase.controladores;

import co.unicauca.proyectobase.dao.EstudianteFacade;
import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.utilidades.Utilidades;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


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
        if(actual == null)
        {
            actual = new Estudiante();
        }
        return actual;
    }
    
    public String index(){
        return INICIO;
    }
    
    public List<Estudiante> listado(){
        return dao.findAll();
    }    
    
        
    public void agregar(){ 
        
        String contraseña = cifrarBase64(actual.getEstCodigo());
        actual.setEstContrasena(contraseña);

        String [] nombreusuario = actual.getEstCorreo().split("@");
        actual.setEstUsuario(nombreusuario[0]);

        actual.setEstEstado("Activo");

        dao.create(actual);
        confirmarRegistro();
        limpiarCampos();
        redirigirAlistar();
    }
    
    public void limpiarCampos()
    {
        actual = new Estudiante();
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
   
    public void verEstudiante(Estudiante est)
    {
        actual = est;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/VerEstudiante.xhtml"); 
    }
   
   
   /*redireccionamiento para boton cancelar*/
   
   public void redirigirAlistar()
   {       
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/ListarEstudiantes.xhtml");   
   }
   
   /*redireccion para volver a registrar */
   
   public void redirigirARegistrar()
   {
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/RegistrarEstudiante.xhtml");
   }
   
   /*mensajes de confirmacion */
   
    public void listadoEstudiantes()
    {
        addMessage("Usted abandono el registro y este es ellListado de estudiantes ","");
    }
   
   public void confirmarRegistro() {
        addMessage("Estudiante Registrado con exito ","");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
   
}
