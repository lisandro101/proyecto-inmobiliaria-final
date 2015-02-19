/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;

import entidades.EstadoVisita;
import entidades.Visita;
import expertos.EstadoVisitaSessionBean;
import expertos.VisitaSessionBean;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mobile.entidades.DtoVisitas;

/**
 *
 * @author Dario
 */
public class VisitasServlet extends HttpServlet {
    @EJB
    private VisitaSessionBean ejbVisitaSessionBean;
    @EJB
    private EstadoVisitaSessionBean ejbEstadoVisitaSessionBean;
    private DtoVisitas dtoVisitas;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String consulta = request.getParameter("consulta");
        List<Visita> visitas = null;
        List<DtoVisitas> dtoVisita;
        DataOutputStream out = null;
        if(consulta != null && consulta.equals("Cambio")) 
            cambiarEstado(request,response,request.getParameter("visita"), request.getParameter("estado"));
     
        if(consulta != null && consulta.equals("Cliente")){
            String contacto = request.getParameter("contacto");
            String estado = request.getParameter("estado");
            dtoVisita = new ArrayList<DtoVisitas>();
            visitas = this.getEjbVisitaSessionBean().obtenerVisitasPorContacto(contacto,estado);
            if(visitas.isEmpty()){
                response.sendError(500, "El Cliente " + contacto + " no tiene visitas " + estado);
            }else{
                dtoVisita = armarDTOVisita(visitas);
                enviarRespuesta(response,dtoVisita);
                response.setStatus(200);
            }
        }else if(request.getParameter("u") != null){
            String usuario = request.getParameter("u");
            dtoVisita = new ArrayList<DtoVisitas>();
            visitas = this.getEjbVisitaSessionBean().obtenerVisitasPendientes(usuario,"Pendiente");
            if(visitas.isEmpty()){
                response.sendError(500, "No hay visitas Pendientes para "+ usuario);
            }else{
                dtoVisita = armarDTOVisita(visitas);
                enviarRespuesta(response,dtoVisita);
                response.setStatus(200);
            }
        }
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

    public VisitaSessionBean getEjbVisitaSessionBean() {
        return ejbVisitaSessionBean;
    }

    public void setEjbVisitaSessionBean(VisitaSessionBean ejbVisitaSessionBean) {
        this.ejbVisitaSessionBean = ejbVisitaSessionBean;
    }

    private List<DtoVisitas> armarDTOVisita(List<Visita> visitas) {
       
        List<DtoVisitas> dtoVisitas = new ArrayList<DtoVisitas>();
        for (Visita visita : visitas) {
            DtoVisitas dto = new DtoVisitas();
            dto.setContacto(visita.getCliente().getApellidoNombre());
            dto.setDomicilio(visita.getInmueble().getDireccion().getDireccionCorta());
            dto.setEmpleado(visita.getEmpleado().getUsuario().getNombreUsuario());
            dto.setEstado(visita.getEstadoVisita().getEstado());
            dto.setFecha(visita.getFecha());
            dto.setHora(visita.getHora());
            dto.setNroVisita(visita.getNroVisita());
            dtoVisitas.add(dto);
        }
        return dtoVisitas;
    }

    private void enviarRespuesta(HttpServletResponse response, List<DtoVisitas> dtoVisita) throws IOException {
        String data;// = new byte[0];
        data = serialize(dtoVisita);
        response.setContentLength(data.length());
//        response.setContentType("application/octect-stream");
        DataOutputStream out = new DataOutputStream(response.getOutputStream());
        out.writeBytes(data);
        out.close();
    }

    private String serialize(List<DtoVisitas> dtoVisita) throws IOException {
        String resultado = "";
        for (DtoVisitas dtoVisitas1 : dtoVisita) {
            resultado += dtoVisitas1.toString();            
        }
        
        return resultado;
    }

    public void cambiarEstado(HttpServletRequest request, HttpServletResponse response, String visita, String estado) throws IOException {
        EstadoVisita estadoVisita = buscarEstadoVisita(estado);
        if(cambiarEstado(visita,estadoVisita))
            response.setStatus(200);
        else
            response.sendError(500, "No Se pudo cambiar el estado de la Visita");
    }

    public EstadoVisitaSessionBean getEjbEstadoVisitaSessionBean() {
        return ejbEstadoVisitaSessionBean;
    }

    public void setEjbEstadoVisitaSessionBean(EstadoVisitaSessionBean ejbEstadoVisitaSessionBean) {
        this.ejbEstadoVisitaSessionBean = ejbEstadoVisitaSessionBean;
    }

    public DtoVisitas getDtoVisitas() {
        return dtoVisitas;
    }

    public void setDtoVisitas(DtoVisitas dtoVisitas) {
        this.dtoVisitas = dtoVisitas;
    }

    private EstadoVisita buscarEstadoVisita(String estado) {
        return this.getEjbEstadoVisitaSessionBean().obtenerEstadoVisita(estado);
    }

    public boolean cambiarEstado(String visita, EstadoVisita estadoVisita) {
        boolean resultado = false;
        resultado = this.getEjbVisitaSessionBean().cambiarEstadoVisita(visita, estadoVisita);
        
        return resultado;
    }

}
