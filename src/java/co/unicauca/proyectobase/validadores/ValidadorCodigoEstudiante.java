package co.unicauca.proyectobase.validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="validadorCodigoEstudiante")
public class ValidadorCodigoEstudiante implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length() == 0)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código del estudiante es obligatorio.");
            throw new ValidatorException(msg);  
        }
        
        if((texto.length() < 7) || (texto.length() > 20))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código debe contener de 7 a 20 caracteres.");
            throw new ValidatorException(msg);  
        }
        
        boolean cumplePatron = Pattern.matches(".*[a-zA-Z0-9_]", texto);
        if(!cumplePatron)
        {            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código contiene caracteres no validos.");
            throw new ValidatorException(msg);  
        }        
    }
}