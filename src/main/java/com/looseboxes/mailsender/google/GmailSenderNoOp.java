package com.looseboxes.mailsender.google;

import com.looseboxes.gmailapi.GmailSender;
import com.looseboxes.gmailapi.MailException;
import java.util.Collections;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hp
 */
public class GmailSenderNoOp implements GmailSender{
    
    private static final Logger LOG = LoggerFactory.getLogger(GmailSenderNoOp.class);

    @Override
    public Map send(MimeMessage mimeMessage) throws MailException {
        try {
            LOG.info("Message will not be sent as this is a NO_OP MailSender. Sender: {}, Subject: {}, to {} recipients.",
                    mimeMessage.getSender(), mimeMessage.getSubject(), mimeMessage.getAllRecipients().length);
        }catch(MessagingException e) {
            throw new MailException(e);
        }
        return Collections.EMPTY_MAP;
    }
}
