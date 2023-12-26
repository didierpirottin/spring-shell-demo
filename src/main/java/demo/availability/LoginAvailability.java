package demo.availability;

import demo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAvailability implements AvailabilityProvider {
    private final LoginService loginService;

    @Override
    public Availability get() {
        return loginService.isLogged() ?
                Availability.unavailable("you are already logged in !")
                : Availability.available();
    }
}
