/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Alta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        
        String[] idAlumnos = request.getParameterValues("id_alumnos");
        String[] asistenciaAlumnos = request.getParameterValues("assist_alumnos");
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            
            
            mainJo.put("id_alumnos", "");
            
            db.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgregarGrupoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarGrupoService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
            Logger.getLogger(AgregarGrupoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            out.println(res);
            
        }
        
    }

}
