/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author Sahydo
 */
@Entity
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findByPubIdentificador", query = "SELECT p FROM Publicacion p WHERE p.pubIdentificador = :pubIdentificador"),
    @NamedQuery(name = "Publicacion.findByPubHash", query = "SELECT p FROM Publicacion p WHERE p.pubHash = :pubHash"),
    @NamedQuery(name = "Publicacion.findByPubCreditos", query = "SELECT p FROM Publicacion p WHERE p.pubCreditos = :pubCreditos"),
    @NamedQuery(name = "Publicacion.findByPubFechaVisado", query = "SELECT p FROM Publicacion p WHERE p.pubFechaVisado = :pubFechaVisado"),
    @NamedQuery(name = "Publicacion.findByPubFechaRegistro", query = "SELECT p FROM Publicacion p WHERE p.pubFechaRegistro = :pubFechaRegistro"),
    @NamedQuery(name = "Publicacion.findByPubEstado", query = "SELECT p FROM Publicacion p WHERE p.pubEstado = :pubEstado"),
    @NamedQuery(name = "Publicacion.findByPubNombreAutor", query = "SELECT p FROM Publicacion p WHERE p.pubNombreAutor = :pubNombreAutor"),
    @NamedQuery(name = "Publicacion.findByPubAutoresSecundarios", query = "SELECT p FROM Publicacion p WHERE p.pubAutoresSecundarios = :pubAutoresSecundarios"),
    @NamedQuery(name = "Publicacion.findByPubTipoPublicacion", query = "SELECT p FROM Publicacion p WHERE p.pubTipoPublicacion = :pubTipoPublicacion"),
    @NamedQuery(name = "Publicacion.findByPubFechaPublicacion", query = "SELECT p FROM Publicacion p WHERE p.pubFechaPublicacion = :pubFechaPublicacion"),
    @NamedQuery(name = "Publicacion.findByPubDoi", query = "SELECT p FROM Publicacion p WHERE p.pubDoi = :pubDoi"),
    @NamedQuery(name = "Publicacion.findByPubIsbn", query = "SELECT p FROM Publicacion p WHERE p.pubIsbn = :pubIsbn")})
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
    private List<Archivo> archivoList;
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
    public List<Archivo> getArchivoList() {
        return archivoList;
    }

    public void setArchivoList(List<Archivo> archivoList) {
        this.archivoList = archivoList;
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
    
}
