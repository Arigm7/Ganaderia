
package servicios;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(servicios.CatalogoConceptoWS.class);
        resources.add(servicios.CriaWS.class);
        resources.add(servicios.EgresoWS.class);
        resources.add(servicios.HatoWS.class);
        resources.add(servicios.IngresoWS.class);
        resources.add(servicios.LoteWS.class);
        resources.add(servicios.RanchoWS.class);
        resources.add(servicios.RazaWS.class);
        resources.add(servicios.RolWS.class);
        resources.add(servicios.SesionWS.class);
        resources.add(servicios.TraspasoWS.class);
        resources.add(servicios.UsuarioWS.class);
        resources.add(servicios.VeterinarioWS.class);
       

    }
    
}
