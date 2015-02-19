package controladores;

import entidades.AnalisisCrediticio;
import entidades.Asignacion;
import entidades.Caracteristica;
import entidades.Cliente;
import entidades.Contacto;
import entidades.Departamento;
import entidades.DetalleCaracteristica;
import entidades.DetalleServicio;
import entidades.Direccion;
import entidades.Empleado;
import entidades.EstadoAnalisisCrediticio;
import entidades.EstadoInmueble;
import entidades.EstadoVisita;
import entidades.HistoricoEstadoInmueble;
import entidades.Inmueble;
import entidades.Localidad;
import entidades.Menu;
import entidades.Perfil;
import entidades.Permiso;
import entidades.Provincia;
import entidades.Servicio;
import entidades.TipoInmueble;
import entidades.Trabajo;
import entidades.Usuario;
import entidades.Visita;
import expertos.AnalisisCrediticioSessionBean;
import expertos.AsignacionSessionBean;
import expertos.CaracteristicaSessionBean;
import expertos.ClienteSessionBean;
import expertos.ContactoSessionBean;
import expertos.DireccionSessionBean;
import expertos.EmpleadoSessionBean;
import expertos.EstadoAnalisisCredSessionBean;
import expertos.EstadoInmuebleSessionBean;
import expertos.EstadoVisitaSessionBean;
import expertos.InmuebleSessionBean;
import expertos.MenuSessionBean;
import expertos.PerfilSessionBean;
import expertos.PermisoSessionBean;
import expertos.ProvinciaSessionBean;
import expertos.ServicioSessionBean;
import expertos.TipoInmuebleSessionBean;
import expertos.UsuarioSessionBean;
import expertos.VisitaSessionBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@RequestScoped
public class CargadorEjemplosManagedBean {

    @EJB 
    private ProvinciaSessionBean provinciaSessionBean;
    @EJB
    private DireccionSessionBean direccionSessionBean;
    @EJB
    private ClienteSessionBean clienteSessionBean;
    @EJB
    private CaracteristicaSessionBean caracteristicaSessionBean;
    @EJB
    private ContactoSessionBean contactoSessionBean;
    @EJB
    private EmpleadoSessionBean empleadoSessionBean;
    @EJB
    private EstadoInmuebleSessionBean estadoInmuebleSessionBean;
    @EJB
    private ServicioSessionBean servicioSessionBean;
    @EJB
    private TipoInmuebleSessionBean tipoInmuebleSessionBean;
    @EJB
    private EstadoVisitaSessionBean estadoVisitaSessionBean;
    @EJB
    private EstadoAnalisisCredSessionBean estadoAnalisisCredSessionBean;
    @EJB
    private InmuebleSessionBean inmuebleSessionBean;
    @EJB
    private VisitaSessionBean visitaSessionBean;
    @EJB
    private AnalisisCrediticioSessionBean analisisCrediticioSessionBean;
    @EJB
    private MenuSessionBean menuSessionBean;
    @EJB
    private AsignacionSessionBean asignacionSessionBean;
    @EJB
    private PermisoSessionBean permisoSessionBean;
    @EJB
    private PerfilSessionBean perfilSessionBean;
    @EJB
    private UsuarioSessionBean usuarioSessionBean;

    @PostConstruct
    public void cargarTodosLosDatos() {
        cargarMenu();
        cargarEjemplosProvinciaDepartamentoLocalidad();
        cargarEjemplosDirecciones();
        cargarEjemplosClientes();
        cargarEjemplosCaracteristicas();
        cargarEjemplosServicios();
        cargarEjemplosContactos();
        cargarEjemplosEmpleadosUsuarios();
        cargarEjemplosEstadosInmueble();
        cargarEjemplosTipoInmueble();
        cargarEjemplosEstadosVisita();
        cargarEjemplosEstadoAnalisisCrediticio();
        cargarEjemplosInmuebles();
        cargarEjemplosVisitas();
        cargarEjemplosAnalisisCrediticio();
        cargarEjemplosPerfil();
        cargarEjemplosPermiso();
        cargarEjemplosAsignaciones();
    }

    private void cargarEjemplosProvinciaDepartamentoLocalidad() {
        if (provinciaSessionBean.count() < 1) {
            Provincia provincia;
            Localidad localidad;
            Departamento departamento;

            List<Localidad> localidades1 = new ArrayList<Localidad>();
            List<Localidad> localidades2 = new ArrayList<Localidad>();
            List<Localidad> localidades3 = new ArrayList<Localidad>();
            List<Localidad> localidades4 = new ArrayList<Localidad>();

            List<Departamento> departamentos = new ArrayList<Departamento>();

//-- valores por defecto
            provincia = new Provincia();
            provincia.setIdProvincia("-1");
            provincia.setNombreProvincia("- seleccionar -");


            departamento = new Departamento();
            departamento.setIdDepartamento("-1");
            departamento.setNombreDepartamento("- seleccionar -");


            localidad = new Localidad();
            localidad.setIdLocalidad("-1");
            localidad.setNombreLocalidad("- seleccionar -");

            localidades1.add(localidad);
            departamento.setLocalidades(localidades1);
            departamentos.add(departamento);
            provincia.setDepartamentos(departamentos);
            provinciaSessionBean.save(provincia);

//-- Para la provincia Mendoza            
            localidades1.clear();
            departamentos.clear();

            provincia = new Provincia();
            provincia.setIdProvincia("1");
            provincia.setNombreProvincia("Mendoza");


//--            
            localidad = new Localidad();
            localidad.setIdLocalidad("1");
            localidad.setNombreLocalidad("1°- Parque Central");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("2");
            localidad.setNombreLocalidad("2°- Barrio Cívico");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("3");
            localidad.setNombreLocalidad("3°- Parque O´Higgins");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("4");
            localidad.setNombreLocalidad("4°- Área Fundacional");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("5");
            localidad.setNombreLocalidad("5°- Residencial Sur");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("6");
            localidad.setNombreLocalidad("6°- Residencial Norte");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("7");
            localidad.setNombreLocalidad("7°- Residencial Parque");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("8");
            localidad.setNombreLocalidad("8°- Aeroparque");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("9");
            localidad.setNombreLocalidad("9°- Parque General San Martín");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("10");
            localidad.setNombreLocalidad("10°- Residencial Los Cerros");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("11");
            localidad.setNombreLocalidad("11°- San Agustín");
            localidades1.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("12");
            localidad.setNombreLocalidad("12°- Piedemonte");
            localidades1.add(localidad);

            departamento = new Departamento();
            departamento.setIdDepartamento("1");
            departamento.setNombreDepartamento("Capital");

            departamento.setLocalidades(localidades1);

            departamentos.add(departamento);
//--

            localidad = new Localidad();
            localidad.setIdLocalidad("20");
            localidad.setNombreLocalidad("Alvear Oeste");
            localidades2.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("21");
            localidad.setNombreLocalidad("Bowen");
            localidades2.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("22");
            localidad.setNombreLocalidad("General Alvear");
            localidades2.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("23");
            localidad.setNombreLocalidad("San Pedro del Atuel");
            localidades2.add(localidad);

            departamento = new Departamento();
            departamento.setIdDepartamento("2");
            departamento.setNombreDepartamento("General Alvear");

            departamento.setLocalidades(localidades2);

            departamentos.add(departamento);
//--            

            localidad = new Localidad();
            localidad.setIdLocalidad("30");
            localidad.setNombreLocalidad("Gobernador Benegas");
            localidades3.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("31");
            localidad.setNombreLocalidad("Godoy Cruz");
            localidades3.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("32");
            localidad.setNombreLocalidad("Las Tortugas");
            localidades3.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("33");
            localidad.setNombreLocalidad("Presidente Sarmiento");
            localidades3.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("34");
            localidad.setNombreLocalidad("San Francisco del Monte");
            localidades3.add(localidad);

            departamento = new Departamento();
            departamento.setIdDepartamento("3");
            departamento.setNombreDepartamento("Godoy Cruz");

            departamento.setLocalidades(localidades3);

            departamentos.add(departamento);
//--

            localidad = new Localidad();
            localidad.setIdLocalidad("40");
            localidad.setNombreLocalidad("Belgrano");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("41");
            localidad.setNombreLocalidad("El Bermejo");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("42");
            localidad.setNombreLocalidad("Buena Nueva");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("43");
            localidad.setNombreLocalidad("Capilla del Rosario");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("44");
            localidad.setNombreLocalidad("Colonia Segovia");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("45");
            localidad.setNombreLocalidad("Dorrego");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("46");
            localidad.setNombreLocalidad("El Sauce");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("47");
            localidad.setNombreLocalidad("Jesús Nazareno");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("48");
            localidad.setNombreLocalidad("Kilómetro 8");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("49");
            localidad.setNombreLocalidad("Kilómetro 11");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("50");
            localidad.setNombreLocalidad("La Primavera");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("51");
            localidad.setNombreLocalidad("Las Cañas");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("52");
            localidad.setNombreLocalidad("Los Corralitos");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("53");
            localidad.setNombreLocalidad("Nueva Ciudad");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("54");
            localidad.setNombreLocalidad("Pedro Molina");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("55");
            localidad.setNombreLocalidad("Puente de Hierro");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("56");
            localidad.setNombreLocalidad("Rodeo de la Cruz");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("57");
            localidad.setNombreLocalidad("San Francisco del Monte");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("58");
            localidad.setNombreLocalidad("San José");
            localidades4.add(localidad);

            localidad = new Localidad();
            localidad.setIdLocalidad("59");
            localidad.setNombreLocalidad("Villa Nueva");
            localidades4.add(localidad);

            departamento = new Departamento();
            departamento.setIdDepartamento("4");
            departamento.setNombreDepartamento("Guaymallén");

            departamento.setLocalidades(localidades4);

            departamentos.add(departamento);

//--            
            departamento = new Departamento();
            departamento.setIdDepartamento("5");
            departamento.setNombreDepartamento("Junín");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("6");
            departamento.setNombreDepartamento("La Paz");

            departamentos.add(departamento);

//--              

            departamento = new Departamento();
            departamento.setIdDepartamento("7");
            departamento.setNombreDepartamento("Las Heras");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("8");
            departamento.setNombreDepartamento("Lavalle");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("9");
            departamento.setNombreDepartamento("Luján de Cuyo");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("10");
            departamento.setNombreDepartamento("Maipú");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("11");
            departamento.setNombreDepartamento("Malargüe");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("12");
            departamento.setNombreDepartamento("Rivadavia");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("13");
            departamento.setNombreDepartamento("San Carlos");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("14");
            departamento.setNombreDepartamento("San Martín");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("15");
            departamento.setNombreDepartamento("San Rafael");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("16");
            departamento.setNombreDepartamento("Santa Rosa");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("17");
            departamento.setNombreDepartamento("Tunuyán");

            departamentos.add(departamento);

//--  

            departamento = new Departamento();
            departamento.setIdDepartamento("18");
            departamento.setNombreDepartamento("Tupungato");

            departamentos.add(departamento);

//--  

            provincia.setDepartamentos(departamentos);
            provinciaSessionBean.save(provincia);

//-- fin provincia Mendoza


//-- Para la provincia San Juan            
            localidades1.clear();
            localidades2.clear();
            localidades3.clear();
            localidades4.clear();

            departamentos.clear();

            provincia = new Provincia();
            provincia.setIdProvincia("2");
            provincia.setNombreProvincia("San Juan");
            provinciaSessionBean.save(provincia);

//-- fin provincia San Juan

        }
    }

    private void cargarEjemplosDirecciones() {

        Direccion direccion;
        Provincia provincia;

        if (direccionSessionBean.count() < 1) {

            provincia = provinciaSessionBean.find("1");

            direccion = new Direccion();
            direccion.setIdDireccion("1");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(1));
            direccion.setNombreCalle("San Martín");
            direccion.setNumero("124");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("2");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(4));
            direccion.setNombreCalle("San Luis");
            direccion.setNumero("31");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("3");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(10));
            direccion.setNombreCalle("Rivadavia");
            direccion.setNumero("518");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("4");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(9));
            direccion.setNombreCalle("25 de Mayo");
            direccion.setNumero("666");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("5");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(6));
            direccion.setNombreCalle("Gral Mariani");
            direccion.setNumero("69");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("6");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(2));
            direccion.setLocalidad(provincia.getDepartamentos().get(2).getLocalidades().get(1));
            direccion.setNombreCalle("Colon");
            direccion.setNumero("1540");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("7");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(2));
            direccion.setLocalidad(provincia.getDepartamentos().get(2).getLocalidades().get(0));
            direccion.setNombreCalle("Mitre");
            direccion.setNumero("14");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("8");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(2));
            direccion.setLocalidad(provincia.getDepartamentos().get(2).getLocalidades().get(3));
            direccion.setNombreCalle("Palermo");
            direccion.setNumero("857");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("9");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(10));
            direccion.setNombreCalle("Jofre");
            direccion.setNumero("254");
            direccionSessionBean.save(direccion);

            direccion = new Direccion();
            direccion.setIdDireccion("10");
            direccion.setProvincia(provincia);
            direccion.setDepartamento(provincia.getDepartamentos().get(0));
            direccion.setLocalidad(provincia.getDepartamentos().get(0).getLocalidades().get(6));
            direccion.setNombreCalle("Lavalle");
            direccion.setNumero("784");
            direccionSessionBean.save(direccion);

        }
    }

    private void cargarEjemplosClientes() {
        Cliente cliente;
        Direccion direccion;
        if (clienteSessionBean.count() < 1) {

            cliente = new Cliente();
            direccion = direccionSessionBean.find("1");
            cliente.setIdCliente("1");
            cliente.setNroCliente("5800");
            cliente.setNombre("Juan");
            cliente.setApellido("Martinez");
            cliente.setDni("26589467");
            cliente.setCuil("20-26589467-7");
            cliente.setEmail("jmartinez@gmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -12));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -1));
            cliente.setTelefono("2615874526");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("2");
            cliente.setIdCliente("2");
            cliente.setNroCliente("5801");
            cliente.setNombre("Francisco");
            cliente.setApellido("Aguirre");
            cliente.setDni("20589467");
            cliente.setCuil("20-20589467-7");
            cliente.setEmail("fraguirre@gmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -10));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -2));
            cliente.setTelefono("2615254726");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("3");
            cliente.setIdCliente("3");
            cliente.setNroCliente("5802");
            cliente.setNombre("Julio");
            cliente.setApellido("Alarcon");
            cliente.setDni("15849467");
            cliente.setCuil("20-15849467-7");
            cliente.setEmail("jualarcon@hotmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -11));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -3));
            cliente.setTelefono("2614444526");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("4");
            cliente.setIdCliente("4");
            cliente.setNroCliente("5803");
            cliente.setNombre("Antonio");
            cliente.setApellido("Sanchez");
            cliente.setDni("26969467");
            cliente.setCuil("20-26969467-7");
            cliente.setEmail("asanchez02@gmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -9));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -1));
            cliente.setTelefono("2614374526");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("5");
            cliente.setIdCliente("5");
            cliente.setNroCliente("5804");
            cliente.setNombre("Lautaro");
            cliente.setApellido("Acosta");
            cliente.setDni("30589467");
            cliente.setCuil("20-30589467-7");
            cliente.setEmail("laacosta@gmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -8));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -1));
            cliente.setTelefono("2615874533");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("6");
            cliente.setIdCliente("6");
            cliente.setNroCliente("5805");
            cliente.setNombre("Ignacio");
            cliente.setApellido("Canolo");
            cliente.setDni("28289467");
            cliente.setCuil("20-28289467-7");
            cliente.setEmail("canolo40@ymail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -8));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -2));
            cliente.setTelefono("2615871111");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

            cliente = new Cliente();
            direccion = direccionSessionBean.find("7");
            cliente.setIdCliente("7");
            cliente.setNroCliente("5806");
            cliente.setNombre("Geronimo");
            cliente.setApellido("Tornelo");
            cliente.setDni("27789467");
            cliente.setCuil("20-27789467-7");
            cliente.setEmail("gtornelo@gmail.com");
            cliente.setFechaAlta(JsfUtil.sumarMesesAFecha(new Date(), -7));
            cliente.setFechaUltimaModificacion(JsfUtil.sumarMesesAFecha(new Date(), -1));
            cliente.setTelefono("2613873322");
            cliente.setTipo("Física");
            cliente.setDireccion(direccion);
            clienteSessionBean.save(cliente);

        }
    }

    private void cargarEjemplosCaracteristicas() {

        Caracteristica caracteristica;
        if (caracteristicaSessionBean.count() < 1) {
            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("1");
            caracteristica.setNombre("dormitorio grande");
            caracteristica.setDescripcion("de 3x4m2");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("2");
            caracteristica.setNombre("dormitorio mediano");
            caracteristica.setDescripcion("de 3x3m2");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("3");
            caracteristica.setNombre("dormitorio chico");
            caracteristica.setDescripcion("de 3x2m2");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("4");
            caracteristica.setNombre("living");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("5");
            caracteristica.setNombre("living-comedor");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("6");
            caracteristica.setNombre("comedor");
            caracteristica.setDescripcion("con bajo y sobre mesada");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("7");
            caracteristica.setNombre("terraza");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("8");
            caracteristica.setNombre("baño con bañera");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("9");
            caracteristica.setNombre("baño con ducha");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("10");
            caracteristica.setNombre("patio parquizado");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("11");
            caracteristica.setNombre("patio embaldosado");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);

            caracteristica = new Caracteristica();
            caracteristica.setIdCaracteristica("12");
            caracteristica.setNombre("pileta");
            caracteristica.setDescripcion("");
            caracteristicaSessionBean.save(caracteristica);
        }
    }

    private void cargarEjemplosContactos() {
        if (contactoSessionBean.count() < 1) {
            Contacto ejemploContacto;
            ejemploContacto = new Contacto();
            ejemploContacto.setNombre("Martin");
            ejemploContacto.setApellido("Palero");
            ejemploContacto.setCuil("20-22543456-7");
            ejemploContacto.setDni("22543456");
            ejemploContacto.setEmail("mpalero@yahoo.com");
            ejemploContacto.setFechaAlta(new Date());
            ejemploContacto.setTelefono("425345531");
            contactoSessionBean.save(ejemploContacto);

            ejemploContacto = new Contacto();
            ejemploContacto.setNombre("Juan Pedro");
            ejemploContacto.setApellido("Perez");
            ejemploContacto.setCuil("20-31525153-2");
            ejemploContacto.setDni("31525153");
            ejemploContacto.setEmail("jpperez@hotmail.com");
            ejemploContacto.setFechaAlta(new Date());
            ejemploContacto.setTelefono("4203344");
            contactoSessionBean.save(ejemploContacto);
        }
    }

    private void cargarEjemplosEmpleadosUsuarios() {
        Empleado ejemploEmpleado;
        Usuario usuario;
        if (empleadoSessionBean.count() < 1) {

            List<Direccion> direcciones = new ArrayList<Direccion>();


            direcciones = direccionSessionBean.findAll();

            ejemploEmpleado = new Empleado();
            ejemploEmpleado.setIdEmpleado("1");
            ejemploEmpleado.setNombre("Dario");
            ejemploEmpleado.setApellido("Maclin");
            ejemploEmpleado.setCuil("20-30555345-4");
            ejemploEmpleado.setDireccion(direcciones.get(0));
            ejemploEmpleado.setDni("30555345");
            ejemploEmpleado.setEmail("dmaclin@gmail.com");
            ejemploEmpleado.setFechaAlta(new Date());
            ejemploEmpleado.setTelefono("4323333");

            usuario = new Usuario();
            usuario.setIdUsuario("1");
            usuario.setNombre("Dario");
            usuario.setApellido("Maclin");
            usuario.setEmail("dmaclin@gmail.com");
            usuario.setNombreUsuario("dmaclin");
            usuario.setContrasenia("8a49317e060e23bb86f9225ca581e0a9");
            ejemploEmpleado.setUsuario(usuario);
            empleadoSessionBean.save(ejemploEmpleado);

            //--

            ejemploEmpleado = new Empleado();
            ejemploEmpleado.setIdEmpleado("2");
            ejemploEmpleado.setNombre("Mario");
            ejemploEmpleado.setApellido("Mariani");
            ejemploEmpleado.setCuil("20-30566345-3");
            ejemploEmpleado.setDireccion(direcciones.get(1));
            ejemploEmpleado.setDni("30566345");
            ejemploEmpleado.setEmail("mmariani@gmail.com");
            ejemploEmpleado.setFechaAlta(new Date());
            ejemploEmpleado.setTelefono("4223235");

            usuario = new Usuario();
            usuario.setIdUsuario("2");
            usuario.setNombre("Mario");
            usuario.setApellido("Mariani");
            usuario.setEmail("mmariani@gmail.com");
            usuario.setNombreUsuario("mmariani");
            usuario.setContrasenia("de2f15d014d40b93578d255e6221fd60");
            ejemploEmpleado.setUsuario(usuario);
            empleadoSessionBean.save(ejemploEmpleado);

//--

            ejemploEmpleado = new Empleado();
            ejemploEmpleado.setIdEmpleado("3");
            ejemploEmpleado.setNombre("Mariana");
            ejemploEmpleado.setApellido("Penesi");
            ejemploEmpleado.setCuil("20-55556345-3");
            ejemploEmpleado.setDireccion(direcciones.get(0));
            ejemploEmpleado.setDni("55556345");
            ejemploEmpleado.setEmail("mpenesi@gmail.com");
            ejemploEmpleado.setFechaAlta(new Date());
            ejemploEmpleado.setTelefono("4834425");

            usuario = new Usuario();
            usuario.setIdUsuario("3");
            usuario.setNombre("Mariana");
            usuario.setApellido("Penesi");
            usuario.setEmail("mpenesi@gmail.com");
            usuario.setNombreUsuario("mpenesi");
            usuario.setContrasenia("e60408e9a55027070e3caf0550d2b4df");
            ejemploEmpleado.setUsuario(usuario);
            empleadoSessionBean.save(ejemploEmpleado);
        }
    }

    private void cargarEjemplosEstadosInmueble() {

        EstadoInmueble estadoInmueble;
        if (estadoInmuebleSessionBean.count() < 1) {
            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setIdEstadoInmueble("-1");
            estadoInmueble.setEstado("- seleccionar -");
            estadoInmuebleSessionBean.save(estadoInmueble);

            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setIdEstadoInmueble("1");
            estadoInmueble.setEstado("En venta");
            estadoInmuebleSessionBean.save(estadoInmueble);

            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setIdEstadoInmueble("2");
            estadoInmueble.setEstado("Vendido");
            estadoInmuebleSessionBean.save(estadoInmueble);

            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setIdEstadoInmueble("3");
            estadoInmueble.setEstado("En alquiler");
            estadoInmuebleSessionBean.save(estadoInmueble);

            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setIdEstadoInmueble("4");
            estadoInmueble.setEstado("Alquilado");
            estadoInmuebleSessionBean.save(estadoInmueble);
        }
    }

    private void cargarEjemplosServicios() {

        Servicio servicio;
        if (servicioSessionBean.count() < 1) {
            servicio = new Servicio();
            servicio.setIdServicio("1");
            servicio.setNombre("agua corriente");
            servicio.setDescripcion("");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("2");
            servicio.setNombre("agua de perforación");
            servicio.setDescripcion("");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("3");
            servicio.setNombre("luz monofásica");
            servicio.setDescripcion("");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("4");
            servicio.setNombre("gas");
            servicio.setDescripcion("gas natural");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("5");
            servicio.setNombre("internet");
            servicio.setDescripcion("servicio de banda ancha");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("6");
            servicio.setNombre("cloacas");
            servicio.setDescripcion("");
            servicioSessionBean.save(servicio);

            servicio = new Servicio();
            servicio.setIdServicio("7");
            servicio.setNombre("luz trifásica");
            servicio.setDescripcion("");
            servicioSessionBean.save(servicio);
        }
    }

    private void cargarEjemplosTipoInmueble() {
        TipoInmueble tipoInmueble;

        if (tipoInmuebleSessionBean.count() < 1) {

            tipoInmueble = new TipoInmueble();
            tipoInmueble.setIdTipoInmueble("-1");
            tipoInmueble.setNombreTipo("- seleccionar -");
            tipoInmuebleSessionBean.save(tipoInmueble);

            tipoInmueble = new TipoInmueble();
            tipoInmueble.setIdTipoInmueble("1");
            tipoInmueble.setNombreTipo("casa");
            tipoInmuebleSessionBean.save(tipoInmueble);

            tipoInmueble = new TipoInmueble();
            tipoInmueble.setIdTipoInmueble("2");
            tipoInmueble.setNombreTipo("departamento");
            tipoInmuebleSessionBean.save(tipoInmueble);

            tipoInmueble = new TipoInmueble();
            tipoInmueble.setIdTipoInmueble("3");
            tipoInmueble.setNombreTipo("galpon");
            tipoInmuebleSessionBean.save(tipoInmueble);
        }
    }

    private void cargarEjemplosEstadosVisita() {
        EstadoVisita estadoVisita;
        if (estadoVisitaSessionBean.count() < 1) {

            estadoVisita = new EstadoVisita();
            estadoVisita.setIdEstadoVisita("1");
            estadoVisita.setEstado("Pendiente");
            estadoVisitaSessionBean.save(estadoVisita);

            estadoVisita = new EstadoVisita();
            estadoVisita.setIdEstadoVisita("2");
            estadoVisita.setEstado("Cancelada");
            estadoVisitaSessionBean.save(estadoVisita);

            estadoVisita = new EstadoVisita();
            estadoVisita.setIdEstadoVisita("3");
            estadoVisita.setEstado("Realizada");
            estadoVisitaSessionBean.save(estadoVisita);
        }
    }

    private void cargarEjemplosEstadoAnalisisCrediticio() {
        EstadoAnalisisCrediticio estado;
        if (estadoAnalisisCredSessionBean.count() < 1) {

            estado = new EstadoAnalisisCrediticio();
            estado.setIdEstadoAnalisisCrediticio("-1");
            estado.setNombre("- seleccionar -");
            estadoAnalisisCredSessionBean.save(estado);

            estado = new EstadoAnalisisCrediticio();
            estado.setIdEstadoAnalisisCrediticio("1");
            estado.setNombre("Aprobado");
            estadoAnalisisCredSessionBean.save(estado);

            estado = new EstadoAnalisisCrediticio();
            estado.setIdEstadoAnalisisCrediticio("2");
            estado.setNombre("Desaprobado");
            estadoAnalisisCredSessionBean.save(estado);
        }
    }

    private void cargarEjemplosInmuebles() {
        Inmueble inmueble;
        Direccion direccion;
        Caracteristica caracteristica;
        HistoricoEstadoInmueble hitoricoEstado;
        List<HistoricoEstadoInmueble> historicos = new ArrayList<HistoricoEstadoInmueble>();
        Servicio servicio;
        List<DetalleCaracteristica> detallesCaracteristicas = new ArrayList<DetalleCaracteristica>();
        List<DetalleServicio> detallesServicios = new ArrayList<DetalleServicio>();
        Cliente cliente1;
        Cliente cliente2;
        List<Cliente> propietarios = new ArrayList<Cliente>();
        DetalleCaracteristica detalleCar;
        DetalleServicio detalleSer;
        Date fecha;

        if (inmuebleSessionBean.count() < 1) {

            inmueble = new Inmueble();
            propietarios.clear();
            historicos.clear();
            detallesCaracteristicas.clear();
            detallesServicios.clear();
            direccion = direccionSessionBean.find("8");
            cliente1 = clienteSessionBean.find("1");
            cliente2 = clienteSessionBean.find("2");
            propietarios.add(cliente1);
            propietarios.add(cliente2);
            inmueble.setIdInmueble("1");
            inmueble.setCodInmueble("2300");
            inmueble.setAnioConstruccion(1998);
            fecha = generarDateRandomPosterior();
            inmueble.setFechaAlta(fecha);
            inmueble.setNroPlantas(1);
            inmueble.setPropietarios(propietarios);
            inmueble.setImporteMensualidad(1000);
            inmueble.setSuperficieTotal("500");
            inmueble.setSuperficieCubierta("200");
            inmueble.setPrecioPesos("330000");
            inmueble.setDireccion(direccion);
            inmueble.setTipoInmueble(tipoInmuebleSessionBean.find("1"));

            hitoricoEstado = new HistoricoEstadoInmueble();
            hitoricoEstado.setFechaIncio(fecha);
            hitoricoEstado.setEstadoInmueble(estadoInmuebleSessionBean.find("3"));
            historicos.add(hitoricoEstado);
            inmueble.setHistoricosEstadosInmuebles(historicos);


            caracteristica = caracteristicaSessionBean.find("2");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(2);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("4");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(3);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("9");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            inmueble.setDetallesCaracteristicas(detallesCaracteristicas);

            servicio = servicioSessionBean.find("1");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("3");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("4");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            inmueble.setDetallesServicios(detallesServicios);

            inmuebleSessionBean.save(inmueble);

//--

            inmueble = new Inmueble();
            propietarios.clear();
            historicos.clear();
            detallesCaracteristicas.clear();
            detallesServicios.clear();
            direccion = direccionSessionBean.find("9");
            cliente1 = clienteSessionBean.find("5");
            cliente2 = clienteSessionBean.find("6");
            propietarios.add(cliente1);
            propietarios.add(cliente2);
            inmueble.setIdInmueble("2");
            inmueble.setCodInmueble("2301");
            inmueble.setAnioConstruccion(1995);
            fecha = generarDateRandomPosterior();
            inmueble.setFechaAlta(fecha);
            inmueble.setNroPlantas(2);
            inmueble.setPropietarios(propietarios);
            inmueble.setImporteMensualidad(1200);
            inmueble.setSuperficieTotal("550");
            inmueble.setSuperficieCubierta("220");
            inmueble.setPrecioPesos("420000");
            inmueble.setDireccion(direccion);
            inmueble.setTipoInmueble(tipoInmuebleSessionBean.find("1"));

            hitoricoEstado = new HistoricoEstadoInmueble();
            hitoricoEstado.setFechaIncio(fecha);
            hitoricoEstado.setEstadoInmueble(estadoInmuebleSessionBean.find("3"));
            historicos.add(hitoricoEstado);
            inmueble.setHistoricosEstadosInmuebles(historicos);

            caracteristica = caracteristicaSessionBean.find("1");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(2);
            detalleCar.setPuntuacion(3);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("3");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(3);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("5");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("8");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(2);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            inmueble.setDetallesCaracteristicas(detallesCaracteristicas);

            servicio = servicioSessionBean.find("1");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("3");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("4");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("6");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            inmueble.setDetallesServicios(detallesServicios);

            inmuebleSessionBean.save(inmueble);

//--

            inmueble = new Inmueble();
            propietarios.clear();
            historicos.clear();
            detallesCaracteristicas.clear();
            detallesServicios.clear();
            direccion = direccionSessionBean.find("10");
            cliente1 = clienteSessionBean.find("6");
            cliente2 = clienteSessionBean.find("7");
            propietarios.add(cliente1);
            propietarios.add(cliente2);
            inmueble.setIdInmueble("3");
            inmueble.setCodInmueble("2302");
            inmueble.setAnioConstruccion(2002);
            fecha = generarDateRandomPosterior();
            inmueble.setFechaAlta(fecha);
            inmueble.setNroPlantas(1);
            inmueble.setPropietarios(propietarios);
            inmueble.setImporteMensualidad(1000);
            inmueble.setSuperficieTotal("700");
            inmueble.setSuperficieCubierta("250");
            inmueble.setPrecioPesos("330000");
            inmueble.setDireccion(direccion);
            inmueble.setTipoInmueble(tipoInmuebleSessionBean.find("1"));

            hitoricoEstado = new HistoricoEstadoInmueble();
            hitoricoEstado.setFechaIncio(fecha);
            hitoricoEstado.setEstadoInmueble(estadoInmuebleSessionBean.find("3"));
            historicos.add(hitoricoEstado);
            inmueble.setHistoricosEstadosInmuebles(historicos);


            caracteristica = caracteristicaSessionBean.find("2");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(2);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("3");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("4");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(3);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("6");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("9");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("8");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(4);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            inmueble.setDetallesCaracteristicas(detallesCaracteristicas);

            servicio = servicioSessionBean.find("1");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("3");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("4");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("5");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("6");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            inmueble.setDetallesServicios(detallesServicios);

            inmuebleSessionBean.save(inmueble);

//--

            inmueble = new Inmueble();
            propietarios.clear();
            historicos.clear();
            detallesCaracteristicas.clear();
            detallesServicios.clear();
            direccion = direccionSessionBean.find("6");
            cliente1 = clienteSessionBean.find("4");
            cliente2 = clienteSessionBean.find("3");
            propietarios.add(cliente1);
            propietarios.add(cliente2);
            inmueble.setIdInmueble("4");
            inmueble.setCodInmueble("2303");
            inmueble.setAnioConstruccion(2000);
            fecha = generarDateRandomPosterior();
            inmueble.setFechaAlta(fecha);
            inmueble.setNroPlantas(1);
            inmueble.setPropietarios(propietarios);
            inmueble.setImporteMensualidad(900);
            inmueble.setSuperficieTotal("200");
            inmueble.setSuperficieCubierta("120");
            inmueble.setPrecioPesos("210000");
            inmueble.setDireccion(direccion);
            inmueble.setTipoInmueble(tipoInmuebleSessionBean.find("1"));

            hitoricoEstado = new HistoricoEstadoInmueble();
            hitoricoEstado.setFechaIncio(fecha);
            hitoricoEstado.setEstadoInmueble(estadoInmuebleSessionBean.find("3"));
            historicos.add(hitoricoEstado);
            inmueble.setHistoricosEstadosInmuebles(historicos);


            caracteristica = caracteristicaSessionBean.find("2");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(2);
            detalleCar.setPuntuacion(5);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("10");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(5);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("5");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(5);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            caracteristica = caracteristicaSessionBean.find("8");
            detalleCar = new DetalleCaracteristica();
            detalleCar.setCaracteristica(caracteristica);
            detalleCar.setCantidad(1);
            detalleCar.setPuntuacion(5);
            detalleCar.setPublicar(true);
            detallesCaracteristicas.add(detalleCar);

            inmueble.setDetallesCaracteristicas(detallesCaracteristicas);

            servicio = servicioSessionBean.find("1");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("3");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("4");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("5");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            servicio = servicioSessionBean.find("6");
            detalleSer = new DetalleServicio();
            detalleSer.setServicio(servicio);
            detalleSer.setPublicar(true);
            detallesServicios.add(detalleSer);

            inmueble.setDetallesServicios(detallesServicios);

            inmuebleSessionBean.save(inmueble);

        }

    }

    private void cargarEjemplosVisitas() {
        Visita visita;
        if (visitaSessionBean.count() < 1) {

            visita = new Visita();
            visita.setIdVisita("1");
            visita.setNroVisita("456");
            visita.setCliente(clienteSessionBean.find("4"));
            visita.setDuracion(30);
            visita.setEmpleado(empleadoSessionBean.find("1"));
            visita.setFecha(JsfUtil.setearDiaADate(1, JsfUtil.sumarMesesAFecha(new Date(), 1)));
            visita.setEstadoVisita(estadoVisitaSessionBean.find("1"));
            visita.setInmueble(inmuebleSessionBean.find("1"));
            visita.setHora("16:15");
            visitaSessionBean.save(visita);

            visita = new Visita();
            visita.setIdVisita("2");
            visita.setNroVisita("458");
            visita.setCliente(clienteSessionBean.find("5"));
            visita.setDuracion(45);
            visita.setEmpleado(empleadoSessionBean.find("1"));
            visita.setFecha(JsfUtil.setearDiaADate(2, JsfUtil.sumarMesesAFecha(new Date(), 1)));
            visita.setEstadoVisita(estadoVisitaSessionBean.find("1"));
            visita.setInmueble(inmuebleSessionBean.find("2"));
            visita.setHora("18:30");
            visitaSessionBean.save(visita);

//            visita = new Visita();
//            visita.setIdVisita("3");
//            visita.setNroVisita("461");
//            visita.setCliente(clienteSessionBean.find("3"));
//            visita.setDuracion(30);
//            visita.setEmpleado(empleadoSessionBean.find("1"));
//            visita.setFecha(JsfUtil.setearDiaADate(3, JsfUtil.sumarMesesAFecha(new Date(), 1)));
//            visita.setEstadoVisita(estadoVisitaSessionBean.find("1"));
//            visita.setInmueble(inmuebleSessionBean.find("1"));
//            visitaSessionBean.save(visita);
//
//            visita = new Visita();
//            visita.setIdVisita("4");
//            visita.setNroVisita("465");
//            visita.setCliente(clienteSessionBean.find("2"));
//            visita.setDuracion(30);
//            visita.setEmpleado(empleadoSessionBean.find("1"));
//            visita.setFecha(JsfUtil.setearDiaADate(4, JsfUtil.sumarMesesAFecha(new Date(), 1)));
//            visita.setEstadoVisita(estadoVisitaSessionBean.find("1"));
//            visita.setInmueble(inmuebleSessionBean.find("3"));
//            visitaSessionBean.save(visita);
        }
    }

    private void cargarEjemplosAnalisisCrediticio() {
        AnalisisCrediticio analisisCrediticio;
        Trabajo trabajo1;
        Trabajo trabajo2;
        List<Trabajo> trabajos = new ArrayList<Trabajo>();

        if (analisisCrediticioSessionBean.count() < 1) {

            trabajos.clear();
            analisisCrediticio = new AnalisisCrediticio();
            analisisCrediticio.setIdAnalisisCrediticio("1");
            analisisCrediticio.setCliente(clienteSessionBean.find("1"));
            analisisCrediticio.setDeudaCodeme(250);
            analisisCrediticio.setDeudaVeraz(0);
            analisisCrediticio.setEstadoAnalisisCrediticio(estadoAnalisisCredSessionBean.find("1"));
            analisisCrediticio.setFechaCreacion(JsfUtil.restarDiasAFecha(new Date(), 10));
            trabajo1 = new Trabajo();
            trabajo1.setEmpleador("Montemar");
            trabajo1.setCuit("30-54981428-3");
            trabajo1.setPuestoLaboral("Encargado salon");
            trabajo1.setSueldo(5800);
            trabajo1.setFechaIngreso(JsfUtil.sumarMesesAFecha(new Date(), -40));
            trabajos.add(trabajo1);
            analisisCrediticio.setTrabajos(trabajos);
            analisisCrediticioSessionBean.save(analisisCrediticio);

            trabajos.clear();
            analisisCrediticio = new AnalisisCrediticio();
            analisisCrediticio.setIdAnalisisCrediticio("2");
            analisisCrediticio.setCliente(clienteSessionBean.find("2"));
            analisisCrediticio.setDeudaCodeme(360);
            analisisCrediticio.setDeudaVeraz(500);
            analisisCrediticio.setEstadoAnalisisCrediticio(estadoAnalisisCredSessionBean.find("1"));
            analisisCrediticio.setFechaCreacion(JsfUtil.restarDiasAFecha(new Date(), 5));
            trabajo1 = new Trabajo();
            trabajo1.setEmpleador("Gráfica Gandhi");
            trabajo1.setCuit("30-25748159-3");
            trabajo1.setPuestoLaboral("Diseñador Gráfico");
            trabajo1.setSueldo(5300);
            trabajo1.setFechaIngreso(JsfUtil.sumarMesesAFecha(new Date(), -24));
            trabajos.add(trabajo1);
            analisisCrediticio.setTrabajos(trabajos);
            analisisCrediticioSessionBean.save(analisisCrediticio);

            trabajos.clear();
            analisisCrediticio = new AnalisisCrediticio();
            analisisCrediticio.setIdAnalisisCrediticio("3");
            analisisCrediticio.setCliente(clienteSessionBean.find("3"));
            analisisCrediticio.setDeudaCodeme(0);
            analisisCrediticio.setDeudaVeraz(0);
            analisisCrediticio.setEstadoAnalisisCrediticio(estadoAnalisisCredSessionBean.find("1"));
            analisisCrediticio.setFechaCreacion(JsfUtil.restarDiasAFecha(new Date(), 8));
            trabajo1 = new Trabajo();
            trabajo1.setEmpleador("Correo Argentino");
            trabajo1.setCuit("30-65982828-3");
            trabajo1.setPuestoLaboral("Despacho");
            trabajo1.setSueldo(3800);
            trabajo1.setFechaIngreso(JsfUtil.sumarMesesAFecha(new Date(), -21));
            trabajos.add(trabajo1);
            trabajo2 = new Trabajo();
            trabajo2.setEmpleador("Gregoris");
            trabajo2.setCuit("30-56849551-3");
            trabajo2.setPuestoLaboral("Vendedor");
            trabajo2.setSueldo(3200);
            trabajo2.setFechaIngreso(JsfUtil.sumarMesesAFecha(new Date(), -10));
            trabajos.add(trabajo2);
            analisisCrediticio.setTrabajos(trabajos);
            analisisCrediticioSessionBean.save(analisisCrediticio);

        }
    }

    private void cargarMenu() {
        Menu menu;
        Menu menuPadre;
        Menu menuAbuelo;
        if (menuSessionBean.count() < 1) {

            menu = new Menu();
            menu.setNroMenu(1);
            menu.setIdMenu("1");
            menu.setNombre("Clientes");
            menu.setMenu_padre(null);
            menu.setVinculo("");
            menuSessionBean.save(menu);
            menuPadre = menu;

            menu = new Menu();
            menu.setNroMenu(2);
            menu.setIdMenu("2");
            menu.setNombre("Administrar Clientes");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../cliente/Create.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(3);
            menu.setIdMenu("3");
            menu.setNombre("Analisis Crediticio");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../analisisCrediticio/Create.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(5);
            menu.setIdMenu("5");
            menu.setNombre("Comercialización");
            menu.setMenu_padre(null);
            menu.setVinculo("");
            menuPadre = menu;
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(6);
            menu.setIdMenu("6");
            menu.setNombre("Alquileres");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../comercializacion/IngresarCliente.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(7);
            menu.setIdMenu("7");
            menu.setNombre("Ventas");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../ventas/IngresarCliente.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(8);
            menu.setIdMenu("8");
            menu.setNombre("Inmuebles");
            menu.setMenu_padre(null);
            menu.setVinculo("");
            menuPadre = menu;
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(9);
            menu.setIdMenu("9");
            menu.setNombre("Administrar Visitas");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../visita/List.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(21);
            menu.setIdMenu("21");
            menu.setNombre("Administrar Inmuebles");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../inmueble/inmuebleCrearEditar.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(23);
            menu.setIdMenu("23");
            menu.setNombre("Listar Inmuebles");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../inmueble/List.xhtml");
            menuSessionBean.save(menu);            
            
            menu = new Menu(); 
            menu.setNroMenu(24);
            menu.setIdMenu("24");
            menu.setNombre("Detalles de Inmuebles");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("");
            menuPadre = menu;
            menuSessionBean.save(menu);
            
            menu = new Menu(); 
            menu.setNroMenu(11);
            menu.setIdMenu("11");
            menu.setNombre("Caracteristicas");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../caracteristica/Create.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(10);
            menu.setIdMenu("10");
            menu.setNombre("Administrar Detalles");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../configuracion/configuracion.xhtml");
            menuSessionBean.save(menu);
            
            menu = new Menu(); 
            menu.setNroMenu(12);
            menu.setIdMenu("12");
            menu.setNombre("Servicios");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../servicio/Create.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(13);
            menu.setIdMenu("13");
            menu.setNombre("Seguridad");
            menu.setMenu_padre(null);
            menu.setVinculo("");
            menuSessionBean.save(menu);
            menuAbuelo = menu;

            menu = new Menu();
            menu.setNroMenu(14);
            menu.setIdMenu("14");
            menu.setNombre("Administrar Usuario");
            menu.setMenu_padre(menuAbuelo);
            menu.setVinculo("");
            menuSessionBean.save(menu);
            menuPadre = menu;

            menu = new Menu();
            menu.setNroMenu(15);
            menu.setIdMenu("15");
            menu.setNombre("Alta de Usuario");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../usuario/crearUsuario.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(16);
            menu.setIdMenu("16");
            menu.setNombre("Modificar Usuario");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../usuario/modificarUsuarios.xhtml");
            menuSessionBean.save(menu);

            menu = new Menu();
            menu.setNroMenu(17);
            menu.setIdMenu("17");
            menu.setNombre("Perfiles");
            menu.setMenu_padre(menuAbuelo);
            menu.setVinculo("");
            menuSessionBean.save(menu);
            menuPadre = menu;

            menu = new Menu();
            menu.setNroMenu(18);
            menu.setIdMenu("18");
            menu.setNombre("Administrar Perfil");
            menu.setMenu_padre(menuPadre);
            menu.setVinculo("../usuario/administrarPerfil.xhtml");
            menuSessionBean.save(menu);
                      
            menu = new Menu(); 
            menu.setNroMenu(25);
            menu.setIdMenu("25");
            menu.setNombre("Reportes");
            menu.setMenu_padre(null);
            menu.setVinculo("../reportes/ListaReportesSistema.xhtml");
            menuSessionBean.save(menu);

        }

    }
    
    private void cargarPerfiles() {
//        Perfil perfil = new Perfil();
//        perfil.setIdPerfil("1");
//        perfil.setDescripcion("Administrador del Sistema");
//        perfil.setEstado(true);
//        perfil.setNombre("Administrador");
//        perfilSessionBean.save(perfil);
//        
//        perfil.setIdPerfil("2");
//        perfil.setDescripcion("Perfil usuarios Comercial");
//        perfil.setEstado(true);
//        perfil.setNombre("Comercial");
//        perfilSessionBean.save(perfil);
//        
//        perfil.setIdPerfil("3");
//        perfil.setDescripcion("Perfil usuarios Administración");
//        perfil.setEstado(true);
//        perfil.setNombre("Administracion");
//        perfilSessionBean.save(perfil);
    }
    
    private void cargarPermisos() {
//        Permiso permiso = new Permiso();
//        int i = 1;
//        for (Menu menu : menuSessionBean.findAll()) {
//            permiso.setIdPermiso(Integer.toString(i++));
//            permiso.setAccion(null);
//            permiso.setMenu(menu);
//            permiso.setPerfil(perfilSessionBean.buscarPorId("1"));
//            permisoSessionBean.save(permiso);
//        }
    }
    
    /**
     * Genera un fecha posterior al día actual
     * @return 
     */
    private Date generarDateRandomPosterior() {
        Calendar cal = new GregorianCalendar(generarNroRandomEntre(2009, 2010), generarNroRandomEntre(1, 12), generarNroRandomEntre(1, 28));
        return cal.getTime();
    }

    private int generarNroRandomEntre(int nroMenor, int nroMayor) {
        return (int) Math.floor(Math.random() * (nroMayor - nroMenor + 1) + nroMenor);
    }

    private void cargarEjemplosPerfil() {
        Perfil perfil;

        if (perfilSessionBean.count() < 1) {

            perfil = new Perfil();
            perfil.setIdPerfil("1");
            perfil.setDescripcion("Administrador del Sistema");
            perfil.setEstado(true);
            perfil.setNombre("Administrador");
            perfilSessionBean.save(perfil);

            perfil = new Perfil();
            perfil.setIdPerfil("2");
            perfil.setDescripcion("Usuario del Área Comercial");
            perfil.setEstado(true);
            perfil.setNombre("Comercial");
            perfilSessionBean.save(perfil);

            perfil = new Perfil();
            perfil.setIdPerfil("3");
            perfil.setDescripcion("Usuario de Administración y Finanzas");
            perfil.setEstado(true);
            perfil.setNombre("Administracion");
            perfilSessionBean.save(perfil);

        }
    }

    private void cargarEjemplosPermiso() {
        Permiso permiso;

        if (permisoSessionBean.count() < 1) {

            permiso = new Permiso();
            permiso.setIdPermiso("1");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("1"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("10");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("12"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("11");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("12"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("12");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("13"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("13");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("14"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("14");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("15"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("15");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("16"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("16");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("17"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("17");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("18"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("18");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("20"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("19");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("21"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("2");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("2"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("20");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("23"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("21");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("24"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("22");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("25"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("23");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("5"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("24");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("6"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("25");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("7"));
            permiso.setPerfil(perfilSessionBean.find("2"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("26");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("25"));
            permiso.setPerfil(perfilSessionBean.find("2"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("27");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("1"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("28");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("2"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("29");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("3"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("3");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("3"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("30");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("8"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("31");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("9"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("32");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("21"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("33");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("23"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("34");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("24"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("35");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("10"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("36");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("11"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("37");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("12"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);


            permiso = new Permiso();
            permiso.setIdPermiso("38");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("25"));
            permiso.setPerfil(perfilSessionBean.find("3"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("4");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("5"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("5");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("6"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("6");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("7"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("7");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("8"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("8");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("9"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);

            permiso = new Permiso();
            permiso.setIdPermiso("9");
            permiso.setAccion(null);
            permiso.setMenu(menuSessionBean.find("10"));
            permiso.setPerfil(perfilSessionBean.find("1"));
            permisoSessionBean.save(permiso);
        }
    }

    private void cargarEjemplosAsignaciones() {

        Asignacion asignacion;

        if (asignacionSessionBean.count() < 1) {

            asignacion = new Asignacion();
            asignacion.setId("1");
            asignacion.setActivo(true);
            asignacion.setFechaDesde(JsfUtil.sumarMesesAFecha(new Date(), -10));
            asignacion.setFechaHasta(JsfUtil.sumarMesesAFecha(new Date(), 2));
            asignacion.setPerfil(perfilSessionBean.find("1"));
            asignacion.setUsuario(usuarioSessionBean.find("1"));
            asignacionSessionBean.save(asignacion);

            asignacion = new Asignacion();
            asignacion.setId("2");
            asignacion.setActivo(true);
            asignacion.setFechaDesde(JsfUtil.sumarMesesAFecha(new Date(), -10));
            asignacion.setFechaHasta(JsfUtil.sumarMesesAFecha(new Date(), 2));
            asignacion.setPerfil(perfilSessionBean.find("2"));
            asignacion.setUsuario(usuarioSessionBean.find("3"));
            asignacionSessionBean.save(asignacion);

            asignacion = new Asignacion();
            asignacion.setId("3");
            asignacion.setActivo(true);
            asignacion.setFechaDesde(JsfUtil.sumarMesesAFecha(new Date(), -10));
            asignacion.setFechaHasta(JsfUtil.sumarMesesAFecha(new Date(), 2));
            asignacion.setPerfil(perfilSessionBean.find("3"));
            asignacion.setUsuario(usuarioSessionBean.find("2"));
            asignacionSessionBean.save(asignacion);
        }
    }
}
