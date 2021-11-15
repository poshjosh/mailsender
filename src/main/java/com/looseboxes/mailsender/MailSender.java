package com.looseboxes.mailsender;

import com.looseboxes.gmailapi.MessageUtil;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author hp
 */
public interface MailSender {

    default boolean isSuccessful(Map messageSendingResponse) {
        return messageSendingResponse != null && !messageSendingResponse.isEmpty();
    }

    default Map send(String from, String [] to, String subject, String content, String charset, String subtype)
            throws MessagingException{

        if(from == null || from.isEmpty()) {
            throw new IllegalArgumentException("Mail sender (from) must be specified");
        }

        if(to == null || to.length == 0) {
            throw new IllegalArgumentException("Mail recipient (to) must be specified");
        }

        if((subject == null || subject.isEmpty()) && (content == null || content.isEmpty())) {
            throw new IllegalArgumentException("Either message subject or content must be specified");
        }

        MimeMessage mimeMessage = MessageUtil.createEmail(to, from, subject, content, charset, subtype);

        return send(mimeMessage);
    }

    Map send(MimeMessage mimeMessage) throws MessagingException;
}
