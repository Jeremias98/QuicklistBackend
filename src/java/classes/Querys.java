/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/**
 *
 * @author Jeremías
 */
public class Querys extends MasterDatabase {
    
    private Integer NUM_DEC = 2;
   
    // Retorna una lista con el nombre de los grupos que administra una cuenta
    public ArrayList<String> getNombreGruposQueAdministra(int cuenta_id) throws SQLException {
        
        ArrayList<String> list = new ArrayList<String>();
        
        ResultSet rs = super.consultar("SELECT * FROM CUENTA_GRUPO");
        
        while(rs.next())
        {
            if (cuenta_id == rs.getInt("CUENTA_ID")) {
                list.add(getGrupoById(rs.getInt("GRUPO_ID")));
            }
        }
        
        return list;
        
    }
    
    // Retorna una lista con el id de los grupos que administra una cuenta
    public ArrayList<Integer> getIdGruposQueAdministra(int cuenta_id) throws SQLException {
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        ResultSet rs = super.consultar("SELECT * FROM CUENTA_GRUPO");
        
        while(rs.next())
        {
            if (cuenta_id == rs.getInt("CUENTA_ID")) {
                list.add(rs.getInt("GRUPO_ID"));
            }
        }
        
        return list;
        
    }
    
    // Retorna una lista con el id de los grupos que administra una cuenta
    public ArrayList<Integer> getIdGrupos() throws SQLException {
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO");
        
        while(rs.next())
        {
            list.add(rs.getInt("GRUPO_ID"));
        }
        
        return list;
        
    }
    
    // Retorna el nombre del grupo por la ID dada
    private String getGrupoById(int grupo_id) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO WHERE GRUPO.GRUPO_ID='"+grupo_id+"'");
        
        String list = null;
        
        while(rs.next())
        {
            list = rs.getString("NOMBRE");
        }
        
        return list;
        
    }
    
    // Método para loggearse, verifica si la cuenta existe
    public boolean isAcountExists(String usuario, String contraseña) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT *, AES_DECRYPT(CONTRASEÑA, 'xcabczxabccz4815162342') FROM CUENTA WHERE USUARIO='"+usuario+"' AND AES_DECRYPT(CONTRASEÑA, 'xcabczxabccz4815162342')='"+contraseña+"'");
        
        boolean log = false;
        
        if (rs.next())
        {
            log = true;
        }
        
        return log;
        
    }
    
    // Método para obtener el ID de cuenta
    public int getIdCuenta(String usuario, String contraseña) throws SQLException {
        
        
        ResultSet rs = super.consultar("SELECT *, AES_DECRYPT(CONTRASEÑA, 'xcabczxabccz4815162342') FROM CUENTA WHERE USUARIO='"+usuario+"' AND AES_DECRYPT(CONTRASEÑA, 'xcabczxabccz4815162342')='"+contraseña+"'");
        
        int id = 0;
        
        if(rs.next())
        {
            id = rs.getInt("CUENTA_ID");
        }
       
        return id;
        
    }
     
    // Método para obtener favoritos
    public String getFavoritos(String usuario) {
        return null;
    }
    
    // Retorna un array con las posibles nacionalidades
    public ArrayList<String> getNacionalidad() throws SQLException {
        
        String sql = "SELECT * FROM PAIS";
        PreparedStatement ps = CONN.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<String> list = new ArrayList<String>();
        while(rs.next())
        {
            list.add(rs.getString("NOMBRE"));
        }
        return list;
    }
    
    // Retorna un array con los grupos NO creados por un usuario
    public ArrayList<String> getCursosIniciales() throws SQLException {
        
        ArrayList<String> list = new ArrayList<String>();
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO WHERE FLAG='0'");
        
        while(rs.next())
        {
            list.add(rs.getString("NOMBRE"));
        }
        
        return list;
    }
    
    // Retorna la lista de todos los cursos iniciales por ID
    public ArrayList<Integer> getCursosInicialesID() throws SQLException {
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO WHERE FLAG='0'");
        
        while (rs.next()) {
            
            list.add(rs.getInt("GRUPO_ID"));
            
        }
        
        return list;
        
    }
    
    // Retorna un array con el nombre de todos los grupos creados
    public ArrayList<String> getNombreDeCurso() throws SQLException {
        
        String sql = "SELECT * FROM GRUPO";
        PreparedStatement ps = CONN.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<String> list = new ArrayList<String>();
        
        while(rs.next())
        {
            list.add(rs.getString("NOMBRE"));
        }
        
        return list;
    }
    
    // Retorna un array con el nombre de los alumnos segun el curso
    public ArrayList<String> getNombreAlumnosByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("APELLIDO") + " " +rs.getString("NOMBRE"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el id de los alumnos segun el curso
    public ArrayList<Integer> getIdAlumnosByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while (rs.next()) {
            list.add(rs.getInt("ALUMNO_ID"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el id de los alumnos segun el curso en la tabla de asistencias
    public ArrayList<Integer> getIdAlumnosByGrupoAsistencia(Integer id_grupo, String fecha) throws SQLException {
        
        
        ResultSet rs = super.consultar("SELECT ASISTENCIA.ALUMNO_ID FROM ASISTENCIA INNER JOIN ALUMNO ON ALUMNO.ALUMNO_ID = ASISTENCIA.ALUMNO_ID WHERE ASISTENCIA.GRUPO_ID = '"+id_grupo+"' AND ASISTENCIA.FECHA = '"+fecha+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while (rs.next()) {
            list.add(rs.getInt("ASISTENCIA.ALUMNO_ID"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el nombre de los alumnos segun el curso
    public ArrayList<String> getNombreAlumnosByGrupoAsistencia(Integer id_grupo, String fecha) throws SQLException {
        
        
        ResultSet rs = super.consultar("SELECT APELLIDO, NOMBRE FROM ASISTENCIA INNER JOIN ALUMNO ON ALUMNO.ALUMNO_ID = ASISTENCIA.ALUMNO_ID WHERE ASISTENCIA.GRUPO_ID = '"+id_grupo+"' AND ASISTENCIA.FECHA = '"+fecha+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("ALUMNO.APELLIDO") + " " +rs.getString("ALUMNO.NOMBRE"));
        }
        
        return list;
        
    }
    
    // Retorna el nombre del alumno por la id dada
    public String getNombreAlumnoById(int id) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM ALUMNO WHERE ALUMNO_ID='"+id+"'");
        String res = null;
        if (rs.next()) {
            res = rs.getString("APELLIDO") + " " + rs.getString("NOMBRE");
        }
        return res;
    }
    
    public Integer getCantidadDeAlumnos(Integer id_grupo) throws SQLException {
        
        Integer cant = 0;
        
        ResultSet rs = super.consultar("SELECT *, COUNT(*) AS CANT_ALUMNOS FROM GRUPO_ALUMNO WHERE GRUPO_ID = '"+id_grupo+"'");
        
        if(rs.next()) {
            cant = rs.getInt("CANT_ALUMNOS");
        }
        
        return cant;
    }
    
    // Revisa la base de datos buscando los alumnos que tengan tres faltas consecutivas
    public List<Integer> revisarAusencias(Integer id_grupo) throws SQLException {
        
        //ResultSet rs = super.consultar("SELECT * FROM ASISTENCIA ORDER BY DESC WHERE GRUPO_ID = '"+id_grupo+"' GROUP BY FECHA LIMIT 3");
        ResultSet rs = super.consultar("SELECT * FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' ORDER BY ASISTENCIA_ID DESC LIMIT "+getCantidadDeAlumnos(id_grupo) * 3);
        
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> ret = new ArrayList<Integer>();
        
        while (rs.next()) {
            
            if (rs.getInt("ASISTENCIA") == 2) {
                list.add(rs.getInt("ALUMNO_ID"));
            }
            
        }
        
        Set<Integer> quipu = new HashSet<Integer>(list);
        for (Integer i : quipu) {
            
            if (Collections.frequency(list, i) >= 3) {
                ret.add(i);
            }
            
        }
        
        return ret;
        
    }
    
    // Retorna los alumnos y su asistencia en una fecha dada
    public List<String> getAsistenciaByGrupo(Integer id_grupo, String fecha) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT ASISTENCIA.ASISTENCIA, TIPO_ASISTENCIA.DESCRIPCION, ALUMNO.APELLIDO FROM ASISTENCIA INNER JOIN TIPO_ASISTENCIA ON TIPO_ASISTENCIA.TIPO_ASISTENCIA_ID = ASISTENCIA.ASISTENCIA INNER JOIN ALUMNO ON ALUMNO.ALUMNO_ID = ASISTENCIA.ALUMNO_ID WHERE GRUPO_ID = '"+id_grupo+"' AND FECHA = '"+fecha+"' ORDER BY ALUMNO.APELLIDO LIMIT "+getCantidadDeAlumnos(id_grupo));
        
        List<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("TIPO_ASISTENCIA.DESCRIPCION"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el nombre de los grupos por fecha
    public ArrayList<String> getNameGruposPorFecha(String fecha) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT GRUPO_ID FROM ASISTENCIA WHERE FECHA='"+fecha+"' GROUP BY GRUPO_ID");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(getGrupoById(rs.getInt("GRUPO_ID")));
        }
        
        return list;
        
    }
    
    // Retorna un array con el id de los grupos por la fecha
    public ArrayList<Integer> getIdGruposPorFecha(String fecha) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT GRUPO_ID FROM ASISTENCIA WHERE FECHA='"+fecha+"' GROUP BY GRUPO_ID");
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while (rs.next()) {
            list.add(rs.getInt("GRUPO_ID"));
        }
        
        return list;
        
    }
    
    // Retorna un string con el telefono del alumno
    public String getTelefonoById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT TELEFONO FROM ALUMNO WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("TELEFONO");
        }
        
        return ret;
        
    }
    
    // Retorna un string con el celular del alumno
    public String getCelularById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT CELULAR FROM ALUMNO WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("CELULAR");
        }
        
        return ret;
        
    }
    
    // Retorna un string con el email del alumno
    public String getEmailById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT EMAIL FROM ALUMNO WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("EMAIL");
        }
        
        return ret;
        
    }
    
    // Retorna un string con la direccion del alumno
    public String getDireccionById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT DIRECCION FROM ALUMNO WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("DIRECCION");
        }
        
        return ret;
        
    }
    
    // Retorna un string con el dni del alumno
    public String getDniById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT DNI FROM ALUMNO WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("DNI");
        }
        
        return ret;
        
    }
    
    // Retorna un string con el dni del alumno
    public String getNacionalidadById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT ALUMNO.NACIONALIDAD, PAIS.NOMBRE FROM ALUMNO INNER JOIN PAIS ON PAIS.PAIS_ID = ALUMNO.NACIONALIDAD WHERE ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("PAIS.NOMBRE");
        }
        
        return ret;
        
    }
    
    // Retorna un string con el dni del alumno
    public String getCursoInicialById(Integer id_alumno) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT GRUPO.NOMBRE FROM GRUPO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.GRUPO_ID = GRUPO.GRUPO_ID WHERE GRUPO_ALUMNO.ALUMNO_ID='"+id_alumno+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("GRUPO.NOMBRE");
        }
        
        return ret;
        
    }
    
    // Retorna un array con el dni de los alumnos en el grupo
    public ArrayList<String> getDniByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT DNI, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("DNI"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el telefono de los alumnos en el grupo
    public ArrayList<String> getTelefonoByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT TELEFONO, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("TELEFONO"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el celular de los alumnos en el grupo
    public ArrayList<String> getCelularByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT CELULAR, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("CELULAR"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el email de los alumnos en el grupo
    public ArrayList<String> getEmailByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT EMAIL, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("EMAIL"));
        }
        
        return list;
        
    }
    
    // Retorna un array con el sexo de los alumnos en el grupo
    public ArrayList<String> getSexoByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT SEXO, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("SEXO"));
        }
        
        return list;
        
    }
    
    // Retorna un array con la nacionalidad de los alumnos en el grupo
    public ArrayList<String> getNacionalidadByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT ALUMNO.NACIONALIDAD, ALUMNO.APELLIDO, PAIS.NOMBRE FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID INNER JOIN PAIS ON PAIS.PAIS_ID = ALUMNO.NACIONALIDAD WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("PAIS.NOMBRE"));
        }
        
        return list;
        
    }
    
    // Retorna un array con la direccion de los alumnos en el grupo
    public ArrayList<String> getDireccionByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT DIRECCION, APELLIDO FROM ALUMNO INNER JOIN GRUPO_ALUMNO ON GRUPO_ALUMNO.ALUMNO_ID = ALUMNO.ALUMNO_ID WHERE GRUPO_ALUMNO.GRUPO_ID = '"+id_grupo+"' ORDER BY ALUMNO.APELLIDO");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("DIRECCION"));
        }
        
        return list;
        
    }
    
    // Retorna un string con la asistencia del alumno
    public String getAsistenciaById(Integer id_alumno, String fecha) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT ASISTENCIA, TIPO_ASISTENCIA.DESCRIPCION FROM ASISTENCIA INNER JOIN TIPO_ASISTENCIA ON TIPO_ASISTENCIA.TIPO_ASISTENCIA_ID = ASISTENCIA.ASISTENCIA WHERE ALUMNO_ID = '"+id_alumno+"' AND FECHA = '"+fecha+"'");
        
        String ret = null;
        
        while (rs.next()) {
            ret = rs.getString("TIPO_ASISTENCIA.DESCRIPCION");
        }
        
        return ret;
        
    }
    
    // Guarda un usuario activo
    public void setActiveAccount(Integer id_user, String username) throws SQLException {
        
        super.guardar("INSERT INTO ACTIVE_USER(USER_ID, NAME) VALUES('"+id_user+"', '"+username+"')");
        
    }
    
    // Elimina un usuario activo
    public void deleteActiveAccount(Integer id_user) throws SQLException {
        
        super.guardar("DELETE FROM ACTIVE_USER WHERE USER_ID='"+id_user+"'");
        
    }
    
    // Verifica si está activo el usuario
    public boolean isActiveAccount(String username) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM ACTIVE_USER WHERE NAME = '"+username+"'");
        
        boolean ret = false;
        
        if (rs.next()) {
            ret = true;
        }
        
        return ret;
        
    }
    
    // Modifica la asistencia
    public void modificarAsistencia(Integer id_alumno, Integer asistencia, Integer id_grupo, String fecha, Integer id_cuenta) throws SQLException {
            
        super.guardar("UPDATE ASISTENCIA SET ASISTENCIA = '"+asistencia+"', CUENTA_ID = '"+id_cuenta+"' WHERE GRUPO_ID = '"+id_grupo+"' AND ALUMNO_ID = '"+id_alumno+"' AND FECHA = '"+fecha+"'");
            
    }
    
    // Obtengo la fecha en la que se tomó lista por última vez
    public String getUltimaTomaAsistencia(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' ORDER BY ASISTENCIA_ID DESC LIMIT 1");
        
        String ret = null;
        
        if (rs.next()) {
            ret = rs.getString("FECHA");
        }
        
        return ret;
    }
    
    
    /* ******** Consultas de estadísticas ******** */
    
    // Total de alumnos en un grupo
    private Integer getTotalAlumnosEnGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT ALUMNO_ID FROM GRUPO_ALUMNO WHERE GRUPO_ID = '"+id_grupo+"'");
        
        Integer cantAlumnos = 0;
        
        while (rs.next()) {
            cantAlumnos ++;
        }
        
        return cantAlumnos;
        
    }
    
     // Total de de presentes en un grupo por mes
    private Integer getTotalPresentesEnGrupo(Integer id_grupo, String mes) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"'");
        
        Integer cantPresentes = 0;
        
        // Una bandera para saber si se accedió al mes
        boolean flag = false;
        
        while (rs.next()) {
            
            String fecha = rs.getString("FECHA");
            
            String month = Character.toString(fecha.charAt(3)) + Character.toString(fecha.charAt(4));
            
            if (rs.getInt("ASISTENCIA") == 1 || rs.getInt("ASISTENCIA") == 3) {
                
                if (month.equals(mes)) {
                    cantPresentes ++;
                    // Una vez que recorremos el mes, ya no va a volver a aparecer
                    flag = true;
                }
                // Si ya se recorrió el mes, no hay porqué seguir
                else if (flag) {
                    break;
                }
            }
        }
        
        return cantPresentes;
        
    }
    
    // Total de de ausentes en un grupo por mes
    private Integer getTotalAusentesEnGrupo(Integer id_grupo, String mes) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' ORDER BY ASISTENCIA.ASISTENCIA_ID DESC");
        
        Integer cantAusentes = 0;
        
        // Una bandera para saber si se accedió al mes
        boolean flag = false;
        
        while (rs.next()) {
            
            String fecha = rs.getString("FECHA");
            
            String month = Character.toString(fecha.charAt(3)) + Character.toString(fecha.charAt(4));
            
            if (rs.getInt("ASISTENCIA") == 2) {
                
                if (month.equals(mes)) {
                    cantAusentes ++;
                    // Una vez que recorremos el mes, ya no va a volver a aparecer
                    flag = true;
                }
                // Si ya se recorrió el mes, no hay porqué seguir
                else if (flag) {
                    break;
                }
                
            }
        }
        
        return cantAusentes;
        
    }
    
    // Días hábiles de un mes determinado
    private Integer getDiasHabiles(Integer id_grupo, String mes) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"'");
        
        Integer diasHabiles = 0;
        
        // Una bandera para saber si se accedió al mes
        boolean flag = false;
        
        String dayAux = "";
        
        while (rs.next()) {
            
            String fecha = rs.getString("FECHA");
            
            String day = Character.toString(fecha.charAt(0)) + Character.toString(fecha.charAt(1));
            String month = Character.toString(fecha.charAt(3)) + Character.toString(fecha.charAt(4));
            
            if (month.equals(mes)) {
                
                if (!day.equals(dayAux)) {
                    diasHabiles ++;
                    dayAux = day;
                    // Una vez que recorremos el mes, ya no va a volver a aparecer
                    flag = true;
                }
            }
            // Si ya se recorrió el mes, no hay porqué seguir
            else if (flag) {
                break;
            }
        }
        
        return diasHabiles;
        
    }
    
    // Método que retorna el porcentaje de asistencia
    public double getPorcentajeDeAsistencia(Integer id_grupo, String date) throws SQLException {
        
        String mes = Character.toString(date.charAt(3)) + Character.toString(date.charAt(4));
        
        double totalPresentes = getTotalPresentesEnGrupo(id_grupo, mes);
        double totalAusentes = getTotalAusentesEnGrupo(id_grupo, mes);
        
        double porcentajeAsistencia = Maths.redondearDecimales((totalPresentes * 100) / (totalPresentes + totalAusentes), NUM_DEC);
        
        return porcentajeAsistencia;
        
    }
    
    // Método que retorna el porcentaje de inasistencia
    public double getPorcentajeDeInasistencia(Integer id_grupo, String date) throws SQLException {
        
        String mes = Character.toString(date.charAt(3)) + Character.toString(date.charAt(4));
        
        double totalPresentes = getTotalPresentesEnGrupo(id_grupo, mes);
        double totalAusentes = getTotalAusentesEnGrupo(id_grupo, mes);
        
        double porcentajeInasistencia = Maths.redondearDecimales((totalAusentes * 100) / (totalPresentes + totalAusentes), NUM_DEC);
        
        return porcentajeInasistencia;
        
    }
    
    // Cálculo de la media de asistencia por mes
    public double getMediaDeAsistencia(Integer id_grupo, String date) throws SQLException {
        
        String mes = Character.toString(date.charAt(3)) + Character.toString(date.charAt(4));
        
        double totalPresentes = getTotalPresentesEnGrupo(id_grupo, mes);
        double diasHabiles = getDiasHabiles(id_grupo, mes);
        
        double mediaAsistencia = Maths.redondearDecimales(totalPresentes / diasHabiles, NUM_DEC);
        
        return mediaAsistencia;
        
    }
    
    // Cálculo de la asistencia por mes (total)
    public Integer getAsistenciaPorMes(Integer id_grupo, String date) throws SQLException {
        
        String mes = Character.toString(date.charAt(3)) + Character.toString(date.charAt(4));
        
        Integer totalPresentes = getTotalPresentesEnGrupo(id_grupo, mes);
        
        return totalPresentes;
    }
    
    // Cálculo de la inasistencia por mes (total)
    public Integer getInasistenciaPorMes(Integer id_grupo, String date) throws SQLException {
        
        String mes = Character.toString(date.charAt(3)) + Character.toString(date.charAt(4));
        
        Integer totalAusentes = getTotalAusentesEnGrupo(id_grupo, mes);
        
        return totalAusentes;
    }
    
    // Cálculo de la asistencia en la semana
    public Integer totalAsistenciaPorSemana(Integer id_grupo, ArrayList<String> fechas) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' ORDER BY ASISTENCIA.ASISTENCIA_ID DESC");
        
        Integer asistencias = 0;
        
        while (rs.next()) {
            
            for (String f : fechas) {
                
                if (rs.getString("FECHA").equals(f)) {
                    
                    if (rs.getInt("ASISTENCIA") == 1 || rs.getInt("ASISTENCIA") == 3) {
                        asistencias ++;
                    }
                    
                }
                
            }
            
            
        }
        
        return asistencias;
        
    }
    
    // Cálculo de la inasistencia en la semana
    public Integer totalInasistenciaPorSemana(Integer id_grupo, ArrayList<String> fechas) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' ORDER BY ASISTENCIA.ASISTENCIA_ID DESC");
        
        Integer inasistencias = 0;
        
        while (rs.next()) {
            
            for (String f : fechas) {
                
                if (rs.getString("FECHA").equals(f)) {
                    
                    if (rs.getInt("ASISTENCIA") == 2) {
                        inasistencias ++;
                    }
                    
                }
                
            }
            
            
        }
        
        return inasistencias;
        
    }
    
    // Obtener las estadísticas del alumno por semana
    public ArrayList<Integer> getAsistenciaPorAlumnoByFecha(Integer id_grupo, Integer id_alumno, ArrayList<String> fechas) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' AND ALUMNO_ID = '"+id_alumno+"' ORDER BY ASISTENCIA.ASISTENCIA_ID DESC");
        
        ArrayList<Integer> assist = new ArrayList<Integer>();
        
        assist.add(0, 0);
        assist.add(1, 0);
        assist.add(2, 0);

        while (rs.next()) {

           for (String f : fechas) {

                if (f.equals(rs.getString("FECHA"))) {
                    
                    switch (rs.getInt("ASISTENCIA")) {
                        case 1:
                            assist.set(0, assist.get(0) + 1);
                            break;
                        case 2:
                            assist.set(1, assist.get(1) + 1);
                            break;
                        case 3:
                            assist.set(2, assist.get(2) + 1);
                            break;
                        default:
                            break;
                    }
                }

            }
           
        }
        
        return assist;
        
    }
    
    // Obtener las estadísticas del alumno por mes
    public ArrayList<Integer> getAsistenciaPorAlumnoByMes(Integer id_grupo, Integer id_alumno, String fecha) throws SQLException {
        
        String mes = Character.toString(fecha.charAt(3)) + Character.toString(fecha.charAt(4));
        
        ResultSet rs = super.consultar("SELECT FECHA, ASISTENCIA FROM ASISTENCIA WHERE GRUPO_ID = '"+id_grupo+"' AND ALUMNO_ID = '"+id_alumno+"' ORDER BY ASISTENCIA.ASISTENCIA_ID DESC");
        
        ArrayList<Integer> assist = new ArrayList<Integer>();
        
        assist.add(0, 0);
        assist.add(1, 0);
        assist.add(2, 0);

        while (rs.next()) {
            
            String fechaDB = rs.getString("FECHA");
            String mesDB = Character.toString(fechaDB.charAt(3)) + Character.toString(fechaDB.charAt(4));

            if (mesDB.equals(mes)) {

                switch (rs.getInt("ASISTENCIA")) {
                    case 1:
                        assist.set(0, assist.get(0) + 1);
                        break;
                    case 2:
                        assist.set(1, assist.get(1) + 1);
                        break;
                    case 3:
                        assist.set(2, assist.get(2) + 1);
                        break;
                    default:
                        break;
                }
            }
           
        }
        
        return assist;
        
    }
    
    // A partir de una fecha obtengo las fechas de los dias hábiles de esa semana
    public ArrayList<String> getDiasDeSemanaByFecha(String fecha) throws ParseException {
        
        ArrayList<String> list = new ArrayList<String>();
        
        String auxFecha = fecha;
        
        // Busco el lunes de la semana
        while (true) {
            
            if (getDayName(auxFecha).equals("Monday")) {
                
                for (int i = 0; i < 5; i++) {
                    
                    list.add(i, auxFecha);
                    
                    auxFecha = sumarDias(auxFecha, 1);
                    
                }
                
                break;
                
            }
            else {
                
                auxFecha = restarDias(auxFecha, 1);
                
            }
            
        }
        
        return list;
        
    }
    
    
    
    // Método para restar dias a una fecha dada
    private String restarDias(String fecha, Integer cantidad) throws ParseException {

        DateTime dateTime = DateTime.parse(fecha, DateTimeFormat.forPattern("dd-MM-yyyy"));
        
        dateTime = dateTime.minusDays(cantidad);
        
        return dateTime.toString("dd-MM-yyyy");
        
    }
    
    // Método para sumar dias a una fecha dada
    private String sumarDias(String fecha, Integer cantidad) throws ParseException {

        DateTime dateTime = DateTime.parse(fecha, DateTimeFormat.forPattern("dd-MM-yyyy"));
        
        dateTime = dateTime.plusDays(cantidad);
        
        return dateTime.toString("dd-MM-yyyy");
        
    }
    
    // Método para obtener el nombre del día
    private static String getDayName(String fecha) throws ParseException{
        
        Date date = new SimpleDateFormat("d-M-yyyy").parse(fecha);
        
        String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
        
        return dayName;
        
    }
    
}
