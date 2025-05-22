package ui;

import model.Ticket;
import persistence.TicketRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class NewTicketPanel extends JPanel {
    private final TicketRepository repo;
    private final JTextField nameField;
    private final JTextField subjectField;
    private final JComboBox<String> priorityBox;

    public NewTicketPanel(TicketRepository repo) {
        this.repo = repo;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Nuevo Ticket");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(15));

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nameField.setBorder(BorderFactory.createTitledBorder("Nombre"));
        add(nameField);
        add(Box.createVerticalStrut(10));

        subjectField = new JTextField();
        subjectField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        subjectField.setBorder(BorderFactory.createTitledBorder("Asunto"));
        add(subjectField);
        add(Box.createVerticalStrut(10));

        priorityBox = new JComboBox<>(new String[]{"Baja","Media","Alta"});
        priorityBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        priorityBox.setBorder(BorderFactory.createTitledBorder("Prioridad"));
        add(priorityBox);
        add(Box.createVerticalStrut(20));

        JButton submit = new JButton("Crear Ticket");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        stylePrimaryButton(submit);
        submit.addActionListener(e -> onSubmit());
        add(submit);
    }

    private void onSubmit() {
        String nombre = nameField.getText().trim();
        String asunto = subjectField.getText().trim();
        if (nombre.isEmpty() || asunto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        Ticket t = new Ticket(id, now, nombre, asunto,
                "Abierto",
                "Cliente",
                priorityBox.getSelectedItem().toString());
        repo.add(t);
        JOptionPane.showMessageDialog(this, "Ticket creado: " + id, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

    private void clearForm() {
        nameField.setText("");
        subjectField.setText("");
        priorityBox.setSelectedIndex(0);
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(24, 82, 157));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }
}
