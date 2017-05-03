/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jgonzalezc
 */
@Entity
@Table(name = "core_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoreUsuario.findAll", query = "SELECT c FROM CoreUsuario c"),
    @NamedQuery(name = "CoreUsuario.findByCoreUsuarioId", query = "SELECT c FROM CoreUsuario c WHERE c.coreUsuarioId = :coreUsuarioId"),
    @NamedQuery(name = "CoreUsuario.findByUsuario", query = "SELECT c FROM CoreUsuario c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "CoreUsuario.findByContrasena", query = "SELECT c FROM CoreUsuario c WHERE c.contrasena = :contrasena"),
    @NamedQuery(name = "CoreUsuario.findByNombre", query = "SELECT c FROM CoreUsuario c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CoreUsuario.findByApellidos", query = "SELECT c FROM CoreUsuario c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "CoreUsuario.findByEmail", query = "SELECT c FROM CoreUsuario c WHERE c.email = :email"),
    @NamedQuery(name = "CoreUsuario.findByTelefono", query = "SELECT c FROM CoreUsuario c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "CoreUsuario.findByEstatus", query = "SELECT c FROM CoreUsuario c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "CoreUsuario.findByEsSuper", query = "SELECT c FROM CoreUsuario c WHERE c.esSuper = :esSuper"),
    @NamedQuery(name = "CoreUsuario.findByEstilo", query = "SELECT c FROM CoreUsuario c WHERE c.estilo = :estilo"),
    @NamedQuery(name = "CoreUsuario.findByVigencia", query = "SELECT c FROM CoreUsuario c WHERE c.vigencia = :vigencia"),
    @NamedQuery(name = "CoreUsuario.findByAplicaBitacora", query = "SELECT c FROM CoreUsuario c WHERE c.aplicaBitacora = :aplicaBitacora"),
    @NamedQuery(name = "CoreUsuario.findByIsLogin", query = "SELECT c FROM CoreUsuario c WHERE c.isLogin = :isLogin"),
    @NamedQuery(name = "CoreUsuario.findByMultiSesion", query = "SELECT c FROM CoreUsuario c WHERE c.multiSesion = :multiSesion"),
    @NamedQuery(name = "CoreUsuario.findByIpRestriccion", query = "SELECT c FROM CoreUsuario c WHERE c.ipRestriccion = :ipRestriccion")})
public class CoreUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CORE_USUARIO_ID")
    private Integer coreUsuarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "APELLIDOS")
    private String apellidos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 22)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ESTATUS")
    private String estatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ES_SUPER")
    private String esSuper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTILO")
    private String estilo;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APLICA_BITACORA")
    private boolean aplicaBitacora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_LOGIN")
    private boolean isLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MULTI_SESION")
    private boolean multiSesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IP_RESTRICCION")
    private boolean ipRestriccion;
    @JoinTable(name = "core_usuario_perfil", joinColumns = {
        @JoinColumn(name = "CORE_USUARIO_ID", referencedColumnName = "CORE_USUARIO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "CORE_PERFIL_ID", referencedColumnName = "CORE_PERFIL_ID")})
    @ManyToMany
    private List<CorePerfil> corePerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coreUsuarioId")
    private List<CorePermisosIp> corePermisosIpList;

    public CoreUsuario() {
    }

    public CoreUsuario(Integer coreUsuarioId) {
        this.coreUsuarioId = coreUsuarioId;
    }

    public CoreUsuario(Integer coreUsuarioId, String usuario, String contrasena, String nombre, String apellidos, String email, String estatus, String esSuper, String estilo, Date vigencia, boolean aplicaBitacora, boolean isLogin, boolean multiSesion, boolean ipRestriccion) {
        this.coreUsuarioId = coreUsuarioId;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.estatus = estatus;
        this.esSuper = esSuper;
        this.estilo = estilo;
        this.vigencia = vigencia;
        this.aplicaBitacora = aplicaBitacora;
        this.isLogin = isLogin;
        this.multiSesion = multiSesion;
        this.ipRestriccion = ipRestriccion;
    }

    public Integer getCoreUsuarioId() {
        return coreUsuarioId;
    }

    public void setCoreUsuarioId(Integer coreUsuarioId) {
        this.coreUsuarioId = coreUsuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEsSuper() {
        return esSuper;
    }

    public void setEsSuper(String esSuper) {
        this.esSuper = esSuper;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public boolean getAplicaBitacora() {
        return aplicaBitacora;
    }

    public void setAplicaBitacora(boolean aplicaBitacora) {
        this.aplicaBitacora = aplicaBitacora;
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean getMultiSesion() {
        return multiSesion;
    }

    public void setMultiSesion(boolean multiSesion) {
        this.multiSesion = multiSesion;
    }

    public boolean getIpRestriccion() {
        return ipRestriccion;
    }

    public void setIpRestriccion(boolean ipRestriccion) {
        this.ipRestriccion = ipRestriccion;
    }

    @XmlTransient
    public List<CorePerfil> getCorePerfilList() {
        return corePerfilList;
    }

    public void setCorePerfilList(List<CorePerfil> corePerfilList) {
        this.corePerfilList = corePerfilList;
    }

    @XmlTransient
    public List<CorePermisosIp> getCorePermisosIpList() {
        return corePermisosIpList;
    }

    public void setCorePermisosIpList(List<CorePermisosIp> corePermisosIpList) {
        this.corePermisosIpList = corePermisosIpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coreUsuarioId != null ? coreUsuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreUsuario)) {
            return false;
        }
        CoreUsuario other = (CoreUsuario) object;
        if ((this.coreUsuarioId == null && other.coreUsuarioId != null) || (this.coreUsuarioId != null && !this.coreUsuarioId.equals(other.coreUsuarioId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String devolver="{" +
            "\"coreUsuarioId\":"+coreUsuarioId+"," +
            "\"usuario\":\""+usuario+"\"," +
            "\"contrasena\":\"SAME\"," +
            "\"nombre\":\""+nombre+"\"," +
            "\"apellidos\":\""+apellidos+"\"," +
            "\"email\":\""+email+"\"," +
            "\"telefono\":"+(telefono!=null?"\""+telefono+"\"":null)+"," +
            "\"estilo\":\""+estilo+"\"," +
            "\"esSuper\":\""+esSuper+"\"," +
            "\"isLogin\":"+isLogin+"," +
            "\"multiSesion\":"+multiSesion+"," +
            "\"aplicaBitacora\":"+aplicaBitacora+"," +
            "\"ipRestriccion\":"+ipRestriccion+"," +
            "\"estatus\":\""+estatus+"\"";                
        return devolver+"}";
    }
}
