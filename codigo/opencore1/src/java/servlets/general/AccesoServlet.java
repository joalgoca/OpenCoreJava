/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.general;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ejb.CoreUsuarioFacadeLocal;
import clases.FCom;
import clases.ObjetoSesion;
import ejb.BitacoraFacadeLocal;
import ejb.CorePermisosIpFacadeLocal;
import entities.Bitacora;
import entities.CorePermisosIp;
import java.util.Date;

/**
 *
 * @author jgonzalezc
 */
@WebServlet(name = "AccesoServlet", urlPatterns = {"/acceso.do"})
public class AccesoServlet extends HttpServlet {
    @EJB
    CoreUsuarioFacadeLocal usuarioEJBLocal;
    @EJB
    BitacoraFacadeLocal bitacoraEJBLocal;
    @EJB
    CorePermisosIpFacadeLocal corePermisosIpEJBLocal;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession(true);
        response.setHeader("Cache-Control","no-store"); //HTTP 1.1
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0);
        response.setDateHeader("Last-Modified", 0);
        String modo=request.getParameter("modo");
        boolean ajaxRequest="XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        String cadenaEncriptacion=getServletContext().getInitParameter("SEC_ENCRYP");
        if(modo!=null) {  
            if(modo.equals("login")) {// proviene de general/login.jsp  - administracion del sistema
                int noIntentos=0;
                int maxIntentos=3;
                if(session.getAttribute(getServletContext().getInitParameter("vsi")+"MaxLog")!=null)
                    noIntentos=Integer.parseInt(session.getAttribute(getServletContext().getInitParameter("vsi")+"MaxLog").toString());
                if(noIntentos<maxIntentos)
                {
                    noIntentos++;
                    session.setAttribute(getServletContext().getInitParameter("vsi")+"MaxLog",noIntentos);
                    String username=request.getParameter("logUsuario");
                    String contrasena=request.getParameter("logContrasena");
                    if(username!=null && contrasena!=null) {
                        username = FCom.injectionFree(username.toUpperCase());
                        contrasena = FCom.injectionFree(contrasena);
                        ObjetoSesion objetoSesion=usuarioEJBLocal.queryCoreUsuarioFindByUserContrasena(username,contrasena,cadenaEncriptacion);
                        if(objetoSesion!=null) {
                                boolean iprestrict=true;
                                boolean multisesion=true;
                                if(objetoSesion.getUsuario().getIpRestriccion()){
                                   CorePermisosIp permisoIp= corePermisosIpEJBLocal.getPermisoIp(objetoSesion.getUsuario().getCoreUsuarioId(), request.getRemoteAddr());
                                   if(permisoIp!=null){
                                        if(permisoIp.getAccion().equals("BL")){   
                                            iprestrict=false;                                         
                                            Bitacora bitacora=new Bitacora();
                                            bitacora.setFechaOperacion(new Date());
                                            bitacora.setOperacion("El usuario ha intentado accesar desde una IP bloqueada "+request.getRemoteAddr());
                                            bitacora.setOrigen("");
                                            bitacora.setTipo("ACCESO DEN");
                                            bitacora.setUsuarioId(objetoSesion.getUsuario().getCoreUsuarioId());
                                            bitacoraEJBLocal.persistir(bitacora, "nuevo");
                                        }
                                   }else
                                       iprestrict=false;
                                }
                                if(!objetoSesion.getUsuario().getMultiSesion()){
                                    if(objetoSesion.getUsuario().getIsLogin())
                                           multisesion=false;                  
                                }
                                if(iprestrict){
                                    if(multisesion){
                                        objetoSesion.getUsuario().setIsLogin(true);
                                        usuarioEJBLocal.persistirUsuario(objetoSesion.getUsuario(), "editar");
                                        session.setAttribute(getServletContext().getInitParameter("vsi")+"SESION",objetoSesion);
                                        if(objetoSesion.getUsuario().getAplicaBitacora())
                                        {
                                            Bitacora bitacora=new Bitacora();
                                            bitacora.setFechaOperacion(new Date());
                                            bitacora.setOperacion("LOGIN");
                                            bitacora.setOrigen("");
                                            bitacora.setTipo("ACCESO");
                                            bitacora.setUsuarioId(objetoSesion.getUsuario().getCoreUsuarioId());
                                            bitacoraEJBLocal.persistir(bitacora, "nuevo");            
                                        }                               
                                        if(ajaxRequest)
                                        {
                                            response.getWriter().print("{\"success\":true}");
                                            response.flushBuffer();
                                        }else{
                                            if(objetoSesion.getPerfilSelected()!=null)
                                                response.sendRedirect(objetoSesion.getPerfilSelected().getHomePage());
                                            else
                                                response.sendRedirect(objetoSesion.getSuperAdmin().getHomePage());
                                        }
                                    }else{                                    
                                        if(ajaxRequest)
                                        {
                                            response.getWriter().print("{\"success\":false,\"msg\":\"El usuario actualmente se encuentra en el sistema.\"}");
                                            response.flushBuffer();
                                        }
                                        else
                                            response.sendRedirect("index.jsp?msg=El usuario actualmente se encuentra en el sistema.");
                                    }
                                }else{                                    
                                    if(ajaxRequest)
                                    {
                                        response.getWriter().print("{\"success\":false,\"msg\":\"El usuario no tiene permisos de accesar desde esta direccion IP.\"}");
                                        response.flushBuffer();
                                    }
                                    else
                                        response.sendRedirect("index.jsp?msg=El usuario no tiene permisos de accesar desde esta direccion IP.");
                                }
                        }else {
                            if(ajaxRequest)
                            {
                                response.getWriter().print("{\"success\":false,\"msg\":\"Tiene "+(maxIntentos-noIntentos)+" oportunidad(es) mas para introducir los datos correctos.\"}");
                                response.flushBuffer();
                            }
                            else
                                response.sendRedirect("index.jsp?msg=Tiene "+(maxIntentos-noIntentos)+" oportunidad(es) mas para introducir los datos correctos.");
                        }
                    }
                }else {
                    if(ajaxRequest)
                    {
                        response.getWriter().print("{\"success\":false,\"msg\":\"El acceso ha sido bloqueado por 20min.\"}");
                        response.flushBuffer();
                    }
                    else 
                        response.sendRedirect("index.jsp?msg=El acceso ha sido bloqueado por 20min.");
                }
            }else if(modo.equals("logout")) {
                session.invalidate();
            }
        }
        if(!response.isCommitted())
            response.sendRedirect("index.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
