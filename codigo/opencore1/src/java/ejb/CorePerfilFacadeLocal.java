/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.CoreDerecho;
import entities.CorePerfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jgonzalezc
 */
@Local
public interface CorePerfilFacadeLocal {

    void create(CorePerfil corePerfil);

    void edit(CorePerfil corePerfil);

    void remove(CorePerfil corePerfil);

    CorePerfil find(Object id);

    List<CorePerfil> findAll();

    List<CorePerfil> findRange(int[] range);

    int count();
    
    List<CoreDerecho> queryDerechoFindAll();
    
    String persistir(CorePerfil perfil,String tipo) ;
    
    String select(int rows, int page, String sort, String order, String search);
    
    String perfilDerechos(Object id);
    
    String actualizarPerfilDerechos(Object id,String ids);
    
    String generaCombo();
    
    String generaCombo2();
    
    String usuarioPerfiles(Object id);
    
    String actualizarUsuarioPerfiles(Object id,String ids) ;
        
    List<CoreDerecho> getDerechos(Integer id);
    
    CorePerfil getPerfilByName(String name);
    
}
