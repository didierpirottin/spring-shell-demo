package demo.helpers;

import org.jline.utils.AttributedStyle;

public interface StyleHelper {
    AttributedStyle IMPORTANT = AttributedStyle.BOLD.foreground(AttributedStyle.GREEN);
    AttributedStyle FILE_LINK = AttributedStyle.DEFAULT.italic().foreground(AttributedStyle.BLUE);
    AttributedStyle ERROR = AttributedStyle.DEFAULT.foreground(AttributedStyle.RED);
    AttributedStyle GOOD = AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN);
}
