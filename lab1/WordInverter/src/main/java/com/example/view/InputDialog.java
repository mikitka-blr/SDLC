package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Диалоговое окно для ввода предложения
 */
public class InputDialog extends JDialog {
    private JTextField txtSentence;
    private String enteredSentence = null;

    public InputDialog(JFrame parent, String lastValue) {
        super(parent, "Ввод предложения", true);
        setSize(450, 160);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Панель с полем ввода
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblPrompt = new JLabel("Введите предложение для инвертирования слов:");
        txtSentence = new JTextField();

        // Восстанавливаем последнее значение, если оно есть
        if (lastValue != null && !lastValue.isEmpty()) {
            txtSentence.setText(lastValue);
        }

        inputPanel.add(lblPrompt, BorderLayout.NORTH);
        inputPanel.add(txtSentence, BorderLayout.CENTER);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Отмена");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredSentence = txtSentence.getText();
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredSentence = null;
                dispose();
            }
        });

        buttonPanel.add(btnOk);
        buttonPanel.add(btnCancel);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Enter в поле = нажатие OK
        getRootPane().setDefaultButton(btnOk);
    }

    public String getEnteredSentence() {
        return enteredSentence;
    }
}