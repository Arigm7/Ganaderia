
package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import utils.JavaUtils;


@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllUsers() {
        List<Usuario> list = new ArrayList<Usuario>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Usuario.getAllUsers");
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
    @Path("getAllUsersActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllUsersActivo() {
        List<Usuario> list = new ArrayList<Usuario>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtil.getSession();
            list = conn.selectList("Usuario.getAllUsersActivo");
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
    @Path("registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("usuario") String usuario,
            @FormParam("password") String password,
            @FormParam("idRol") Integer idRol){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("usuario", usuario);
            param.put("password", JavaUtils.hashString(password.toUpperCase()));
            param.put("idRol", idRol);
            
            conn.insert("Usuario.registrarUsuario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Usuario registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el usuario");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    
    @POST
    @Path("actualizarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarUsuario(
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("usuario") String usuario,
            @FormParam("password") String password,
            @FormParam("estatus") String estatus,
            @FormParam("idRol") Integer idRol){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idUsuario", idUsuario);
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("usuario", usuario);
            param.put("password", JavaUtils.hashString(password.toUpperCase()));
            param.put("estatus", estatus);
            param.put("idRol", idRol);
            
            
            conn.update("Usuario.actualizarUsuario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Usuario actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el usuario");
        }finally{
            conn.close();
        }
        return res;
    }
    
    @POST
    @Path("eliminarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarUsuario(
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idUsuario", idUsuario);
            
            conn.update("Usuario.eliminarUsuario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Usuario eliminado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo eliminar el usuario");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusUsuario(
            @FormParam("idUsuario") Integer idUsuario){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idUsuario", idUsuario);
            
            conn.update("Usuario.actualizarEstatus",param);
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
    @Path("getUsuarioById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarioById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        try {
            return conn.selectList("Usuario.getUsuarioById", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    @POST
    @Path("usuarioId")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta usuarioById(@FormParam("usuario") String usuario) {
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        long num=0;
        try {
            
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("usuario", usuario);
           
            Map<String, Object> result = conn.selectOne("Usuario.usuarioId", param);
            conn.commit();
            num = (Long) result.get("RESULT");

            res.setError(false);
           
            res.setMensaje(Long.toString(num));
         
        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("Error al consultar");
        } finally {
            conn.close();
        }
        return res;
    }
}
