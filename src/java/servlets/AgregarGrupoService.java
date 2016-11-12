/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import classes.Alta;
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
public class AgregarGrupoService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Alta db = new Alta();
        
        PrintWriter out = response.getWriter();
        
        String res = null;
        
        String idCuenta = request.getParameter("id_cuenta");
        String idGrupo = request.getParameter("id_grupo");
        
        JSONObject mainJo = new JSONObject();
        
        try {
            
            db.conectar();
            
            if (idCuenta != null && idGrupo != null) {
                
                db.enlazarCuentaGrupo(Integer.parseInt(idCuenta), Integer.parseInt(idGrupo));
                
                mainJo.put("msj", "Se ha unido correctamente");
                res = mainJo.toString();
                
            }
            else {
                
                mainJo.put("msj", "Ocurrió un error al intentar unirse");
                res = mainJo.toString();
                
            }
            
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
