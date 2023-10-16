package py.com.evaluacion.ws.registrousuarios.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DateUtils {
    public static final String DATE_STORE = "yyyyMMdd";
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_VIEW = "dd/MM/yyyy";
    public static final String DATE_TIME_MOVILE_STORE = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_MOVILE_STORE = "dd/MM/yyyy";

    public static String getFechaFormateada(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            return null;
        }
    }

    public static Date getDateFromString(String fecha, String format) {
        if (fecha == null || format == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static String getParse(String fecha, String format) {
        if (fecha == null || format == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        String aux = "";
        try {
            date = sdf.parse(fecha);
            aux = sdf.format(date);

        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    public static String getFechaFormateada(Date fecha, String format) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(fecha);
        } else {
            return null;
        }
    }

    public static String getHoraFormateada(Date dateTime) {
        if (dateTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(dateTime);
        } else {
            return null;
        }
    }

    public static String getDiaAnterior(String fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            try {
                Date fechaActual = sdf.parse(fecha);
                Calendar calendar = restarUnDia(fechaActual);
                return sdf.format(calendar.getTime());
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getDiaAnteriorAsyyyyMMdd(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (fecha != null && !fecha.isEmpty()) {

            try {
                Date fechaActual = sdf.parse(fecha);
                Calendar calendar = restarUnDia(fechaActual);
                return sdf.format(calendar.getTime());
            } catch (Exception ex) {
                return null;
            }
        } else {
//            Calendar calendar = restarUnDia(new Date());
//            return sdf.format(calendar.getTime());
            //Cuando se instala recien tiene que descargar todos los saldos
            return sdf.format(getOrigenTiempo());
        }
    }

    private static Calendar restarUnDia(Date fechaActual) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }

    private static Date getOrigenTiempo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        return calendar.getTime();
    }

    public static Date convertirStringADate(String fechaString) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(fechaString);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    public static Date convertirStringADate(String fechaString, String formato) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(fechaString);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
}
