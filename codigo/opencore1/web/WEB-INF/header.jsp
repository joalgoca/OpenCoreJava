<%@ page contentType="text/html;charset=utf-8"%>
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href='${pageContext.request.contextPath}/general/escritorio.jsp'><img src="${pageContext.request.contextPath}/recursos/img/logo-a.png" alt="Logo administración" style="width:200px;"/></a>
    </div>
    <ul class="nav navbar-top-links navbar-right">
        <!--<li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i id="asBuzon" class="fa fa-envelope"></i> <b  class="caret"></b>
            </a>
            <ul id="caBuzon" class="dropdown-menu message-dropdown" style="width:250px;"></ul>
        </li>-->
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i id="asAvisos" class="fa fa-bell"></i> <b class="caret"></b>
            </a>
            <ul id="caAvisos" class="dropdown-menu alert-dropdown"></ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="${pageContext.request.contextPath}/catalogos/generals/usuario.do?modo=getImage" style="width:18px;height:18px;" alt="Usuario" title="${open_SESION.usuario.usuario} - ${open_SESION.usuario.nombre}" id='imgUsuario'>  <b class="caret"></b></a>
            <ul class="dropdown-menu" style="width:200px;">
                <li>
                    <a href="${pageContext.request.contextPath}/general/perfil.jsp"><i class="fa fa-fw fa-user"></i> Perfil</a>
                </li>
                <!--<li>
                    <a href="${pageContext.request.contextPath}/catalogos/general/mensaje.jsp"><i class="fa fa-fw fa-envelope"></i> Mensajes</a>
                </li>-->
                <li class="divider"></li>
                <li>
                    <a href="${pageContext.request.contextPath}/acceso.do?modo=logout"><i class="fa fa-fw fa-power-off"></i> Cerrar sesión</a>
                </li>
            </ul>
        </li>
    </ul>  
   