
package servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Ingreso;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;




@Path("ingreso")
public class IngresoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of IngresoWS
     */
    public IngresoWS() {
    }

    @GET
    @Path("getAllIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingreso>getAllIngreso(){
        List<Ingreso> list = new ArrayList<Ingreso>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Ingreso.getAllIngreso");
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
    @Path("getAllIngresoHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingreso> getAllIngresoHistorial() {
        List<Ingreso> list = new ArrayList<Ingreso>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list=conn.selectList("Ingreso.getAllIngreso_historial");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    @GET
    @Path("getHistorialById/{rancho}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingreso> gethistoriaById(@PathParam("rancho") String rancho){
        SqlSession conn = MyBatisUtil.getSession();
        try{
            return conn.selectList("Ingreso.getIngresoById", rancho);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("registrarIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarIngreso(
            @FormParam("cantidad") Integer cantidad,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaCreacion") String fechaCreacion,  
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("idRancho") Integer idRancho,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();    
        String currentTime = now.toString();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("cantidad", cantidad);
            param.put("observaciones", observaciones);
            param.put("fechaCreacion", currentTime);
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            param.put("idRancho", idRancho);
            param.put("idUsuario", idUsuario);
            
            conn.insert("Ingreso.registrarIngreso",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Ingreso registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el ingreso");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarIngreso(
            @FormParam("idIngreso") Integer idIngreso,
            @FormParam("cantidad") Integer cantidad,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaModificacion") String fechaModificacion,  
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("idRancho") Integer idRancho,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
                //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();    
        String currentTime = now.toString();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idIngreso", idIngreso);
            param.put("cantidad", cantidad);
            param.put("observaciones", observaciones);
            param.put("fechaModificacion", currentTime);
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            param.put("idRancho", idRancho);
            param.put("idUsuario", idUsuario);
            
            conn.update("Ingreso.actualizarIngreso",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Ingreso actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el ingreso");
        }finally{
            conn.close();
        }
        return res;
    }
    
 
    @GET
    @Path("getIngresoById/{rancho}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingreso> getIngresoById(@PathParam("rancho") String rancho){
        SqlSession conn = MyBatisUtil.getSession();
        try{
            return conn.selectList("Ingreso.getIngresoById", rancho);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }

    @POST
    @Path("eliminarIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarIngreso(
            @FormParam("idIngreso") Integer idIngreso) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idIngreso", idIngreso);

            conn.update("Ingreso.eliminarIngreso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Ingreso eliminada correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el ingreso");
        } finally {
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusIngreso(
            @FormParam("idIngreso") Integer idIngreso){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idIngreso", idIngreso);
            
            conn.update("Ingreso.actualizarEstatus",param);
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
