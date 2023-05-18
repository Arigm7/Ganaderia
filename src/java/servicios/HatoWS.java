
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
import modelo.pojos.Hato;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;


@Path("hato")
public class HatoWS {

    @Context
    private UriInfo context;

    public HatoWS() {
    }

    @GET
    @Path("getAllHato")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hato> getAllHato() {
        List<Hato> list = new ArrayList<Hato>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Hato.getAllHato");
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
    @Path("getHatoById/{numArete}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hato> getHatoById(@PathParam("numArete") String numArete) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Hato.getHatoById", numArete);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("registrarHato")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarHato(
            @FormParam("numArete") String numArete,
            @FormParam("sexo") String sexo,
            @FormParam("descripcion") String descripcion,  
            //@FormParam("imagen1") big imagen1,                                FALTA VER LO DE LAS FOTOS
            //@FormParam("imagen2") Integer imagen2,
            @FormParam("tipoGanado") String tipoGanado,
            @FormParam("diaDeAlta") String diaDeAlta,
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("idRaza") Integer idRaza,
            @FormParam("idLote") Integer idLote,
            @FormParam("idRancho") Integer idRancho){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();    
        String currentTime = now.toString();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("numArete", numArete);
            param.put("sexo", sexo);
            param.put("descripcion", descripcion);
            //param.put("imagen1", imagen1);
            //param.put("imagen2", imagen2);
            param.put("tipoGanado", tipoGanado);
            param.put("diaDeAlta", currentTime);
            param.put("idUsuario", idUsuario);
            param.put("idRaza", idRaza);
            param.put("idLote", idLote);
            param.put("idRancho", idRancho);
            
            conn.insert("Hato.registrarHato",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Hato registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el hato");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarHato")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarHato(
            @FormParam("numArete") String numArete,
            @FormParam("sexo") String sexo,
            @FormParam("descripcion") String descripcion,  
            //@FormParam("imagen1") big imagen1,                                FALTA VER LO DE LAS FOTOS
            //@FormParam("imagen2") Integer imagen2,
            @FormParam("tipoGanado") String tipoGanado,
            @FormParam("diaDeModificacion") String diaDeModificacion,
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("idRaza") Integer idRaza,
            @FormParam("idLote") Integer idLote,
            @FormParam("idRancho") Integer idRancho) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("numArete", numArete);
            param.put("sexo", sexo);
            param.put("descripcion", descripcion);
            //param.put("imagen1", imagen1);
            //param.put("imagen2", imagen2);
            param.put("tipoGanado", tipoGanado);
            param.put("diaDeModificacion", currentTime);
            param.put("idUsuario", idUsuario);
            param.put("idRaza", idRaza);
            param.put("idLote", idLote);
            param.put("idRancho", idRancho);

            conn.update("Hato.actualizarHato", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Hato actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el hato");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("eliminarHato")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarHato(
            @FormParam("numArete") String numArete,
            @FormParam("diaDeBaja") String diaDeBaja,
            @FormParam("motivoDeBaja") String motivoDeBaja,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("numArete", numArete);
            param.put("diaDeBaja", currentTime);
            param.put("motivoDeBaja", motivoDeBaja);
            param.put("idUsuario", idUsuario);

            conn.update("Hato.eliminarHato", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Ingreso eliminada correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el hato");
        } finally {
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusHato(
            @FormParam("numArete") String numArete) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("numArete", numArete);

            conn.update("Hato.actualizarEstatus", param);
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
