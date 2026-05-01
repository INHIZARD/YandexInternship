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
     * Параметр пути
     */
    public static final String PARAM_PATH = "path";

    /**
     * Путь ресурса
     */
    public static final String PATH_RESOURCES = "/resources";

    /**
     * Параметр откуда берется ресурс
     */
    public static final String PARAM_FROM = "from";

    /**
     * Путь копирования
     */
    public static final String PATH_COPY = "/resources/copy";

    /**
     * Параметр удаления навсегда
     */
    public static final String PARAM_PERMANENTLY = "permanently";

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
                .queryParam(PARAM_PATH, path)
                .when()
                .get(PATH_RESOURCES);
    }

    /**
     * Создание папки
     * @param path путь к создаваемой папке
     * @return ответ о созданной папке
     */
    public Response createFolder(String path) {
        return baseSpec()
                .queryParam(PARAM_PATH, path)
                .when()
                .put(PATH_RESOURCES);
    }

    /**
     * Копирование ресурса
     * @param from путь к копируемому ресурсу
     * @param to путь к создаваемой копии
     * @return ответ о копируемом ресурсе
     */
    public Response copyResource(String from, String to) {
        return baseSpec()
                .queryParam(PARAM_FROM, from)
                .queryParam(PARAM_PATH, to)
                .when()
                .post(PATH_COPY);
    }

    /**
     * Удаление ресурса
     * @param path путь к удаляемому ресурсу
     * @return ответ об удаляемом ресурсе
     */
    public Response deleteResource(String path) {
        return baseSpec()
                .queryParam(PARAM_PATH, path)
                .queryParam(PARAM_PERMANENTLY, true)
                .when()
                .delete(PATH_RESOURCES);
    }
}
