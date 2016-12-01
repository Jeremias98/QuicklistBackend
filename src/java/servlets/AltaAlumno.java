/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Alta;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremías
 */
public class AltaAlumno extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Codificación de los parámetros
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        Alta db = new Alta();
            
        // Strings para almacenar los datos del formulario
        String apellido = request.getParameter("apellido");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        String nacionalidad = request.getParameter("nacionalidad");
        String dni = request.getParameter("dni");
        String legajo = request.getParameter("legajo");
        String direccion = request.getParameter("direccion");
        String curso = request.getParameter("curso");
        String division = request.getParameter("division");
        
        String res = null;
        
        try {
            
            db.conectar();
            
            // Validación de datos
            if(apellido.equals("") || nombre.equals("") || dni.equals("") || legajo.equals("")){
                // Error, llenar los campos correspondientes
                res = "administrar.jsp?message=" + URLEncoder.encode("No ha completado todos los campos obligatorios", "UTF-8");
            } 
            else {
                //Debe haber al menos una forma de contactar al alumno
                if(telefono.equals("") && celular.equals("") && email.equals("")){
                    res = "administrar.jsp?message=" + URLEncoder.encode("Debe ingresar al menos una forma de contacto", "UTF-8");
                }
                else
                {
                    res = "administrar.jsp?message2=" + URLEncoder.encode("Datos guardados", "UTF-8");
                    db.altaAlumno(dni, apellido, nombre, telefono, celular, email, sexo, nacionalidad, direccion, curso, division, legajo);
                }
                
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
