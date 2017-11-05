package facturatron.email;

import facturatron.Dominio.Configuracion;
import com.phesus.facturatron.persistence.dao.ConfiguracionDao;
import com.phesus.facturatron.presentation.mvc.view.ConfiguracionForm;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 * Class that will handle all the functions to send the email.
 *
 * @author Ulises Estecche
 */
public abstract class EmailMgmt {

    /**
     * Constant to indicate that the smtp host is invalid.
     */
    public static final int WRONG_SMTP = 1;
    /**
     * Constant to indicate that the address to is invalid.
     */
    public static final int WRONG_TO_ADDRESS = 2;
    /**
     * Attribute to indicate the status of the error.
     */
    private int errorStatus;
    /**
     * Regular expression to validate the email.
     */
    public static final String EMAIL_REGULAR_EXPRESSION = "[A-Za-z0-9_.-]+@([A-Za-z0-9]+([\\.-][A-Za-z0-9]+)*)+";
    /**
     * The smtp address of the server.
     */
    private static String mailSmtpHost;
    /**
     * Logger for the class.
     */
    private static Logger logger = Logger.getLogger(EmailMgmt.class.getName());
    /**
     * The email address of the sender.
     */
    private String emailAddressFrom;
    /**
     * The email address of the recipient.
     */
    private String emailAddressTo;
    /**
     * The Reply To address.
     */
    private String emailReplyTo;
    /**
     * The BCC address.
     */
    private String emailBCC;
    /**
     * The footer to be included on each email.
     */
    private StringBuffer footer;
    /**
     * The message content of the email.
     */
    private String msgContent;
    /**
     * The message subject of the email.
     */
    private String msgSubject;
    /**
     * The name of the sender.
     */
    private String nameFrom;
    /**
     * The name of the recipient.
     */
    private String nameTo;
    /**
     * All the attachments of the email.
     */
    private ArrayList<EmailAttachment> lstAttachments;

    /**
     * Class constructor.
     */
    public EmailMgmt(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
        lstAttachments = new ArrayList<EmailAttachment>();
        buildFooter();
    }

    /**
     * Method that will add a new attachment to the email.
     *
     * @param pathToAttach The path of the attachment.
     * @param description The description of the attachment.
     */
    public void addAttachment(String pathToAttach, String description) {
        EmailAttachment emailAttach = new EmailAttachment();
        emailAttach.setPath(pathToAttach);
        emailAttach.setDisposition(EmailAttachment.ATTACHMENT);
        emailAttach.setDescription(description);
        emailAttach.setName(nameFrom);
        lstAttachments.add(emailAttach);
    }

    /**
     * Method that builds the footer of the email.
     */
    private void buildFooter() {
        footer = new StringBuffer();
        footer.append("\n\n");
        footer.append("---------------------------------------------------------");
        footer.append("\n\n");
        footer.append("Este email ha sido enviado como un servicio de Facturatron. ");
        footer.append("\n");
    }

    /**
     * Method that will check if the email is well formed.
     *
     * @param email The object to send the email.
     * @return boolean If the email addresses are valid.
     * @throws EmailException
     */
    private boolean checkAddressFrom(MultiPartEmail email) throws EmailException {
        if (!emailAddressFrom.matches(EMAIL_REGULAR_EXPRESSION)) {
            logger.log(Level.SEVERE, "The email From " + emailAddressFrom
                    + " is not well formed! Please check the address!");
            return false;
        }
        if (nameFrom != null && !nameFrom.equals("")) {
            email.setFrom(emailAddressFrom, nameFrom);
        } else {
            email.setFrom(emailAddressFrom, "Facturatron");
        }
        return true;
    }

    /**
     * Method that will check if the email address to is well formed.
     *
     * @param email
     * @return boolean
     * @throws EmailException
     */
    private boolean checkAddressTo(MultiPartEmail email) throws EmailException {
        if (emailAddressTo == null || emailAddressTo.equals("")) {
            errorStatus = WRONG_TO_ADDRESS;
            //logger.log(Level.SEVERE, "No Email Address To has been set!");
            return false;
        } else {
            emailAddressTo = emailAddressTo.trim();
            if (!emailAddressTo.matches(EMAIL_REGULAR_EXPRESSION)) {
                errorStatus = WRONG_TO_ADDRESS;
                logger.log(Level.SEVERE, "The emailTo " + emailAddressTo
                        + " is not well formed! Please check the address!");
                return false;
            }
        }
        if (nameTo != null && !nameTo.equals("") && !nameTo.trim().equals("")) {
            email.addTo(emailAddressTo, nameTo);
        } else {
            email.addTo(emailAddressTo);
        }
        return true;
    }

    /**
     * The main method to send the email. All the emails pass through here.
     */
    protected boolean sendEmail() {
        Configuracion configuracion = new ConfiguracionDao().load();
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(configuracion.getSmtpHost());
        setEmailAddressFrom(configuracion.getCorreoRemitente());
        if (email.getHostName() == null || email.getHostName().equals("")) {
            errorStatus = WRONG_SMTP;
            return false;
        }
        if (configuracion.getSeguridad().equals(ConfiguracionForm.USA_SSL)) {
            email.setAuthentication(configuracion.getUsuarioSmtp(), configuracion.getClaveSmtp());
            email.setSmtpPort(Integer.valueOf(configuracion.getPuertoSmtp()));
            email.setSSL(true);
        } else if (configuracion.getSeguridad().equals(ConfiguracionForm.USA_TLS)) {
            email.setAuthentication(configuracion.getUsuarioSmtp(), configuracion.getClaveSmtp());
            email.setSmtpPort(Integer.valueOf(configuracion.getPuertoSmtp()));
            email.setTLS(true);
        }
        try {
            if (!checkAddressTo(email)) {
                return false;
            }
            if (!checkAddressFrom(email)) {
                return false;
            }
            email.setSubject(msgSubject);
            email.setMsg(msgContent + footer);
            for (EmailAttachment emailAttachment : lstAttachments) {
                email.attach(emailAttachment);
            }
            email.send();
            return true;
        } catch (EmailException e) {
            logger.log(Level.SEVERE, "", e);
            return false;
        }
    }

    /**
     * Method that will return the email address from.
     *
     * @return String
     */
    public String getEmailAddressFrom() {
        return emailAddressFrom;
    }

    /**
     * Method that will set the email address from.
     *
     * @param emailAddressFrom
     */
    public void setEmailAddressFrom(String emailAddressFrom) {
        this.emailAddressFrom = emailAddressFrom;
    }

    /**
     * Method that will return the email address to.
     *
     * @return String
     */
    public String getEmailAddressTo() {
        return emailAddressTo;
    }

    /**
     * Method that will set the email address to.
     *
     * @param emailAddressTo
     */
    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    /**
     * Method that will return the content of the email.
     *
     * @return String
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * Method that will set the content of the message.
     *
     * @param msgContent
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * Method that will return the subject of the email.
     *
     * @return String
     */
    public String getMsgSubject() {
        return msgSubject;
    }

    /**
     * Method that will set the email subject.
     *
     * @param msgSubject
     */
    public void setMsgSubject(String msgSubject) {
        this.msgSubject = msgSubject;
    }

    /**
     * Method that will return the name of the sender.
     *
     * @return
     */
    public String getNameFrom() {
        return nameFrom;
    }

    /**
     * Method that will set the name of the sender.
     *
     * @param nameFrom
     */
    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    /**
     * Method that will return the name of the recipient.
     *
     * @return String
     */
    public String getNameTo() {
        return nameTo;
    }

    /**
     * Method that will set the name of the recipient.
     *
     * @param nameTo
     */
    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    /**
     * @return the errorStatus
     */
    public int getErrorStatus() {
        return errorStatus;
    }
}
