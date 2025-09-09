package Common;

import Config.EmailConfig;
import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;
import java.util.Properties;

public class MailReader {

    public String readEmail(EmailConfig emailConfig) {
        String host = emailConfig.getImapHost();
        String username = emailConfig.getUsername();
        String password = emailConfig.getPassword();

        StringBuilder emailContent = new StringBuilder();

        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // lấy email chưa đọc
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            if (messages.length == 0) {
                System.out.println("No new emails found.");
                return null;
            }

            Message message = messages[messages.length - 1]; // email mới nhất
            Object content = message.getContent();

            if (content instanceof String) {
                emailContent.append((String) content);
            } else {
                System.out.println("Unsupported email content type: " + content.getClass());
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailContent.toString();
    }
}
