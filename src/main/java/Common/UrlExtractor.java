package Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {

    public static String confirmationUrlResetPassword(String emailContent) {
        if (emailContent == null || emailContent.isEmpty()) {
            return null;
        }
        // regex tìm URL
        Pattern pattern = Pattern.compile("https?://\\S+reset-password\\S*");
        Matcher matcher = pattern.matcher(emailContent);

        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
