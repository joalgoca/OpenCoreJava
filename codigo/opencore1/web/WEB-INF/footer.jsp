<%@page contentType="text/html;charset=UTF-8"%>
        <span id="notification" style="display:none;"></span>
        <div id="relogin" style="display: none;">
            <form id="logform" name="logform" action="${pageContext.request.contextPath}/general/acceso.do">
                <input type="hidden" name="modo" value="login" />
                <div class="logforms k-block">
                    <li><input type="text" class="k-textbox"  id="logUsuario" style="width:200px;" name="logUsuario"  placeholder="Nombre de usuario"  required /></li>
                    <li><input type="password"  id="logContrasena" name="logContrasena" class="k-textbox"  placeholder="Contraseña"  required /></li>
                    <li><a href="javascript:relogin()" class="k-button">Autenticar</a></li>
                </div>
            </form>
        </div>
        <script type="text/javascript" charset="UTF-8" src="https://code.jquery.com/jquery-1.11.1.min.js"   integrity="sha256-VAvG3sHdS5LqTT+5A/aeq/bZGa/Uj04xKxY8KM/w9EE="   crossorigin="anonymous"></script>
        <script type="text/javascript" charset="UTF-8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.2/jszip.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="https://kendo.cdn.telerik.com/2016.1.226/js/kendo.all.min.js"></script> 
        <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/recursos/js/kendo.culture.es-MX.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/recursos/js/kendo.messages.es-ES.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/recursos/js/core.js"></script>
        <!-- Metis Menu Plugin JavaScript -->
        <script type="text/javascript" charset="UTF-8" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/recursos/css/bootstrap/sb-admin-2.1/js/metisMenu.min.js"></script>
        <script src="${pageContext.request.contextPath}/recursos/css/bootstrap/sb-admin-2.1/js/sb-admin-2.js"></script>
        
        <script id="infoTemplate" type="text/x-kendo-template">
            <div class="msg-info">
                <img src="${pageContext.request.contextPath}/recursos/img/envelope.png" />
                <h3>#= title #</h3>
                <p>#= message #</p>
            </div>
        </script>

        <script id="errorTemplate" type="text/x-kendo-template">
            <div class="msg-error">
                <img src="${pageContext.request.contextPath}/recursos/img/error-icon.png" />
                <h3>#= title #</h3>
                <p>#= message #</p>
            </div>
        </script>

        <script id="successTemplate" type="text/x-kendo-template">
            <div class="msg-success">
                <img src="${pageContext.request.contextPath}/recursos/img/success-icon.png" />
                <h3>#= title #</h3>
                <p>#= message #</p>
            </div>
        </script>