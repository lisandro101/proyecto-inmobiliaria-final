/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Departamento;
import entidades.Localidad;
import entidades.Provincia;
import expertos.DepartamentoSessionBean;
import expertos.LocalidadSessionBean;
import expertos.ProvinciaSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author mariodante
 */
@ManagedBean (name="provinciaMB")
@SessionScoped
public class ProvinciaManagedBean implements Serializable{

    @EJB
    private ProvinciaSessionBean provinciaSessionBean;
//    @EJB
//    private LocalidadSessionBean localidadSessionBean;
//    @EJB
//    private DepartamentoSessionBean departamentoSessionBean;
//    
//    @ManagedProperty("#{departamentoMB}")
//    private DepartamentoManagedBean departamentoManagedBean;
//    
//    @ManagedProperty("#{localidadMB}")
//    private LocalidadManagedBean localidadManagedBean;
       
    private Provincia provincia;
    
    private List<Provincia> provincias;
    private int indiceProvincias;
    
    
    private List<Departamento> departamentos;
    private int indiceDepartamentos;
    
    private List<Localidad> localidades;
    private int indiceLocalidades;
    
    
    /** Creates a new instance of ProvinciaManagedBean */
    public ProvinciaManagedBean() {
    
    }
    
    
    public ProvinciaSessionBean getEjbSessionProvincia() {
        return getProvinciaSessionBean();
    }

    
    public void setEjbSessionProvincia(ProvinciaSessionBean ejbSessionProvincia) {
        this.setProvinciaSessionBean(ejbSessionProvincia);
    }
    
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia pro) {
        this.provincia = pro;
    }
    
    
//    
//    public int getIndiceProvincia() {
//        Object id = FacesContext.getCurrentInstance().getExternalContext()
//                .getSessionMap().get("idProvincia");
//        if (id != null){
//            indiceProvincias = Integer.parseInt(id.toString());
//            return indiceProvincias;
//        }else
//            return 0;
//    }
//    
//    
    
//
    
//    public void setIndiceProvincia  (int indice) {
//        this.indiceProvincias = indice;
//    }
    
//     private int obtenerIdProvincia() {
//        int id = Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idProvincia")); 
//        return id;
//    }
     
     
     
     
      public List<SelectItem> buscarNombresProvincias(){
        
        List<SelectItem> nombres = new ArrayList<SelectItem>();

        if(getProvincia()==null)
        {
        nombres.add(new SelectItem(0,"Seleccione una provincia..."));
        provincias =  getEjbSessionProvincia().buscarProvincias();
        for (Provincia prov : provincias) {
            nombres.add(new SelectItem(Integer.parseInt(prov.getIdProvincia()), 
                    prov.getNombreProvincia()));
            }
          
        }
        
        
        return nombres;
    }
      
//    public void cambiarProvincia(ValueChangeEvent event) {
//       
//        indiceProvincias =  Integer.parseInt(event.getNewValue().toString());
//        
////        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
////                        .put("idProvincia",indiceProvincias);
//        
//    }
    
    public void cambiarDepartamento(ValueChangeEvent event) {
       
        indiceDepartamentos =  Integer.parseInt(event.getNewValue().toString());
        
    }
    
//   public int getIndiceDepartamento() {
//        Object id = FacesContext.getCurrentInstance().getExternalContext()
//                .getSessionMap().get("idDepartamento");
//        if (id != null){
//            indiceDepartamentos = Integer.parseInt(id.toString());
//            return indiceDepartamentos;
//        }else
//            return 0;
//    }
    
    
    public List<SelectItem> getDepartamentos() {
        List<SelectItem> nombresDepartamentos = new ArrayList<SelectItem>();

        int id =indiceProvincias;
        
        
//        if (indiceProvincias != 0) 
//        {
            for (Provincia pro : provincias) {
                
                int idPro= Integer.parseInt(pro.getIdProvincia());
                
                if( indiceProvincias==idPro)
                {
                    nombresDepartamentos.add(new SelectItem(0,"Seleccione un departamento..."));

                    for (Departamento deptos : pro.getDepartamentos()) {
                        nombresDepartamentos.add(new SelectItem(Integer.parseInt(deptos.getIdDepartamento()), 
                                                                deptos.getNombreDepartamento()));
                    }
                
                 departamentos= pro.getDepartamentos();
                    setProvincia(pro);
                }
                
            }
            
//        }
//        else{
//                nombresDepartamentos.add(new SelectItem(0,"Seleccione un departamento..."));
//        }
        return nombresDepartamentos;
 }
    
    public List<SelectItem> getLocalidades() {
        List<SelectItem> nombresLocalidades = new ArrayList<SelectItem>();

        if (indiceDepartamentos != 0) 
        {
            for (Departamento depto : departamentos) {
                
                int idDepto = Integer.parseInt(depto.getIdDepartamento());
                
                if(indiceDepartamentos==idDepto)
                {
                    nombresLocalidades.add(new SelectItem(0,"Seleccione una localidad..."));
                    
                    for (Localidad local : depto.getLocalidades()) {
                         nombresLocalidades.add(new SelectItem(Integer.parseInt(local.getIdLocalidad()), 
                                                                local.getNombreLocalidad()));
                    }
                }
            }
        }
        else
        {
          nombresLocalidades.add(new SelectItem(0,"Seleccione una localidad..."));
        }
        return nombresLocalidades;
 }
    
    public void setIndiceDepartamento (int indice) {
        this.indiceDepartamentos = indice;
    }

       
    
//   public List<SelectItem> buscarNombresDepartamentos(){
//        
//       return cambiarDepartamentos(indiceProvincias);
//       
//    }
   
//   private List<SelectItem> cambiarDepartamentos(int indiceProvincias) {
//        
//       
//       List<SelectItem> nombres = new ArrayList<SelectItem>();
//        
//        departamentos = getEjbSessionProvincia().buscarDepartamentosProvincia(indiceProvincias);
//        
//        nombres.add(new SelectItem(0,"Seleccione un departamento..."));
//
//        for (Departamento deptos : departamentos) {
//           
//            nombres.add(new SelectItem(Integer.parseInt(deptos.getIdDepartamento()), 
//                    deptos.getNombreDepartamento()));
//       }
//        
//        return nombres;
//    }
    

    public ProvinciaSessionBean getProvinciaSessionBean() {
        return provinciaSessionBean;
    }

    public void setProvinciaSessionBean(ProvinciaSessionBean provinciaSessionBean) {
        this.provinciaSessionBean = provinciaSessionBean;
    }


  
}
