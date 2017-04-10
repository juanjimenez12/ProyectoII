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
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "estudianteController")
@ManagedBean
@SessionScoped
public class EstudianteController implements Serializable 
{
    @EJB
    private EstudianteFacade dao;

    private Estudiante actual;

    public EstudianteController() {
    }

    public Estudiante getActual() {
        if (actual == null) {
            actual = new Estudiante();
        }
        return actual;
    }

    public List<Estudiante> listado() {
        return dao.findAll();
    }

    public void agregar() throws EJBException
    {
        try
        {
            String contraseña = cifrarBase64(actual.getEstCodigo());
            actual.setEstContrasena(contraseña);

            String[] nombreusuario = actual.getEstCorreo().split("@");
            actual.setEstUsuario(nombreusuario[0]);

            actual.setEstEstado("Activo");

            dao.create(actual);
            mensajeconfirmarRegistro();
            limpiarCampos();
            redirigirAlistar();
        }
        catch(EJBException e)
        {
        }
    }

    public void limpiarCampos() {
        actual = new Estudiante();
    }

    
    public void guardarEdicion() throws EJBException
    {
        try
        {
            dao.edit(actual);
            mensajeEditar();
            redirigirAlistar(); 
        }
        catch(EJBException e)
        {
        }
    }

    public void cambiarEstado(int id) throws EJBException
    {
        try
        {
            actual = dao.find(id);
            actual.setEstEstado("Inactivo");
            dao.edit(actual);
            mensajeDeshabilitar(); 
        }
        catch(EJBException e)
        {
        }
    }

    public boolean estudianteRegistrado(String codigo) throws EJBException
    {
        try
        {
            Estudiante estudiante = dao.find(codigo);
            if (estudiante != null) {
                return true;
            }
            return false;
        }
        catch(EJBException e)
        {
        }
        return false;
    }

    public String cifrarBase64(String a) {
        Base64.Encoder encoder = Base64.getEncoder();
        String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8));
        return b;
    }

    public String descifrarBase64(String a) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(a);

        String b = new String(decodedByteArray);
        return b;
    }
    //jquery-3.1.1//

    public void verEstudiante(Estudiante est) {
        actual = est;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/VerEstudiante.xhtml");
    }

    public void editarEstudiante(Estudiante est) {
        actual = est;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/EditarEstudiante.xhtml");
    }

    /*redireccionamiento para boton cancelar*/
    public void redirigirAlistar() {
        limpiarCampos();
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/ListarEstudiantes.xhtml");
    }

    /*redireccion para volver a registrar */
    public void redirigirARegistrar() {
        limpiarCampos();
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/RegistrarEstudiante.xhtml");
    }

    /*Redireccion para volver a editar*/
    public void redirigirAEditar() {
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionUsuarios/EditarEstudiante.xhtml");
    }

    /*mensajes de confirmacion */
    public void mensajeEditar() {
        addMessage("ha editado satisfactoriamente al estudiante", "");
    }

    public void mensajeDeshabilitar() {

        addMessage("ha deshabilitado satisfactoriamente al estudiante", "");
    }

    public void mensajelistadoEstudiantes() {
        addMessage("Usted abandono el registro y este es el istado de estudiantes.", "");
    }

    public void mensajeconfirmarRegistro() {
        addMessage("Estudiante Registrado con exito ", "");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
