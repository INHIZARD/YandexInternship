package ru.yandex.test;

import org.junit.jupiter.api.BeforeEach;
import ru.yandex.api.YandexDiskClient;

/**
 * Базовый класс, от которого будут наследоваться все тестовые классы.
 * Нужен, чтобы инициализировать клиент
 */
public abstract class BaseTest {

    /**
     * Объект клиента
     */
    protected YandexDiskClient client;

    /**
     * Метод, инициализирующий клиент перед каждым тестом
     */
    @BeforeEach
    void initClient() {
        client = new YandexDiskClient();
    }
}
