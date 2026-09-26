package com.example.view;

import com.example.model.WordInverterModel;
import com.example.controller.WordInverterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Главное окно приложения (View)
 * Реализует ModelListener для получения уведомлений от модели
 */
public class MainFrame extends JFrame implements WordInverterModel.ModelListener {
    private final WordInverterModel model;
    private final WordInverterController controller;

    // UI компоненты
    private JLabel lblOriginal;
    private JLabel lblInverted;
    private JButton btnEnterData;

    // Храним последнее введенное предложение (по заданию)
    private String lastEnteredSentence = "";

    public MainFrame(WordInverterModel model, WordInverterController controller) {
        this.model = model;
        this.controller = controller;

        // Подписываемся на изменения модели
        this.model.addListener(this);

        initUI();
    }

    private void initUI() {
        setTitle("Инвертор слов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Центральная панель с метками
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        lblOriginal = new JLabel("Исходное предложение: (не введено)");
        lblOriginal.setFont(new Font("Arial", Font.PLAIN, 14));

        lblInverted = new JLabel("Инвертированное: (нет данных)");
        lblInverted.setFont(new Font("Arial", Font.BOLD, 16));
        lblInverted.setForeground(Color.BLUE);

        panel.add(lblOriginal);
        panel.add(lblInverted);

        // Кнопка "Ввести данные"
        btnEnterData = new JButton("Ввести данные");
        btnEnterData.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnterData.setPreferredSize(new Dimension(200, 45));
        btnEnterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInputDialog();
            }
        });

        // Панель для кнопки (с центрированием)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnEnterData);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Открытие диалога для ввода данных
     */
    private void openInputDialog() {
        InputDialog dialog = new InputDialog(this, lastEnteredSentence);
        dialog.setVisible(true);

        String newSentence = dialog.getEnteredSentence();
        if (newSentence != null && !newSentence.trim().isEmpty()) {
            // Сохраняем последнее введенное значение
            lastEnteredSentence = newSentence.trim();
            // Передаем контроллеру
            controller.processInput(newSentence.trim());
        } else if (newSentence != null && newSentence.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Предложение не может быть пустым!",
                "Ошибка ввода",
                JOptionPane.ERROR_MESSAGE);
        }
        // Если newSentence == null — пользователь нажал Cancel
    }

    /**
     * Реализация ModelListener — обновление UI при изменении модели
     */
    @Override
    public void onModelChanged() {
        SwingUtilities.invokeLater(() -> {
            lblOriginal.setText("Исходное предложение: " + model.getOriginalSentence());
            lblInverted.setText("Инвертированное: " + model.getInvertedSentence());
        });
    }

    // Геттер/сеттер для последнего введенного предложения
    public String getLastEnteredSentence() {
        return lastEnteredSentence;
    }

    public void setLastEnteredSentence(String lastEnteredSentence) {
        this.lastEnteredSentence = lastEnteredSentence;
    }
}