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
public class BuscarCursosService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        String res = null;
        
        String fecha = request.getParameter("fecha");
        //String fecha = "17-11-2016";
        
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            ArrayList<String> listaNombre = db.getNameGruposPorFecha(fecha);
            ArrayList<Integer> listaId = db.getIdGruposPorFecha(fecha);
            
            mainJo.put("id_grupo", listaId);
            mainJo.put("nombre_grupo", listaNombre);
            
            ja.put(mainJo);
            
            res = ja.toString();
            
            db.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(ListarCursosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarCursosService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
            Logger.getLogger(ListarCursosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            out.println(res);
            
        }
        
    } 

}
