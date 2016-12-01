<%-- 
    Document   : administrar
    Created on : 03/09/2016, 16:44:58
    Author     : Jeremías
--%>

<%@page import="classes.Querys"%>
<%@page import="classes.Alta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="es">
    <head>
        <title>QuickList | Administrar</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="icon" href="img/logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/menu.js"></script>
    </head>
    
    <body>
        <div class="contenedor">
            
            <div class="cabecera">
                
                <div class="menu">
                    
                    <div class="menu_bar">
                        <span style="float: left; margin-top: 10px; margin-left: 8px; font-weight: 600;">QuickList</span>
                        <a href="#" class="bt-menu"><span class="icon-menu"></span></a>
                    </div>
                    
                    <nav>
                        <ul>
                            <li><img src="img/logo.png" width="30px" height="30px"><h4>QuickList</h4></li>
                            <li><a href="index.html">Inicio</a></li>
                            <li><a href="administrar.jsp">Administrar</a></li>
                            <li><a href="registrarse.jsp">Registrarse</a></li>
                            <li><a href="ingresar.jsp">Ingresar</a></li>
                            <!--div class="logo">
                                <img src="img/logo.png" alt="" width="150px" height="40px">
                            </div-->
                        </ul>
                    </nav>
                    
                    <!--div class="social">
                        <a href="https://www.facebook.com/" target="_blank"><span class="icon-facebook"></span></a>
                    </div-->
                    
                </div>
                
            </div>
            
            <div class="cuerpo">

                <div class="formulario">
                    <form method="POST" action="AltaAlumno">
                        <h2>Datos Personales</h2><p class="msg_error">${param.message}</p><p class="msg_correcto">${param.message2}</p><br>
                        <input type="text" placeholder="Número DNI (*)" class="input_left" name="dni">
                        <input type="text" placeholder="Número de legajo (*)" class="input_right" name="legajo">
                        <input type="text" placeholder="Apellido(s) (*)" class="input_left" name="apellido">
                        <input type="text" placeholder="Nombre(s) (*)" class="input_right" name="nombre">
                        <select name="sexo" class="input_left">
                            <option value="Varón">Varón</option>
                            <option value="Mujer">Mujer</option>
                        </select>
                        <select name="nacionalidad" class="input_right">
                            <%
                                try {
                                    
                                    Querys querys = new Querys();
                                    Alta alta = new Alta();
                                    
                                    querys.conectar();
                                    alta.conectar();
                                    
                                    //alta.generarCursos();
                                    ArrayList<String> list = new ArrayList<String>();
                                    list = querys.getNacionalidad();
                                    
                                    String[] result = new String[list.size()];
                                    result = list.toArray(result);
                                    
                                    
                                    for (int i = 0; i < result.length; i++){%>
                                        
                                    <option value="<%=(i+1)%>" <%if(result[i].equals("Argentina")){%>selected<%}%>><%=result[i]%></option>
                                    
                                    <%}
                                    querys.desconectar();
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                                //out.println("<option>"+list.get(1)+"</option>");
                                %>
                        </select>
                        
                        <input type="text" placeholder="Dirección" class="entrada_form" name="direccion">
                        
                        <select name="curso" class="input_left" style="margin-bottom: 30px;">
                            <option value="" disabled selected>Curso</option>
                            <%
                                for(int i = 1;i <= 7;i++){%>
                                    <option value="<%=i%>"><%=i%></option>
                                <%}
                            %>
                        </select>
                        
                        <select name="division" class="input_right" style="margin-bottom: 30px;">
                            <option value="" disabled selected>División</option>
                            <%
                                for(int i = 1;i <= 10;i++){%>
                                    <option value="<%=i%>"><%=i%></option>
                                <%}
                            %>
                        </select>
                        
                        <br><h2>Contacto</h2><br>
                        <input type="text" placeholder="Teléfono de línea" class="input_left" name="telefono">
                        <input type="text" placeholder="Teléfono móvil" class="input_right" name="celular">
                        <input type="text" placeholder="E-Mail" class="entrada_form" name="email">
                        
                        <p style="font-weight: bold;">(*) Campos obligatorios</p><br>
                        
                        <center><input type="submit" value="Aceptar" style="width: 600px; height: 30px; font-weight: bold; margin-bottom: 20px; font-family: 'Raleway-Thin';"></center>
                    </form>
                </div>
                
                <div class="pie" style="margin-top: 250px;">
                    <div class="copy">
                        <h4>Copyright &copy; 2016 - QuickList</h4>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
