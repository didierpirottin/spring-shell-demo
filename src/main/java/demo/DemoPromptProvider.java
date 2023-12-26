package demo;

import demo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemoPromptProvider implements PromptProvider {
    public static final AttributedStyle NOT_LOGGED_STYLE =
            AttributedStyle.DEFAULT.foreground(AttributedStyle.RED);
    public static final AttributedStyle LOGGED_STYLE =
            AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN);

    private final LoginService loginService;

    private static AttributedString notLoggedPrompt() {
        return new AttributedStringBuilder()
                .style(NOT_LOGGED_STYLE)
                .append("not logged > ")
                .toAttributedString();
    }

    @Override
    public AttributedString getPrompt() {
        if (loginService.isLogged()) {
            return loggedPrompt();
        } else {
            return notLoggedPrompt();
        }
    }

    private AttributedString loggedPrompt() {
        return new AttributedStringBuilder()
                .style(LOGGED_STYLE)
                .append(loginService.getLoggedUser())
                .append(" > ")
                .toAttributedString();
    }
}
