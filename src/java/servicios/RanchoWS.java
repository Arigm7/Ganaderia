
package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Rancho;
import modelo.pojos.Raza;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;


@Path("rancho")
public class RanchoWS {

    @Context
    private UriInfo context;


    public RanchoWS() {
    }

    @GET
    @Path("getAllRancho")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rancho>getAllRancho(){
        List<Rancho> list = new ArrayList<Rancho>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Rancho.getAllRancho");
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
    @Path("getAllRanchoActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rancho> getAllRanchoActivo() {
        List<Rancho> list = new ArrayList<Rancho>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Rancho.getAllRanchoActivo");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    @POST
    @Path("registrarRancho")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarRancho(
            @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia,
            @FormParam("calle") String calle,
            @FormParam("numExt") Integer numExt,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
            param.put("colonia", colonia);
            param.put("calle", calle);
            param.put("numExt", numExt);
            param.put("idUsuario", idUsuario);
            
            conn.insert("Rancho.registrarRancho",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("El rancho fue registrada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el rancho");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarRancho")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarRancho(
            @FormParam("idRancho") Integer idRancho,
            @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia,
            @FormParam("calle") String calle,
            @FormParam("numExt") Integer numExt,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRancho", idRancho);
            param.put("nombre", nombre);
            param.put("colonia", colonia);
            param.put("calle", calle);
            param.put("numExt", numExt);
            param.put("idUsuario", idUsuario);
            
            conn.update("Rancho.actualizarRancho",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("El rancho fue actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el rancho");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("eliminarRancho")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarRancho(
            @FormParam("idRancho") Integer idRancho){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRancho", idRancho);
            
            conn.update("Rancho.eliminarRancho",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Rancho eliminada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el rancho");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusRancho(
            @FormParam("idRancho") Integer idRancho){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRancho", idRancho);
            
            conn.update("Rancho.actualizarEstatus",param);
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
    
    @GET
    @Path("getRanchoById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rancho> getRanchoById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Rancho.getRanchoById", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("ranchoId")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta ranchoById(@FormParam("nombre") String nombre) {
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        long num=0;
        try {
            
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
           
            Map<String, Object> result = conn.selectOne("Rancho.ranchoId", param);
            conn.commit();
            num = (Long) result.get("RESULT");

            res.setError(false);
           
            res.setMensaje(Long.toString(num));
         
        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("Error al consultar");
        } finally {
            conn.close();
        }
        return res;
    }
}
