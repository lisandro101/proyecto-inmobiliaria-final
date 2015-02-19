/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Cliente;
import entidades.ContratoAlquiler;
import entidades.ContratoCompraVenta;
import entidades.Inmueble;
import entidades.Visita;
import expertos.ClienteSessionBean;
import expertos.ContratoAlquilerSessionBean;
import expertos.ContratoVenderSessionBean;
import expertos.InmuebleSessionBean;
import expertos.VisitaSessionBean;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import util.JsfUtil;

/**
 *
 * @author mariodante
 */
@ManagedBean(name="reporteMB")
@SessionScoped
public class ReportesManagedBean implements Serializable{

//   private String url = "jdbc:mysql://localhost/inmobiliariaDB";
//   private String admin="root";
//   private String pass="1234";
//   private String dirBaseReporte="C://ReportesInmobiliaria//";
   private boolean popupReporteCorrecto = false;
   private String mensajeResultado;
           //+ "c://NetBeansProjects//";
    
   //el filtro puede ser por nombre o apellido
   private String filtroClienteBuscado;
   
   //el filtro puede ser por nombre o apellido
   private String inmuebleBuscado;
  
   // cant de registros que queremos ver
   //private String cantRegistros;
   
    //nroContratoAlquier o compraventa
   private String nroContrato;
   
   
    private Date fechaInferior;
    private Date fechaSuperior;
   
   @EJB(beanName="ClienteSessionBean")
   private ClienteSessionBean clienteSessionBeans;
   
   @EJB(beanName="InmuebleSessionBean")
   private InmuebleSessionBean inmuebleSessionBeans;
   
   @EJB(beanName="VisitaSessionBean")
   private VisitaSessionBean visitaSessionBeans;
   
   @EJB(beanName="ContratoAlquilerSessionBean")
   private ContratoAlquilerSessionBean contratoAlquilerSessionBeans;
   
    @EJB(beanName="ContratoVenderSessionBean")
   private ContratoVenderSessionBean contratoVentaSessionBeans;
   
   
   @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
   EntityManager em;
   @Resource(name="jdbc/inmobiliaria")
   private DataSource dataSource;
   private Connection  conexion;
   
   private static final int DEFAULT_BUFFER_SIZE = 61440; 
    
    /** Creates a new instance of ReportesManagedBean */
    public ReportesManagedBean() 
    {
       
    }

    /**
     * @return the cantRegistros
     */
//    public String getCantRegistros() {
//        return cantRegistros;
//    }
//
//    /**
//     * @param cantRegistros the cantRegistros to set
//     */
//    public void setCantRegistros(String cantRegistros) {
//        this.cantRegistros = cantRegistros;
//    }
    
    public ClienteSessionBean getClienteSessionBeans() {
        return clienteSessionBeans;
    }
   

    public void setClienteSessionBeans(ClienteSessionBean clienteSessionBeans) {
        this.clienteSessionBeans = clienteSessionBeans;
    }
    
    public InmuebleSessionBean getInmuebleSessionBeans() {
        return inmuebleSessionBeans;
    }
   
    public void setInmuebleSessionBeans(InmuebleSessionBean inmuebleSessionBeans) {
        this.inmuebleSessionBeans = inmuebleSessionBeans;
    }
    
    public VisitaSessionBean getVsitaSessionBeans() {
        return visitaSessionBeans;
    }
   
    public void setVisitaSessionBeans(VisitaSessionBean visitaSessionBeans) {
        this.visitaSessionBeans = visitaSessionBeans;
    }
    
     public ContratoAlquilerSessionBean getContratoAlquilerSessionBeans() {
        return contratoAlquilerSessionBeans;
    }
   
    public void setContratoAlquilerSessionBeans(ContratoAlquilerSessionBean contratoAlquilerSessionBeans) {
        this.contratoAlquilerSessionBeans = contratoAlquilerSessionBeans;
    }
    
    
    public Date getFechaInferior() {
        return fechaInferior;
    }

    public void setFechaInferior(Date fechaInferior) {
        this.fechaInferior = fechaInferior;
    }

    public Date getFechaSuperior() {
        return fechaSuperior;
    }

    public void setFechaSuperior(Date fechaSuperior) {
        this.fechaSuperior = fechaSuperior;
    }
    
//     public void crearReporte(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//            conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//          
//            JasperReport reporte = (JasperReport) JRLoader.loadObject( getClass().getResourceAsStream("../reportes/"+nombreReporte));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
//         // Exporta el informe a PDF
//            String destFileNamePdf=nombrePDF;
//            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileNamePdf);
//
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//            mensajeResultado = "El reporte se guardó correctamente en " + nombrePDF;
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//            mensajeResultado = "Error: "+ex.toString();
//         }
//    
//    }
    
    public void crearReporte_Mario(HashMap parametros,String nombreReporte)//,String nombrePDF)
    {
         try
         {
             System.out.println("llego");
             conexion = dataSource.getConnection();   
            JasperReport reporte = (JasperReport) JRLoader.loadObject( getClass().getResourceAsStream("../reportes/"+nombreReporte));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
            
            JRExporter exporter = null;
            exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            File pdf = File.createTempFile("output.", ".pdf");//,new File(dirBaseReporte));
            //Fijarme como hacer para no crear el archivo
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(pdf));
            exporter.exportReport();
            downloadPDF(pdf);
            
            System.out.println("paso");
          
         } catch (Exception ex) 
         {
            JsfUtil.addErrorMessage(ex,"Error al imprimir");
            mensajeResultado = "Error: "+ex.toString();
         }
    
    }
    
    private void downloadPDF(File pdf) throws IOException  {
        
        // Prepare.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(pdf), DEFAULT_BUFFER_SIZE);

            // Init servlet response.
            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(pdf.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" +pdf.getName()+ "\"");
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

                // Finalize task.
                output.flush();
        }
        catch(Exception ex)
        {
            JsfUtil.addErrorMessage(ex,"Error al imprimir");
            mensajeResultado = "Error: "+ex.toString();
        }
        finally {
            // Gently close streams.
            close(output);
            close(input);
        }

        // Inform JSF that it doesn't need to handle response.
        // This is very important, otherwise you will get the following exception in the logs:
        // java.lang.IllegalStateException: Cannot forward after response has been committed.
        facesContext.responseComplete();
    }
    
     private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ex) {
                JsfUtil.addErrorMessage(ex,"Error al imprimir");
            }
        }
    }
    
    public String getMensajeResultado(){
        return mensajeResultado;
    }
    
    public void imprimirClienteConFiltro()    {
          
        HashMap map = new HashMap();
        
        String filtro = filtroClienteBuscado;
        
//        String cantReg = cantRegistros;
        
        if(filtro!="")
        {
        
            List<Cliente> listaClientes = getClienteSessionBeans().buscarClientes(filtro);

             if(listaClientes!=null && !listaClientes.isEmpty())
            {
//                if(validarNumerico(cantReg))
//                {
//                
//                    if(Integer.parseInt(cantReg)>0)
//                    {
                        map.put("imgFondo",setearImagen("fondo"));
                        map.put("imgCabecera",setearImagen("cabecera"));
                        map.put("txtNombreReporte","REPORTE CLIENTES.");
                    //    map.put("limiteSuperior",cantReg);
                        map.put("valor",filtro);
                        map.put("SUBREPORT_DIR",setearURLSubReporte());

                        crearReporte_Mario(map,"reporteClientesConFiltro.jasper");//,dirBaseReporte+"ReporteClientes.pdf" );
                        
//                        popupReporteCorrecto=true;
//                    }
//                    else
//                    {
//                        JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
//                    }
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");
//
//                }

            }
            else
            {
               JsfUtil.addErrorMessage("El cliente ingresado no existe.");
            }
        }
        else
        {
        JsfUtil.addErrorMessage("Debe ingresar al menos un carácter.");
        }
        
    }

    ///visitas a inmuebles
    
     public void imprimirVisitasAInmueble(){
          
        HashMap map = new HashMap();
        
        String filtro = inmuebleBuscado;
        
        String cantReg = "6";
                //cantRegistros;
     
        
        //verifico que al menos exista una visita
        Inmueble inmConVisita = getVsitaSessionBeans().validarInmuebleConVisitas(filtro);
       
        if(inmConVisita!=null)
        {
            if(validarNumerico(cantReg))
            {

                if(Integer.parseInt(cantReg)>0)
                {
                    map.put("imgFondo",setearImagen("fondo"));
                    map.put("imgCabecera",setearImagen("cabecera"));
                    map.put("txtNombreReporte","VISITAS A INMUEBLE");
                    map.put("limiteSuperior",cantReg);
                    map.put("cod_Inmueble",filtro);
                    map.put("SUBREPORT_DIR",setearURLSubReporte());
            
                crearReporte_Mario(map,"reportesVisitasAInmueble.jasper");//,dirBaseReporte+"reportesVisitasAInmueble.pdf" );
                
//                popupReporteCorrecto=true;
                }
                else
                {
                    JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
                }
            }
            else
            {
                JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");

            }
            
           
        }
        else
        {
            JsfUtil.addErrorMessage("El inmueble ingresado no posee visitas.");
        }

        
    }
       
   public void imprimirInfoCliente(){
          
        HashMap map = new HashMap();
        
        String filtro = filtroClienteBuscado;
              
       // String cantReg=cantRegistros; 
      
        //verifico que el cliente exista
        List<Cliente> clientes = getClienteSessionBeans().buscarClientes(filtro);
        
        if(clientes!=null && !clientes.isEmpty())
        {
          Cliente cli = clientes.get(0);
            //verifico que al menos exista una visita
          Cliente cliente = getVsitaSessionBeans().validarClienteConVisitas(cli);
          
            if(cliente!=null)
            {
//                if(validarNumerico(cantReg))
//                {
//                
//                    if(Integer.parseInt(cantReg)>0)
//                    {
                        map.put("imgFondo",setearImagen("fondo"));
                        map.put("imgCabecera",setearImagen("cabecera"));
                        map.put("txtNombreReporte","VISITAS DEL CLIENTE");
                   //     map.put("limiteSuperior",cantReg);
                        map.put("nroCliente",cliente.getNroCliente());
                        map.put("SUBREPORT_DIR",setearURLSubReporte());
                      
                        crearReporte_Mario(map,"ReporteInfoCliente.jasper");//,dirBaseReporte+"ReporteInfoCliente.pdf" );
                        
//                        popupReporteCorrecto=true;
//                    }
//                    else
//                    {
//                        JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
//                    }
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");
//
//                }
//                
                
            }
            else
            {
                JsfUtil.addErrorMessage("El cliente no ha realizado ninguna visita.");

            }
          
        }
        else
        {
            JsfUtil.addErrorMessage("El cliente ingresado no existe.");
        
        }
         
    }
   ///
   //visitasFiltras
    public void imprimirVisitasFiltradas(){
          
        HashMap map = new HashMap();
        
     
       // String limSup= cantRegistros;
        Date fecInf = fechaInferior; 
        Date fecSup = fechaSuperior; 
        
        if(fecInf!=null && fecSup!=null)
        {
            if(fecInf.before(fecSup))
            {
//                if(cantRegistros!=null)
//                {
//                    if(validarNumerico(cantRegistros))
//                    {
                     List<Visita> visitas = getVsitaSessionBeans().validarVisitasEntreFechas(fecInf,fecSup);

                        if(visitas!=null && !visitas.isEmpty())
                        {
                            map.put("imgFondo",setearImagen("fondo"));
                            map.put("imgCabecera",setearImagen("cabecera"));
                            map.put("txtNombreReporte","VISITAS FILTRADAS");
                     //       map.put("limiteSuperior",limSup);
                            map.put("fechaInferior",fecInf);
                            map.put("fechaSuperior",fecSup);

                            crearReporte_Mario(map,"ReporteVisitasEntreFechas.jasper");//,dirBaseReporte+"ReporteVisitasEntreFechas.pdf" );
                            
//                            popupReporteCorrecto=true;
                        }
                        else
                        {
                            JsfUtil.addErrorMessage("No existen visitas en el rango ingresado.");
                        }
//                    }
//                    else
//                    {
//                        JsfUtil.addErrorMessage("Debe ingresar un valor numérico para la cantidad de registros.");
//                    }
//                 
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("Debe ingresar un valor numérico para la cantidad de registros.");
//                }   
           }

             else
            {
                JsfUtil.addErrorMessage("La fecha de inicio debe ser inferior a la actual.");
            }
        
        }
        else
        {
            JsfUtil.addErrorMessage("Debe ingresar una fecha válida.");
        }
        
    }
    
   
    public void imprimirContratoAlquiler(){
          
        HashMap map = new HashMap();
        
        String nroCo = nroContrato;
        
        
        if(nroCo!=null)
        {
            if(validarNumerico(nroCo))
            {
                 List<ContratoAlquiler> contrato = getContratoAlquilerSessionBeans().validarExistenciaContratoAlquiler(Integer.parseInt(nroCo));
   
                if(contrato!=null && !contrato.isEmpty())
                {
                    map.put("imgFondo",setearImagen("fondo"));
                    map.put("imgCabecera",setearImagen("cabecera"));
                    map.put("txtNombreReporte","CONTRATO ALQUILER");
                    map.put("nroContrato",nroCo);
                    map.put("SUBREPORT_DIR",setearURLSubReporte());
                    map.put("imgCodigoBarra",setearImagen("codigo"));
                    
                    
                    crearReporte_Mario(map,"reporteCuotasInmueble.jasper");//,dirBaseReporte+"ReporteContratoAlquiler.pdf" );
//                    popupReporteCorrecto=true;
                }
                else
                {
                    JsfUtil.addErrorMessage("No existe el contrato de alquiler ingresado.");
                }
            }
            else
            {
                JsfUtil.addErrorMessage("El número de contrato debe ser numérico.");
            }   
        }
        else
        {
            JsfUtil.addErrorMessage("El número de contrato no debe estar vacío.");
        }
    }
    
      public void imprimirContratoVenta(){
          
        HashMap map = new HashMap();
        
        String nroCo = nroContrato;
        
        
        if(nroCo!=null)
        {
            if(validarNumerico(nroCo))
            {
                
                List<ContratoCompraVenta> contrato = getContratoVentaSessionBeans().validarExistenciaContratoVenta(Integer.parseInt(nroCo));
   
                if(contrato!=null && !contrato.isEmpty())
                {
                    map.put("imgFondo",setearImagen("fondo"));
                    map.put("imgCabecera",setearImagen("cabecera"));
                    map.put("txtNombreReporte","CONTRATO COMPRA-VENTA");
                    map.put("nroContrato",nroCo);
                    map.put("SUBREPORT_DIR",setearURLSubReporte());
                    
                    
                    crearReporte_Mario(map,"reporteVenta.jasper");//,dirBaseReporte+"ReporteContratoCompraVenta.pdf" );
//                    popupReporteCorrecto=true;
                }
                else
                {
                    JsfUtil.addErrorMessage("No existe el contrato de alquiler ingresado.");
                }
            }
            else
            {
                JsfUtil.addErrorMessage("El número de contrato debe ser numérico.");
            }   

        }
        else
        {
            JsfUtil.addErrorMessage("El número de contrato no debe estar vacío.");
        }
        
 
    }
    

//   private String setearImagenPorString(String lugar)
//   {
//         String retorno=null;
//         
//         if(lugar=="fondo")
//         {
//             retorno="C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\logoGris.png";
//         }
//         else
//         {
//             retorno="C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\cabecera.png";
//         }
//             
//         
//             return retorno;
//     }
     
   private InputStream setearImagen(String lugar)
   {
         InputStream retorno=null;
         
         if(lugar=="fondo")
         {
             retorno=this.getClass().getResourceAsStream("/reportes/logoGris.png");
         }
         else if(lugar=="cabecera")
         {
             retorno=this.getClass().getResourceAsStream("/reportes/cabecera.png");
         }
         else
         {
            retorno=this.getClass().getResourceAsStream("/reportes/codigoBarras.png");
         }
             
         
        return retorno;
     }
   
   private String setearURLSubReporte()
   {
       
       URL reportsDirPath = getClass().getResource("/reportes/");
    
       
        return reportsDirPath.getPath();
        
                //.substring(1,reportsDirPath.getPath().length() );
        
        //por si no funka
                //"C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\";
   }

   /**
     * @return the filtroClienteBuscado
     */
   public String getFiltroClienteBuscado() {
        return filtroClienteBuscado;
    }
   
   /**
     * @param filtroClienteBuscado the filtroClienteBuscado to set
     */
   public void setFiltroClienteBuscado(String filtroClienteBuscado) {
        this.filtroClienteBuscado = filtroClienteBuscado;
    }
   
   /**
     * @return the inmuebleBuscado
     */
   public String getInmuebleBuscado() {
        return inmuebleBuscado;
    }
   
   /**
     * @param inmuebleBuscado the inmuebleBuscado to set
     */
   public void setInmuebleBuscado(String inmuebleBuscado) {
        this.inmuebleBuscado = inmuebleBuscado;
    }
   
   public String irAClientesFiltro()
   {
       return "reporteClientesConFiltro";
   }
   
   public String irAInfoCliente()
   {
       return "reporteInformacionCliente";
   }
   
   public String irAVisitasEntreFechas()
   {
       return "ReporteVisitasEntreFechas";
   }
   
   public String irAreporteVisitasAInmueble()
   {
       return "reporteVisitasAInmueble";
   }
   
   public String irAreporteContratoAlquiler()
   {
       return "ReporteContratoAlquiler";
   }
   
   public String irAReporteContratoVenta()
   {
       return "ReporteContratoCompraVenta";
   }
   
   public String irAtrasReporte()
   {
       return "ListaReportesSistema";
   }
   
   
   
//    public void crearReporte2(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//             FacesContext context = FacesContext.getCurrentInstance();
//             HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//             InputStream reportStream = //context.getExternalContext().getResourceAsStream("../reportes/"+nombreReporte); 
//                                        //                  getClass().getResourceAsStream("../reportes/"+nombreReporte);
//             
//             this.getClass().getResourceAsStream("/reportes/"+nombreReporte);
//             
//             ServletOutputStream servletOutputStream = response.getOutputStream();
//
//            conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//            
//            byte[] bytes = null;
//            
//            bytes = JasperRunManager.runReportToPdf(reportStream, new HashMap(), conexion);
//            
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            
//            
//            servletOutputStream.write(bytes, 0, bytes.length);
//            servletOutputStream.flush();
//            servletOutputStream.close();
//
//            conexion.close();
//            
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//         }
//         
//    }
   
//    public void crearReporte2(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//                FacesContext context = FacesContext.getCurrentInstance();
//                HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
//                InputStream reportStream = //context.getExternalContext().getResourceAsStream("/reportes/"+nombreReporte);
//                this.getClass().getResourceAsStream("/reportes/"+nombreReporte);
//                
//                ServletOutputStream servletOutputStream = response.getOutputStream();
//
//                conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//                
//                JasperRunManager.runReportToPdfStream(reportStream ,servletOutputStream, parametros, conexion);
//                conexion.close();
//                response.setContentType("application/pdf");
//
//                servletOutputStream.flush();
//                servletOutputStream.close();
//            
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//         }
//         
//    }
//    

    private boolean validarNumerico(String cantRegistros) {
        
        boolean retorno=false;
        
        try {
            Integer.parseInt(cantRegistros);
            retorno=true;
        } catch (NumberFormatException nfe)
        {
            
            retorno=false;
        }
    
        return retorno;
    
    }

    /**
     * @return the nroContrato
     */
    public String getNroContrato() {
        return nroContrato;
    }

    /**
     * @param nroContrato the nroContrato to set
     */
    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    /**
     * @return the contratoVentaSessionBeans
     */
    public ContratoVenderSessionBean getContratoVentaSessionBeans() {
        return contratoVentaSessionBeans;
    }

    /**
     * @param contratoVentaSessionBeans the contratoVentaSessionBeans to set
     */
    public void setContratoVentaSessionBeans(ContratoVenderSessionBean contratoVentaSessionBeans) {
        this.contratoVentaSessionBeans = contratoVentaSessionBeans;
    }

    public boolean isPopupReporteCorrecto() {
        return popupReporteCorrecto;
    }

    public void setPopupReporteCorrecto(boolean popupReporteCorrecto) {
        this.popupReporteCorrecto = popupReporteCorrecto;
    }
    
    public String closePopupReporteCorrecto(){
//        popupReporteCorrecto = false;
        return "ListaReportesSistema";
    }
    
//TODO PARA ABAJO ESTABA EN LO QUE ME PASÓ DARIO
    
//   private String url = "jdbc:mysql://localhost/inmobiliariaDB";
//   private String admin="root";
//   private String pass="mumi20";
//   private String dirBaseReporte="d://Reportes//";
    
//   //el filtro puede ser por nombre o apellido
//   private String filtroClienteBuscado;
//   
//   //el filtro puede ser por nombre o apellido
//   private String inmuebleBuscado;
//  
//   // cant de registros que queremos ver
//   //private String cantRegistros;
//   
//    //nroContratoAlquier o compraventa
//   private String nroContrato;
   
   
//    private Date fechaInferior;
//    private Date fechaSuperior;
//   
//   @EJB(beanName="ClienteSessionBean")
//   private ClienteSessionBean clienteSessionBeans;
//   
//   @EJB(beanName="InmuebleSessionBean")
//   private InmuebleSessionBean inmuebleSessionBeans;
//   
//   @EJB(beanName="VisitaSessionBean")
//   private VisitaSessionBean visitaSessionBeans;
//   
//   @EJB(beanName="ContratoAlquilerSessionBean")
//   private ContratoAlquilerSessionBean contratoAlquilerSessionBeans;
//   
//    @EJB(beanName="ContratoVenderSessionBean")
//   private ContratoVenderSessionBean contratoVentaSessionBeans;
   
     
//   private Connection  conexion;
//    private String mensajeResultado;
   
    
//    /** Creates a new instance of ReportesManagedBean */
//    public ReportesManagedBean() 
//    {
//       
//    }

    /**
     * @return the cantRegistros
     */
//    public String getCantRegistros() {
//        return cantRegistros;
//    }
//
//    /**
//     * @param cantRegistros the cantRegistros to set
//     */
//    public void setCantRegistros(String cantRegistros) {
//        this.cantRegistros = cantRegistros;
//    }
    
//    public ClienteSessionBean getClienteSessionBeans() {
//        return clienteSessionBeans;
//    }
//   
//
//    public void setClienteSessionBeans(ClienteSessionBean clienteSessionBeans) {
//        this.clienteSessionBeans = clienteSessionBeans;
//    }
//    
//    public InmuebleSessionBean getInmuebleSessionBeans() {
//        return inmuebleSessionBeans;
//    }
//   
//    public void setInmuebleSessionBeans(InmuebleSessionBean inmuebleSessionBeans) {
//        this.inmuebleSessionBeans = inmuebleSessionBeans;
//    }
//    
//    public VisitaSessionBean getVsitaSessionBeans() {
//        return visitaSessionBeans;
//    }
//   
//    public void setVisitaSessionBeans(VisitaSessionBean visitaSessionBeans) {
//        this.visitaSessionBeans = visitaSessionBeans;
//    }
//    
//     public ContratoAlquilerSessionBean getContratoAlquilerSessionBeans() {
//        return contratoAlquilerSessionBeans;
//    }
//   
//    public void setContratoAlquilerSessionBeans(ContratoAlquilerSessionBean contratoAlquilerSessionBeans) {
//        this.contratoAlquilerSessionBeans = contratoAlquilerSessionBeans;
//    }
    
    
//    public Date getFechaInferior() {
//        return fechaInferior;
//    }
//
//    public void setFechaInferior(Date fechaInferior) {
//        this.fechaInferior = fechaInferior;
//    }
//
//    public Date getFechaSuperior() {
//        return fechaSuperior;
//    }
//
//    public void setFechaSuperior(Date fechaSuperior) {
//        this.fechaSuperior = fechaSuperior;
//    }
    
//     public void crearReporte(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//            conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//          
//            JasperReport reporte = (JasperReport) JRLoader.loadObject( getClass().getResourceAsStream("../reportes/"+nombreReporte));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
//         // Exporta el informe a PDF
//            String destFileNamePdf=nombrePDF;
//            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileNamePdf);
//
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//            mensajeResultado = "El reporte se guardó correctamente en " + nombrePDF;
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//            mensajeResultado = "Error: "+ex.toString();
//         }
//    
//    }
//    
//    public String getMensajeResultado(){
//        return mensajeResultado;
//    }
    
//    public void imprimirClienteConFiltro()    {
//          
//        HashMap map = new HashMap();
//        
//        String filtro = filtroClienteBuscado;
//        
////        String cantReg = cantRegistros;
//        
//        if(filtro!="")
//        {
//        
//            List<Cliente> listaClientes = getClienteSessionBeans().buscarClientes(filtro);
//
//             if(listaClientes!=null && !listaClientes.isEmpty())
//            {
////                if(validarNumerico(cantReg))
////                {
////                
////                    if(Integer.parseInt(cantReg)>0)
////                    {
//                        map.put("imgFondo",setearImagen("fondo"));
//                        map.put("imgCabecera",setearImagen("cabecera"));
//                        map.put("txtNombreReporte","REPORTE CLIENTES.");
//                    //    map.put("limiteSuperior",cantReg);
//                        map.put("valor",filtro);
//                        map.put("SUBREPORT_DIR",setearURLSubReporte());
//
//                        crearReporte(map,"reporteClientesConFiltro.jasper",dirBaseReporte+"ReporteClientes.pdf" );
//                        
//                        popupReporteCorrecto=true;
////                    }
////                    else
////                    {
////                        JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
////                    }
////                }
////                else
////                {
////                    JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");
////
////                }
//
//            }
//            else
//            {
//               JsfUtil.addErrorMessage("El cliente ingresado no existe.");
//            }
//        }
//        else
//        {
//        JsfUtil.addErrorMessage("Debe ingresar al menos un carácter.");
//        }
//        
//    }

    ///visitas a inmuebles
    
//     public void imprimirVisitasAInmueble(){
//          
//        HashMap map = new HashMap();
//        
//        String filtro = inmuebleBuscado;
//        
//        String cantReg = "6";
//                //cantRegistros;
//     
//        
//        //verifico que al menos exista una visita
//        Inmueble inmConVisita = getVsitaSessionBeans().validarInmuebleConVisitas(filtro);
//       
//        if(inmConVisita!=null)
//        {
//            if(validarNumerico(cantReg))
//            {
//
//                if(Integer.parseInt(cantReg)>0)
//                {
//                    map.put("imgFondo",setearImagen("fondo"));
//                    map.put("imgCabecera",setearImagen("cabecera"));
//                    map.put("txtNombreReporte","VISITAS A INMUEBLE");
//                    map.put("limiteSuperior",cantReg);
//                    map.put("cod_Inmueble",filtro);
//                    map.put("SUBREPORT_DIR",setearURLSubReporte());
//            
//                crearReporte(map,"reportesVisitasAInmueble.jasper",dirBaseReporte+"reportesVisitasAInmueble.pdf" );
//                
//                popupReporteCorrecto=true;
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
//                }
//            }
//            else
//            {
//                JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");
//
//            }
//            
//           
//        }
//        else
//        {
//            JsfUtil.addErrorMessage("El inmueble ingresado no posee visitas.");
//        }
//
//        
//    }
       
//   public void imprimirInfoCliente(){
//          
//        HashMap map = new HashMap();
//        
//        String filtro = filtroClienteBuscado;
//              
//       // String cantReg=cantRegistros; 
//      
//        //verifico que el cliente exista
//        List<Cliente> clientes = getClienteSessionBeans().buscarClientes(filtro);
//        
//        if(clientes!=null && !clientes.isEmpty())
//        {
//          Cliente cli = clientes.get(0);
//            //verifico que al menos exista una visita
//          Cliente cliente = getVsitaSessionBeans().validarClienteConVisitas(cli);
//          
//            if(cliente!=null)
//            {
////                if(validarNumerico(cantReg))
////                {
////                
////                    if(Integer.parseInt(cantReg)>0)
////                    {
//                        map.put("imgFondo",setearImagen("fondo"));
//                        map.put("imgCabecera",setearImagen("cabecera"));
//                        map.put("txtNombreReporte","VISITAS DEL CLIENTE");
//                   //     map.put("limiteSuperior",cantReg);
//                        map.put("nroCliente",cliente.getNroCliente());
//                        map.put("SUBREPORT_DIR",setearURLSubReporte());
//                      
//                        crearReporte(map,"ReporteInfoCliente.jasper",dirBaseReporte+"ReporteInfoCliente.pdf" );
//                        
//                        popupReporteCorrecto=true;
////                    }
////                    else
////                    {
////                        JsfUtil.addErrorMessage("La cantidad de registros debe ser mayor a uno.");
////                    }
////                }
////                else
////                {
////                    JsfUtil.addErrorMessage("La cantidad de registros debe ser numérico.");
////
////                }
////                
//                
//            }
//            else
//            {
//                JsfUtil.addErrorMessage("El cliente no ha realizado ninguna visita.");
//
//            }
//          
//        }
//        else
//        {
//            JsfUtil.addErrorMessage("El cliente ingresado no existe.");
//        
//        }
//         
//    }
   ///
   //visitasFiltras
//    public void imprimirVisitasFiltradas(){
//          
//        HashMap map = new HashMap();
//        
//     
//       // String limSup= cantRegistros;
//        Date fecInf = fechaInferior; 
//        Date fecSup = fechaSuperior; 
//        
//        if(fecInf!=null && fecSup!=null)
//        {
//            if(fecInf.before(fecSup))
//            {
////                if(cantRegistros!=null)
////                {
////                    if(validarNumerico(cantRegistros))
////                    {
//                     List<Visita> visitas = getVsitaSessionBeans().validarVisitasEntreFechas(fecInf,fecSup);
//
//                        if(visitas!=null && !visitas.isEmpty())
//                        {
//                            map.put("imgFondo",setearImagen("fondo"));
//                            map.put("imgCabecera",setearImagen("cabecera"));
//                            map.put("txtNombreReporte","VISITAS FILTRADAS");
//                     //       map.put("limiteSuperior",limSup);
//                            map.put("fechaInferior",fecInf);
//                            map.put("fechaSuperior",fecSup);
//
//                            crearReporte(map,"ReporteVisitasEntreFechas.jasper",dirBaseReporte+"ReporteVisitasEntreFechas.pdf" );
//                            
//                            popupReporteCorrecto=true;
//                        }
//                        else
//                        {
//                            JsfUtil.addErrorMessage("No existen visitas en el rango ingresado.");
//                        }
////                    }
////                    else
////                    {
////                        JsfUtil.addErrorMessage("Debe ingresar un valor numérico para la cantidad de registros.");
////                    }
////                 
////                }
////                else
////                {
////                    JsfUtil.addErrorMessage("Debe ingresar un valor numérico para la cantidad de registros.");
////                }   
//           }
//
//             else
//            {
//                JsfUtil.addErrorMessage("La fecha de inicio debe ser inferior a la actual.");
//            }
//        
//        }
//        else
//        {
//            JsfUtil.addErrorMessage("Debe ingresar una fecha válida.");
//        }
//        
//    }
    
   
//    public void imprimirContratoAlquiler(){
//          
//        HashMap map = new HashMap();
//        
//        String nroCo = nroContrato;
//        
//        
//        if(nroCo!=null)
//        {
//            if(validarNumerico(nroCo))
//            {
//                 List<ContratoAlquiler> contrato = getContratoAlquilerSessionBeans().validarExistenciaContratoAlquiler(Integer.parseInt(nroCo));
//   
//                if(contrato!=null && !contrato.isEmpty())
//                {
//                    map.put("imgFondo",setearImagen("fondo"));
//                    map.put("imgCabecera",setearImagen("cabecera"));
//                    map.put("txtNombreReporte","CONTRATO ALQUILER");
//                    map.put("nroContrato",nroCo);
//                    map.put("SUBREPORT_DIR",setearURLSubReporte());
//                    map.put("imgCodigoBarra",setearImagen("codigo"));
//                    
//                    
//                    crearReporte(map,"reporteCuotasInmueble.jasper",dirBaseReporte+"ReporteContratoAlquiler.pdf" );
//                    
//                    popupReporteCorrecto=true;
//                    
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("No existe el contrato de alquiler ingresado.");
//                    
//                }
//            }
//            else
//            {
//                JsfUtil.addErrorMessage("El número de contrato debe ser numérico.");
//            }   
//
//        }
//        else
//        {
//            JsfUtil.addErrorMessage("El número de contrato no debe estar vacío.");
//        }
//        
// 
//    }
    
//      public void imprimirContratoVenta(){
//          
//        HashMap map = new HashMap();
//        
//        String nroCo = nroContrato;
//        
//        
//        if(nroCo!=null)
//        {
//            if(validarNumerico(nroCo))
//            {
//                
//                List<ContratoCompraVenta> contrato = getContratoVentaSessionBeans().validarExistenciaContratoVenta(Integer.parseInt(nroCo));
//   
//                if(contrato!=null && !contrato.isEmpty())
//                {
//                    map.put("imgFondo",setearImagen("fondo"));
//                    map.put("imgCabecera",setearImagen("cabecera"));
//                    map.put("txtNombreReporte","CONTRATO COMPRA-VENTA");
//                    map.put("nroContrato",nroCo);
//                    map.put("SUBREPORT_DIR",setearURLSubReporte());
//                    
//                    
//                    crearReporte(map,"reporteVenta.jasper",dirBaseReporte+"ReporteContratoCompraVenta.pdf" );
//                    
//                    popupReporteCorrecto=true;
//                }
//                else
//                {
//                    JsfUtil.addErrorMessage("No existe el contrato de alquiler ingresado.");
//                }
//            }
//            else
//            {
//                JsfUtil.addErrorMessage("El número de contrato debe ser numérico.");
//            }   
//
//        }
//        else
//        {
//            JsfUtil.addErrorMessage("El número de contrato no debe estar vacío.");
//        }
//        
// 
//    }
    

//   private String setearImagenPorString(String lugar)
//   {
//         String retorno=null;
//         
//         if(lugar=="fondo")
//         {
//             retorno="C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\logoGris.png";
//         }
//         else
//         {
//             retorno="C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\cabecera.png";
//         }
//             
//         
//             return retorno;
//     }
     
//   private InputStream setearImagen(String lugar)
//   {
//         InputStream retorno=null;
//         
//         if(lugar=="fondo")
//         {
//             retorno=this.getClass().getResourceAsStream("/reportes/logoGris.png");
//         }
//         else if(lugar=="cabecera")
//         {
//             retorno=this.getClass().getResourceAsStream("/reportes/cabecera.png");
//         }
//         else
//         {
//            retorno=this.getClass().getResourceAsStream("/reportes/codigoBarras.png");
//         }
//             
//         
//        return retorno;
//     }
   
//   private String setearURLSubReporte()
//   {
//       
//       URL reportsDirPath = getClass().getResource("/reportes/");
//    
//       
//        return reportsDirPath.getPath();
//        
//                //.substring(1,reportsDirPath.getPath().length() );
//        
//        //por si no funka
//                //"C:\\NetBeansProjects\\inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria\\ar.edu.utn.frm.subversion.inmobiliaria-war\\src\\java\\reportes\\";
//   }

   /**
     * @return the filtroClienteBuscado
     */
//   public String getFiltroClienteBuscado() {
//        return filtroClienteBuscado;
//    }
//   
//   /**
//     * @param filtroClienteBuscado the filtroClienteBuscado to set
//     */
//   public void setFiltroClienteBuscado(String filtroClienteBuscado) {
//        this.filtroClienteBuscado = filtroClienteBuscado;
//    }
//   
//   /**
//     * @return the inmuebleBuscado
//     */
//   public String getInmuebleBuscado() {
//        return inmuebleBuscado;
//    }
//   
//   /**
//     * @param inmuebleBuscado the inmuebleBuscado to set
//     */
//   public void setInmuebleBuscado(String inmuebleBuscado) {
//        this.inmuebleBuscado = inmuebleBuscado;
//    }
//   
//   
//   
//   
//   public String irAClientesFiltro()
//   {
//       return "reporteClientesConFiltro";
//   }
//   
//   public String irAInfoCliente()
//   {
//       return "reporteInformacionCliente";
//   }
//   
//   public String irAVisitasEntreFechas()
//   {
//       return "ReporteVisitasEntreFechas";
//   }
//   
//   public String irAreporteVisitasAInmueble()
//   {
//       return "reporteVisitasAInmueble";
//   }
//   
//   public String irAreporteContratoAlquiler()
//   {
//       return "ReporteContratoAlquiler";
//   }
//   
//   public String irAReporteContratoVenta()
//   {
//       return "ReporteContratoCompraVenta";
//   }
//   
//   public String irAtrasReporte()
//   {
//       return "ListaReportesSistema";
//   }
   
   
   
//    public void crearReporte2(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//             FacesContext context = FacesContext.getCurrentInstance();
//             HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//             InputStream reportStream = //context.getExternalContext().getResourceAsStream("../reportes/"+nombreReporte); 
//                                        //                  getClass().getResourceAsStream("../reportes/"+nombreReporte);
//             
//             this.getClass().getResourceAsStream("/reportes/"+nombreReporte);
//             
//             ServletOutputStream servletOutputStream = response.getOutputStream();
//
//            conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//            
//            byte[] bytes = null;
//            
//            bytes = JasperRunManager.runReportToPdf(reportStream, new HashMap(), conexion);
//            
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            
//            
//            servletOutputStream.write(bytes, 0, bytes.length);
//            servletOutputStream.flush();
//            servletOutputStream.close();
//
//            conexion.close();
//            
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//         }
//         
//    }
   
//    public void crearReporte2(HashMap parametros,String nombreReporte,String nombrePDF)
//    {
//         try
//         {
//                FacesContext context = FacesContext.getCurrentInstance();
//                HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
//                InputStream reportStream = //context.getExternalContext().getResourceAsStream("/reportes/"+nombreReporte);
//                this.getClass().getResourceAsStream("/reportes/"+nombreReporte);
//                
//                ServletOutputStream servletOutputStream = response.getOutputStream();
//
//                conexion = (Connection) DriverManager.getConnection(url, admin, pass);
//                
//                JasperRunManager.runReportToPdfStream(reportStream ,servletOutputStream, parametros, conexion);
//                conexion.close();
//                response.setContentType("application/pdf");
//
//                servletOutputStream.flush();
//                servletOutputStream.close();
//            
//            JsfUtil.addSuccessMessage("El reporte se guardó correctamente en " + nombrePDF );
//          
//         } catch (Exception ex) 
//         {
//            JsfUtil.addErrorMessage(ex,"Error al imprimir");
//         }
//         
//    }
//    

//    private boolean validarNumerico(String cantRegistros) {
//        
//        boolean retorno=false;
//        
//        try {
//            Integer.parseInt(cantRegistros);
//            retorno=true;
//        } catch (NumberFormatException nfe)
//        {
//            
//            retorno=false;
//        }
//    
//        return retorno;
//    
//    }
//
//    /**
//     * @return the nroContrato
//     */
//    public String getNroContrato() {
//        return nroContrato;
//    }
//
//    /**
//     * @param nroContrato the nroContrato to set
//     */
//    public void setNroContrato(String nroContrato) {
//        this.nroContrato = nroContrato;
//    }
//
//    /**
//     * @return the contratoVentaSessionBeans
//     */
//    public ContratoVenderSessionBean getContratoVentaSessionBeans() {
//        return contratoVentaSessionBeans;
//    }
//
//    /**
//     * @param contratoVentaSessionBeans the contratoVentaSessionBeans to set
//     */
//    public void setContratoVentaSessionBeans(ContratoVenderSessionBean contratoVentaSessionBeans) {
//        this.contratoVentaSessionBeans = contratoVentaSessionBeans;
//    }
//
//    public boolean isPopupReporteCorrecto() {
//        return popupReporteCorrecto;
//    }
//
//    public void setPopupReporteCorrecto(boolean popupReporteCorrecto) {
//        this.popupReporteCorrecto = popupReporteCorrecto;
//    }
//    
//    public String closePopupReporteCorrecto(){
//        popupReporteCorrecto = false;
//        return "ListaReportesSistema";
//    }
    
}
