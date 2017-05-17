package co.unicauca.proyectobase.validadores;
import co.unicauca.proyectobase.dao.EstudianteFacade;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


//@FacesValidator(value="validadorCodigoEstudiante")
//public class ValidadorCodigoEstudiante implements Validator
//{
//    @EJB
//    private EstudianteFacade dao;
//    
//    @Override
//    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
//    {
//        String texto = String.valueOf(value);
//        
//        if(texto.length() == 0)
//        {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código del estudiante es obligatorio.");
//            throw new ValidatorException(msg);  
//        }
//        
//        if((texto.length() < 7) || (texto.length() > 20))
//        {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código debe contener de 7 a 20 caracteres.");
//            throw new ValidatorException(msg);  
//        }
//        
//        boolean cumplePatron = Pattern.matches(".*[a-zA-Z0-9_]", texto);
//        if(!cumplePatron)
//        {            
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código contiene caracteres no válidos.");
//            throw new ValidatorException(msg);  
//        }    
//        
//        boolean existe = dao.findByEstCodigo(texto);
//        if(existe)
//        {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ya existe un estudiante con este código.");
//            throw new ValidatorException(msg);  
//        }
//    }
//}

@FacesValidator(value="validadorCredito")
public class ValidadorCredito implements Validator {
    @EJB
 
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String credito = String.valueOf(value);
        
        if(!validarCredito(credito)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El valor del numero de creditos debe ser numérico.");
            throw new ValidatorException(msg); 
        }
        
        if(credito.length() == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El valor del numero de creditos no puede estar vacio");
            throw new ValidatorException(msg);  
        }
        int auxCredito = Integer.parseInt(credito);
        if((auxCredito > 9) || (auxCredito < 1)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "EL numero de creditos debe ser mayor a 1 y menor a 10");
            throw new ValidatorException(msg);  
        }
 
    }
    
   //valida que el valor ingresado para el numero de creditos  sea numerico
    public boolean validarCredito(String numCreditos) {
        Pattern p = Pattern.compile("^[1-9]*$");
        Matcher m = p.matcher(numCreditos);
        return m.find();
    }
  
    
 
}