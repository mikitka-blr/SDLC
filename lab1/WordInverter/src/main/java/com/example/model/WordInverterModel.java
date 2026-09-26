package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Активная модель — хранит данные, содержит бизнес-логику
 * и уведомляет слушателей (View) об изменениях
 */
public class WordInverterModel {
    private String originalSentence;
    private String invertedSentence;
    private final List<ModelListener> listeners = new ArrayList<>();

    /**
     * Интерфейс для слушателей (Observer)
     * View должен реализовать этот интерфейс
     */
    public interface ModelListener {
        void onModelChanged();
    }

    /**
     * Добавление слушателя
     */
    public void addListener(ModelListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Удаление слушателя
     */
    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * Уведомление всех слушателей об изменении
     */
    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    // Геттеры
    public String getOriginalSentence() {
        return originalSentence;
    }

    public String getInvertedSentence() {
        return invertedSentence;
    }

    /**
     * Бизнес-логика: инвертирование каждого слова в предложении
     * @param sentence исходное предложение
     * @throws IllegalArgumentException если предложение пустое или null
     */
    public void invertSentence(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("Предложение не может быть пустым!");
        }

        this.originalSentence = sentence.trim();

        // Разбиваем на слова
        String[] words = sentence.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            // Инвертируем каждое слово
            String invertedWord = new StringBuilder(word).reverse().toString();
            result.append(invertedWord).append(" ");
        }

        this.invertedSentence = result.toString().trim();

        // Уведомляем View об изменении
        notifyListeners();
    }
}