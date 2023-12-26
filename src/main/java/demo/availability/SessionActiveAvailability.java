package demo.availability;

import demo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionActiveAvailability implements AvailabilityProvider {
    private final LoginService loginService;

    @Override
    public Availability get() {
        return loginService.isLogged() ?
                Availability.available()
                : Availability.unavailable("you are not logged yet !");
    }
}
