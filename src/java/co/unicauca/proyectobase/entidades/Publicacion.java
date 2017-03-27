/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findByPubIdentificador", query = "SELECT p FROM Publicacion p WHERE p.pubIdentificador = :pubIdentificador"),
    @NamedQuery(name = "Publicacion.findByPubTitulo", query = "SELECT p FROM Publicacion p WHERE p.pubTitulo = :pubTitulo"),
    @NamedQuery(name = "Publicacion.findByPubTituloRevista", query = "SELECT p FROM Publicacion p WHERE p.pubTituloRevista = :pubTituloRevista"),
    @NamedQuery(name = "Publicacion.findByPubCategoria", query = "SELECT p FROM Publicacion p WHERE p.pubCategoria = :pubCategoria"),
    @NamedQuery(name = "Publicacion.findByPubPdfPortadaRevista", query = "SELECT p FROM Publicacion p WHERE p.pubPdfPortadaRevista = :pubPdfPortadaRevista"),
    @NamedQuery(name = "Publicacion.findByPubPdfIndiceRevista", query = "SELECT p FROM Publicacion p WHERE p.pubPdfIndiceRevista = :pubPdfIndiceRevista"),
    @NamedQuery(name = "Publicacion.findByPubPdfArticulo", query = "SELECT p FROM Publicacion p WHERE p.pubPdfArticulo = :pubPdfArticulo"),
    @NamedQuery(name = "Publicacion.findByPubCreditos", query = "SELECT p FROM Publicacion p WHERE p.pubCreditos = :pubCreditos"),
    @NamedQuery(name = "Publicacion.findByPubFechaVisado", query = "SELECT p FROM Publicacion p WHERE p.pubFechaVisado = :pubFechaVisado"),
    @NamedQuery(name = "Publicacion.findByPubEstado", query = "SELECT p FROM Publicacion p WHERE p.pubEstado = :pubEstado")})
public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pub_identificador")
    private String pubIdentificador;
    @Size(max = 60)
    @Column(name = "pub_titulo")
    private String pubTitulo;
    @Size(max = 60)
    @Column(name = "pub_titulo_revista")
    private String pubTituloRevista;
    @Size(max = 5)
    @Column(name = "pub_categoria")
    private String pubCategoria;
    @Size(max = 45)
    @Column(name = "pub_pdf_portada_revista")
    private String pubPdfPortadaRevista;
    @Size(max = 45)
    @Column(name = "pub_pdf_indice_revista")
    private String pubPdfIndiceRevista;
    @Size(max = 45)
    @Column(name = "pub_pdf_articulo")
    private String pubPdfArticulo;
    @Column(name = "pub_creditos")
    private Integer pubCreditos;
    @Size(max = 20)
    @Column(name = "pub_fecha_visado")
    private String pubFechaVisado;
    @Size(max = 15)
    @Column(name = "pub_estado")
    private String pubEstado;
    @JoinColumn(name = "pub_est_identificador", referencedColumnName = "est_identificador")
    @ManyToOne(optional = false)
    private Estudiante pubEstIdentificador;

    public Publicacion() {
    }

    public Publicacion(String pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public String getPubIdentificador() {
        return pubIdentificador;
    }

    public void setPubIdentificador(String pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public String getPubTitulo() {
        return pubTitulo;
    }

    public void setPubTitulo(String pubTitulo) {
        this.pubTitulo = pubTitulo;
    }

    public String getPubTituloRevista() {
        return pubTituloRevista;
    }

    public void setPubTituloRevista(String pubTituloRevista) {
        this.pubTituloRevista = pubTituloRevista;
    }

    public String getPubCategoria() {
        return pubCategoria;
    }

    public void setPubCategoria(String pubCategoria) {
        this.pubCategoria = pubCategoria;
    }

    public String getPubPdfPortadaRevista() {
        return pubPdfPortadaRevista;
    }

    public void setPubPdfPortadaRevista(String pubPdfPortadaRevista) {
        this.pubPdfPortadaRevista = pubPdfPortadaRevista;
    }

    public String getPubPdfIndiceRevista() {
        return pubPdfIndiceRevista;
    }

    public void setPubPdfIndiceRevista(String pubPdfIndiceRevista) {
        this.pubPdfIndiceRevista = pubPdfIndiceRevista;
    }

    public String getPubPdfArticulo() {
        return pubPdfArticulo;
    }

    public void setPubPdfArticulo(String pubPdfArticulo) {
        this.pubPdfArticulo = pubPdfArticulo;
    }

    public Integer getPubCreditos() {
        return pubCreditos;
    }

    public void setPubCreditos(Integer pubCreditos) {
        this.pubCreditos = pubCreditos;
    }

    public String getPubFechaVisado() {
        return pubFechaVisado;
    }

    public void setPubFechaVisado(String pubFechaVisado) {
        this.pubFechaVisado = pubFechaVisado;
    }

    public String getPubEstado() {
        return pubEstado;
    }

    public void setPubEstado(String pubEstado) {
        this.pubEstado = pubEstado;
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
