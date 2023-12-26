package demo.commands;

import demo.helpers.StyleHelper;
import demo.services.CodeGenerationService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(group = "Demo")
@RequiredArgsConstructor
public class CodeGeneratorCommand {
    private final CodeGenerationService codeGenerationService;

    @Command(alias = "gen", description = "Code generation sample")
    @CommandAvailability(provider = "sessionActiveAvailability")
    AttributedString generateCode() throws IOException {
        String code = codeGenerationService.generateCode();
        saveCodeAsJavaFile(code);
        return new AttributedStringBuilder()
                .append("Class has been generated : ")
                .style(StyleHelper.FILE_LINK)
                .append("DemoHelloWorld.java")
                .toAttributedString();
    }

    private static void saveCodeAsJavaFile(String code) throws IOException {
        String filePath = "src/main/java/demo/hello/DemoHelloWorld.java";
        createDirectories(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(code);
        }
    }

    private static void createDirectories(String filePath) throws IOException {
        Path directoryPath = Paths.get(filePath).getParent();
        Files.createDirectories(directoryPath);
    }
}
