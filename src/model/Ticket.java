package model;

import java.time.LocalDateTime;

public class Ticket {
    private String trackingId;
    private LocalDateTime updated;
    private String name;
    private String subject;
    private String status;
    private String lastReplier;
    private String priority;

    public Ticket(String trackingId,
                  LocalDateTime updated,
                  String name,
                  String subject,
                  String status,
                  String lastReplier,
                  String priority) {
        this.trackingId = trackingId;
        this.updated = updated;
        this.name = name;
        this.subject = subject;
        this.status = status;
        this.lastReplier = lastReplier;
        this.priority = priority;
    }

    // Getters y setters
    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }

    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = updated; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLastReplier() { return lastReplier; }
    public void setLastReplier(String lastReplier) { this.lastReplier = lastReplier; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    @Override
    public String toString() {
        return String.format(
                "Ticket[%s] %s – %s (estatus: %s, prioridad: %s, último: %s, actualizado: %s)",
                trackingId, name, subject, status, priority, lastReplier, updated
        );
    }
}
