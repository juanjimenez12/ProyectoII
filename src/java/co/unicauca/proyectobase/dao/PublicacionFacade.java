/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.dao;

import co.unicauca.proyectobase.entidades.Estudiante;
import co.unicauca.proyectobase.entidades.Publicacion;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.List;
 
@Stateless
public class PublicacionFacade extends AbstractFacade<Publicacion> {

    @PersistenceContext(unitName = "ProyectoDoctoradoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacionFacade() {
        super(Publicacion.class);
    }

    public int getnumFilasPubRev() {
        try {
            String queryStr;
            queryStr = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'doctorado' AND TABLE_NAME = 'publicacion'";
            javax.persistence.Query query = getEntityManager().createNativeQuery(queryStr);
            List results = query.getResultList();
            int autoIncrement = ((BigInteger) results.get(0)).intValue();
            return autoIncrement;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            System.out.println(e);
            return -1;
        }
    }
    
        public int getIdArchivo() {
        try {
            String queryStr;
            queryStr = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'doctorado' AND TABLE_NAME = 'archivo'";
            javax.persistence.Query query = getEntityManager().createNativeQuery(queryStr);
            List results = query.getResultList();
            int autoIncrement = ((BigInteger) results.get(0)).intValue();
            return autoIncrement;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            System.out.println(e);
            return -1;
        }
    }

    public Estudiante getEst() {
        int estIden = 1;
        Estudiante est;
        try {
            est = em.find(Estudiante.class, estIden);
            return est;
        } catch (Exception e) {
            return null;
        }
    }
    
        public Estudiante obtenerEstudiante(String nombreUsuario) {

        String comSimple = "\'";
        String queryStr;
        queryStr = " SELECT est_identificador FROM doctorado.estudiante WHERE est_usuario = " + comSimple + nombreUsuario + comSimple;
        javax.persistence.Query query = getEntityManager().createNativeQuery(queryStr);
        List results = query.getResultList();
        int estIden = (int) results.get(0);

        Estudiante est;
        try {
            est = em.find(Estudiante.class, estIden);
            return est;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            System.out.println(e);
            return null;
        }
    }
        
      public List<Publicacion> ListadoPublicacionEst(int estIdentificador) {

        String comSimple = "\'";
        String queryStr;
        queryStr = "SELECT * FROM doctorado.publicacion where pub_est_identificador = " + comSimple + estIdentificador + comSimple;
        javax.persistence.Query query = getEntityManager().createNativeQuery(queryStr);
//        List results = 
      
        try {
            System.out.println("co.unicauca.proyectobase.dao.PublicacionFacade.ListadoPublicacionEst()");
            List<Publicacion> aux = query.getResultList();
            return aux;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            System.out.println(e);
            return null;
        }
    }

}
