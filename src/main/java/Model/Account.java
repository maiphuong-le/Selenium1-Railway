package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String email;
    private String password;
    private String confirmPassword;
    private String pid;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
