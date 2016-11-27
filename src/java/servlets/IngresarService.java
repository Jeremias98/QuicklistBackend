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
/*
    Retorna una JSONArray con los cursos  que administra cada preceptor
    y el id de cuenta.
    Si es null, la cuenta no existe
*/
public class IngresarService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Querys db = new Querys();
        
        PrintWriter out = response.getWriter();
        
        // Solicito los datos como parámetros
        String user = request.getParameter("user");
        String pswd = request.getParameter("password");
        String id = request.getParameter("id");
        /*
        String id = null; 
        String user = "Jere";
        String pswd = "123";*/
        
        // Respuesta
        String res = null;
        
        // Creo un array y un objeto donde ir guardando datos
        JSONArray ja = new JSONArray();
        JSONObject mainJo = new JSONObject();
        
        try {
            
            // Conecto a la base de datos
            db.conectar();
            
            // Verifico que la cuenta no esté iniciada
            if (!db.isActiveAccount(user)) {
                
                // Si no hay una id ya enlazada, es un loggeo
                // Verifico que si hay campos vacíos
                if (id == null && user != null && pswd != null) {
                    // Verifico que los datos coincidan
                    if (db.isAcountExists( user, pswd )) {

                        int idCuenta = db.getIdCuenta(user, pswd);

                        ArrayList<Integer> grupos_id = db.getIdGruposQueAdministra(idCuenta);
                        ArrayList<String> grupos_name = db.getNombreGruposQueAdministra(idCuenta);

                        mainJo.put("id_cuenta", idCuenta);
                        mainJo.put("usuario", user);
                        mainJo.put("id_grupos", grupos_id);
                        mainJo.put("name_grupos", grupos_name);
                        mainJo.put("error", false);
                        
                        db.setActiveAccount(idCuenta, user);
                        
                        ja.put(mainJo);

                        res = ja.toString();

                    }
                    // Si los datos no coinciden
                    else {
                        mainJo.put("error", true);
                        mainJo.put("msj", "Usuario o contraseña incorrectos");
                        ja.put(mainJo);
                        res = ja.toString();
                    }
                }
                // Si hay una id enlazada, se están pidiendo datos actualizados
                else if (id != null){

                    ArrayList<String> grupos_name = db.getNombreGruposQueAdministra(Integer.parseInt(id));
                    ArrayList<Integer> grupos_id = db.getIdGruposQueAdministra(Integer.parseInt(id));

                    mainJo.put("name_grupos", grupos_name);
                    mainJo.put("id_grupos", grupos_id);
                    mainJo.put("error", false);

                    ja.put(mainJo);

                    res = ja.toString();

                }
                // Si no, hay campos vacíos
                else {
                    mainJo.put("error", true);
                    mainJo.put("msj", "No puede dejar campos vacíos");
                    ja.put(mainJo);
                    res = ja.toString();
                }
            }
            else {
                
                mainJo.put("error", true);
                mainJo.put("msj", "Esa cuenta ya está iniciada en el sistema");
                ja.put(mainJo);
                res = ja.toString();
            }
            
            db.desconectar();
                        
        } catch (SQLException ex) {
            Logger.getLogger(IngresarService.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(IngresarService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
            Logger.getLogger(IngresarService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            out.println(res);
            
        }
    }

}
