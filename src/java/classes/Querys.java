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
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO_ALUMNO WHERE GRUPO_ID='"+id_grupo+"'");
        
        ArrayList<String> list = new ArrayList<String>();
        
        while (rs.next()) {
            list.add(getNombreAlumnoById(rs.getInt("ALUMNO_ID")));
        }
        
        return list;
        
    }
    
    // Retorna un array con el id de los alumnos segun el curso
    public ArrayList<Integer> getIdAlumnosByGrupo(Integer id_grupo) throws SQLException {
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO_ALUMNO WHERE GRUPO_ID='"+id_grupo+"'");
        
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
    
}
