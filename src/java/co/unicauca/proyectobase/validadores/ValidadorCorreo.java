package co.unicauca.proyectobase.validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="validadorCorreo")
public class ValidadorCorreo implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length() == 0)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El correo del estudiante es obligatorio.");
            throw new ValidatorException(msg);  
        }
        
        boolean cumplePatron = Pattern.matches("([_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))", texto);
        if(!cumplePatron)
        {            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato del correo electronico invalido.");
            throw new ValidatorException(msg);  
        }        
    }
}