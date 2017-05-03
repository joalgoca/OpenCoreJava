<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 6]> <div class='alert-box alert' style="padding-5px;text-align:center;"><table><tr><td><b>Nota importante:</b> Te recomendamos actualizar tu navegador, para tener una navegaci&oacute;n correcta del sitio.<br/> 
O puedes descargar la &uacute;ltima versi&oacute;n de: </td><td>
<a href='https://www.google.com/chrome'><img src='${pageContext.request.contextPath}/recursos/img/chrome.png' alt='chrome' style='vertical-align:middle;border:0px;'/></a>
<a href='http://www.apple.com/safari/download/'><img src='${pageContext.request.contextPath}/recursos/img/safari.png' alt='safari' style='vertical-align:middle;border:0px;'/></a>
<a href='http://www.mozilla-europe.org/es/firefox/'><img src='${pageContext.request.contextPath}/recursos/img/firefox.png' alt='FireFox' style='vertical-align:middle;border:0px;'/></td></tr></table></div><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width" />
        <title>${initParam.appName} - Inicio</title>
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
                            <h3 class="panel-title">Favor de ingresar sus credenciales</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" name="loginform" id="loginform" action="acceso.do" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input type="hidden" name="modo" id="modo" value="login">
                                        <input type="text" name="logUsuario" id="logUsuario"  class="form-control" placeholder="Usuario" required="true" size="20" autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="logContrasena" id="logContrasena" class="form-control" placeholder="Contraseña" required="true" size="20">
                                    </div>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <input type="submit" name="wp-submit" id="wp-submit" class="btn btn-primary btn-lg" value="Acceder">
                                    <a href="recuperarContrasena.jsp" class="btn btn-default btn-lg" target="_self">Recuperar contraseña</a>
                                </fieldset>
                            </form>
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
                $("#loginform").validate();
                if("${param.msg}"!="")
                    $("#error").show();
            });
        </script>
    </body>
</html>