package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static String encriptarMD5(String code) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = code.getBytes(); //"UTF8");
            input = md.digest(input);
            code = toHexadecimal(input); //new String(input,"UTF8");

            return code;
        } catch (Exception e) {

            return code;
        }

    }

    public static int diasEntreFechas(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static boolean esFechaValida(Date d1, int cantidadDias) {
        boolean result = false;
        Date d2 = new Date();
        if (d1.compareTo(d2) <= 0) {
            if (diasEntreFechas(d1, d2) <= cantidadDias) {
                result = true;
            }
        }
        return result;
    }

    private static String toHexadecimal(byte[] datos) {
        String resultado = "";
        ByteArrayInputStream input = new ByteArrayInputStream(datos);
        String cadAux;
        boolean ult0 = false;
        int leido = input.read();
        while (leido != -1) {
            cadAux = Integer.toHexString(leido);
            if (cadAux.length() < 2) { //Hay que aÒadir un 0
                resultado += "0";
                if (cadAux.length() == 0) {
                    ult0 = true;
                }
            } else {
                ult0 = false;
            }
            resultado += cadAux;
            leido = input.read();
        }
        if (ult0)//quitamos el 0 si es un caracter aislado
        {
            resultado = resultado.substring(0, resultado.length() - 2) + resultado.charAt(resultado.length() - 1);
        }
        return resultado;
    }

    @Deprecated
    public static Object getManagedBean(String nombre) {
        ELContext contexto = FacesContext.getCurrentInstance().getELContext();
        return FacesContext.getCurrentInstance().getApplication().
                getExpressionFactory().createValueExpression(contexto, "#{" + nombre + "}", Object.class).getValue(contexto);
    }

    public static Object obtenerManejador(String nombre) {
        return FacesContext.getCurrentInstance().getApplication().
                getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, nombre);
    }

    public static void redirigirWeb(String webDestino) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ar.edu.utn.frm.subversion.inmobiliaria-war/faces/" + webDestino.trim());
        } catch (IOException ex) {
            System.out.println("--> Error al redirigir pagina: " + ex.toString());
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una fecha a partir de la fecha del argumente sumada la cantidad de meses enviados 
     * por argumento. El argumento cantMeses puede ser negativo apra restar meses
     * @param fecha
     * @param cantMeses
     * @return 
     */
    public static Date sumarMesesAFecha(Date fecha, int cantMeses) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, cantMeses);
        return cal.getTime();
    }

    public static Date setearDiaADate(int nroDia, Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), nroDia-1);
        return cal.getTime();
    }
    
    public static Date restarDiasAFecha(Date fecha, int cantMeses) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, -cantMeses);
        return cal.getTime();
    }
    
    
}