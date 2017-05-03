/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import clases.ObjetoSesion;
import entities.CoreDerecho;
import entities.CoreUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jgonzalezc
 */
@Local
public interface CoreUsuarioFacadeLocal {

    void create(CoreUsuario coreUsuario);

    void edit(CoreUsuario coreUsuario);

    void remove(CoreUsuario coreUsuario);

    CoreUsuario find(Object id);

    List<CoreUsuario> findAll();

    List<CoreUsuario> findRange(int[] range);

    int count();
    
    List<CoreUsuario> queryCoreUsuarioFindByUserName(Object usuario);
    
    List<CoreUsuario> queryCoreUsuarioFindByEmail(Object email);
    
    ObjetoSesion queryCoreUsuarioFindByUserContrasena(Object usuario, Object contrasena,String cadenaEncriptacion);
    
    List<CoreDerecho> queryCoreDerechoFindMenu();
    
    String persistirUsuario(CoreUsuario usuario,String tipo);
    
    String selectUsuario(int rows, int page, String sort, String order, String search) ;
    
    String verificarUnico(String campo, String textoBuscar,String id);
    
    String generaComboUsuario(CoreUsuario coreUsuario);
    
    String recuperarContrasena(String cadenaEncriptacion,String email,String aplicacion,String rutaApp,String emisor);
    
}
