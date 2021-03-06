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
public class RevisarTodasAsistenciasService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        String res = null;
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            List<Integer> idAlumnos = new ArrayList<Integer>();
                    
            for (Integer i : db.getIdGrupos()) {
                for (Integer j : db.revisarAusencias(i)) {
                    idAlumnos.add(j);
                }
            }
            
            List<String> nameAlumnos = new ArrayList<String>();
            List<String> telfonoAlumnos = new ArrayList<String>();
            List<String> celularAlumnos = new ArrayList<String>();
            List<String> emailAlumnos = new ArrayList<String>();
            List<String> direccionAlumnos = new ArrayList<String>();
            List<String> dniAlumnos = new ArrayList<String>();
            List<String> nacionalidadAlumnos = new ArrayList<String>();
            List<String> cursoAlumnos = new ArrayList<String>();
            
            for (Integer i : idAlumnos) {
                nameAlumnos.add(db.getNombreAlumnoById(i));
                telfonoAlumnos.add(db.getTelefonoById(i));
                celularAlumnos.add(db.getCelularById(i));
                emailAlumnos.add(db.getEmailById(i));
                direccionAlumnos.add(db.getDireccionById(i));
                dniAlumnos.add(db.getDniById(i));
                nacionalidadAlumnos.add(db.getNacionalidadById(i));
                cursoAlumnos.add(db.getCursoInicialById(i));
            }
            
            mainJo.put("id", idAlumnos);
            mainJo.put("name", nameAlumnos);
            mainJo.put("phone", telfonoAlumnos);
            mainJo.put("mobile", celularAlumnos);
            mainJo.put("email", emailAlumnos);
            mainJo.put("dni", dniAlumnos);
            mainJo.put("direccion", direccionAlumnos);
            mainJo.put("nacionalidad", nacionalidadAlumnos);
            mainJo.put("curso", cursoAlumnos);
            
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
