<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width" />
        <title>${initParam.appName} - escritorio</title>
        <jsp:include page="/WEB-INF/css-script.jsp"></jsp:include>
    </head>
    <body>    
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">            
                <jsp:include page="/WEB-INF/header.jsp"></jsp:include>         
                <jsp:include page="/menu.do"/>
            </nav>
            <div id="page-wrapper">
                <div class="container-fluid" >
                    <noscript>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="alert alert-warning alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <i class="fa fa-info-circle"></i>  <b>Alerta:</b> Es necesario tener habilitado el uso de javascript de su navegador.
                                </div>
                            </div>
                        </div>
                    </noscript>
                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h2 class="page-header">
                                Dashboard <small>información de la aplicación</small>
                            </h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="testA" style="height:100%; margin:10px;"></div>
                        </div>
                    </div>
                    <jsp:include page="/escritorio.view">
                        <jsp:param name="modo" value="escritorio"/>
                    </jsp:include>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->
        <jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
        <script>
            $(document).ready(function(){
                inicializar();
            });
        </script>
    </body>
</html>