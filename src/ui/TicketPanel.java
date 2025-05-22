package ui;

import model.Ticket;
import persistence.TicketRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TicketPanel extends JPanel {

    private JTable ticketTable;
    private DefaultTableModel tableModel;
    private TicketRepository ticketRepository;

    public TicketPanel(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        setLayout(new BorderLayout());
        initializeTable();
        loadTickets();
    }

    private void initializeTable() {
        String[] columnNames = {
                "Tracking ID", "Updated", "Name", "Subject",
                "Status", "Last Replier", "Priority"
        };
        // Modelo con celdas no editables
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ticketTable = new JTable(tableModel);
        ticketTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadTickets() {
        tableModel.setRowCount(0);
        List<Ticket> tickets = ticketRepository.loadAll();
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

    public void refresh() {
        loadTickets();
    }
}