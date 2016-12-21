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
public class EstadisticasService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        Integer idGrupo = Integer.parseInt(request.getParameter("id_grupo"));
        String fecha = request.getParameter("fecha");
        
        //Integer idGrupo = 2;
        //String fecha = "30-11-2016";
        
        String res = null;
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            double porcentajeAsistencia = db.getPorcentajeDeAsistencia(idGrupo, fecha);
            double porcentajeInasistencia = db.getPorcentajeDeInasistencia(idGrupo, fecha);
            double mediaAsistencia = db.getMediaDeAsistencia(idGrupo, fecha);
            
            ArrayList<String> fechasSemana = db.getDiasDeSemanaByFecha(fecha);
            
            Integer inasistenciaSemana = db.totalInasistenciaPorSemana(idGrupo, fechasSemana);
            Integer asistenciaSemana = db.totalAsistenciaPorSemana(idGrupo, fechasSemana);
            
            Integer inasistenciaMes = db.getInasistenciaPorMes(idGrupo, fecha);
            Integer asistenciaMes = db.getAsistenciaPorMes(idGrupo, fecha);
                        
            // Porcentajes
            mainJo.put("porcentaje_asistencia", porcentajeAsistencia);
            mainJo.put("porcentaje_inasistencia", porcentajeInasistencia);
            
            // Media
            mainJo.put("media_asistencia", mediaAsistencia);
            
            // Por semana
            mainJo.put("inasistencia_semana", inasistenciaSemana);
            mainJo.put("asistencia_semana", asistenciaSemana);
            
            // Por mes
            mainJo.put("inasistencia_mes", inasistenciaMes);
            mainJo.put("asistencia_mes", asistenciaMes);
            
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
