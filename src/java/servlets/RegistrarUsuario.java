/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Alta;
import classes.Validar;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeremías
 */
public class RegistrarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Codificación de los parámetros
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        HttpSession sesion = request.getSession();
        
        String user = request.getParameter("user");
        String pswd = request.getParameter("password");
        String pswd2 = request.getParameter("password2");
        String pin = request.getParameter("pin");
        
        String res = null;
        
        Alta db = new Alta();
        
        Validar v = new Validar();
        
        try {
            
            if (!user.equals("") && !pswd.equals("") && !pswd2.equals("") && !pin.equals("")) {
                
                if (v.isUsernameOrPasswordValid(user) && v.isUsernameOrPasswordValid(pswd)) {
                    
                    db.conectar();
                
                    if (pswd.equals(pswd2) && pin.equals(db.getPin())) {

                        res = "registrarse.jsp?message2=" + URLEncoder.encode("Registrado correctamente", "UTF-8");
                        db.registrarUsuario(user, pswd);

                    }
                    else {

                        res = "registrarse.jsp?message=" + URLEncoder.encode("Las contraseñas no coinciden", "UTF-8");

                    }

                    db.desconectar();
                    
                }
                else {
                    res = "registrarse.jsp?message=" + URLEncoder.encode("Caracteres incorrectos", "UTF-8");
                }
                
            }
            else {
                
                res = "registrarse.jsp?message=" + URLEncoder.encode("No puede dejar campos vacíos", "UTF-8");
                
            }
            
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
