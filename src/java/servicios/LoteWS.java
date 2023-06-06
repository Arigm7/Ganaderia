
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
import modelo.pojos.Lote;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;


@Path("lote")
public class LoteWS {

    @Context
    private UriInfo context;

    public LoteWS() {
    }

    @GET
    @Path("getAllLote")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lote> getAllLote() {
        List<Lote> list = new ArrayList<Lote>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Lote.getAllLote");
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
    @Path("getAllLoteActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lote> getAllLoteActivo() {
        List<Lote> list = new ArrayList<Lote>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Lote.getAllLoteActivo");
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
    @Path("getLoteById/{nombreLote}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lote> getLoteById(@PathParam("nombreLote") String nombreLote) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Lote.getLoteById", nombreLote);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    @POST
    @Path("registrarLote")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarLote(
            @FormParam("nombreLote") String nombreLote,
            @FormParam("numLote") Integer numLote,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombreLote", nombreLote);
            param.put("numLote", numLote);
            param.put("idUsuario", idUsuario);

            conn.insert("Lote.registrarLote", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Lote registrado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el lote");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("actualizarLote")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarLote(
            @FormParam("idLote") Integer idLote,
            @FormParam("nombreLote") String nombreLote,
            @FormParam("numLote") Integer numLote,
            @FormParam("estatus") String estatus,
            @FormParam("idUsuario") Integer idUsuario) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idLote", idLote);
            param.put("nombreLote", nombreLote);
            param.put("numLote", numLote);
            param.put("estatus", estatus);
            param.put("idUsuario", idUsuario);

            conn.update("Lote.actualizarLote", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Lote actualizado correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el lote");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("eliminarLote")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarLote(
            @FormParam("idLote") Integer idLote) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idLote", idLote);

            conn.update("Lote.eliminarLote", param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Lote eliminada correctamente...");

        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el lote");
        } finally {
            conn.close();
        }
        return res;
    }

    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusLote(
            @FormParam("idLote") Integer idLote) {

        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idLote", idLote);

            conn.update("Lote.actualizarEstatus", param);
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
