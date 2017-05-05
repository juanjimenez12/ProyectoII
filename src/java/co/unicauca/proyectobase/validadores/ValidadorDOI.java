/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.proyectobase.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Juan
 */
@FacesValidator(value="validadorDOI")
public class ValidadorDOI implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String doi = value.toString();
//        String[] valores = fecha.split(" ");
//        
//        System.out.println("MIRALOOOOOOO: " + fecha);

        if(doi.length() == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Se debe registrar el DOI del artículo");
            throw new ValidatorException(msg);
        }

        if(!validarFormato(doi)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El formato del DOI es incorrecto");
            throw new ValidatorException(msg);
        }
        
    }
    
//    //valida que el año de publicacion este en un periodo de 5 años
//    public boolean validarAnioPublicacion(int anio) {
//        Calendar c = Calendar.getInstance();
//        int anioLimite = c.get(Calendar.YEAR)-5;
//        return anio < anioLimite;
//    }
    
    //valida el formato del DOI
    public boolean validarFormato(String doi) {
        //Pattern p = Pattern.compile("^([0-9]{2}+[.]{1})+[0-9]+[/]{1}+[0-9-.]");
       Pattern p = Pattern.compile("^([0-9]{2}+[.]{1})+[0-9]+[/]{1}+[0-9-.a-zA-Z]"); 
        //Pattern p = Pattern.compile("0-9/-.");
        Matcher m = p.matcher(doi);
        return m.find();
    }
    
}


