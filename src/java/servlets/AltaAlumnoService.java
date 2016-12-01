/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Alta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremías
 */
public class AltaAlumnoService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Codificación de los parámetros
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
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
        
        Alta db = new Alta();
        
        try {
            
            // Validación de datos
            if(apellido.equals("") || nombre.equals("") || dni.equals("")){
                // Error, llenar los campos correspondientes
                res = "No podés dejar campos vacíos";
            } 
            else {
                //Debe haber al menos una forma de contactar al alumno
                if(telefono.equals("") && celular.equals("") && email.equals("")){
                    res = "No hay formas de contacto";
                }
                else
                {
                    db.conectar();
                
                    db.altaAlumno(apellido, nombre, telefono, celular, email, sexo, nacionalidad, dni, direccion, curso, division, legajo);

                    db.desconectar();
                    
                    res = "Guardado correctamente";
                    
                }
                
            }
                        
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            
            out.print(res);
            
        }
    }

}

