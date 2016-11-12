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
    
}
