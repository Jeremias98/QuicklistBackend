/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Querys;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremías
 */
public class LogoutService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        // Solicito los datos como parámetros
        Integer id = Integer.parseInt(request.getParameter("id"));
        
        try {
            
            // Conecto a la base de datos
            db.conectar();
            
            // Elimino la cuenta activa
            db.deleteActiveAccount(id);
            
            db.desconectar();
                        
        } catch (SQLException ex) {
            Logger.getLogger(IngresarService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(IngresarService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
