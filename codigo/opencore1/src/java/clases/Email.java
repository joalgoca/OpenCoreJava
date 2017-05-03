/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author jgonzalezc
 */
public class Email {
    
    private String from;
    private List<InternetAddress> to;
    private List<InternetAddress> cc;
    private List<InternetAddress> co;
    private String subject;
    private String message;

    
    
    public boolean send(String server,String puerto,String tipo,String usuario,String contrasena){
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", server);
            if(tipo.equals("TLS")){
                props.setProperty("mail.smtp.ssl.trust", server);
                props.setProperty("mail.smtp.starttls.enable", "true");
            }
            props.setProperty("mail.smtp.port", puerto);
            props.setProperty("mail.smtp.user", usuario);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage mimessage = new MimeMessage(session);
            mimessage.setFrom(new InternetAddress(from));
            for(InternetAddress toa:to)
                mimessage.setRecipient(Message.RecipientType.TO,toa);
            if(cc!=null){
                for(InternetAddress cca:cc)
                    mimessage.setRecipient(Message.RecipientType.CC,cca);     
            }
            if(co!=null){
                for(InternetAddress coa:co)
                    mimessage.setRecipient(Message.RecipientType.BCC,coa);     
            }
            mimessage.setSubject(subject);
            mimessage.setContent(message, "text/html; charset=utf-8");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(usuario,contrasena);
            t.sendMessage(mimessage, mimessage.getAllRecipients());

            // Cierre.
            t.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public List<InternetAddress> getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(List<InternetAddress> to) {
        this.to = to;
    }
    
    public void addTo(InternetAddress toa) {
        if(this.to==null)
            this.to=new ArrayList();
        this.to.add(toa);
    }

    /**
     * @return the cc
     */
    public List<InternetAddress> getCc() {
        return cc;
    }
    public void addCc(InternetAddress cca) {
        if(this.cc==null)
            this.cc=new ArrayList();
        this.cc.add(cca);
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(List<InternetAddress> cc) {
        this.cc = cc;
    }

    /**
     * @return the co
     */
    public List<InternetAddress> getCo() {
        return co;
    }

    /**
     * @param co the co to set
     */
    public void setCo(List<InternetAddress> co) {
        this.co = co;
    }
    public void addCo(InternetAddress coa) {
        if(this.co==null)
            this.co=new ArrayList();
        this.co.add(coa);
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the mimessage
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the mimessage to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
