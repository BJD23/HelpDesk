package ui;

import javax.swing.*;
import java.awt.*;
import persistence.TicketRepository;

public class DashboardPanel extends JPanel {
    private final HelpDeskApp app;
    private final TicketRepository repo;
    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public DashboardPanel(HelpDeskApp app, TicketRepository repo) {
        this.app = app;
        this.repo = repo;
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(40, 55, 71));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(180, getHeight()));

        String[] items = {"Tickets", "Nuevo Ticket", "Cerrar Sesión"};
        for (String item : items) {
            JButton btn = new JButton(item);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
            btn.setOpaque(true);
            btn.setContentAreaFilled(true);
            btn.setBackground(new Color(40, 55, 71));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.addActionListener(e -> switchPanel(item));
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(5));
        }
        add(sidebar, BorderLayout.WEST);

        // Content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(new TicketPanel(repo), "Tickets");
        contentPanel.add(new NewTicketPanel(repo), "Nuevo Ticket");
        add(contentPanel, BorderLayout.CENTER);

        // Start at Tickets view
        cardLayout.show(contentPanel, "Tickets");
    }

    private void switchPanel(String name) {
        if ("Cerrar Sesión".equals(name)) {
            app.show(HelpDeskApp.LOGIN);
        } else {
            if ("Tickets".equals(name)) {
                ((TicketPanel)contentPanel.getComponent(0)).refresh();
            }
            cardLayout.show(contentPanel, name);
        }
    }
}