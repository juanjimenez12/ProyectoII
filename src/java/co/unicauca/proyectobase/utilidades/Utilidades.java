package co.unicauca.proyectobase.utilidades;

import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Utilidades {

    public static void redireccionar(String pagina) {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extcontext = context.getExternalContext();
        extcontext.getFlash().setKeepMessages(true);
        try {
            extcontext.redirect(pagina);
        } catch (IOException ex) {
            Logger.getLogger("Error al redireccionar a " + pagina);
        }

    }
}
