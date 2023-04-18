
package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Raza;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import utils.JavaUtils;


@Path("raza")
public class RazaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RazaWS
     */
    public RazaWS() {
    }

    @GET
    @Path("getAllRaza")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Raza>getAllRaza(){
        List<Raza> list = new ArrayList<Raza>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Raza.getAllRaza");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return list;
    }
    
    
    @GET
    @Path("getAllRazaActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Raza>getAllRazaActivo(){
        List<Raza> list = new ArrayList<Raza>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Raza.getAllRazaActivo");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return list;
    }
    
    
    @POST
    @Path("registrarRaza")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarRaza(@FormParam("nombre") String nombre){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
            
            conn.insert("Raza.registrarRaza",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("La raza fue registrada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar la raza");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarRaza")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarRaza(
            @FormParam("idRaza") Integer idRaza,
            @FormParam("nombre") String nombre,
            @FormParam("estatus") String estatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRaza", idRaza);
            param.put("nombre", nombre);
            param.put("estatus", estatus);
            
            conn.update("Raza.actualizarRaza",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("La raza fue actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar la raza");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("eliminarRaza")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarRaza(
            @FormParam("idRaza") Integer idRaza){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRaza", idRaza);
            
            conn.update("Raza.eliminarRaza",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Raza eliminada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar la raza");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusRaza(
            @FormParam("idRaza") Integer idRaza){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRaza", idRaza);
            
            conn.update("Raza.actualizarEstatus",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Estatus actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el estado");
        }finally{
            conn.close();
        }
        return res;
    }
}
