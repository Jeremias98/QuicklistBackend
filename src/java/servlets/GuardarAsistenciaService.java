/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Alta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Aaron2
 */
public class GuardarAsistenciaService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Codificación de los parámetros
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Alta db = new Alta();
        
        PrintWriter out = response.getWriter();
        
        String res = null;
        
        
        String idAlumnos = request.getParameter("id_alumnos");
        String asistenciaAlumnos = request.getParameter("assist_alumnos");
        String fecha = request.getParameter("fecha");
        Integer grupo = Integer.parseInt(request.getParameter("grupo"));
        Integer cuenta = Integer.parseInt(request.getParameter("cuenta"));
        
        /*
        String idAlumnos = "1";
        String asistenciaAlumnos = "1";
        String fecha = "10/11/2016";
        Integer grupo = 2;
        Integer cuenta = 1;*/
        
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            db.guardarAsistencia(Integer.parseInt(idAlumnos), grupo, 
                    fecha, Integer.parseInt(asistenciaAlumnos), cuenta);
            
            mainJo.put("success", true);
            mainJo.put("msj", "Se ha guardado la asistencia");
            ja.put(mainJo);
            
            res = ja.toString();
            
            db.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(GuardarAsistenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(GuardarAsistenciaService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
            Logger.getLogger(GuardarAsistenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            out.println(res);
            
        }
        
    }

}
