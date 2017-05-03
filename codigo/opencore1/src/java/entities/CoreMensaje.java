/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jgonzalezc
 */
@Entity
@Table(name = "core_mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoreMensaje.findAll", query = "SELECT c FROM CoreMensaje c"),
    @NamedQuery(name = "CoreMensaje.findByCoreMensajeId", query = "SELECT c FROM CoreMensaje c WHERE c.coreMensajeId = :coreMensajeId"),
    @NamedQuery(name = "CoreMensaje.findByTitulo", query = "SELECT c FROM CoreMensaje c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "CoreMensaje.findByTexto", query = "SELECT c FROM CoreMensaje c WHERE c.texto = :texto"),
    @NamedQuery(name = "CoreMensaje.findByFechaCreacion", query = "SELECT c FROM CoreMensaje c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CoreMensaje.findByToId", query = "SELECT c FROM CoreMensaje c WHERE c.toId = :toId"),
    @NamedQuery(name = "CoreMensaje.findByFromId", query = "SELECT c FROM CoreMensaje c WHERE c.fromId = :fromId"),
    @NamedQuery(name = "CoreMensaje.findByToType", query = "SELECT c FROM CoreMensaje c WHERE c.toType = :toType"),
    @NamedQuery(name = "CoreMensaje.findByFromType", query = "SELECT c FROM CoreMensaje c WHERE c.fromType = :fromType"),
    @NamedQuery(name = "CoreMensaje.findByEstatus", query = "SELECT c FROM CoreMensaje c WHERE c.estatus = :estatus")})
public class CoreMensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CORE_MENSAJE_ID")
    private Integer coreMensajeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TEXTO")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TO_ID")
    private int toId;
    @Column(name = "FROM_ID")
    private Integer fromId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TO_TYPE")
    private String toType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "FROM_TYPE")
    private String fromType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ESTATUS")
    private String estatus;

    public CoreMensaje() {
    }

    public CoreMensaje(Integer coreMensajeId) {
        this.coreMensajeId = coreMensajeId;
    }

    public CoreMensaje(Integer coreMensajeId, String titulo, String texto, Date fechaCreacion, int toId, String toType, String fromType, String estatus) {
        this.coreMensajeId = coreMensajeId;
        this.titulo = titulo;
        this.texto = texto;
        this.fechaCreacion = fechaCreacion;
        this.toId = toId;
        this.toType = toType;
        this.fromType = fromType;
        this.estatus = estatus;
    }

    public Integer getCoreMensajeId() {
        return coreMensajeId;
    }

    public void setCoreMensajeId(Integer coreMensajeId) {
        this.coreMensajeId = coreMensajeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coreMensajeId != null ? coreMensajeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreMensaje)) {
            return false;
        }
        CoreMensaje other = (CoreMensaje) object;
        if ((this.coreMensajeId == null && other.coreMensajeId != null) || (this.coreMensajeId != null && !this.coreMensajeId.equals(other.coreMensajeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss");
        String devolver="{" +
            "\"coreMensajeId\":"+coreMensajeId+"," +
            "\"estatus\":\""+estatus+"\"," +
            "\"fechaCreacion\":\""+format2.format(fechaCreacion)+"\"," +
            "\"fechaCreacionHora\":\""+format.format(fechaCreacion)+"\"," +
            "\"titulo\":\""+titulo+"\"," +
            "\"texto\":\""+texto.replaceAll("&lt;", "<").replaceAll("&gt;", ">")+"\"," +
            "\"toType\":\""+toType+"\"," +
            "\"fromId\":"+fromId+"," +
            "\"fromType\":\""+fromType+"\"," +
            "\"toId\":"+toId+"";                
        return devolver+"}";
    }
    
}
