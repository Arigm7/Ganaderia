
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
import modelo.pojos.Cria;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;


@Path("cria")
public class CriaWS {

    @Context
    private UriInfo context;

    public CriaWS() {
    }

    @GET
    @Path("getAllCria")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cria> getAllCria() {
        List<Cria> list = new ArrayList<Cria>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Cria.getAllCria");
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
    @Path("getCriaById/{numArete}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cria> getCriaById(@PathParam("numArete") String numArete) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Cria.getCriaById", numArete);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("registrarCria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarCria(
            @FormParam("numArete") String numArete,
            @FormParam("sexo") String sexo,
            @FormParam("fechaNac") String fechaNac,             //VER LO DE LA FECHA
            @FormParam("idRaza") Integer idRaza,
            @FormParam("observaciones") String observaciones,
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("numArete", numArete);
            param.put("sexo", sexo);
            param.put("fechaNac", fechaNac);
            param.put("idRaza", idRaza);
            param.put("observaciones", observaciones);
            param.put("idUsuario", idUsuario);
            
            conn.insert("Cria.registrarCria",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Cria registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar la cria");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarCria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarCria(
            @FormParam("idCria") Integer idCria,
            @FormParam("numArete") String numArete,
            @FormParam("sexo") String sexo,
            @FormParam("fechaNac") String fechaNac,             //VER LO DE LA FECHA
            @FormParam("idRaza") Integer idRaza,
            @FormParam("observaciones") String observaciones,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCria", idCria);
            param.put("numArete", numArete);
            param.put("sexo", sexo);
            param.put("fechaNac", fechaNac);
            param.put("idRaza", idRaza);
            param.put("observaciones", observaciones);
            param.put("idUsuario", idUsuario);

            conn.update("Cria.actualizarCria", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Cria actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar la cria");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("eliminarCria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarCria(
            @FormParam("idCria") Integer idCria) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCria", idCria);

            conn.update("Cria.eliminarCria", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Cria eliminada correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar la cria");
        } finally {
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusCria(
            @FormParam("idCria") Integer idCria) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCria", idCria);

            conn.update("Cria.actualizarEstatus", param);
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
