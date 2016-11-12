<%-- 
    Document   : ingresar
    Created on : 26/09/2016, 15:51:49
    Author     : Jeremías
--%>

<!DOCTYPE html>

<html lang="es">
    <head>
        <title>QuickList | Ingresar</title>
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
                    <h2>Ingresar</h2><p class="msg_error">${param.message}</p><p class="msg_correcto">${param.message2}</p><br>
                    <form method="POST" action="Ingresar">
                        <input class="entrada_form" type="text" name="user" placeholder="Usuario" autofocus>
                        <input class="entrada_form" type="password" placeholder="Contraseña" name="password">
                        <center><input type="submit" value="Aceptar" style="width: 600px; height: 30px; font-weight: bold; margin-bottom: 20px; font-family: 'Raleway-Thin';"></center>
                    </form>     
                </div>
                    <div class="pie" style="margin-top: 30%;">
                    <div class="copy">
                        <h4>Copyright &copy; 2016 - QuickList</h4>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

