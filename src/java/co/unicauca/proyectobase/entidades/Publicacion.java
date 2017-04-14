/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/* Librerias Itext y sdk Openkm*/
import com.openkm.sdk4j.bean.Folder;
import org.apache.commons.io.IOUtils;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.form.Input;
import java.util.List;
import java.io.InputStream;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.AutomationException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.ExtensionException;
import com.openkm.sdk4j.exception.FileSizeExceededException;
import com.openkm.sdk4j.exception.ItemExistsException;
import com.openkm.sdk4j.exception.LockException;
import com.openkm.sdk4j.exception.NoSuchGroupException;
import com.openkm.sdk4j.exception.NoSuchPropertyException;
import com.openkm.sdk4j.exception.ParseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.UnsupportedMimeTypeException;
import com.openkm.sdk4j.exception.UserQuotaExceededException;
import com.openkm.sdk4j.exception.VirusDetectedException;
import com.openkm.sdk4j.exception.WebserviceException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DELL7
 */
@Entity
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p")
    , @NamedQuery(name = "Publicacion.findByPubIdentificador", query = "SELECT p FROM Publicacion p WHERE p.pubIdentificador = :pubIdentificador")
    , @NamedQuery(name = "Publicacion.findByPubHash", query = "SELECT p FROM Publicacion p WHERE p.pubHash = :pubHash")
    , @NamedQuery(name = "Publicacion.findByPubCreditos", query = "SELECT p FROM Publicacion p WHERE p.pubCreditos = :pubCreditos")
    , @NamedQuery(name = "Publicacion.findByPubFechaVisado", query = "SELECT p FROM Publicacion p WHERE p.pubFechaVisado = :pubFechaVisado")
    , @NamedQuery(name = "Publicacion.findByPubFechaRegistro", query = "SELECT p FROM Publicacion p WHERE p.pubFechaRegistro = :pubFechaRegistro")
    , @NamedQuery(name = "Publicacion.findByPubEstado", query = "SELECT p FROM Publicacion p WHERE p.pubEstado = :pubEstado")
    , @NamedQuery(name = "Publicacion.findByPubNombreAutor", query = "SELECT p FROM Publicacion p WHERE p.pubNombreAutor = :pubNombreAutor")
    , @NamedQuery(name = "Publicacion.findByPubAutoresSecundarios", query = "SELECT p FROM Publicacion p WHERE p.pubAutoresSecundarios = :pubAutoresSecundarios")
    , @NamedQuery(name = "Publicacion.findByPubTipoPublicacion", query = "SELECT p FROM Publicacion p WHERE p.pubTipoPublicacion = :pubTipoPublicacion")
    , @NamedQuery(name = "Publicacion.findByPubFechaPublicacion", query = "SELECT p FROM Publicacion p WHERE p.pubFechaPublicacion = :pubFechaPublicacion")
    , @NamedQuery(name = "Publicacion.estIdentificacion", query = "SELECT e FROM Estudiante e WHERE e.estIdentificador = :estIdentificador")
    , @NamedQuery(name = "Publicacion.findByPubDoi", query = "SELECT p FROM Publicacion p WHERE p.pubDoi = :pubDoi")
    , @NamedQuery(name = "Publicacion.findByPubIsbn", query = "SELECT p FROM Publicacion p WHERE p.pubIsbn = :pubIsbn")})

public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pub_identificador")
    private Integer pubIdentificador;
    @Size(max = 40)
    @Column(name = "pub_hash")
    private String pubHash;
    @Column(name = "pub_creditos")
    private Integer pubCreditos;
    @Column(name = "pub_fecha_visado")
    @Temporal(TemporalType.DATE)
    private Date pubFechaVisado;
    @Column(name = "pub_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date pubFechaRegistro;
    @Size(max = 15)
    @Column(name = "pub_estado")
    private String pubEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "pub_nombre_autor")
    private String pubNombreAutor;
    @Size(max = 300)
    @Column(name = "pub_autores_secundarios")
    private String pubAutoresSecundarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pub_tipo_publicacion")
    private String pubTipoPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pub_fecha_publicacion")
    @Temporal(TemporalType.DATE)
    private Date pubFechaPublicacion;
    @Size(max = 50)
    @Column(name = "pub_doi")
    private String pubDoi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pub_isbn")
    private String pubIsbn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arcPubIdentificador")
    private Collection<Archivo> archivoCollection;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "publicacion")
    private Congreso congreso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "publicacion")
    private Revista revista;
    @JoinColumn(name = "pub_est_identificador", referencedColumnName = "est_identificador")
    @ManyToOne(optional = false)
    private Estudiante pubEstIdentificador;
   
 

    public Publicacion() {
    }

    public Publicacion(Integer pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public Publicacion(Integer pubIdentificador, String pubNombreAutor, String pubTipoPublicacion, Date pubFechaPublicacion, String pubIsbn) {
        this.pubIdentificador = pubIdentificador;
        this.pubNombreAutor = pubNombreAutor;
        this.pubTipoPublicacion = pubTipoPublicacion;
        this.pubFechaPublicacion = pubFechaPublicacion;
        this.pubIsbn = pubIsbn;

    }

    public Integer getPubIdentificador() {
        return pubIdentificador;
    }

    public void setPubIdentificador(Integer pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public String getPubHash() {
        return pubHash;
    }

    public void setPubHash(String pubHash) {
        this.pubHash = pubHash;
    }

    public Integer getPubCreditos() {
        return pubCreditos;
    }

    public void setPubCreditos(Integer pubCreditos) {
        this.pubCreditos = pubCreditos;
    }

    public Date getPubFechaVisado() {
        return pubFechaVisado;
    }

    public void setPubFechaVisado(Date pubFechaVisado) {
        this.pubFechaVisado = pubFechaVisado;
    }

    public Date getPubFechaRegistro() {
        return pubFechaRegistro;
    }

    public void setPubFechaRegistro(Date pubFechaRegistro) {
        this.pubFechaRegistro = pubFechaRegistro;
    }

    public String getPubEstado() {
        return pubEstado;
    }

    public void setPubEstado(String pubEstado) {
        this.pubEstado = pubEstado;
    }

    public String getPubNombreAutor() {
        return pubNombreAutor;
    }

    public void setPubNombreAutor(String pubNombreAutor) {
        this.pubNombreAutor = pubNombreAutor;
    }

    public String getPubAutoresSecundarios() {
        return pubAutoresSecundarios;
    }

    public void setPubAutoresSecundarios(String pubAutoresSecundarios) {
        this.pubAutoresSecundarios = pubAutoresSecundarios;
    }

    public String getPubTipoPublicacion() {
        return pubTipoPublicacion;
    }

    public void setPubTipoPublicacion(String pubTipoPublicacion) {
        this.pubTipoPublicacion = pubTipoPublicacion;
    }

    public Date getPubFechaPublicacion() {
        return pubFechaPublicacion;
    }

    public void setPubFechaPublicacion(Date pubFechaPublicacion) {
        this.pubFechaPublicacion = pubFechaPublicacion;
    }

    public String getPubDoi() {
        return pubDoi;
    }

    public void setPubDoi(String pubDoi) {
        this.pubDoi = pubDoi;
    }

    public String getPubIsbn() {
        return pubIsbn;
    }

    public void setPubIsbn(String pubIsbn) {
        this.pubIsbn = pubIsbn;
    }

    @XmlTransient
    public Collection<Archivo> getArchivoCollection() {
        return archivoCollection;
    }

    public void setArchivoCollection(Collection<Archivo> archivoCollection) {
        this.archivoCollection = archivoCollection;
    }

    public Congreso getCongreso() {
        return congreso;
    }

    public void setCongreso(Congreso congreso) {
        this.congreso = congreso;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public Estudiante getPubEstIdentificador() {
        return pubEstIdentificador;
    }

    public void setPubEstIdentificador(Estudiante pubEstIdentificador) {
        this.pubEstIdentificador = pubEstIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pubIdentificador != null ? pubIdentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.pubIdentificador == null && other.pubIdentificador != null) || (this.pubIdentificador != null && !this.pubIdentificador.equals(other.pubIdentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.Publicacion[ pubIdentificador=" + pubIdentificador + " ]";
    }

    public void agregarMetadatos(UploadedFile ArticuloPDF , UploadedFile TablaContenidoPDF) throws IOException, GeneralSecurityException, DocumentException, PathNotFoundException, AccessDeniedException {
  
        /*Nombre de los archivos que se almacenaran en el repositorio*/
        MetodosPDF mpdf = new MetodosPDF();
        String codigoEst = this.pubEstIdentificador.getEstCodigo();
        String codigoFirma = mpdf.codigoFirma(codigoEst);
        codigoFirma = codigoFirma.trim();


        String nombrePublicacion;
        String nombreTablaC = "Tabla de Contenido-" + codigoFirma;

        if (this.pubTipoPublicacion.equalsIgnoreCase("revista")) {
            nombrePublicacion = this.revista.getRevNombreArticulo();

        } /* Si no es una revista, entonces corresponde al nombre
             de la ponencia de un Congreso*/ else {
            nombrePublicacion = this.congreso.getCongNombrePonencia();

        }

        /*Obtiene la ruta de la ubicacion del servidor donde se almacenaran 
          temporalmente los archivos ,para luego subirlos al Gestor Documental OpenKm  */
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String destArticulo = realPath + "WEB-INF\\temp\\" + nombrePublicacion + ".pdf";
        String destTablaC = realPath + "WEB-INF\\temp\\Tabla de Contenido.pdf";

        
        /*  Estampa de Tiempo
            Obtiene el dia y hora actual del servidor para posteriormente adicionrarlo
            como Metadato en el documento PDF/A y el Gestor Documental*/
        Date date = new Date();
        DateFormat datehourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String estampaTiempo = "" + datehourFormat.format(date);

        this.setPubFechaRegistro(date);

        /*  Metodo para almacenar los metadatos del Articulo y Tabla de Contenido 
            para almacenarlo en formato PDFA */
        ArrayList<String> rutasArchivos = new ArrayList<>();
        rutasArchivos.add(destArticulo);
        rutasArchivos.add(destTablaC);

        ArrayList<String> nombreArchivos = new ArrayList<>();
        nombreArchivos.add(nombrePublicacion);
        nombreArchivos.add(nombreTablaC);

        ArrayList<InputStream> archivosIS = new ArrayList<>();
        archivosIS.add(ArticuloPDF.getInputstream());
        archivosIS.add(TablaContenidoPDF.getInputstream());
        CrearPDFA_Metadata(rutasArchivos, archivosIS, estampaTiempo);
        
                String hash    = mpdf.obtenerHash(destArticulo);
        
        /*  Metodo para almacenar en el Gestor Documental(OPENKM),
            el articulo en formato PDFA y la Tabla de Contenido del Articulo (formato PDFA) */
        SubirOpenKM(rutasArchivos, nombreArchivos, estampaTiempo, codigoFirma,hash);
    }

    public void CrearPDFA_Metadata(ArrayList<String> rutasArchivos, ArrayList<InputStream> ArchivosIS, String estampaTiempo) throws IOException, GeneralSecurityException, DocumentException {

        for (int s = 0; s < rutasArchivos.size(); s++) {

            Document document = new Document();
            PdfReader reader;

            try {
                PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(
                        rutasArchivos.get(s)), PdfAConformanceLevel.PDF_A_1B);
                writer.setTagged();

                SimpleDateFormat formateador = new SimpleDateFormat("MM-yyyy");
                String FechaPublicacion = formateador.format(this.pubFechaPublicacion);

                Archivo arch = (Archivo) archivoCollection.toArray()[s];
               
                document.addHeader("Identificador Publicacion", "" + this.pubIdentificador);
                document.addHeader("Identificador Archivo", "" + arch.getArcIdentificador());
                document.addHeader("Estampa Tiempo", "" + estampaTiempo);
                document.addAuthor("" + this.pubNombreAutor);
                document.addCreator("" + this.pubNombreAutor);
                document.addHeader("Autores_Secundarios", this.pubAutoresSecundarios);
                document.addHeader("Fecha_Publicacion", FechaPublicacion);
                document.addHeader("Tipo_Publicacion", this.pubTipoPublicacion);
                /* Comprubea si los metadatos a llenar son para una revista
                    o para un congreso*/
                if (this.pubTipoPublicacion.equalsIgnoreCase("revista")) {
                    document.addTitle("" + this.revista.getRevNombreArticulo());
                    document.addHeader("Nombre_Revista", this.revista.getRevNombreRevista());
                    document.addHeader("Categoria", this.revista.getRevCategoria());

                } /* Si no es una revista, entonces los Metadatos 
                a llenar corresponden a los de un Congreso*/ else {
                    document.addTitle("" + this.congreso.getCongNombrePonencia());
                    document.addHeader("Nombre_Evento", this.congreso.getCongNombreEvento());
                    document.addHeader("Tipo_Congreso", this.congreso.getCongTipoCongreso());

                }

                document.addHeader("DOI", this.pubDoi);
                document.addHeader("ISBN", this.pubIsbn);
                document.addCreationDate();

                writer.setTagged();
                writer.createXmpMetadata();
                writer.setCompressionLevel(9);
                document.open();
                PdfContentByte cb = writer.getDirectContent();
                reader = new PdfReader(ArchivosIS.get(s));
                PdfImportedPage page;
                int pageCount = reader.getNumberOfPages();
                for (int i = 0; i < pageCount; i++) {
                    document.newPage();
                    page = writer.getImportedPage(reader, i + 1);
                    cb.addTemplate(page, 0, 0);
                }
            } catch (DocumentException | IOException de) {
                System.err.println(de.getMessage());
            }
            document.close();

        }
    }

    public void SubirOpenKM(ArrayList<String> rutasArchivos, ArrayList<String> nombreArchivos, String estampaTiempo, String codigoFirma,String hash) throws IOException {

        //  System.out.println("El hash es: " + mpdf.obtenerHash(rutaArchivo));
        // System.out.println("Codigo: " + mpdf.codigoFirma("104611024139"));
        /* Inicia una instancia del Gestor Documental Openkm*/
        this.setPubHash(hash);
        String host = "http://localhost:8082/OpenKM";
        String username = "okmAdmin";
        String password = "admin";
        OKMWebservices ws = OKMWebservicesFactory.newInstance(host, username, password);
        try {
            boolean crearFolder = false;
            String rutaFolderCrear;
            /* codigoFirma - en este caso corresponde al nombre de la carpeta que contendra
                el articulo y su tabla de contenido en formato PDFA*/

            //    String codigoFirma = mpdf.codigoFirma("104611024139");

            /* Ruta del folder a crear en el Gestor Openkm*/
            rutaFolderCrear = "/okm:root/" + codigoFirma;
            try {
                /* Se valida si el forder a crear existe o no*/
                ws.isValidFolder(rutaFolderCrear);
                crearFolder = false;
            } catch (Exception e) {
                crearFolder = true;
            }
            if (crearFolder == true) {
                /* Si no existe el folder, se crea con la ruta(rutaFolderCrear)*/
                Folder fld = new Folder();
                fld.setPath(rutaFolderCrear);
                ws.createFolder(fld);
            }
            for (int i = 0; i < rutasArchivos.size(); i++) {

                Archivo arch = (Archivo) archivoCollection.toArray()[i];

                if (this.pubTipoPublicacion.equalsIgnoreCase("revista")) {

                    InputStream is = new FileInputStream("" + rutasArchivos.get(i));
                    com.openkm.sdk4j.bean.Document doc = new com.openkm.sdk4j.bean.Document();
                    doc.setPath(rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf");
                    ws.createDocument(doc, is);
                    IOUtils.closeQuietly(is);
                    List<FormElement> fElements = ws.getPropertyGroupProperties("" + rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf", "okg:revista");
                    for (FormElement fElement : fElements) {
                        if (fElement.getName().equals("okp:revista.identPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubIdentificador);
                        }
                        if (fElement.getName().equals("okp:revista.identArchivo")) {
                            Input name = (Input) fElement;
                            name.setValue("" + arch.getArcIdentificador());
                        }
                        if (fElement.getName().equals("okp:revista.estampaTiempo")) {
                            Input name = (Input) fElement;
                            name.setValue("" + estampaTiempo);
                        }
                        if (fElement.getName().equals("okp:revista.nombreAutor")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubNombreAutor);
                        }
                        if (fElement.getName().equals("okp:revista.autoresSecundarios")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubAutoresSecundarios);
                        }

                        SimpleDateFormat formateador = new SimpleDateFormat("MM-yyyy");
                        String FechaPublicacion = formateador.format(this.pubFechaPublicacion);

                        if (fElement.getName().equals("okp:revista.fechaPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + FechaPublicacion);
                        }
                        if (fElement.getName().equals("okp:revista.tipoPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubTipoPublicacion);
                        }
                        if (fElement.getName().equals("okp:revista.nombreArticulo")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.revista.getRevNombreArticulo());
                        }
                        if (fElement.getName().equals("okp:revista.nombreRevista")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.revista.getRevNombreRevista());
                        }
                        if (fElement.getName().equals("okp:revista.categoria")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.revista.getRevCategoria());
                        }
                        if (fElement.getName().equals("okp:revista.DOI")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubDoi);
                        }
                        if (fElement.getName().equals("okp:revista.ISBN")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubIsbn);
                        }
                    }
                    ws.setPropertyGroupProperties("" + rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf", "okg:revista", fElements);

                } /* Si no es una revista, entonces los Metadatos del OpenKm 
                a llenar corresponden a los de un Congreso*/ else {

                    InputStream is = new FileInputStream("" + rutasArchivos.get(i));
                    com.openkm.sdk4j.bean.Document doc = new com.openkm.sdk4j.bean.Document();

                    doc.setPath(rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf");
                    ws.createDocument(doc, is);
                    IOUtils.closeQuietly(is);
                    List<FormElement> fElements = ws.getPropertyGroupProperties("" + rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf", "okg:publicacion");

                    for (FormElement fElement : fElements) {
                        if (fElement.getName().equals("okp:congreso.identPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubIdentificador);
                        }
                        if (fElement.getName().equals("okp:congreso.identArchivo")) {
                            Input name = (Input) fElement;
                            name.setValue("" + arch.getArcIdentificador());
                        }
                        if (fElement.getName().equals("okp:congreso.estampaTiempo")) {
                            Input name = (Input) fElement;
                            name.setValue("" + estampaTiempo);
                        }
                        if (fElement.getName().equals("okp:congreso.nombreAutor")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubNombreAutor);
                        }
                        if (fElement.getName().equals("okp:congreso.autoresSecundarios")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubAutoresSecundarios);
                        }

                        SimpleDateFormat formateador = new SimpleDateFormat("MM-yyyy");
                        String FechaPublicacion = formateador.format(this.pubFechaPublicacion);

                        if (fElement.getName().equals("okp:congreso.fechaPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + FechaPublicacion);
                        }
                        if (fElement.getName().equals("okp:congreso.tipoPublicacion")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubTipoPublicacion);
                        }
                        if (fElement.getName().equals("okp:congreso.nombrePonencia")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.congreso.getCongNombrePonencia());
                        }
                        if (fElement.getName().equals("okp:congreso.nombreEvento")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.congreso.getCongNombreEvento());
                        }
                        if (fElement.getName().equals("okp:congreso.tipoCongreso")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.congreso.getCongTipoCongreso());
                        }
                        if (fElement.getName().equals("okp:congreso.DOI")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubDoi);
                        }
                        if (fElement.getName().equals("okp:congreso.ISBN")) {
                            Input name = (Input) fElement;
                            name.setValue("" + this.pubIsbn);
                        }
                    }
                    ws.setPropertyGroupProperties("" + rutaFolderCrear + "/" + nombreArchivos.get(i) + ".pdf", "okg:congreso", fElements);

                }

                File fichero = new File(rutasArchivos.get(i));
                fichero.delete();

            }

        } catch (AccessDeniedException | AutomationException | DatabaseException | ExtensionException | FileSizeExceededException | ItemExistsException | LockException | NoSuchGroupException | NoSuchPropertyException | ParseException | PathNotFoundException | RepositoryException | UnknowException | UnsupportedMimeTypeException | UserQuotaExceededException | VirusDetectedException | WebserviceException | IOException e) {
            e.printStackTrace();
        }

    }

}
