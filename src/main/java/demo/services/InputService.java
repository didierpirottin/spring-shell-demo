package demo.services;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.component.StringInput;
import org.springframework.shell.style.TemplateExecutor;
import org.springframework.stereotype.Service;

import static org.springframework.shell.component.StringInput.StringInputContext.empty;

@Service
@RequiredArgsConstructor
public class InputService {
    private final Terminal terminal;
    private final ResourceLoader resourceLoader;
    private final TemplateExecutor templateExecutor;

    public String getInput(String message, String defaultValue) {
        return createStringInput(message, defaultValue)
                .run(empty())
                .getResultValue();
    }

    public String getMaskedInput(String message, String defaultValue) {
        var stringInput = createStringInput(message, defaultValue);
        stringInput.setMaskCharacter('*');
        return stringInput
                .run(empty())
                .getResultValue();
    }

    private StringInput createStringInput(String message, String defaultValue) {
        StringInput component = new StringInput(terminal, message, defaultValue);
        component.setResourceLoader(resourceLoader);
        component.setTemplateExecutor(templateExecutor);
        return component;
    }

}
