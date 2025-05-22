package ui;

import model.Ticket;
import persistance.TicketRepository;  // Import corregido

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TicketPanel extends JPanel {

    private JTable ticketTable;
    private DefaultTableModel tableModel;          // Ya no es estático
    private TicketRepository ticketRepository;

    public TicketPanel() {
        setLayout(new BorderLayout());
        // Usar la misma ruta que tu HelpDeskApp al crear el repositorio
        ticketRepository = new TicketRepository("data/tickets.csv");
        initializeTable();
        loadTickets();
    }

    private void initializeTable() {
        String[] columnNames = {
                "Tracking ID", "Updated", "Name", "Subject",
                "Status", "Last Replier", "Priority"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        ticketTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadTickets() {
        // Limpiamos cualquier fila previa
        tableModel.setRowCount(0);

        List<Ticket> tickets;
        try {
            tickets = ticketRepository.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (Ticket t : tickets) {
            Object[] row = {
                    t.getTrackingId(),
                    t.getUpdated(),
                    t.getName(),
                    t.getSubject(),
                    t.getStatus(),
                    t.getLastReplier(),
                    t.getPriority()
            };
            tableModel.addRow(row);
        }
    }

    /** Permite recargar la tabla tras cambios en el CSV */
    public void refresh() {
        loadTickets();
    }
}
