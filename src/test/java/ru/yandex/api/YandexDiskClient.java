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
}
