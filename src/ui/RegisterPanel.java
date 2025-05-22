package ui;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private final HelpDeskApp app;
    private final JTextField matField;
    private final JPasswordField passField;
    private final JPasswordField confirmField;

    public RegisterPanel(HelpDeskApp app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JLabel logo = new JLabel("UV", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 32));
        logo.setForeground(new Color(24, 82, 157));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);

        add(Box.createVerticalStrut(30));

        JLabel title = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(30, 58, 138));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(20));

        matField = new JTextField();
        matField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        matField.setAlignmentX(Component.CENTER_ALIGNMENT);
        matField.setBorder(BorderFactory.createTitledBorder("Matrícula institucional…"));
        add(matField);

        add(Box.createVerticalStrut(15));

        passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passField.setBorder(BorderFactory.createTitledBorder("Contraseña…"));
        add(passField);

        add(Box.createVerticalStrut(15));

        confirmField = new JPasswordField();
        confirmField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        confirmField.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmField.setBorder(BorderFactory.createTitledBorder("Confirmar contraseña…"));
        add(confirmField);

        add(Box.createVerticalStrut(10));

        JCheckBox show = new JCheckBox("Ver contraseñas");
        show.setAlignmentX(Component.CENTER_ALIGNMENT);
        show.addActionListener(e -> {
            char echo = show.isSelected() ? (char)0 : '*';
            passField.setEchoChar(echo);
            confirmField.setEchoChar(echo);
        });
        add(show);

        add(Box.createVerticalStrut(20));

        JButton createBtn = new JButton("Crear Cuenta");
        createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        stylePrimaryButton(createBtn);
        createBtn.addActionListener(e -> onRegister());
        add(createBtn);

        add(Box.createVerticalStrut(10));

        JButton toLogin = new JButton("Volver a Iniciar Sesión");
        toLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        toLogin.setBorderPainted(false);
        toLogin.setContentAreaFilled(false);
        toLogin.setForeground(new Color(24, 82, 157));
        toLogin.addActionListener(e -> app.show(HelpDeskApp.LOGIN));
        add(toLogin);
    }

    private void onRegister() {
        String mat = matField.getText().trim().toLowerCase();
        String pwd = new String(passField.getPassword());
        String conf = new String(confirmField.getPassword());
        if (mat.isEmpty()) {
            showError("Ingresa matrícula.", matField);
        } else if (!(mat.startsWith("zs") || mat.startsWith("g"))) {
            showError("La matrícula debe iniciar con 'zs' o 'g'.", matField);
        } else if (pwd.isEmpty()) {
            showError("Crea contraseña.", passField);
        } else if (!pwd.equals(conf)) {
            showError("Contraseñas no coinciden.", confirmField);
        } else {
            matField.setText("");
            passField.setText("");
            confirmField.setText("");
            app.show(HelpDeskApp.LOGIN);
        }
    }

    private void showError(String msg, JComponent comp) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        comp.requestFocus();
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBackground(new Color(24, 82, 157));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }
}