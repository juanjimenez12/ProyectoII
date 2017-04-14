package co.unicauca.proyectobase.controladores;

import co.unicauca.proyectobase.dao.PublicacionFacade;
import co.unicauca.proyectobase.entidades.Publicacion;
import co.unicauca.proyectobase.utilidades.Utilidades;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
/*import java.nio.charset.StandardCharsets;
import java.util.Base64;*/
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "publicacionController")
@ManagedBean
@SessionScoped
public class PublicacionController implements Serializable {

    @EJB
    private PublicacionFacade dao;

    private Publicacion actual;

    private List<Publicacion> listaPublicaciones;

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<Publicacion> listaPublicacion) {
        this.listaPublicaciones = listaPublicacion;
    }

    String INICIO = "index";
    String CREAR = "new";
    String EDITAR = "editar";

    public PublicacionController() {
    }

    public Publicacion getActual() {
        if (actual == null) {
            actual = new Publicacion();
        }
        return actual;
    }

    public String index() {
        return INICIO;
    }

    public List<Publicacion> listado() {
        return dao.findAll();
    }

    public void agregar() {
        /*
        String contraseña = cifrarBase64(actual.getEstCodigo());
        actual.setEstContrasena(contraseña);

        String[] nombreusuario = actual.getEstCorreo().split("@");
        actual.setEstUsuario(nombreusuario[0]);

        actual.setEstEstado("Activo");

        dao.create(actual);
        mensajeconfirmarRegistro();
        limpiarCampos();
        */
        redirigirAlistar();
    }

    public void limpiarCampos() {
        actual = new Publicacion();
    }

    
    public String guardarEdicion() {
        dao.edit(actual);
        mensajeEditar();
        redirigirAlistar();
        return INICIO;
    }

   
    
    /*
    public String cambiarEstado(int id) {
        actual = dao.find(id);
        actual.setEstEstado("Inactivo");
        dao.edit(actual);
        mensajeDeshabilitar();
        return INICIO;
    }

    
    public boolean estudianteRegistrado(String codigo) {
        Estudiante estudiante = dao.find(codigo);

        if (estudiante != null) {
            return true;
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
*/
    public void verPublicacion(Publicacion pub) {
        actual = pub;
        System.out.println("si esta pasando por aqui");
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/VerPublicacion.xhtml");
    }

    public void editarPublicacion(Publicacion pub) {
        actual = pub;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/EditarPublicacion.xhtml");
    }

    /*redireccionamiento para boton cancelar*/
    public void redirigirAlistar() {
        limpiarCampos();
        //System.out.println("si esta pasando por aqui");
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/ListarPublicaciones.xhtml");
    }

    /*redireccion para volver a registrar */
    public void redirigirARegistrar() {
        limpiarCampos();
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/RegistrarPublicacion.xhtml");
    }

    /*Redireccion para volver a editar*/
    public void redirigirAEditar() {
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/EditarPublicacion.xhtml");
    }

    /*mensajes de confirmacion */
    public void mensajeEditar() {
        addMessage("ha editado satisfactoriamente la publicacion", "");
    }
/*
    public void mensajeDeshabilitar() {

        addMessage("ha deshabilitado satisfactoriamente la publicacion", "");
    }

    public void mensajelistadoEstudiantes() {
        addMessage("Usted abandono el registro y este es el istado de estudiantes.", "");
    }
*/
    public void mensajeconfirmarRegistro() {
        addMessage("Publicacion Registrada con exito ", "");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
