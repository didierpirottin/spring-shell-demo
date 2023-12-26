package demo.commands;

import demo.helpers.StyleHelper;
import demo.jooq.generated.tables.records.ConferencesRecord;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jooq.DSLContext;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.table.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static demo.jooq.generated.tables.Conferences.CONFERENCES;

@Command(group = "Demo")
@RequiredArgsConstructor
public class ReportCommand {
    private final DSLContext dslContext;
    private final Terminal terminal;

    @Command(alias = "conf", description = "Provide a list of Java conferences in Europe")
    @CommandAvailability(provider = "sessionActiveAvailability")
    public Table javaConferences() throws IOException {
        List<ConferencesRecord> conferences = fetchConferencesFromDB();
        storeAsCsvFile(conferences);
        return buildTable(conferences);
    }


    private static void printRecord(ConferencesRecord conferencesRecord, CSVPrinter printer) {
        try {
            printer.printRecord(
                    conferencesRecord.getName(),
                    conferencesRecord.getCity(),
                    conferencesRecord.getCountry());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] headerAsArray() {
        return (String[]) getHeader().sequencedKeySet().stream().toArray(String[]::new);
    }

    private static Table buildTable(List<ConferencesRecord> conferences) {
        TableModel model = new BeanListTableModel<ConferencesRecord>(
                conferences,
                getHeader());
        TableBuilder tableBuilder = new TableBuilder(model);

        tableBuilder.on(CellMatchers.table())
                .addSizer(new NoWrapSizeConstraints())
                .addAligner(SimpleHorizontalAligner.center);

        return tableBuilder
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }

    private static LinkedHashMap getHeader() {
        LinkedHashMap headers = new LinkedHashMap();
        headers.put("Name", "name");
        headers.put("City", "city");
        headers.put("Country", "country");
        return headers;
    }


    private void storeAsCsvFile(List<ConferencesRecord> conferences) throws IOException {
        FileWriter writer = new FileWriter("conferences.csv");
        CSVFormat csvFormat = getCsvFormat();
        try (final CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            conferences.forEach(conferencesRecord ->
                    printRecord(conferencesRecord, printer));
        }
        writeMessageToTerminal();
    }

    private void writeMessageToTerminal() {
        terminal.writer().println(
                new AttributedStringBuilder()
                        .append("Conference list has been stored in ")
                        .style(StyleHelper.FILE_LINK)
                        .append("conferences.csv")
                        .toAttributedString()
                        .toAnsi());
    }

    private static CSVFormat getCsvFormat() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headerAsArray())
                .build();
        return csvFormat;
    }

    private List<ConferencesRecord> fetchConferencesFromDB() {
        List<ConferencesRecord> conferences = dslContext
                .selectFrom(CONFERENCES)
                .fetch()
                .stream()
                .toList();
        return conferences;
    }

}
