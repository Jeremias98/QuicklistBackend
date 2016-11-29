/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Querys;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
 * @author Jeremías
 */
public class ModificarAsistenciaService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        Integer idAlumnos = Integer.parseInt(request.getParameter("id_alumnos"));
        Integer asistenciaAlumnos = Integer.parseInt(request.getParameter("assist_alumnos"));
        String fecha = request.getParameter("fecha");
        Integer grupo = Integer.parseInt(request.getParameter("grupo"));
        Integer cuenta = Integer.parseInt(request.getParameter("cuenta"));
        
        /*
        Integer idGrupo = 2;
        Integer idCuenta = 1;
        Integer asistencia = 1;
        Integer idAlumno = 1;
        String fecha = "28-11-2016";*/
        
        String res = null;
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            db.modificarAsistencia(idAlumnos, asistenciaAlumnos, grupo, fecha, cuenta);
            
            mainJo.put("success", true);
            mainJo.put("msj", "Se ha modificado la asistencia");
            
            ja.put(mainJo);
            
            res = ja.toString();
            
            db.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoGrupoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoGrupoService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
            Logger.getLogger(AlumnoGrupoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            out.println(res);
        }
        
    }

}
