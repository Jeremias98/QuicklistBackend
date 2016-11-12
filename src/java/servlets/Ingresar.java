/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Alta;
import classes.Querys;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeremías
 */
public class Ingresar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Codificación de los parámetros
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        HttpSession sesion = request.getSession(true);
        
        String user = request.getParameter("user");
        String pswd = request.getParameter("password");
        
        Querys db = new Querys();
        
        String res = null;
        
        try {
            
            db.conectar();
            
            if (db.isAcountExists(user, pswd)) {
                
                int idCuenta = db.getIdCuenta(user, pswd);
                ArrayList<String> grupos = db.getNombreGruposQueAdministra(idCuenta);
                
                sesion.setAttribute("usuario", user);
                sesion.setAttribute("contraseña", pswd);
                sesion.setAttribute("grupos", grupos);// Array
                
                res = "ingresar.jsp?message2=" + URLEncoder.encode("Ingresaste correctamente", "UTF-8");
                
            }
            else {
                
                res = "ingresar.jsp?message=" + URLEncoder.encode("Usuario o contraseña incorrectos", "UTF-8");
                
            }
            
            db.desconectar();
                        
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            response.sendRedirect(res);
        }
        
    }

}
