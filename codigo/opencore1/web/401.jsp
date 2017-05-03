<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width" />
        <title>${initParam.appName} - sin permisos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/bootstrap/bootstrap.min.css" />
    </head>
      <body>  
          
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <br/><br/><br/>
                    <img src="${pageContext.request.contextPath}/recursos/img/logo.png" alt="Logo administración"/>
                    <div class="alert alert-danger" role="alert" id="error">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        Disculpe las molestias pero su perfil no cuenta con los permisos necesarios.
                    </div>
                    <a href="${pageContext.request.contextPath}/index.jsp"  class="btn btn-primary btn-lg"  target="_self">Volver a inicio</a>                    
                </div>
            </div>
        </div>
       <script type="text/javascript" src="${pageContext.request.contextPath}/recursos/js/jquery.min.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/recursos/js/bootstrap/jquery.validate.min.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/recursos/js/bootstrap/bootstrap.min.js"></script>
    </body>
</html>