/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.dao;

import co.unicauca.proyectobase.entidades.Publicacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian
 */
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
    
}
