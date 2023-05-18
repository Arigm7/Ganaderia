
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
import modelo.pojos.Respuesta;
import modelo.pojos.Traspaso;
import modelo.pojos.Veterinario;
import org.apache.ibatis.session.SqlSession;

@Path("traspaso")
public class TraspasoWS {

    @Context
    private UriInfo context;

    public TraspasoWS() {
    }

    @GET
    @Path("getAllTraspaso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Traspaso> getAllTraspaso() {
        List<Traspaso> list = new ArrayList<Traspaso>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Traspaso.getAllTraspaso");
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
    @Path("getTraspasoById/{nombreLote}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Traspaso> getVeterinarioById(@PathParam("nombreLote") String nombreLote) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Traspaso.getTraspasoById", nombreLote);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    @POST
    @Path("registrarTraspaso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarTraspaso(
            @FormParam("descripcion") String descripcion,
            @FormParam("motivo") String motivo,
            @FormParam("fechaCreacion") String fechaCreacion,
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("idLote") Integer idLote) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("descripcion", descripcion);
            param.put("motivo", motivo);
            param.put("fechaCreacion", currentTime);
            param.put("idUsuario", idUsuario);
            param.put("idLote", idLote);

            conn.insert("Traspaso.registrarTraspaso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Traspaso registrado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el traspaso");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("actualizarTraspaso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarTrasoasi(
            @FormParam("idTraspaso") Integer idTraspaso,
            @FormParam("descripcion") String descripcion,
            @FormParam("motivo") String motivo,
            @FormParam("fechaModificacion") String fechaModificacion,
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("idLote") Integer idLote) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        //FECHA ACTUAL
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idTraspaso", idTraspaso);
            param.put("descripcion", descripcion);
            param.put("motivo", motivo);
            param.put("fechaModificacion", currentTime);
            param.put("idUsuario", idUsuario);
            param.put("idLote", idLote);

            conn.update("Traspaso.actualizarTraspaso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Traspaso actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el traspaso");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("eliminarTraspaso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarTraspaso(
            @FormParam("idTraspaso") Integer idTraspaso,
            @FormParam("fechaCancelacion") String fechaCancelacion,
            @FormParam("motivoDeCancelacion") String motivoDeCancelacion,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idTraspaso", idTraspaso);
            param.put("fechaCancelacion", fechaCancelacion);
            param.put("motivoDeCancelacion", motivoDeCancelacion);
            param.put("idUsuario", idUsuario);

            conn.update("Traspaso.eliminarTraspaso", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Traspaso eliminado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el traspaso");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusTraspaso(
            @FormParam("idTraspaso") Integer idTraspaso) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idTraspaso", idTraspaso);

            conn.update("Traspaso.actualizarEstatus", param);
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
