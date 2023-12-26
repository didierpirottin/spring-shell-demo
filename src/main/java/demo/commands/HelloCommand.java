package demo.commands;

import demo.helpers.StyleHelper;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Demo")
public class HelloCommand {

    @Command(description = "Hello Sample")
    AttributedString hello(@Option(defaultValue = "World") String name) {
        return new AttributedStringBuilder()
                .append("Hello ")
                .style(StyleHelper.IMPORTANT)
                .append(name)
                .style(AttributedStyle.DEFAULT)
                .toAttributedString();
    }
}
