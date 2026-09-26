package com.example;

import com.example.model.WordInverterModel;
import com.example.view.MainFrame;
import com.example.controller.WordInverterController;

import javax.swing.*;

/**
 * Точка входа в приложение
 */
public class Main {
    public static void main(String[] args) {
        // Запускаем UI в потоке обработки событий
        SwingUtilities.invokeLater(() -> {
            try {
                // Устанавливаем стиль оформления под систему
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Создаем компоненты MVC
            WordInverterModel model = new WordInverterModel();
            MainFrame view = new MainFrame(model, null);
            WordInverterController controller = new WordInverterController(model, view);

            // Пересоздаем View с контроллером
            view.dispose(); // закрываем временный
            MainFrame finalView = new MainFrame(model, controller);
            finalView.setVisible(true);
        });
    }
}