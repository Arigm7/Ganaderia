
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.CatalogoConcepto;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;


@Path("catalogoConcepto")
public class CatalogoConceptoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CatalogoConceptoWS
     */
    public CatalogoConceptoWS() {
    }

 
    @GET
    @Path("getAllCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogoConcepto>getAllCatalogo(){
        List<CatalogoConcepto> list = new ArrayList<CatalogoConcepto>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Catalogo.getAllCatalogo");
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
    @Path("getAllCatalogoActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogoConcepto>getAllCatalogoActivo(){
        List<CatalogoConcepto> list = new ArrayList<CatalogoConcepto>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Catalogo.getAllCatalogoActivo");
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
    @Path("getAllCatalogoIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogoConcepto>getAllCatalogoIngreso(){
        List<CatalogoConcepto> list = new ArrayList<CatalogoConcepto>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Catalogo.getAllCatalogoIngreso");
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
    @Path("getAllCatalogoEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogoConcepto>getAllCatalogoEgreso(){
        List<CatalogoConcepto> list = new ArrayList<CatalogoConcepto>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Catalogo.getAllCatalogoEgreso");
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
    @Path("registrarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarCatalogo(
            @FormParam("catalogo") String catalogo,
            @FormParam("concepto") String concepto){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("catalogo", catalogo);
            param.put("concepto", concepto);
            
            conn.insert("Catalogo.registrarCatalogo",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Catalogo registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el catalogo");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarCatalogo(
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto,
            @FormParam("catalogo") String catalogo,
            @FormParam("concepto") String concepto){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            param.put("catalogo", catalogo);
            param.put("concepto", concepto);
            
            conn.update("Catalogo.actualizarCatalogo",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Catalogo actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el catalogo");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("eliminarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarCatalogo(
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            
            conn.update("Catalogo.eliminarCatalogo",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Catalogo eliminado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el catalogo");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusCatalogo(
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCatalogoConcepto", idCatalogoConcepto);
            
            conn.update("Catalogo.actualizarEstatus",param);
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
    @Path("getCatalogoById/{concepto}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatalogoConcepto> getCatalogoById(@PathParam("concepto") String concepto) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Catalogo.getCatalogoById", concepto);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
}
