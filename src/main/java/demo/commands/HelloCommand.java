package demo.commands;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import static demo.helpers.StyleHelper.IMPORTANT;

@Command(group = "Demo")
public class HelloCommand {

    @Command(description = "Hello world sample")
    AttributedString hello(@Option(defaultValue = "World") String name) {
        return new AttributedStringBuilder()
                .style(IMPORTANT)
                .append("Hello ").append(name).append(" !")
                .toAttributedString();
    }
}
