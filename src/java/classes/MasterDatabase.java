/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeremías
 */
public abstract class MasterDatabase {
    
    public Connection CONN;
    private final static String USER = "admin";
    private final static String PASS = "admin";
    
    //Conectar a la Base de datos
    public void conectar() throws SQLException,ClassNotFoundException {
        
         Class.forName("com.mysql.jdbc.Driver");
         CONN = DriverManager.getConnection("jdbc:mysql://localhost:3306/quicklist", USER, PASS);
         
    }
    
    //Desconectar a la Base de datos
    public void desconectar() throws SQLException, ClassNotFoundException {
        
        CONN.close();
        
    }
    
    public Connection getConnection() throws SQLException {
        
        return this.CONN;
    
    }
    
    public ResultSet consultar(String sql) throws SQLException {
        
        PreparedStatement ps = CONN.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        return rs;
        
    }
    
    public void guardar(String sql) throws SQLException {
        
        PreparedStatement ps = CONN.prepareStatement(sql);
        ps.executeUpdate();
        
    }
    
    public ResultSet guardarAndGetKey(String sql) throws SQLException {
        
        PreparedStatement ps = CONN.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        
        // Creo un Result Set para buscar el ultimo id ingresado
        ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
        
        return rs;
    }
    
}
