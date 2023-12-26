package demo.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LoginService {
    private String loggedUser;

    public void login(String user, String password) {
        loggedUser = user;
    }

    public void logout() {
        loggedUser = null;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }

}
