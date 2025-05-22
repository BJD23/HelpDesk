package ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final HelpDeskApp app;
    private final JTextField matField;
    private final JPasswordField passField;
    private final JCheckBox showPassword;

    public LoginPanel(HelpDeskApp app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JLabel logo = new JLabel("UV", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 32));
        logo.setForeground(new Color(24, 82, 157));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);

        add(Box.createVerticalStrut(30));

        JLabel title = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
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

        add(Box.createVerticalStrut(10));

        showPassword = new JCheckBox("Ver contraseña");
        showPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        showPassword.addActionListener(e -> passField.setEchoChar(showPassword.isSelected() ? (char)0 : '*'));
        add(showPassword);

        add(Box.createVerticalStrut(20));

        JButton loginBtn = new JButton("Iniciar Sesión");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        stylePrimaryButton(loginBtn);
        loginBtn.addActionListener(e -> onLogin());
        add(loginBtn);

        add(Box.createVerticalStrut(10));

        JButton toRegister = new JButton("Crear Cuenta");
        toRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        toRegister.setBorderPainted(false);
        toRegister.setContentAreaFilled(false);
        toRegister.setForeground(new Color(24, 82, 157));
        toRegister.addActionListener(e -> app.show(HelpDeskApp.REGISTER));
        add(toRegister);
    }

    private void onLogin() {
        String mat = matField.getText().trim().toLowerCase();
        String pwd = new String(passField.getPassword());
        if (mat.isEmpty()) {
            showError("Ingresa tu matrícula.", matField);
        } else if (!(mat.startsWith("zs") || mat.startsWith("g"))) {
            showError("La matrícula debe iniciar con 'zs' o 'g'.", matField);
        } else if (pwd.isEmpty()) {
            showError("Ingresa tu contraseña.", passField);
        } else {
            matField.setText("");
            passField.setText("");
            showPassword.setSelected(false);
            passField.setEchoChar('*');
            app.show(HelpDeskApp.DASHBOARD);
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