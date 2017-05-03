<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width" />
        <title>${initParam.appName} - Actualizar Contraseña</title>
        <link rel="stylesheet" href="recursos/css/bootstrap/bootstrap.min.css" />
    </head>
      <body>  
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <br/><br/><br/>
                    <img src="recursos/img/logo.png" alt="Logo administración"/>
                    <div class="alert alert-danger" role="alert" id="error" style="display: none;">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        ${param.msg}
                    </div>
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Favor de ingresar los siguientes datos</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" name="form" id="form" action="actualizarContrasena.do" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input type="hidden" name="modo" id="modo" value="login"/>
                                        <input type="text" name="logUsuario" id="logUsuario" value="${param.logUsuario}"  class="form-control" placeholder="Nombre de usuario" required="true" readonly="readonly" required="true" size="20" autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="logContrasenaActual" id="logContrasenaActual"  class="form-control" placeholder="Contraseña actual" required="true" size="20" autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="logContrasenaNueva" id="logContrasenaNueva" class="form-control" placeholder="Nueva contraseña" required="true">
                                    </div>
                                    <input type="submit" name="wp-submit" id="wp-submit" value="Actualizar" class="btn btn-primary btn-lg">
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
            $(document).ready(function(){
                $("#form").validate();
                if("${param.msg}"!="")
                    $("#error").show();
            });
        </script>
    </body>
</html>