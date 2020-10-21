# Simplify mail sending with gmail api

### Example Usage

```java
package readme;

import com.looseboxes.fileclient.FileHandler;
import com.looseboxes.fileclient.LocalFileHandler;
import com.looseboxes.gmailapi.MessageUtil;
import com.looseboxes.mailsender.config.GmailProperties;
import com.looseboxes.mailsender.MailSender;
import com.looseboxes.mailsender.google.MailSenderGmail;
import java.util.Arrays;
import javax.mail.MessagingException;

/**
 * @author chinomso ikwuagwu
 */
public class ExampleUsage {
    
    public static void main(String[] args) {
        
        final String appName = "";
        final String credentialsFilePath = "";
        final String tokensDirectoryPath = "";
        
        FileHandler fileHandler = new LocalFileHandler();
        
        GmailProperties props = new GmailProperties();
        props.setAccessTokenScopes(Arrays.asList("https://www.googleapis.com/auth/gmail.send"));
        props.setCredentialsFilePath(credentialsFilePath);
        props.setTokensDirectoryPath(tokensDirectoryPath);
        
        MailSender mailSender = new MailSenderGmail(appName, fileHandler, props);
        
        final String to = "";
        final String from = "";
        final String subject = "";
        final String bodyText = "";
        
        try{
            
            mailSender.send(MessageUtil.createEmail(to, from, subject, bodyText));
            
        }catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
```
