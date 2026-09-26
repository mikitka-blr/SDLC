package com.example.controller;

import com.example.model.WordInverterModel;
import com.example.view.MainFrame;

import javax.swing.*;

/**
 * Контроллер — обрабатывает ввод пользователя и
 * управляет взаимодействием Model и View
 */
public class WordInverterController {
    private final WordInverterModel model;
    private final MainFrame view;

    public WordInverterController(WordInverterModel model, MainFrame view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Обработка введенного предложения
     * @param sentence предложение от пользователя
     */
    public void processInput(String sentence) {
        try {
            model.invertSentence(sentence);
            // Сохраняем последнее введенное значение во View
            view.setLastEnteredSentence(sentence);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view,
                ex.getMessage(),
                "Ошибка ввода",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Произошла непредвиденная ошибка:\n" + ex.getMessage(),
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
