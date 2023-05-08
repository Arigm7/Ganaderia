
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
    
    @POST
    @Path("registrarIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarIngreso(
            @FormParam("cantidad") Integer cantidad,
            @FormParam("observaciones") String observaciones,
            @FormParam("fechaCreacion") String fechaCreacion,  
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto){
        
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
            @FormParam("idCatalogoConcepto") Integer idCatalogoConcepto){
        
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
}
