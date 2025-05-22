package persistence;

import model.Ticket;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TicketRepository {
    private static final Logger LOGGER = Logger.getLogger(TicketRepository.class.getName());
    private final Path csvPath;

    public TicketRepository(Path csvPath) {
        this.csvPath = csvPath;
    }

    public List<Ticket> loadAll() {
        if (!Files.exists(csvPath)) {
            return new ArrayList<>();
        }
        try {
            return Files.lines(csvPath)
                    .filter(line -> !line.isBlank())
                    // Omitir la cabecera si existe
                    .filter(line -> !line.startsWith("trackingId"))
                    .map(this::parseLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading tickets", e);
            return new ArrayList<>();
        }
    }

    public void saveAll(List<Ticket> tickets) {
        try {
            List<String> lines = tickets.stream()
                    .map(this::formatTicket)
                    .collect(Collectors.toList());
            Files.write(csvPath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving tickets", e);
        }
    }

    public void add(Ticket ticket) {
        try {
            // Asegurar existencia de directorio
            Path parent = csvPath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            // Asegurar existencia de archivo con cabecera
            if (!Files.exists(csvPath)) {
                Files.createFile(csvPath);
                String header = "trackingId,updated,name,subject,status,lastReplier,priority";
                Files.write(csvPath, List.of(header), StandardOpenOption.APPEND);
            }
            // Añadir línea de ticket
            String line = formatTicket(ticket);
            Files.write(csvPath, List.of(line), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error appending ticket", e);
        }
    }

    private Ticket parseLine(String line) {
        String[] parts = line.split("(?<!\\\\),", -1);
        return new Ticket(
                parts[0],
                LocalDateTime.parse(parts[1]),
                unescape(parts[2]),
                unescape(parts[3]),
                unescape(parts[4]),
                unescape(parts[5]),
                unescape(parts[6])
        );
    }

    private String formatTicket(Ticket t) {
        return String.join(",",
                t.getTrackingId(),
                t.getUpdated().toString(),
                escape(t.getName()),
                escape(t.getSubject()),
                escape(t.getStatus()),
                escape(t.getLastReplier()),
                escape(t.getPriority())
        );
    }

    private String escape(String text) {
        return text.replace("\\", "\\\\").replace(",", "\\,");
    }

    private String unescape(String text) {
        return text.replace("\\,", ",").replace("\\\\", "\\");
    }
}
