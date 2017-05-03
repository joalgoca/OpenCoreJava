/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.catalogos.general;

import clases.ObjetoSesion;
import ejb.CoreAvisoFacadeLocal;
import ejb.CoreUsuarioFacadeLocal;
import entities.CoreAviso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jgonzalezc
 */
@WebServlet(name = "AvisoServlet", urlPatterns = {"/catalogos/generals/aviso.do"})
public class AvisoServlet extends HttpServlet {
    @EJB
    CoreAvisoFacadeLocal coreAvisoEJBLocal;
    @EJB
    CoreUsuarioFacadeLocal coreUsuarioEJBLocal;

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
        HttpSession session=request.getSession();
        response.setHeader("Cache-Control","no-store"); //HTTP 1.1
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0);
        response.setDateHeader("Last-Modified", 0);
        ObjetoSesion objetoSesion;
        if(session.getAttribute(getServletContext().getInitParameter("vsi")+"SESION")==null){
            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
                response.getWriter().print("{\"error\":\"901\",\"data\":[],\"draw\":0}");
            else
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            objetoSesion=(ObjetoSesion)session.getAttribute(getServletContext().getInitParameter("vsi")+"SESION");
            try {
                if(request.getParameter("modo")==null) {
                    int rows=10;
                    int page=1;   
                    String sort="CORE_AVISO_ID"; 
                    String order="desc"; 
                    int draw=1; 
                    String filter=" where (PERFIL_DESTINO='"+objetoSesion.getPerfilSelected().getPerfil()+"' or PERFIL_DESTINO is null) ";
                    if(request.getParameter("tipo")!=null && request.getParameter("tipo").equals("admin"))
                        filter="";
                    if(request.getParameter("length")!=null)
                        rows = Integer.parseInt(request.getParameter("length"));
                    if(request.getParameter("draw")!=null)
                        draw = Integer.parseInt(request.getParameter("draw"));
                    if(request.getParameter("start")!=null)
                        page = Integer.parseInt(request.getParameter("start"))+1; 
                    if(request.getParameter("order[0][column]")!=null)
                        sort = fieldParse(request.getParameter("columns["+request.getParameter("order[0][column]")+"][data]"));
                    if(request.getParameter("order[0][dir]")!=null)
                        order = request.getParameter("order[0][dir]");
                        
                    List<Integer> mFiltro=new ArrayList();
                    for(int i=0;i<request.getParameterMap().size();i++){
                        if(request.getParameter("columns["+i+"][searchable]")!=null && request.getParameter("columns["+i+"][searchable]").equals("true") && !request.getParameter("columns["+i+"][search][value]").equals(""))
                            mFiltro.add(i);                            
                    }
                    for(Integer i:mFiltro){
                        filter += (filter.contains("where")?" and ":" where ")+fieldParse(request.getParameter("columns["+i+"][data]"))+"='"+request.getParameter("columns["+i+"][search][value]")+"'";
                    }
                    response.getWriter().print(coreAvisoEJBLocal.select(rows,page,sort,order,filter,draw));
                }else {
                    String modo=request.getParameter("modo");
                    String id=request.getParameter("coreAvisoId");
                    CoreAviso aviso=new CoreAviso();
                    if(modo.equals("eliminar")) {
                        coreAvisoEJBLocal.remove(coreAvisoEJBLocal.find(Integer.parseInt(id)));
                        response.getWriter().print("{\"success\":true,\"title\":\"El registro se ha eliminado con exito.\",\"msg\":\"\"}");
                    }else if(modo.equals("editar") || modo.equals("nuevo")) {
                        if(id!=null && !id.equals(""))
                            aviso=coreAvisoEJBLocal.find(Integer.parseInt(id)); 
                        else
                            aviso.setFechaCreacion(new Date());                     
                        aviso.setContenido(request.getParameter("contenido").replaceAll("\"", "'"));     
                        aviso.setEstatus(request.getParameter("estatus"));
                        aviso.setPrioridad(request.getParameter("prioridad"));
                        aviso.setTitulo(request.getParameter("titulo"));
                        aviso.setVigencia(request.getParameter("vigencia"));
                        response.getWriter().print(coreAvisoEJBLocal.persistir(aviso,modo)); 
                    }else if(modo.equals("navisos")) {
                        response.getWriter().print(coreAvisoEJBLocal.navisos(" where  DATE_ADD(FECHA_CREACION,INTERVAL VIGENCIA DAY)>=NOW() and ESTATUS='AC'"));
                    }
                } 
            }catch (IOException | NumberFormatException e) {
                if(request.getParameter("modo")==null)
                    response.getWriter().print("{\"data\":[],\"total\":0}"); 
                else
                    response.getWriter().print("{\"errors\":\"Error al realizar la operacion.\"}");
            }
        }
     }
     private String fieldParse(String campo){
        switch (campo) {
            case "coreAvisoId":
                return "CORE_AVISO_ID";
            case "perfilDestino":
                return "PERFIL_DESTINO";
            case "fechaCreacion":
                return "FECHA_CREACION";
            default:
                return campo;
        }
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
