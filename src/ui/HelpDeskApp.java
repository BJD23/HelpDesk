package ui;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class HelpDeskApp {
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String DASHBOARD = "dashboard";

    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public HelpDeskApp() {
        frame = new JFrame("HelpDesk UV");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        var repo = new persistence.TicketRepository(Paths.get("data/tickets.csv"));
        mainPanel.add(new LoginPanel(this), LOGIN);
        mainPanel.add(new RegisterPanel(this), REGISTER);
        mainPanel.add(new DashboardPanel(this, repo), DASHBOARD);

        frame.add(mainPanel);
        show(LOGIN);
    }

    public void show(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void start() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        new HelpDeskApp().start();
    }
}
