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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a"),
    @NamedQuery(name = "Archivo.findByArcIdentificador", query = "SELECT a FROM Archivo a WHERE a.arcIdentificador = :arcIdentificador")})
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "arc_identificador")
    private Integer arcIdentificador;
    @JoinColumn(name = "arc_pub_identificador", referencedColumnName = "pub_identificador")
    @ManyToOne(optional = false)
    private Publicacion arcPubIdentificador;

    public Archivo() {
    }

    public Archivo(Integer arcIdentificador) {
        this.arcIdentificador = arcIdentificador;
    }

    public Integer getArcIdentificador() {
        return arcIdentificador;
    }

    public void setArcIdentificador(Integer arcIdentificador) {
        this.arcIdentificador = arcIdentificador;
    }

    public Publicacion getArcPubIdentificador() {
        return arcPubIdentificador;
    }

    public void setArcPubIdentificador(Publicacion arcPubIdentificador) {
        this.arcPubIdentificador = arcPubIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arcIdentificador != null ? arcIdentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        if ((this.arcIdentificador == null && other.arcIdentificador != null) || (this.arcIdentificador != null && !this.arcIdentificador.equals(other.arcIdentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.Archivo[ arcIdentificador=" + arcIdentificador + " ]";
    }
    
}
