package co.unicauca.proyectobase.controladores;

import co.unicauca.proyectobase.dao.PublicacionFacade;
import co.unicauca.proyectobase.entidades.Archivo;
import co.unicauca.proyectobase.entidades.Congreso;
import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.entidades.Publicacion;
import co.unicauca.proyectobase.entidades.Revista;
import co.unicauca.proyectobase.utilidades.Utilidades;
import com.itextpdf.text.DocumentException;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
/*import java.nio.charset.StandardCharsets;
import java.util.Base64;*/
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile; 

@Named(value = "publicacionController")
@ManagedBean
@SessionScoped
public class PublicacionController implements Serializable {

    @EJB

    private PublicacionFacade dao;
    private Publicacion actual;
    private List<Publicacion> listaPublicaciones;
    private UploadedFile ArticuloPDF;
    private UploadedFile TablaContenidoPDF;

    public UploadedFile getArticuloPDF() {
        return ArticuloPDF;
    }

    public void setArticuloPDF(UploadedFile ArticuloPDF) {
        this.ArticuloPDF = ArticuloPDF;
    }

    public UploadedFile getTablaContenidoPDF() {
        return TablaContenidoPDF;
    }

    public void setTablaContenidoPDF(UploadedFile TablaContenidoPDF) {
        this.TablaContenidoPDF = TablaContenidoPDF;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaEstudiantes(List<Publicacion> listaPublicacion) {
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

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void agregar() {
        System.out.println("agregar");
        try {

            Estudiante est = dao.getEst();
            actual.setPubEstIdentificador(est);
            String nombreAut = getnombreAut();
            actual.setPubNombreAutor(nombreAut);
            int numPubRevis = dao.getnumFilasPubRev();
            actual.setPubIdentificador(numPubRevis);
            actual.getRevista().setPubIdentificador(numPubRevis);
            actual.getRevista().setPublicacion(actual);
            ArrayList<Archivo> CollArchivo = new ArrayList<>();
            int numArchivos = numPubRevis;
            Archivo archArt = new Archivo();
            archArt.setArcPubIdentificador(actual);
            archArt.setArcIdentificador(numArchivos);
            CollArchivo.add(archArt);
            Archivo arcTablaC = new Archivo();
            numArchivos = numArchivos + 1;
            arcTablaC.setArcPubIdentificador(actual);
            arcTablaC.setArcIdentificador(numArchivos);
            CollArchivo.add(arcTablaC);
            actual.setArchivoCollection(CollArchivo);
            actual.agregarMetadatos(ArticuloPDF, TablaContenidoPDF); 

        } catch (IOException | GeneralSecurityException | DocumentException | PathNotFoundException | AccessDeniedException ex) {
            Logger.getLogger(PublicacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        actual.setCongreso(null);
       
        actual.setPubEstado("Activo");
        dao.create(actual);
        mensajeconfirmarRegistro();
        limpiarCampos();
        redirigirAlistar();

    }

    public void limpiarCampos() {
        actual = new Publicacion();
        Revista rev = new Revista();
        Congreso cong = new Congreso();
        actual.setRevista(rev);
        actual.setCongreso(cong);

    }

    public String getnombreAut() {
        Estudiante est = dao.getEst();
        actual.setPubEstIdentificador(est);
        String nombreAut = "";
        nombreAut = "" + actual.getPubEstIdentificador().getEstNombre() + " " + actual.getPubEstIdentificador().getEstApellido();
        return nombreAut;
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
    public void verPublicacion(Publicacion est) {
        actual = est;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/VerPublicacion.xhtml");
    }

    public void editarPublicacion(Publicacion est) {
        actual = est;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/EditarPublicacion.xhtml");
    }

    /*redireccionamiento para boton cancelar*/
    public void redirigirAlistar() {
        limpiarCampos();
        System.out.println("si esta pasando por aqui");
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
