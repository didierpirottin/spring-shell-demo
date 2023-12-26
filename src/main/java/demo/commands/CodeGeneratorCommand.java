package demo.commands;

import demo.helpers.StyleHelper;
import demo.services.CodeGenerationService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.springframework.shell.command.annotation.Command;

import java.io.FileWriter;
import java.io.IOException;

@Command(group = "Demo")
@RequiredArgsConstructor
public class CodeGeneratorCommand {
    private final CodeGenerationService openAiService;

    @Command(alias = "gen", description = "Code generation sample")
    AttributedString generateCode() throws IOException {
        String code = openAiService.generateCode();
        try (FileWriter writer = new FileWriter("src/main/java/demo/hello/HelloWorld.java")) {
            writer.write(code);
        }
        return new AttributedStringBuilder()
                .append("Class has been generated : ")
                .style(StyleHelper.FILE_LINK)
                .append("HelloWorld.java")
                .toAttributedString();
    }
}
