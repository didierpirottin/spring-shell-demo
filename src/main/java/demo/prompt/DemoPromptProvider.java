package demo.prompt;

import demo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import static demo.helpers.StyleHelper.ERROR;
import static demo.helpers.StyleHelper.GOOD;

@Component
@RequiredArgsConstructor
public class DemoPromptProvider implements PromptProvider {
    private final LoginService loginService;

    @Override
    public AttributedString getPrompt() {
        if (loginService.isLogged()) {
            return loggedPrompt();
        } else {
            return notLoggedPrompt();
        }
    }


    private static AttributedString notLoggedPrompt() {
        return new AttributedStringBuilder()
                .style(ERROR)
                .append("not logged > ")
                .toAttributedString();
    }

    private AttributedString loggedPrompt() {
        return new AttributedStringBuilder()
                .style(GOOD)
                .append(loginService.getLoggedUser())
                .append(" > ")
                .toAttributedString();
    }
}
