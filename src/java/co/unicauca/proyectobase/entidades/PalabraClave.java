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
@Table(name = "palabra_clave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PalabraClave.findAll", query = "SELECT p FROM PalabraClave p"),
    @NamedQuery(name = "PalabraClave.findByPclaIndentificador", query = "SELECT p FROM PalabraClave p WHERE p.pclaIndentificador = :pclaIndentificador"),
    @NamedQuery(name = "PalabraClave.findByPclaPalabra", query = "SELECT p FROM PalabraClave p WHERE p.pclaPalabra = :pclaPalabra")})
public class PalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pcla_indentificador")
    private Integer pclaIndentificador;
    @Size(max = 25)
    @Column(name = "pcla_palabra")
    private String pclaPalabra;
    @JoinColumn(name = "pcla_pub_identificador", referencedColumnName = "pub_identificador")
    @ManyToOne(optional = false)
    private Publicacion pclaPubIdentificador;

    public PalabraClave() {
    }

    public PalabraClave(Integer pclaIndentificador) {
        this.pclaIndentificador = pclaIndentificador;
    }

    public Integer getPclaIndentificador() {
        return pclaIndentificador;
    }

    public void setPclaIndentificador(Integer pclaIndentificador) {
        this.pclaIndentificador = pclaIndentificador;
    }

    public String getPclaPalabra() {
        return pclaPalabra;
    }

    public void setPclaPalabra(String pclaPalabra) {
        this.pclaPalabra = pclaPalabra;
    }

    public Publicacion getPclaPubIdentificador() {
        return pclaPubIdentificador;
    }

    public void setPclaPubIdentificador(Publicacion pclaPubIdentificador) {
        this.pclaPubIdentificador = pclaPubIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pclaIndentificador != null ? pclaIndentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PalabraClave)) {
            return false;
        }
        PalabraClave other = (PalabraClave) object;
        if ((this.pclaIndentificador == null && other.pclaIndentificador != null) || (this.pclaIndentificador != null && !this.pclaIndentificador.equals(other.pclaIndentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.PalabraClave[ pclaIndentificador=" + pclaIndentificador + " ]";
    }
    
}
