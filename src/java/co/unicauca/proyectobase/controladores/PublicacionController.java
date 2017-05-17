package co.unicauca.proyectobase.controladores;

import co.unicauca.proyectobase.dao.PublicacionFacade;
import co.unicauca.proyectobase.entidades.Archivo;
import co.unicauca.proyectobase.entidades.Congreso;
import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.entidades.Publicacion;
import co.unicauca.proyectobase.entidades.Revista;
import co.unicauca.proyectobase.entidades.Libro;
import co.unicauca.proyectobase.entidades.CapituloLibro;
//import static co.unicauca.proyectobase.entidades.GrupoTipoUsuario_.nombreUsuario;
import co.unicauca.proyectobase.entidades.archivoPDF;
import co.unicauca.proyectobase.utilidades.Utilidades;
import com.itextpdf.text.DocumentException;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.EJBException;
/*import java.nio.charset.StandardCharsets;
import java.util.Base64;*/
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named(value = "publicacionController")
@ManagedBean
@SessionScoped
public class PublicacionController implements Serializable {

    @EJB

    private PublicacionFacade dao;
    private Publicacion actual;
    private List<Publicacion> listaPublicaciones;
    private UploadedFile publicacionPDF;
    private UploadedFile TablaContenidoPDF;
    private UploadedFile cartaAprobacionPDF;
    private byte[] exportContent;
    private String pdfUrl;

    private StreamedContent streamedContent;
    private InputStream stream;
    private Estudiante auxEstudiante;

    private String creditos;
    private String variableFiltrado;

    public void visPdfPub() throws IOException {

        archivoPDF archivoPublic = actual.descargaPublicacion();
        InputStream fis = archivoPublic.getArchivo();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = fis.read(buffer)) != -1;) {
            os.write(buffer, 0, len);
        }
        os.flush();
        byte[] b = os.toByteArray();
        stream = new ByteArrayInputStream(b);
        stream.mark(0); //remember to this position!
        streamedContent = new DefaultStreamedContent(stream, "application/pdf");
    }

    public StreamedContent getStreamedContent() throws IOException {
        if (streamedContent != null) {
            streamedContent.getStream().reset(); //reset stream to the start position!
        }
        return streamedContent;
    }

    public String getCreditos() {
        creditos = "" + actual.getPubCreditos();
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public UploadedFile getPublicacionPDF() {
        return publicacionPDF;
    }

    public void setPublicacionPDF(UploadedFile publicacionPDF) {
        this.publicacionPDF = publicacionPDF;
    }

    public UploadedFile getTablaContenidoPDF() {
        return TablaContenidoPDF;
    }

    public void setTablaContenidoPDF(UploadedFile TablaContenidoPDF) {
        this.TablaContenidoPDF = TablaContenidoPDF;
    }

    public UploadedFile getCartaAprobacionPDF() {
        return cartaAprobacionPDF;
    }

    public void setCartaAprobacionPDF(UploadedFile cartaAprobacionPDF) {
        this.cartaAprobacionPDF = cartaAprobacionPDF;
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

        if ((variableFiltrado == null) || (variableFiltrado.equals(""))) {

            return dao.findAll();

        } else {

            return dao.ListadoPublicacionFilt(variableFiltrado);

        }

    }

    public List<Publicacion> listadoPublicaciones(String nombreUsuario) {

        if ((variableFiltrado == null) || (variableFiltrado.equals(""))) {

            Estudiante est = dao.obtenerEstudiante(nombreUsuario);
            setAuxEstudiante(est);

            int idEstudiante = est.getEstIdentificador();
            return dao.ListadoPublicacionEst(idEstudiante);

        } else {

            Estudiante est = dao.obtenerEstudiante(nombreUsuario);
            setAuxEstudiante(est);

            int idEstudiante = est.getEstIdentificador();
            return dao.ListadoPublicacionEstFilt(idEstudiante, variableFiltrado);

        }

    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        // facesContext.addMessage("event", new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void pdfCartaAprob() throws FileNotFoundException, IOException, IOException, IOException {
        archivoPDF archivoPublic = actual.descargaCartaAprobac();
        if (archivoPublic.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Para esta publicacion el Usuario no ha cargado un PDF de la carta de aprobacion  ", ""));

        } else {
            String[] nombreArchivo = archivoPublic.getNombreArchivo().split("\\.");
            InputStream fis = archivoPublic.getArchivo();

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            // response.setHeader("Content-Disposition", "inline;filename=" + archivoPublic.getNombreArchivo() + ".pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + nombreArchivo[0] + ".pdf");
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    public void pdfPub() throws FileNotFoundException, IOException, IOException, IOException {
        archivoPDF archivoPublic = actual.descargaPublicacion();

        if (archivoPublic.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no ha cargado un PDF para esta publicacion", ""));

        } else {

            String[] nombreArchivo = archivoPublic.getNombreArchivo().split("\\.");
            InputStream fis = archivoPublic.getArchivo();

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            // response.setHeader("Content-Disposition", "inline;filename=" + archivoPublic.getNombreArchivo() + ".pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + nombreArchivo[0] + ".pdf");
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();

        }

    }

    public void pdfPubTC() throws FileNotFoundException, IOException, IOException, IOException {
        archivoPDF archivoPublic = actual.descargaPubTC();
        if (archivoPublic.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no ha cargado un PDF de la tabla de contenido", ""));

        } else {
            String[] nombreArchivo = archivoPublic.getNombreArchivo().split("\\.");
            InputStream fis = archivoPublic.getArchivo();

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            // response.setHeader("Content-Disposition", "inline;filename=" + archivoPublic.getNombreArchivo() + ".pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + nombreArchivo[0] + ".pdf");
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();

        }
    }

    public void descargarCartaAprobac() throws FileNotFoundException, IOException {
        archivoPDF archivoPublic = actual.descargaCartaAprobac();
        if (archivoPublic.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Para esta publicacion el Usuario no ha cargado un PDF de la carta de aprobacion  ", ""));

        } else {

            InputStream fis = archivoPublic.getArchivo();
            String[] nombreArchivo = archivoPublic.getNombreArchivo().split("\\.");
            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo[0] + ".pdf");

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    public void descargarPublicacion() throws FileNotFoundException, IOException {
        archivoPDF archivoPublic = actual.descargaPublicacion();
        if (archivoPublic.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no ha cargado un PDF para esta publicacion", ""));

        } else {
            InputStream fis = archivoPublic.getArchivo();
            String[] nombreArchivo = archivoPublic.getNombreArchivo().split("\\.");
            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo[0] + ".pdf");

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    public void descargarPubTC() throws FileNotFoundException, IOException {
        archivoPDF archivoPubTC = actual.descargaPubTC();
        if (archivoPubTC.getNombreArchivo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no ha cargado un PDF de la tabla de contenido", ""));

        } else {
            byte[] buf;
            InputStream fis = archivoPubTC.getArchivo();
            String[] nombreArchivo = archivoPubTC.getNombreArchivo().split("\\.");

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo[0] + ".pdf");

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            // response.getOutputStream().write(buf);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    public void agregar() {
        if (cartaAprobacionPDF.getFileName().equalsIgnoreCase("")) {

            FacesContext.getCurrentInstance().addMessage("cartaAprobacion", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe subir una evidencia de la publicacion", ""));
        } else {
            System.out.println("agregar");
            Estudiante est = getAuxEstudiante();
            try {

                actual.setPubEstIdentificador(est);
                String nombreAut = "";
                nombreAut = "" + est.getEstNombre() + " " + est.getEstApellido();

                actual.setPubNombreAutor(nombreAut);
                int numPubRevis = dao.getnumFilasPubRev();
                actual.setPubIdentificador(numPubRevis);

                /* Dependiendo de si se adiciona una revista, un congreso,un libro o un  
               capitulo de un libro se crea el objeto respectivo*/
                if (actual.getPubTipoPublicacion().equals("revista")) {
                    actual.getRevista().setPubIdentificador(numPubRevis);
                    actual.getRevista().setPublicacion(actual);
                    actual.setCongreso(null);
                    actual.setCapituloLibro(null);
                    actual.setLibro(null);

                }
                if (actual.getPubTipoPublicacion().equals("congreso")) {

                    actual.getCongreso().setPubIdentificador(numPubRevis);
                    actual.getCongreso().setPublicacion(actual);
                    actual.setRevista(null);
                    actual.setCapituloLibro(null);
                    actual.setLibro(null);
                }

                if (actual.getPubTipoPublicacion().equals("libro")) {
                    /* SI no es una revista, el objeto a adicionar es un congreso*/
                    actual.getLibro().setPubIdentificador(numPubRevis);
                    actual.getLibro().setPublicacion(actual);
                    actual.setRevista(null);
                    actual.setCongreso(null);
                    actual.setCapituloLibro(null);
                }

                if (actual.getPubTipoPublicacion().equals("capitulo_libro")) {
                    /* SI no es una revista, el objeto a adicionar es un congreso*/
                    actual.getCapituloLibro().setPubIdentificador(numPubRevis);
                    actual.getCapituloLibro().setPublicacion(actual);
                    actual.setRevista(null);
                    actual.setCongreso(null);
                    actual.setLibro(null);
                }

                ArrayList<Archivo> CollArchivo = new ArrayList<>();
                int numArchivos = dao.getIdArchivo();

                Archivo archCartaAprob = new Archivo();
                archCartaAprob.setArcPubIdentificador(actual);
                archCartaAprob.setArcIdentificador(numArchivos);
                archCartaAprob.setArctipoPDFcargar("cartaAprobacion");
                CollArchivo.add(archCartaAprob);

                //int numArchivos = numPubRevis;
                if (!publicacionPDF.getFileName().equalsIgnoreCase("")) {
                    Archivo archArt = new Archivo();
                    numArchivos = numArchivos + 1;
                    archArt.setArcPubIdentificador(actual);
                    archArt.setArcIdentificador(numArchivos);
                    archArt.setArctipoPDFcargar("tipoPublicacion");
                    CollArchivo.add(archArt);
                }
                if (!TablaContenidoPDF.getFileName().equalsIgnoreCase("")) {
                    Archivo arcTablaC = new Archivo();
                    numArchivos = numArchivos + 1;
                    arcTablaC.setArcPubIdentificador(actual);
                    arcTablaC.setArcIdentificador(numArchivos);
                    arcTablaC.setArctipoPDFcargar("tablaContenido");
                    CollArchivo.add(arcTablaC);
                }
                actual.setArchivoCollection(CollArchivo);
                actual.agregarMetadatos(publicacionPDF, TablaContenidoPDF, cartaAprobacionPDF);

                actual.setPubEstado("Activo");
                dao.create(actual);
                dao.flush();
                mensajeconfirmarRegistro();
                limpiarCampos();
                redirigirAlistar(est.getEstUsuario());
                // redirigirAlistar(est.getEstUsuario());
            } catch (IOException | GeneralSecurityException | DocumentException | PathNotFoundException | AccessDeniedException | EJBException ex) {
                mensajeRegistroFallido();
                limpiarCampos();
                redirigirAlistar(est.getEstUsuario());
                //    redirigirAlistar(est.getEstUsuario());
                Logger.getLogger(PublicacionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void limpiarCampos() {
        actual = new Publicacion();
        Revista rev = new Revista();
        Congreso cong = new Congreso();
        Libro lib = new Libro();
        CapituloLibro caplib = new CapituloLibro();
        actual.setRevista(rev);
        actual.setCongreso(cong);
        actual.setLibro(lib);
        actual.setCapituloLibro(caplib);

    }

    public void limpiarCampos(String nombreUsuario) {

        actual = new Publicacion();
        Revista rev = new Revista();
        Congreso cong = new Congreso();
        Libro lib = new Libro();
        CapituloLibro caplib = new CapituloLibro();
        actual.setRevista(rev);
        actual.setCongreso(cong);
        actual.setLibro(lib);
        actual.setCapituloLibro(caplib);

        Estudiante est = dao.obtenerEstudiante(nombreUsuario);
        setAuxEstudiante(est);
    }

    public void fijarEstudiante(String nombreUsuario) {

        Estudiante est = dao.obtenerEstudiante(nombreUsuario);
        setAuxEstudiante(est);
    }

    public String getnombreAut() {
        Estudiante est = getAuxEstudiante();
        String nombreAut = "";
        nombreAut = "" + est.getEstNombre() + " " + est.getEstApellido();

        return nombreAut;
    }

    public String guardarEdicion() {
        dao.edit(actual);
        mensajeEditar();
        redirigirAlistar(getAuxEstudiante().getEstUsuario());
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
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/VerPublicacion.xhtml");
    }

    public void verPublicacionEst(Publicacion pub) {
        actual = pub;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/VerPublicacion_Est.xhtml");
    }

    public void editarPublicacion(Publicacion pub) {
        actual = pub;
        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/EditarPublicacion.xhtml");
    }

    /*redireccionamiento para boton cancelar*/
    public void redirigirAlistar(String nombreUsuario) {
        fijarEstudiante(nombreUsuario);
        limpiarCampos();
        System.out.println("si esta pasando por aqui");

        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/ListarPublicaciones_Est.xhtml");
    }

    public void redirigirAlistar() {

        limpiarCampos();
        System.out.println("si esta pasando por aqui");

        Utilidades.redireccionar("/ProyectoII/faces/componentes/gestionPublicaciones/ListarPublicaciones_Coord.xhtml");
    }

    /*redireccion para volver a registrar */
    public void redirigirARegistrar(String nombreUsuario) {
        limpiarCampos(nombreUsuario);
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

    public void mensajeRegistroFallido() {
        addErrorMessage("Ocurrio un error durante el registro de la Publicacion ", "");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addErrorMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Estudiante getAuxEstudiante() {
        return auxEstudiante;
    }

    public void setAuxEstudiante(Estudiante auxEstudiante) {
        this.auxEstudiante = auxEstudiante;
    }

    public String getVariableFiltrado() {
        return variableFiltrado;
    }

    public void setVariableFiltrado(String variableFiltrado) {
        this.variableFiltrado = variableFiltrado;
    }

    public void seleccionarArchivo(FileUploadEvent event) {
        String nombreArchivo = event.getFile().getFileName();
        UploadedFile Archivo = event.getFile();
        FacesMessage message = new FacesMessage("El archivo", event.getFile().getFileName() + " se selecciono exitosamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("filemessage");

    }

    public boolean renderizarRevista() {
        boolean ret = false;
        if (actual.getPubTipoPublicacion().equalsIgnoreCase("revista")) {
            ret = true;
        }
        return ret;

    }

    public boolean renderizarCongreso() {
        boolean ret = false;
        if (actual.getPubTipoPublicacion().equalsIgnoreCase("congreso")) {
            ret = true;
        }
        return ret;

    }

    public boolean renderizarLibro() {
        boolean ret = false;
        if (actual.getPubTipoPublicacion().equalsIgnoreCase("libro")) {
            ret = true;
        }
        return ret;

    }

    public boolean renderizarCapLibro() {
        boolean ret = false;
        if (actual.getPubTipoPublicacion().equalsIgnoreCase("capitulo_libro")) {
            ret = true;
        }
        return ret;

    }

    public void asignarCreditos() {

        int auxCreditos = Integer.parseInt(creditos);
        actual.setPubCreditos(auxCreditos);
        dao.edit(actual);
        dao.flush();
        redirigirAlistar();

    }

}
