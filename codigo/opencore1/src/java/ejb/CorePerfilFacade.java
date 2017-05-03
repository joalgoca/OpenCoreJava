/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import clases.FCom;
import entities.CoreDerecho;
import entities.CorePerfil;
import entities.CoreUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author jgonzalezc
 */
@Stateless
public class CorePerfilFacade extends AbstractFacade<CorePerfil> implements CorePerfilFacadeLocal {

    @PersistenceContext(unitName = "opencore1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorePerfilFacade() {
        super(CorePerfil.class);
    }
    
    private Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    private Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public List<CoreDerecho> queryDerechoFindAll() {
        List<CoreDerecho> coreDerechos=em.createNamedQuery("CoreDerecho.findMenu").getResultList();
        return coreDerechos;
    }
    
    @Override
    public String persistir(CorePerfil perfil,String tipo) {
        String mensaje;
        try {
            Object respuesta=null;
            switch (tipo) {
                case "nuevo":
                    respuesta=persistEntity(perfil);
                    break;
                case "editar":
                    respuesta=mergeEntity(perfil);
                    break;
            }
            if (respuesta != null)
                mensaje = "{\"success\":true,\"title\":\"Operación exitosa.\",\"msg\":\"\"}";
            else
                mensaje = "{\"success\":false,\"title\":\"Operación no encontrada.\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        } catch (ConstraintViolationException e) {              
            mensaje = "{\"success\":false,\"title\":\"Error de persistencia de datos\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        }
        return mensaje;
    }
    
    @Override
    public String select(int rows, int page, String sort, String order, String search) {
        String json;
        
        int inicio = ((page - 1) * rows);
        try {
            Query queryTotal =em.createNativeQuery("SELECT COUNT(1) FROM core_perfil " + search);
            int count=Integer.parseInt(queryTotal.getSingleResult().toString());
        
            String query = "select * from core_perfil " + search + " order by " + sort + " " +order;
            String queryPaginado = query + " LIMIT " + inicio + "," + rows;
            Query queryRegistros = em.createNativeQuery(queryPaginado, CorePerfil.class);

            List<CorePerfil> listPerfil = (List<CorePerfil>)queryRegistros.getResultList();
            json = "{\"data\":[";
            for (CorePerfil perfil: listPerfil) {
                json += perfil.toString()+",";
            }
            if (count > 0)
                json = json.substring(0, json.length() - 1);
            json += "],\"total\":"+count+"}";
        } catch (NumberFormatException e) {
            FCom.printDebug(e.getMessage());
            json = "{\"data\":[],\"total\":0}";
        }
        return json;
    }
    
  @Override
  public String perfilDerechos(Object id) {
        int count = 0;
        String json ;        
        try {        
            CorePerfil perfil = em.find(CorePerfil.class, id);
            json = "{\"data\":\"";
            for (CoreDerecho derecho: perfil.getCoreDerechoList()) {
                json += derecho.getCoreDerechoId()+",";
                count++;
            }
            if (count > 0)
                json = json.substring(0, json.length() - 1);
            json += "\",\"total\":"+count+"}";
        } catch (Exception e) {
            FCom.printDebug(e.getMessage());
            json = "{\"data\":\"\",\"total\":0}";
        }
        return json;
    }     
    @Override
    public String actualizarPerfilDerechos(Object id,String ids) {
        String mensaje;
        try {     
            Object respuesta=null;
            ids=ids.replaceAll("CH-","");
            String[] aids=ids.split(",");
            
            CorePerfil perfil=em.find(CorePerfil.class,id);
            perfil.getCoreDerechoList().clear();
            respuesta=em.merge(perfil);
            em.flush();
            if(!ids.equals("")){
                for(String idDerecho:aids) {
                    CoreDerecho derecho=em.find(CoreDerecho.class,Integer.parseInt(idDerecho));
                    perfil.getCoreDerechoList().add(derecho);
                }               
                respuesta=em.merge(perfil);
            }
            if (respuesta != null)
                mensaje = "{\"success\":true,\"title\":\"Operación exitosa.\",\"msg\":\"\"}";
            else
                mensaje = "{\"success\":false,\"title\":\"Operación no encontrada.\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        } catch (ConstraintViolationException e) {              
            mensaje = "{\"success\":false,\"title\":\"Error de persistencia de datos\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        }
        return mensaje;
    } 
    @Override
    public String generaCombo() {
        String json = "[{\"id\":\"\",\"text\":\"SELECCIONE UNA OPCION\"},";
        try {
            Query query =em.createNativeQuery("select * from core_perfil where estatus='AC' ", CorePerfil.class);
            List<CorePerfil> listPerfil = 
                (List<CorePerfil>)query.getResultList();
            for (CorePerfil tbPerfil: listPerfil)
                json += "{\"id\":\"" + tbPerfil.getCorePerfilId()+ "\",\"text\":\"" +tbPerfil.getDescripcion()+ "\"},";
            json = json.substring(0, json.length() - 1);
            json += "]";
        } catch (Exception e) {
            FCom.printDebug(e.getMessage());
            json = "[{\"id\":\"\",\"text\":\"NO EXISTEN OPCIONES\"}]";
        }
        return json;
    }
    
    @Override
    public String generaCombo2() {
        String json = "[{\"id\":\"\",\"text\":\"SELECCIONE UNA OPCION\"},";
        json+="{\"id\":\"TODOS\",\"text\":\"TODOS\"},";
        try {
            Query query =em.createNativeQuery("select * from core_perfil where estatus='AC' ", CorePerfil.class);
            List<CorePerfil> listPerfil = 
                (List<CorePerfil>)query.getResultList();
            for (CorePerfil tbPerfil: listPerfil)
                json += "{\"id\":\"" + tbPerfil.getPerfil()+ "\",\"text\":\"" +tbPerfil.getPerfil()+ "\"},";
            json = json.substring(0, json.length() - 1);
            json += "]";
        } catch (Exception e) {
            FCom.printDebug(e.getMessage());
            json = "[{\"id\":\"\",\"text\":\"NO EXISTEN OPCIONES\"}]";
        }
        return json;
    }
    
    @Override
    public String usuarioPerfiles(Object id) {
        int count = 0;
        String json ;        
        try {        
            CoreUsuario usuario = em.find(CoreUsuario.class, id);
            json = "{\"data\":\"";
            for (CorePerfil perfil: usuario.getCorePerfilList()) {
                json += perfil.getCorePerfilId()+",";
                count++;
            }
            if (count > 0)
                json = json.substring(0, json.length() - 1);
            json += "\",\"total\":"+count+"}";
        } catch (Exception e) {
            FCom.printDebug(e.getMessage());
            json = "{\"data\":\"\",\"total\":0}";
        }
        return json;
    }
    @Override
    public String actualizarUsuarioPerfiles(Object id,String ids) {
        String mensaje;
        try {     
            Object respuesta;
            String[] aids=ids.split(",");
            
            CoreUsuario usuario=em.find(CoreUsuario.class,id);
            usuario.getCorePerfilList().clear();
            respuesta=em.merge(usuario);
            em.flush();
            if(!ids.equals("")){
                for(String idPerfil:aids) {
                    CorePerfil perfil=em.find(CorePerfil.class,Integer.parseInt(idPerfil));
                    usuario.getCorePerfilList().add(perfil);
                }               
                respuesta=em.merge(usuario);
            }
            if (respuesta != null)
                mensaje = "{\"success\":true,\"title\":\"Operación exitosa.\",\"msg\":\"\"}";
            else
                mensaje = "{\"success\":false,\"title\":\"Operación no encontrada.\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        } catch (ConstraintViolationException e) {              
            mensaje = "{\"success\":false,\"title\":\"Error de persistencia de datos\",\"msg\":\"Favor de comunicarse con el administrador\"}";
        }
        return mensaje;
    } 
    @Override
    public List<CoreDerecho> getDerechos(Integer id) {
        String select="select * from core_derecho where core_derecho_id in ( select core_derecho_id from core_perfil_derecho where core_perfil_id="+id+") and estatus='AC' order by orden";
        if(id==0)
            select="select * from core_derecho where estatus='AC' order by orden";
        Query query =em.createNativeQuery(select, CoreDerecho.class);
        return (List<CoreDerecho>)query.getResultList();
    }
    
    @Override
    public CorePerfil getPerfilByName(String name) {
        String select="select * from core_perfil where perfil ='"+name+"'";        
        Query query =em.createNativeQuery(select, CorePerfil.class);
        List<CorePerfil> listPerfil = (List<CorePerfil>)query.getResultList();
        if(listPerfil.size()>-1)
            return listPerfil.get(0);
        else
            return null;
    }
    
}
