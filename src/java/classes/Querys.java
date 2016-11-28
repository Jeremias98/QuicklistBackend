/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jeremías
 */
public class Querys extends MasterDatabase {
   
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
    
    public void setActiveAccount(Integer id_user, String username) throws SQLException {
        
        super.guardar("INSERT INTO ACTIVE_USER(USER_ID, NAME) VALUES('"+id_user+"', '"+username+"')");
        
    }
    
    public void deleteActiveAccount(Integer id_user) throws SQLException {
        
        super.guardar("DELETE FROM ACTIVE_USER WHERE USER_ID='"+id_user+"'");
        
    }
    
    public boolean isActiveAccount(String username) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM ACTIVE_USER WHERE NAME = '"+username+"'");
        
        boolean ret = false;
        
        if (rs.next()) {
            ret = true;
        }
        
        return ret;
        
    }
    
}
