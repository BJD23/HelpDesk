package model;

import java.time.LocalDateTime;
import java.util.Objects;

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
        this.trackingId = Objects.requireNonNull(trackingId);
        this.updated = Objects.requireNonNull(updated);
        this.name = Objects.requireNonNull(name);
        this.subject = Objects.requireNonNull(subject);
        this.status = Objects.requireNonNull(status);
        this.lastReplier = Objects.requireNonNull(lastReplier);
        this.priority = Objects.requireNonNull(priority);
    }

    // Getters y setters
    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = Objects.requireNonNull(trackingId); }

    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = Objects.requireNonNull(updated); }

    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = Objects.requireNonNull(subject); }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = Objects.requireNonNull(status); }

    public String getLastReplier() { return lastReplier; }
    public void setLastReplier(String lastReplier) { this.lastReplier = Objects.requireNonNull(lastReplier); }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = Objects.requireNonNull(priority); }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s (status=%s, priority=%s, updated=%s)",
                trackingId, name, subject, status, priority, updated);
    }
}