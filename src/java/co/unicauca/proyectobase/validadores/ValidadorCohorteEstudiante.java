package co.unicauca.proyectobase.validadores;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="validadorCohorteEstudiante")
public class ValidadorCohorteEstudiante implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        Date fecha = new Date();
        int anio = fecha.getYear() + 1900;
        int cohorte;
        
        if((texto == null) || (texto.length() == 0) || (texto.equals("null")))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El cohorte del estudiante es obligatorio.");
            throw new ValidatorException(msg);  
        }
        
        cohorte = Integer.parseInt(texto);       
        if((cohorte > anio) || (cohorte <= 2000))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El cohorte debe ser un año mayor al 2000 y menor o igual al año actual.");
            throw new ValidatorException(msg);  
        }
    }
}