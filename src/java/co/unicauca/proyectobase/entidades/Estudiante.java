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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByEstIdentificador", query = "SELECT e FROM Estudiante e WHERE e.estIdentificador = :estIdentificador"),
    @NamedQuery(name = "Estudiante.findByEstCodigo", query = "SELECT e FROM Estudiante e WHERE e.estCodigo = :estCodigo"),
    @NamedQuery(name = "Estudiante.findByEstNombre", query = "SELECT e FROM Estudiante e WHERE e.estNombre = :estNombre"),
    @NamedQuery(name = "Estudiante.findByEstApellido", query = "SELECT e FROM Estudiante e WHERE e.estApellido = :estApellido"),
    @NamedQuery(name = "Estudiante.findByEstCorreo", query = "SELECT e FROM Estudiante e WHERE e.estCorreo = :estCorreo"),
    @NamedQuery(name = "Estudiante.findByEstCohorte", query = "SELECT e FROM Estudiante e WHERE e.estCohorte = :estCohorte"),
    @NamedQuery(name = "Estudiante.findByEstTutor", query = "SELECT e FROM Estudiante e WHERE e.estTutor = :estTutor"),
    @NamedQuery(name = "Estudiante.findByEstSemestre", query = "SELECT e FROM Estudiante e WHERE e.estSemestre = :estSemestre"),
    @NamedQuery(name = "Estudiante.findByEstEstado", query = "SELECT e FROM Estudiante e WHERE e.estEstado = :estEstado"),
    @NamedQuery(name = "Estudiante.findByEstUsuario", query = "SELECT e FROM Estudiante e WHERE e.estUsuario = :estUsuario"),
    @NamedQuery(name = "Estudiante.findByEstContrasena", query = "SELECT e FROM Estudiante e WHERE e.estContrasena = :estContrasena")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_identificador")
    private Integer estIdentificador;
    @Size(max = 20)
    @Column(name = "est_codigo")
    private String estCodigo;
    @Size(max = 20)
    @Column(name = "est_nombre")
    private String estNombre;
    @Size(max = 20)
    @Column(name = "est_apellido")
    private String estApellido;
    @Size(max = 30)
    @Column(name = "est_correo")
    private String estCorreo;
    @Column(name = "est_cohorte")
    private Integer estCohorte;
    @Size(max = 45)
    @Column(name = "est_tutor")
    private String estTutor;
    @Column(name = "est_semestre")
    private Integer estSemestre;
    @Size(max = 12)
    @Column(name = "est_estado")
    private String estEstado;
    @Size(max = 20)
    @Column(name = "est_usuario")
    private String estUsuario;
    @Size(max = 40)
    @Column(name = "est_contrasena")
    private String estContrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private List<Doctorado> doctoradoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pubEstIdentificador")
    private List<Publicacion> publicacionList;

    public Estudiante() {
        
    }

    public Estudiante(Integer estIdentificador) {
        this.estIdentificador = estIdentificador;
    }

    public Integer getEstIdentificador() {
        return estIdentificador;
    }

    public void setEstIdentificador(Integer estIdentificador) {
        this.estIdentificador = estIdentificador;
    }

    public String getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(String estCodigo) {
        this.estCodigo = estCodigo;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public String getEstApellido() {
        return estApellido;
    }

    public void setEstApellido(String estApellido) {
        this.estApellido = estApellido;
    }

    public String getEstCorreo() {
        return estCorreo;
    }

    public void setEstCorreo(String estCorreo) {
        this.estCorreo = estCorreo;
    }

    public Integer getEstCohorte() {
        return estCohorte;
    }

    public void setEstCohorte(Integer estCohorte) {
        this.estCohorte = estCohorte;
    }

    public String getEstTutor() {
        return estTutor;
    }

    public void setEstTutor(String estTutor) {
        this.estTutor = estTutor;
    }

    public Integer getEstSemestre() {
        return estSemestre;
    }

    public void setEstSemestre(Integer estSemestre) {
        this.estSemestre = estSemestre;
    }

    public String getEstEstado() {
        return estEstado;
    }

    public void setEstEstado(String estEstado) {
        this.estEstado = estEstado;
    }

    public String getEstUsuario() {
        return estUsuario;
    }

    public void setEstUsuario(String estUsuario) {
        this.estUsuario = estUsuario;
    }

    public String getEstContrasena() {
        return estContrasena;
    }

    public void setEstContrasena(String estContrasena) {
        this.estContrasena = estContrasena;
    }

    @XmlTransient
    public List<Doctorado> getDoctoradoList() {
        return doctoradoList;
    }

    public void setDoctoradoList(List<Doctorado> doctoradoList) {
        this.doctoradoList = doctoradoList;
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
        hash += (estIdentificador != null ? estIdentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.estIdentificador == null && other.estIdentificador != null) || (this.estIdentificador != null && !this.estIdentificador.equals(other.estIdentificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.proyectobase.entidades.Estudiante[ estIdentificador=" + estIdentificador + " ]";
    }
    
}
