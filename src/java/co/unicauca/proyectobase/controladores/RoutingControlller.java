
package co.unicauca.proyectobase.controladores;


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



public class RoutingControlller  {
    
    
    
    public RoutingControlller() {
    }
    
    
    public void irAListarEstudiantes()
    {
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/ListarEstudiantes.xhtml");  
    }
    
    public void irARegistrarEstudiante()
    {
         Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/RegistrarEstudiante.xhtml");
    }
    
    public void irAEditarEstudiantes()
    {
        
    }
    
    public void irAVerEstudiante()
    {
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/VerEstudiante.xhtml");
    }
   
  
   
   
}






