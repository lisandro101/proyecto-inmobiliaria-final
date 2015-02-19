/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import expertos.ClienteSessionBean;
import expertos.ContratoAlquilerSessionBean;
import expertos.ReporteSessionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import util.JsfUtil;

/**
 *
 * @author mariodante
 */
@ManagedBean(name="reportesMB")
@SessionScoped
public class ReporteManagedBean  {

   private String url = "jdbc:postgresql://localhost:5432/inmobiliariaDB";
   private String admin="admin";
   private String pass="admin";
   private String dirBaseReporte="c://Reportes//";
   
   //nro del cliente que deseo buscar
   private String nroClienteBuscado;
   
   //nroContrato, ya sea alquiler o venta
   private String nroContratoBuscado;
   
   private Connection  conexion;
   
   @EJB(beanName="ClienteSessionBean")
   private ClienteSessionBean  clienteSessionBeans;
   
   @EJB(beanName="ContratoAlquilerSessionBean")
   private ContratoAlquilerSessionBean contratoAlquilerSessionBean;
   
   @EJB(beanName="ReporteSessionBean")
   private ReporteSessionBean reporteSessionBean;
   
   
        
    /** Creates a new instance of ReporteManagedBean */
    public ReporteManagedBean() 
    {
        nroClienteBuscado =null;
        nroContratoBuscado=null;
    }
           
     public void crearReporte(HashMap parametros,String nombreReporte,String nombrePDF)
    {
         try {
            // funka
           //Class.forName("com.mysql.jdbc.Driver").newInstance();
           
//           conexion = getReporteSessionBeans().obtenerConexion();
//                   
//           //por si no logramos conectar, usamos los hardcodeos
//           if(conexion==null)
//           {
            conexion = (Connection) DriverManager.getConnection(url, admin, pass);
           //}

           //InputStream imgInputStream = this.getClass().getResourceAsStream("/your/package/imgName.ext");
            //parameters.put("myImg", imgInputStream);
           
          JasperReport reporte = (JasperReport) JRLoader.loadObject( getClass().getResourceAsStream("../reportes/"+nombreReporte));

           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
         // Exporta el informe a PDF
          String destFileNamePdf=nombrePDF;
          JasperExportManager.exportReportToPdfFile(jasperPrint, destFileNamePdf);
         
          
//            //funka,  lo guarda la direccion q le pasamos
//            JRExporter exporter = new JRPdfExporter();       
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);       
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombrePDF) );
//            exporter.exportReport();
           
////            no funka, salts exception
//            JasperViewer jviewer = new JasperViewer(jasperPrint, true);
//            jviewer.setVisible(true);
            
            //no funka
//            JasperPrint reporte_view;
//            reporte_view = JasperFillManager.fillReport(reporte, parametros, conexion);
//            JasperViewer.viewReport(reporte_view, false);
          
//          no funka         
//          URL in =  this.getClass().getResource("../reportes/"+nombreReporte); 
//          File reportes = new File(in.toURI());
//          Map param = new HashMap();
//          param.put("nroCliente","nn");
//            byte[] bytes = JasperRunManager.runReportToPdf(reportes.getPath(), param,conexion);
//            FacesContext context = FacesContext.getCurrentInstance();
//            ExternalContext ext = context.getExternalContext(); 
//            HttpServletResponse response = (HttpServletResponse) ext.getResponse();
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            ServletOutputStream outp= response.getOutputStream();
//            outp.write(bytes, 0, bytes.length);
//            outp.flush();
//            outp.close();
          
//          mostrarReporte(destFileNamePdf);

           JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
          
         } catch (Exception ex) 
         {
            JsfUtil.addErrorMessage(ex,"Error al imprimir");
         }
    
    }
     
     public void imprimirVisitas()    {
          
        HashMap map = new HashMap();
        
//        map.put("nombreP", "laura");
        
                //ejbSessionCliente.findAll();
        crearReporte(map,"reportesVisitas.jasper",dirBaseReporte+"reporteVisitas.pdf" );
    
    }
     
   public void imprimirCliente()    {
          
        HashMap map = new HashMap();
        
        //map.put("nombreP", "laura");

        crearReporte(map,"ReporteClientes.jasper",dirBaseReporte+"reporteClientes.pdf" );
    
    }
   
      public void imprimirInfoCliente() {
          
        HashMap map = new HashMap();
        
        //setNroCliente("5804");
        
        if(nroClienteBuscado!=null)
        {
            if(getClienteSessionBeans().validarExistenciaClientePorNroCliente(nroClienteBuscado.trim()))
            {
                map.put("nroCliente",nroClienteBuscado.trim());
                crearReporte(map,"ReporteInfoCliente.jasper",dirBaseReporte+"ReporteInfoCliente.pdf" );
            }
            else
            {
               JsfUtil.addErrorMessage("El cliente ingresado no existe.");
            }
        }
        else
        {
            JsfUtil.addErrorMessage("Debe ingresar un texto.");
        }
    }
       
    /**
     * @return the nroCliente
     */
    public String getNroCliente() {
        return nroClienteBuscado;
    }

    /**
     * @param nroCliente the nroCliente to set
     */
    public void setNroCliente(String nroCliente) {
        this.nroClienteBuscado = nroCliente;
    }
    
   public ClienteSessionBean getClienteSessionBeans() {
        return clienteSessionBeans;
    }

    public void setClienteSessionBeans(ClienteSessionBean clienteSessionBeans) {
        this.clienteSessionBeans = clienteSessionBeans;
    }
    
     public ReporteSessionBean getReporteSessionBeans() {
        return reporteSessionBean;
    }

    public void setReporteSessionBeans(ReporteSessionBean reporteSessionBeans) {
        this.reporteSessionBean = reporteSessionBeans;
    }
    
    /**
     * @return the nroCotrato
     */
    public String getNroContrato() {
        return nroContratoBuscado;
    }

    /**
     * @param nroCotrato the nroCotrato to set
     */
    public void setNroContrato(String nroContrato) {
        this.nroContratoBuscado = nroContrato;
    }
    
       public ContratoAlquilerSessionBean getContratoSessionBeans() {
        return contratoAlquilerSessionBean;
    }

    public void setContratoSessionBeans(ContratoAlquilerSessionBean contratoAlquilerSessionBean) {
        this.contratoAlquilerSessionBean = contratoAlquilerSessionBean;
    }
    
    
   
    
    
    
   
}
