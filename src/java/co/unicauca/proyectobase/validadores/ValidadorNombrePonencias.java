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
@FacesValidator(value="validadorNombrePonencia")
public class ValidadorNombrePonencias implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nombre = String.valueOf(value);
        
        if(nombre.length() == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Se requiere registrar un nombre de la ponencia");
            throw new ValidatorException(msg);
        }        
        
        if(nombre.length() > 80) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El nombre de la ponencia es deasiado largo");
            throw new ValidatorException(msg);
        } 
        
        if(!validadorNombrePonencia(nombre)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El nombre de la ponencia es incorrecto");
            throw new ValidatorException(msg);
        } 

    }
    
    //valida que el nombre de la revista no contenga caracteres especiales
    public boolean validadorNombrePonencia(String nomRevista) {
        Pattern p = Pattern.compile("^[a-zA-ZñÑáÁéÉíÍóÓúÚ\\s0-9]");
        Matcher m = p.matcher(nomRevista);
        return m.find();
    }
    
    
}
