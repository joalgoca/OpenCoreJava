<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width" />
        <title>${initParam.appName} - Recuperar Contraseña</title>
        <link rel="stylesheet" href="recursos/css/bootstrap/bootstrap.min.css" />
    </head>
      <body>  
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <br/><br/><br/>
                    <img src="recursos/img/logo.png" alt="Logo administración"/>
                    <div class="alert alert-info" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Info:</span>
                        Por favor, escribe tu nombre de usuario o tu correo electrónico. 
                        Recibirás un enlace para crear la contraseña nueva por correo electrónico.
                    </div>
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Favor de ingresar sus credenciales</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" name="loginform" id="loginform" action="recuperarContrasena.do" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input type="email" name="email" id="email"  class="form-control" placeholder="Correo electrónico" required="true" autofocus>
                                    </div>
                                    <a  href="javascript:validaEmail()"  name="submit" id="submit" class="btn btn-primary btn-lg">Recuperar contraseña</a>
                                    <a href="index.jsp" class="btn btn-default btn-lg" target="_self">Acceder</a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                    <div class="progress" style="display: none;" id="progressbar">
                        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                          <span class="sr-only">100% Complete</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
       <script type="text/javascript" src="recursos/js/jquery.min.js"></script>
       <script type="text/javascript" src="recursos/js/bootstrap/jquery.validate.min.js"></script>
       <script type="text/javascript" src="recursos/js/bootstrap/bootstrap.min.js"></script>
       <script>
            function validaEmail(){
                if($("#email").valid())
                {
                    $email=$("#email").val();
                    $.post("recuperarContrasena.do",
                        {email:$("#email").val()},
                         function(data) {
                             if(data!=null)
                                alert(data.msg);
                            else
                                alert("Error en el servidor");
                        },"json");
                }                
            }
            $( document ).ajaxStart(function() {
                var createBtn = $("body");
                $("<div id='loading-gn'>").css({
                    "position": "absolute",
                    "opacity":"0.4",
                    "filter":"alpha(opacity=40)",
                    "background-color":"#fff",
                    "z-index":"1000",
                    "left": createBtn.offset().left,
                    "top": createBtn.offset().top,
                    "width": createBtn.outerWidth(),
                    "height": createBtn.outerHeight()
                }).appendTo($("body"));
                $("#progressbar").show();
            });
            
            $( document ).ajaxStop(function() {
                $('#loading-gn').remove();
                $("#progressbar").hide();
            });
        </script>
    </body>
</html>