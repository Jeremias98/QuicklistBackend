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
import java.text.ParseException;
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
public class EstadisticasAlumnoService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        //Integer idGrupo = Integer.parseInt(request.getParameter("id_grupo"));
        //String fecha = request.getParameter("fecha");
        
        Integer idGrupo = 2;
        String fecha = "30-11-2016";
        
        String res = null;
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            ArrayList<String> fechasSemana = db.getDiasDeSemanaByFecha(fecha);
            
            List<Integer> idAlumnos = db.getIdAlumnosByGrupoAsistencia(idGrupo, fecha);
            List<String> nameAlumnos = db.getNombreAlumnosByGrupoAsistencia(idGrupo, fecha);
            
            ArrayList<Integer> asistencia_semanal = new ArrayList<Integer>();
            ArrayList<Integer> asistencia_mensual = new ArrayList<Integer>();

            ArrayList<Integer> presente_semanal = new ArrayList<Integer>();
            ArrayList<Integer> ausente_semanal = new ArrayList<Integer>();
            ArrayList<Integer> tarde_semanal = new ArrayList<Integer>();
            
            ArrayList<Integer> presente_mensual = new ArrayList<Integer>();
            ArrayList<Integer> ausente_mensual = new ArrayList<Integer>();
            ArrayList<Integer> tarde_mensual = new ArrayList<Integer>();
            
            // Lista de alumnos
            mainJo.put("id_alumno", idAlumnos);
            mainJo.put("nombre_alumno", nameAlumnos);
            
            for (Integer i : idAlumnos) {
                
                asistencia_semanal = db.getAsistenciaPorAlumnoByFecha(idGrupo, i, fechasSemana);
                asistencia_mensual = db.getAsistenciaPorAlumnoByMes(idGrupo, i, fecha);
                
                presente_semanal.add(asistencia_semanal.get(0));
                ausente_semanal.add(asistencia_semanal.get(1));
                tarde_semanal.add(asistencia_semanal.get(2));
                
                presente_mensual.add(asistencia_mensual.get(0));
                ausente_mensual.add(asistencia_mensual.get(1));
                tarde_mensual.add(asistencia_mensual.get(2));
                                
            }
            
            mainJo.put("presentes_semanal", presente_semanal);
            mainJo.put("ausentes_semanal", ausente_semanal);
            mainJo.put("tardes_semanal", tarde_semanal);
            
            mainJo.put("presentes_mensual", presente_mensual);
            mainJo.put("ausentes_mensual", ausente_mensual);
            mainJo.put("tardes_mensual", tarde_mensual);
            
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
        } catch (ParseException ex) {
            Logger.getLogger(EstadisticasService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            out.println(res);
        }
        
    } 

}
