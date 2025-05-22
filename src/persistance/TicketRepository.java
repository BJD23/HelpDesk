package persistance;

import model.Ticket;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase encargada de la persistencia de Tickets en un fichero CSV.
 * Formato CSV: trackingId,updated,name,subject,status,lastReplier,priority
 */
public class TicketRepository {
    private static Path csvPath = null;

    public TicketRepository(String fileName) {
        csvPath = Paths.get(fileName);
    }

    /**
     * Carga todos los tickets desde el CSV.
     */
    public List<Ticket> loadAll() throws IOException {
        if (!Files.exists(csvPath)) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            return reader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseLine)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Guarda la lista completa de tickets en el CSV, reescribiendo el fichero.
     */
    public void saveAll(List<Ticket> tickets) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath)) {
            for (Ticket t : tickets) {
                writer.write(formatTicket(t));
                writer.newLine();
            }
        }
    }

    /**
     * Añade un solo ticket al final del CSV.
     */
    public void add(Ticket t) throws IOException {
        if (!Files.exists(csvPath)) {
            Files.createFile(csvPath);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardOpenOption.APPEND)) {
            writer.write(formatTicket(t));
            writer.newLine();
        }
    }

    private Ticket parseLine(String line) {
        String[] parts = line.split(",", -1);
        return new Ticket(
                parts[0],
                LocalDateTime.parse(parts[1]),
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6]
        );
    }

    private String formatTicket(Ticket t) {
        // Escapamos comas en los textos si fuera necesario
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

    private String escape(String value) {
        return value.replace(",", "\\,");
    }
}