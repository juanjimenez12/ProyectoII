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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sahydo
 */
@Entity
@Table(name = "revista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revista.findAll", query = "SELECT r FROM Revista r"),
    @NamedQuery(name = "Revista.findByPubIdentificador", query = "SELECT r FROM Revista r WHERE r.pubIdentificador = :pubIdentificador"),
    @NamedQuery(name = "Revista.findByRevNombreRevista", query = "SELECT r FROM Revista r WHERE r.revNombreRevista = :revNombreRevista"),
    @NamedQuery(name = "Revista.findByRevNombreArticulo", query = "SELECT r FROM Revista r WHERE r.revNombreArticulo = :revNombreArticulo"),
    @NamedQuery(name = "Revista.findByRevCategoria", query = "SELECT r FROM Revista r WHERE r.revCategoria = :revCategoria")})
public class Revista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pub_identificador")
    private Integer pubIdentificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "rev_nombre_revista")
    private String revNombreRevista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "rev_nombre_articulo")
    private String revNombreArticulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "rev_categoria")
    private String revCategoria;
    @JoinColumn(name = "pub_identificador", referencedColumnName = "pub_identificador", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Publicacion publicacion;

    public Revista() {
    }

    public Revista(Integer pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public Revista(Integer pubIdentificador, String revNombreRevista, String revNombreArticulo, String revCategoria) {
        this.pubIdentificador = pubIdentificador;
        this.revNombreRevista = revNombreRevista;
        this.revNombreArticulo = revNombreArticulo;
        this.revCategoria = revCategoria;
    }

    public Integer getPubIdentificador() {
        return pubIdentificador;
    }

    public void setPubIdentificador(Integer pubIdentificador) {
        this.pubIdentificador = pubIdentificador;
    }

    public String getRevNombreRevista() {
        return revNombreRevista;
    }

    public void setRevNombreRevista(String revNombreRevista) {
        this.revNombreRevista = revNombreRevista;
    }

    public String getRevNombreArticulo() {
        return revNombreArticulo;
    }

    public void setRevNombreArticulo(String revNombreArticulo) {
        this.revNombreArticulo = revNombreArticulo;
    }

    public String getRevCategoria() {
        return revCategoria;
    }

    public void setRevCategoria(String revCategoria) {
        this.revCategoria = revCategoria;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
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
        if (!(object instanceof Revista)) {
            return false;
        }
        Revista other = (Revista) object;
        if ((this.pubIdentificador == null && other.pubIdentificador != null) || (this.pubIdentificador != null && !this.pubIdentificador.equals(other.pubIdentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.Revista[ pubIdentificador=" + pubIdentificador + " ]";
    }
    
}
