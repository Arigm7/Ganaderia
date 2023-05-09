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
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Egreso;
import modelo.pojos.Ingreso;
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

    @POST
    @Path("registrarEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarEgreso(
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaCreacion") String fechaCreacion,
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("idRancho") Integer idRancho) {

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
            @FormParam("idRancho") Integer idRancho) {

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
}
