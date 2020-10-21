package com.looseboxes.mailsender;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author hp
 */
public interface MailSender {
 
    Map send(MimeMessage mimeMessage) throws MessagingException;
}
