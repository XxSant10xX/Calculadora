package utilidades;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
 * Original:
 * 
 * https://riptutorial.com/es/javafx/example/23068/cambio-dinamico-de-idioma-cuando-la-aplicacion-se-esta-ejecutando
 * 
 */

public final class I18N {

    // Locale actual (seleccionada)
	// Un objeto Locale representa una región geográfica, política o cultural específica. 
    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(getDefaultLocale());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    private static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale local) {
        locale.set(local);
        Locale.setDefault(local);
    }    
    
    // Devuelve una lista con los Locales permitidos     
    private static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(Locale.ENGLISH, new Locale("es"), new Locale("ca")));
    }

    // Devuelve el Locale por defecto.
    // Devuelve el valor predeterminado del sistema si está contenido en las configuraciones regionales admitidas, de lo contrario, castellano.
    private static Locale getDefaultLocale() {
        Locale sysDefault = Locale.getDefault();
        return getSupportedLocales().contains(sysDefault) ? sysDefault : new Locale("es");
    }    

    // ResourceBundle
    // Los paquetes de recursos contienen objetos específicos del Locale. 
    // Cuando su programa necesita un recurso específico de la localidad, una Cadena, por ejemplo, 
    // su programa puede cargarlo desde el paquete de recursos apropiado para la localidad del usuario actual. 
    // De esta forma, puede escribir código de programa que sea en gran medida independiente de la configuración regional 
    // del usuario, aislando la mayoría, si no toda, la información específica de la configuración regional en paquetes de recursos.

    // Devuelve la cadena de texto con la clave dada del paquete de recursos para el entorno local actual.
    private static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("strings", getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    // Bind -> enlazar, conectar, ..
    
    // Crea un enlace de cadena a una cadena localizada para la clave del paquete de mensajes dado    
    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }

}