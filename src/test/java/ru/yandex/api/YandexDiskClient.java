package ru.yandex.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.config.TestConfig;

import static io.restassured.RestAssured.given;

/**
 * Класс, создающий запросы в качестве клиента
 */
public class YandexDiskClient {

    /**
     * Имя заголовка авторизации
     */
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    /**
     * Имя заголовка формата данных
     */
    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";

    /**
     * Значение заголовка OAuth
     */
    public static final String OAUTH_HEADER_VALUE = "OAuth ";

    /**
     * Значение заголовка формата json
     */
    public static final String JSON_HEADER_VALUE = "application/json";

    /**
     * Метод, который обращается к ресурсу Яндекс.Диска
     * @return дефолтный запрос
     */
    private RequestSpecification baseSpec() {
        return given()
                .baseUri(TestConfig.BASE_URL)
                .header(AUTHORIZATION_HEADER_NAME, OAUTH_HEADER_VALUE + TestConfig.TOKEN)
                .header(CONTENT_TYPE_HEADER_NAME, JSON_HEADER_VALUE);
    }

    /**
     * Запрос данных о диске пользователя
     * @return данные о диске
     */
    public Response getDiskInfo() {
        return baseSpec()
                .when()
                .get();
    }

    /**
     * Запрос метаинформации
     * @param path путь к файлу или директории
     * @return данные о метоинформации
     */
    public Response getResource(String path) {
        return baseSpec()
                .queryParam("path", path)
                .when()
                .get("/resources");
    }

    /**
     * Создание папки
     * @param path путь к создаваемой папке
     * @return ответ о созданной папке
     */
    public Response createFolder(String path) {
        return baseSpec()
                .queryParam("path", path)
                .when()
                .put("/resources");
    }

    /**
     * Копирование ресурса
     * @param from путь к копируемому ресурсу
     * @param to путь к создаваемой копии
     * @return ответ о копируемом ресурсе
     */
    public Response copyResource(String from, String to) {
        return baseSpec()
                .queryParam("from", from)
                .queryParam("path", to)
                .when()
                .post("/resources/copy");
    }

    /**
     * Удаление ресурса
     * @param path путь к удаляемому ресурсу
     * @return ответ об удаляемом ресурсе
     */
    public Response deleteResource(String path) {
        return baseSpec()
                .queryParam("path", path)
                .queryParam("permanently", true)
                .when()
                .delete("/resources");
    }
}
