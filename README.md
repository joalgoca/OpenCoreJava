# OpenCoreJava
Aplicación Web base en MVC (Front end - jquery, bootstrap) (Backend java - Servlets, Filters, EJB 3.1)

<h3>Objetivo</h3>
<p>Realizar un ejemplo practico de desarrollo Web en Java y servirá como base para el repositorio <a href='https://github.com/chepeteam/TodoJavaMvc' target='_blank'>TodoJavaMvc</a>.</p>

<h3>Objetivos específicos</h3>
<p>Este proyecto lo voy a construir en la manera habitual con que creo la mayoría de mis Aplicaciones Web, con algunos objetivos específicos , como son: </p>
<ul>
<li>Crear un proyecto genérico base opensource (un poco más grande que un ABC para no empezar de cero nuevos proyectos)</li>
<li>Generar documentación del proyecto, para demostrar lo práctico y robusto que nos puede ofrecer utilizar EJB 3.1</li>
<li>Utilizar jquery y bootstrap en el frontend (Actualmente utilizó  Kendoui Pro, pero tiene derechos de uso y no podría dejar libre el código)</li>
<li>Realizar pruebas unitarias al proyecto con JUNIT</li>
<li>Tratar de medir rigurosamente el tiempo de desarrollo de cada modulo</li>
</ul>
<h3>Requerimientos</h3>
<ul>
<li>Utilizar el patrón de diseño <a href='http://www.desarrolloweb.com/articulos/que-es-mvc.html' target='_blank'>MVC</a></li>
<li>Construir los siguientes catálogos:
  <ul>
  <li>Avisos</li>
  <li>Bitacora</li>
  <li>Conexiones BD</li>
  <li>Derecho menú (Derecho-Perfil)</li>
  <li>Mensajes</li>
  <li>Perfil (Perfil-Usuario)</li>
  <li>Permisos</li>
  <li>Usuario</li>
  <li>Reportes jasper</li>
  </ul>
</li>
<li>Controlar permisos de navegación (negar o permitir la navegación en base a los catálogos perfil-derecho)</li>
<li>Todos los CRUD´s de catálogos deben mostrar un grid paginado con busquedas por campos</li>
<li>Agregar páginas de perfil (personal)</li>
<li>Reglas de seguridad: 
<ul>
  <li>No permitir navegar a un usuario en un catálogos que no tenga permisos</li>
  <li>Capturar las acciones de un usuario en la bitacora si así se indica (cuando abre y cierra sesión, aún cuando la sesión es cerrada en automatico por el web server y acciones de altas, bajas, buscar, editar).</li>
  <li>Bloquear logeo al tercer intento.</li>
  <li>Bloquear o permitir logeo desde una(s) IP(s) si se le indica. </li>
  <li>Mensajes</li>
  <li>No permitir sql injection en el logeo</li>
  </ul></li>
<li>Agregar avisos y mensajes en el Top Menú</li>
<li>Menú dinámico</li>
<li>Visualizar reportes pdf</li>
</ul>
<h3>Recursos</h3>
<ul>
<li>Base de datos <b>Mysql 5.1</b> (<a href='https://github.com/joalgoca/OpenCoreJava/blob/master/documentos/db/OPENCORE1.0.mwb' target='_blank'>.mwb</a>,.sql)</li>
<li>IDE <b>Netbeans 8.1</b></li>
<li>Contenedor de versiones <b>Github</b></li>
<li>Javascript library <b>jquery</b></li>
<li>Css framework <b>Bootstrap 3</b></li>
<li>Fuentes <b>font-awesome 4.6.3</b></li>
<li>Template <b>startbootstrap-sb-admin-2-1.0.5</b></li>
<li>Reportes <b>Jasperreports 5.1</b></li>
<li>Capa de persistencia <b>EJB 3.1</b></li>
<li>Utilizar <b>jquery</b></li>
</ul>
