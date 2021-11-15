package com.looseboxes.mailsender;

import com.looseboxes.gmailapi.GmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class MailSenderComposite implements MailSender{

    private static final Logger LOG = LoggerFactory.getLogger(MailSenderComposite.class);

    private final List<MailSender> delegates;

    public MailSenderComposite(List<MailSender> delegates) {
        this.delegates = Collections.unmodifiableList(new ArrayList<>(delegates));
    }

    @Override
    public Map send(MimeMessage mimeMessage) throws MessagingException {

        MessagingException exception = null;
        Map response = null;
        boolean success = false;
        final int size = delegates.size();
        for(int i=0; i<size; i++) {

            final MailSender delegate = delegates.get(i);
            try {

                response = delegate.send(mimeMessage);

                if (delegate.isSuccessful(response)) {
                    success = true;
                    break;
                }
            }catch(MessagingException e) {
                LOG.warn("Failed to send mail using {} {} of {}, instance: {}",
                        MailSender.class.getSimpleName(), (i + 1), size, delegate);
                if(exception == null) {
                    exception = e;
                }else{
                    exception.addSuppressed(e);
                }
            }
        }

        if(exception != null && !success) {
            throw exception;
        }

        return response == null ? Collections.EMPTY_MAP : response;
    }
}
