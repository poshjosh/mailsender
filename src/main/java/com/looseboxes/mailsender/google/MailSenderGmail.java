package com.looseboxes.mailsender.google;

import com.looseboxes.fileclient.FileHandler;
import com.looseboxes.gmailapi.GmailFactory;
import com.looseboxes.gmailapi.GmailFactoryImpl;
import com.looseboxes.gmailapi.MailException;
import com.looseboxes.mailsender.config.GmailProperties;
import com.looseboxes.mailsender.MailSender;
import java.util.Map;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author hp
 */
public class MailSenderGmail implements MailSender{
   
    private final GmailFactory gmailFactory;
    
    public MailSenderGmail(String appName, FileHandler fileHandler, GmailProperties props) {
        this(new GmailFactoryImpl(appName, new GmailConfigImpl(fileHandler, props)));
    }

    public MailSenderGmail(GmailFactory gmailFactory) {
        this.gmailFactory = Objects.requireNonNull(gmailFactory);
    }

    @Override
    public Map send(MimeMessage mimeMessage) throws MessagingException {
        try{
            final Map result = gmailFactory.getSender().send(mimeMessage);
            return result;
        }catch(MailException e) {
            throw new MessagingException(e.getMessage(), e);
        }
    }
}
