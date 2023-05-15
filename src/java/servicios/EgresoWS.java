/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import modelo.pojos.Egreso;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;

/**
 * REST Web Service
 *
 * @author Alex
 */
@Path("egreso")
public class EgresoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EgresoWS
     */
    public EgresoWS() {
    }

    @GET
    @Path("getAllEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Egreso> getAllEgreso() {
        List<Egreso> list = new ArrayList<Egreso>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Egreso.getAllEgreso");
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
    @Path("getAllEgresoHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Egreso> getAllEgresoHistorial() {
        List<Egreso> list = new ArrayList<Egreso>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Egreso.getAllEgreso_historial");
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
    public List<Egreso> gethistoriaById(@PathParam("rancho") String rancho){
        SqlSession conn = MyBatisUtil.getSession();
        try{
            return conn.selectList("Egreso.getHistorialById", rancho);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }

    @POST
    @Path("registrarEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarEgreso(
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaCreacion") String fechaCreacion,
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("idRancho") Integer idRancho,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("fechaCreacion", currentTime);
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            param.put("idRancho", idRancho);
            param.put("idUsuario", idUsuario);

            conn.insert("Egreso.registrarEgreso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Egreso registrado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el egreso");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("actualizarEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEgreso(
            @FormParam("idEgreso") Integer idEgreso,
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaModificacion") String fechaModificacion,
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("idRancho") Integer idRancho,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idEgreso", idEgreso);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("fechaModificacion", currentTime);
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            param.put("idRancho", idRancho);
            param.put("idUsuario", idUsuario);

            conn.update("Egreso.actualizarEgreso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Egreso actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el egreso");
        } finally {
            conn.close();
        }
        return res;
    }
    
    @GET
    @Path("getEgresoById/{rancho}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Egreso> getEgresoById(@PathParam("rancho") String rancho){
        SqlSession conn = MyBatisUtil.getSession();
        try{
            return conn.selectList("Egreso.getEgresoById", rancho);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("eliminarEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarEgreso(
            @FormParam("idEgreso") Integer idEgreso){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idEgreso", idEgreso);
            
            conn.update("Egreso.eliminarEgreso",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Egreso eliminada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el egreso");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusEgreso(
            @FormParam("idEgreso") Integer idEgreso){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idEgreso", idEgreso);
            
            conn.update("Egreso.actualizarEstatus",param);
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
