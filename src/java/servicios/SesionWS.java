
package servicios;

import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import utils.JavaUtils;


@Path("sesion")
public class SesionWS {

    @Context
    private UriInfo context;
    
    public SesionWS() {
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(@FormParam("usuario") String usuario, @FormParam("password") String password){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,String> param = new HashMap<String,String>(); 
            param.put("usuario", usuario);
            param.put("password", JavaUtils.hashString(password.toUpperCase()));
            Usuario u = conn.selectOne("Sesion.login", param);
            if(u == null || u.getIdUsuario() == null){
                res.setError(true);
                res.setMensaje("No se encontro ningun usuario con esas credenciales");
            }else{
                JSONObject jo = new JSONObject(u);
                res.setRespuesta(jo);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            conn.close();
        }
        
        return res;
    }
}
