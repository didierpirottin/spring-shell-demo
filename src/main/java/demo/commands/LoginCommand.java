package demo.commands;

import demo.services.InputService;
import demo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

import static java.util.Optional.ofNullable;

@Command(group = "Demo")
@RequiredArgsConstructor
public class LoginCommand {
    private final LoginService loginService;
    private final InputService inputService;

    @Command(description = "Login with user and password")
    @CommandAvailability(provider = "loginAvailability")
    String login(@Option(required = false) String user) {
        loginService.login(
                ofNullable(user).orElseGet(
                        () -> inputService.getInput("Enter user name", "didier")),
                inputService.getMaskedInput("Enter password", "password"));
        return "Hello " + loginService.getLoggedUser() + ", you are now logged in !";
    }

    @Command(description = "Logout current user")
    @CommandAvailability(provider = "sessionActiveAvailability")
    String logout() {
        String user = loginService.getLoggedUser();
        loginService.logout();
        return "Bye " + user + ",you are now logged out !";
    }
}
