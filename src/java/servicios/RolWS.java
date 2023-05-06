/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import org.apache.ibatis.session.SqlSession;

@Path("rol")
public class RolWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RolWS
     */
    public RolWS() {
    }

    @GET
    @Path("getAllRol")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol>getAllRol(){
        List<Rol> list = new ArrayList<Rol>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Rol.getAllRol");
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
    @Path("registrarRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarRol(@FormParam("nombre") String nombre, @FormParam("estatus") String estatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            
            param.put("nombre", nombre);
            param.put("estatus", estatus);
            
            conn.insert("Rol.registrarRol",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("El rol fue registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el rol");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("actualizarRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarRol(
            @FormParam("idRol") Integer idRol,
            @FormParam("nombre") String nombre,
            @FormParam("estatus") String estatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRol", idRol);
            param.put("nombre", nombre);
            param.put("estatus", estatus);
            
            conn.update("Rol.actualizarRol",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("El rol fue actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el rol");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("eliminarRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarRol(
            @FormParam("idRol") Integer idRol){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRol", idRol);
            
            conn.update("Rol.eliminarRol",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Rol eliminado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el rol");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusRol(
            @FormParam("idRol") Integer idRol){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idRol", idRol);
            
            conn.update("Rol.actualizarEstatus",param);
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