package Config;

import lombok.Data;
import java.util.Properties;

@Data
public class EmailConfig {

    private Properties properties;
    private String imapHost;
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;

    public EmailConfig() {
        this.imapHost = "imap.mailtrap.io"; // để đọc email
        this.smtpHost = "sandbox.smtp.mailtrap.io"; // để gửi email
        this.smtpPort = 2525;

        this.username = "2aab77f7c7116c"; // Mailtrap username
        this.password = "80d64e862ca674"; // Mailtrap password

        this.properties = new Properties();
        this.properties.put("mail.store.protocol", "imaps");
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", "true");
        this.properties.put("mail.smtp.host", smtpHost);
        this.properties.put("mail.smtp.port", String.valueOf(smtpPort));
    }
}
