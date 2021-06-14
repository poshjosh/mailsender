package readme;

import com.looseboxes.fileclient.FileHandler;
import com.looseboxes.fileclient.LocalFileHandler;
import com.looseboxes.gmailapi.MessageUtil;
import com.looseboxes.gmailapi.config.Api;
import com.looseboxes.gmailapi.config.GmailConfig;
import com.looseboxes.gmailapi.config.OAuth2;
import com.looseboxes.mailsender.MailSender;
import com.looseboxes.mailsender.google.MailSenderGmail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.mail.MessagingException;

/**
 * @author chinomso ikwuagwu
 */
public class ExampleUsage {
    
    public static void main(String[] args) {
        
        final String appName = "MyApp";

        FileHandler fileHandler = new LocalFileHandler();

        Path dir = Paths.get(System.getProperty("user.home"), ".webstore", "config", "gmail");
        GmailConfig gmailConfig = new GmailConfig()
                .api(new Api().credentialsFilePath(dir.resolve("client.json").toString()).tokensDirectoryPath(dir.toString()))
                .oAuth2(new OAuth2().accessTokenScopes(Arrays.asList("https://www.googleapis.com/auth/gmail.send")));

        MailSender mailSender = new MailSenderGmail(appName, fileHandler, gmailConfig);

        final String from = "noreply@herobids.com";
        final String to = "posh.bc@gmail.com";
        final String subject = "Subject " + LocalDateTime.now();
        final String content = "Message " + LocalDateTime.now();
        
        try{
            
            mailSender.send(MessageUtil.createEmail(to, from, subject, content));
            
        }catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
