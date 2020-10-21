package com.looseboxes.mailsender.google;

import com.looseboxes.gmailapi.GmailSender;
import com.looseboxes.gmailapi.MailException;
import java.util.Collections;
import java.util.Map;
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
        LOG.info("Message will not be sent as this is a NO_OP Mail Sender. Message: {}", mimeMessage);
        return Collections.EMPTY_MAP;
    }
}
