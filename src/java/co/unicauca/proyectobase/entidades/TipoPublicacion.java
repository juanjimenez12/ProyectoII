/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "tipo_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPublicacion.findAll", query = "SELECT t FROM TipoPublicacion t"),
    @NamedQuery(name = "TipoPublicacion.findByTpubIdentificador", query = "SELECT t FROM TipoPublicacion t WHERE t.tpubIdentificador = :tpubIdentificador"),
    @NamedQuery(name = "TipoPublicacion.findByTpubDescripcion", query = "SELECT t FROM TipoPublicacion t WHERE t.tpubDescripcion = :tpubDescripcion")})
public class TipoPublicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tpub_identificador")
    private Integer tpubIdentificador;
    @Size(max = 30)
    @Column(name = "tpub_descripcion")
    private String tpubDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pubTpubIdentificador")
    private List<Publicacion> publicacionList;

    public TipoPublicacion() {
    }

    public TipoPublicacion(Integer tpubIdentificador) {
        this.tpubIdentificador = tpubIdentificador;
    }

    public Integer getTpubIdentificador() {
        return tpubIdentificador;
    }

    public void setTpubIdentificador(Integer tpubIdentificador) {
        this.tpubIdentificador = tpubIdentificador;
    }

    public String getTpubDescripcion() {
        return tpubDescripcion;
    }

    public void setTpubDescripcion(String tpubDescripcion) {
        this.tpubDescripcion = tpubDescripcion;
    }

    @XmlTransient
    public List<Publicacion> getPublicacionList() {
        return publicacionList;
    }

    public void setPublicacionList(List<Publicacion> publicacionList) {
        this.publicacionList = publicacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpubIdentificador != null ? tpubIdentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPublicacion)) {
            return false;
        }
        TipoPublicacion other = (TipoPublicacion) object;
        if ((this.tpubIdentificador == null && other.tpubIdentificador != null) || (this.tpubIdentificador != null && !this.tpubIdentificador.equals(other.tpubIdentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.TipoPublicacion[ tpubIdentificador=" + tpubIdentificador + " ]";
    }
    
}
