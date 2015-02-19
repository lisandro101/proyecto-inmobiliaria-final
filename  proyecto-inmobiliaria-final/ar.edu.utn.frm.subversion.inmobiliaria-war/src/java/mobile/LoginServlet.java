/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;

import expertos.UsuarioSessionBean;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsfUtil;

/**
 *
 * @author Dario
 */
public class LoginServlet extends HttpServlet {
    @EJB(beanName="UsuarioSessionBean")
    private UsuarioSessionBean usuarioSessionBeans;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String u = request.getParameter("u");
            String c = JsfUtil.encriptarMD5(request.getParameter("c").toString());
            if (this.getUsuarioSessionBeans().validarUsuario(u.trim(), c)) {
                response.setStatus(200);
            } else {
                response.sendError(500, "Error de login");
            }
            DataOutputStream out = new DataOutputStream(response.getOutputStream());
            out.writeUTF("muchos datos");
            out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public UsuarioSessionBean getUsuarioSessionBeans() {
        return usuarioSessionBeans;
    }

    public void setUsuarioSessionBeans(UsuarioSessionBean usuarioSessionBeans) {
        this.usuarioSessionBeans = usuarioSessionBeans;
    }
}
