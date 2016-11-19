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
import java.util.ArrayList;
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
public class RevisarAsistenciaService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        //String idGrupo = request.getParameter("id_grupo");
        Integer idGrupo = 2;
        
        String res = null;
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            List<Integer> idAlumnos = db.revisarAusencias(idGrupo);
            
            mainJo.put("id", idAlumnos);
            
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
