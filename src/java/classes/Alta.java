/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeremías
 */
public class Alta extends MasterDatabase {
    
    //Método para registrar un alumno
    public void altaAlumno(String dni, String apellido, String nombre, String telefono, String celular, String email, String sexo, String nacionalidad, String direccion, String curso, String division) throws SQLException {
        
        ResultSet rs = super.guardarAndGetKey("INSERT INTO ALUMNO(DNI, APELLIDO, NOMBRE, TELEFONO, CELULAR, EMAIL, SEXO, NACIONALIDAD, DIRECCION) VALUES ('"+dni+"','"+apellido+"','"+nombre+"', '"+telefono+"', '"+celular+"', '"+email+"', '"+sexo+"','"+nacionalidad+"', '"+direccion+"')");
        
        int autoIncKeyFromFunc;
        
        if (rs.next()) {
            autoIncKeyFromFunc = rs.getInt(1);
            guardarEnGrupo(autoIncKeyFromFunc, getGrupoId(curso, division));
        }
        
    }
    
    public void guardarEnGrupo(int id_alumno, int id_grupo) throws SQLException {
        
        super.guardar("INSERT INTO GRUPO_ALUMNO(GRUPO_ID, ALUMNO_ID) VALUES ('"+id_grupo+"', '"+id_alumno+"')");
        
    }
    
    public void crearGrupo(String nombre, String flag) throws SQLException{
        
        String sql = "INSERT INTO GRUPO(NOMBRE, FLAG) VALUES ('"+nombre+"', '"+flag+"')";
        PreparedStatement ps = CONN.prepareStatement(sql);
        ps.executeUpdate();
        
    }
    
    // Genera los grupos con 7 cursos y 10 divisiones cada uno
    public void generarCursos() throws SQLException {
        
        String nombre;
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 10; j++) {
                nombre = getCourseName(String.valueOf(i), String.valueOf(j));
                // Flag = 0 para que sea default
                crearGrupo(nombre, "0");
            }
        }
        
    }
     
    // Método para guardar la asistencia tomada
    public void guardarAsistencia(Integer id_alumno, Integer id_grupo, String fecha, Integer asistencia, Integer id_cuenta) throws SQLException {
        
        super.guardar("INSERT INTO ASISTENCIA(ALUMNO_ID, GRUPO_ID, ASISTENCIA, CUENTA_ID, FECHA) VALUES('"+id_alumno+"', '"+id_grupo+"', '"+asistencia+"', '"+id_cuenta+"', '"+fecha+"')");
    }
    
    // Método para registrar un usuario
    public void registrarUsuario(String user, String pass) throws SQLException {
        String sql = "INSERT INTO CUENTA(USUARIO, CONTRASEÑA) VALUES ('"+user+"', AES_ENCRYPT('"+pass+"', 'xcabczxabccz4815162342'))";
        PreparedStatement ps = CONN.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    // Método para asignar un grupo a una cuenta
    public void enlazarCuentaGrupo(int idCuenta, int idGrupo) throws SQLException {
        
        String sql = "INSERT INTO CUENTA_GRUPO(CUENTA_ID, GRUPO_ID) VALUES('"+idCuenta+"', '"+idGrupo+"')";
        PreparedStatement ps = CONN.prepareStatement(sql);
        ps.executeUpdate();
        
    }
    
    // El pin estaría en algún archivo de configuración
    public String getPin() {
        return "1234";
    }
    
    public void guardarComoFavorito(String grupo) throws SQLException {
        //ResultSet rs = super.consultar("SELECT GRUPO_ID WHERE NOMBRE=");
    }
    
    // Retorna el nombre del curso con la sintáxis correcta
    private String getCourseName(String curso, String div) {
        
        String course;
        String divishon = null;
        String nombre;
        
        if (curso.equals("7"))
            course = curso + "mo";
        else if (curso.equals("3") || curso.equals("1"))
            course = curso + "ro";
        else if (curso.equals("2"))
            course = curso + "do";
        else
            course = curso + "to";
        
        if (div.equals("10") || div.equals("7"))
            divishon = div + "ma";
        else if (div.equals("9"))
            divishon = div + "na";
        else if (div.equals("8"))
            divishon = div + "va";
        else if (div.equals("6") || div.equals("5") || div.equals("4"))
            divishon = div + "ta";
        else if (div.equals("3") || div.equals("1"))
            divishon = div + "ra";
        else if (div.equals("2"))
            divishon = div + "da";
                
        nombre = course + " " + divishon;
        
        return nombre;
    }
    
    // Retorna un entero con el ID del curso y división pasados
    public int getGrupoId(String curso, String div) throws SQLException {
        
        String nombre = getCourseName(curso, div);
        
        ResultSet rs = super.consultar("SELECT * FROM GRUPO WHERE GRUPO.NOMBRE='"+nombre+"'");
        
        int id = 0;
        while(rs.next())
        {
            id = rs.getInt("GRUPO_ID");
        }
        
        return id;
        
    }
    
}
