package rpg.v4.middleware.util;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

public class SimpleMail
{
    public static void main(String args[]) throws Exception
    {
        sendResults("Test Message Contents");
    }

    public static void sendResults(String messageText) throws Exception
    {
        sendResults(messageText, "d20charactersfeedback@googlemail.com");
    }

    public static void sendResults(String messageText, String from) throws Exception
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        String[] SEND_TO = {"d20charactersfeedback@googlemail.com"};

        if (null == from || "".equals(from))
        {
            from = "d20charactersfeedback@googlemail.com";
        }

        new SimpleMail().sendSSLMessage(SEND_TO,
                "d20 Character interaction",
                messageText,
                from);
    }

    public void sendSSLMessage(String recipients[], String subject,
                               String message, String from) throws MessagingException
    {
        // add handlers for main MIME types
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);


        boolean debug = false;
        final String SMTP_HOST_NAME = "smtp.gmail.com";
        final String SMTP_PORT = "465";
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
                {

                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("d20charactersfeedback", "S$$lop23");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
}