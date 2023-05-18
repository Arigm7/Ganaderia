
package servicios;

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
import modelo.pojos.Respuesta;
import modelo.pojos.Veterinario;
import org.apache.ibatis.session.SqlSession;

@Path("veterinario")
public class VeterinarioWS {

    @Context
    private UriInfo context;

    public VeterinarioWS() {
    }
           
    @GET
    @Path("getAllVeterinario")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veterinario> getAllVeterinario() {
        List<Veterinario> list = new ArrayList<Veterinario>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Veterinario.getAllVeterinario");
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
    @Path("getVeterinarioById/{numArete}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veterinario> getVeterinarioById(@PathParam("numArete") String numArete) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Veterinario.getVeterinarioById", numArete);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("registrarVeterinario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarVeterinario(
            @FormParam("nombreVisita") String nombreVisita,
            @FormParam("fechaVisita") String fechaVisita,
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("numArete") String numArete,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombreVisita", nombreVisita);
            param.put("fechaVisita", fechaVisita);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("numArete", numArete);
            param.put("idUsuario", idUsuario);
            
            conn.insert("Veterinario.registrarVeterinario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Visita registrada correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar la visita");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarVeterinario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarVeterinario(
            @FormParam("idVisita") Integer idVisita,
            @FormParam("nombreVisita") String nombreVisita,
            @FormParam("fechaVisita") String fechaVisita,
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("numArete") String numArete,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idVisita", idVisita);
            param.put("nombreVisita", nombreVisita);
            param.put("fechaVisita", fechaVisita);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("numArete", numArete);
            param.put("idUsuario", idUsuario);

            conn.update("Veterinario.actualizarVeterinario", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Visita actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar la visita");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("eliminarVeterinario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarVeterinario(
            @FormParam("idVisita") Integer idVisita) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idVisita", idVisita);

            conn.update("Veterinario.eliminarVeterinario", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Visita eliminada correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar la visita");
        } finally {
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusVeterinario(
            @FormParam("idVisita") Integer idVisita) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idVisita", idVisita);

            conn.update("Veterinario.actualizarEstatus", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Estatus actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el estado");
        } finally {
            conn.close();
        }
        return res;
    }
}
