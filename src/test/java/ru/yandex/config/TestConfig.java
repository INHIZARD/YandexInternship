package ru.yandex.config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Класс конфиг, содержит константы
 */
public class TestConfig {

    /**
     * Константа {@code Dotenv} сущности, чтобы читать все из конфига .env
     */
    public static final Dotenv DOTENV = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    /**
     * Константа токена Яндекс.Диска
     */
    public static final String TOKEN = DOTENV.get("YANDEX_TOKEN");

    /**
     * Константа дефолтного url Яндекс.Диска
     */
    public static final String BASE_URL = DOTENV.get("DISK_BASE_URL");
}
