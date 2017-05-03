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
import ejb.CoreUsuarioFacadeLocal;

/**
 *
 * @author jgonzalezc
 */
@WebServlet(name = "RecuperarContrasenaServlet", urlPatterns = {"/recuperarContrasena.do"})
public class RecuperarContrasenaServlet extends HttpServlet {
    @EJB
    CoreUsuarioFacadeLocal coreUsuarioEJB;

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
                response.setHeader("Cache-Control","no-store"); //HTTP 1.1
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0);
        response.setDateHeader("Last-Modified", 0);
        if(request.getParameter("email")!=null){                    
            try {
                String cadenaEncriptacion=getServletContext().getInitParameter("SEC_ENCRYP");
                response.getWriter().print(coreUsuarioEJB.recuperarContrasena(cadenaEncriptacion,request.getParameter("email"),getServletContext().getInitParameter("appName"),"http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/",getServletContext().getInitParameter("datosEmail")));
            }catch (IOException e) {
                response.getWriter().print("{\"success\":false,\"msg\":\"Error al realizar la operacion.\"}");
            }
        }else
            response.getWriter().print("{\"success\":false,\"msg\":\"Error al realizar la operacion.\"}");
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
