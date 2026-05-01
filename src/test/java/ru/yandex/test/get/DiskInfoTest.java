package ru.yandex.test.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.config.TestConfig;
import ru.yandex.test.BaseTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Класс с тестами получения информации с диска
 */
@DisplayName("Данные о Диске пользователя")
public class DiskInfoTest extends BaseTest {

    /**
     * Поле 'total_space' для теста
     */
    public static final String TOTAL_SPACE_FIELD = "total_space";

    /**
     * Описание у поля 'total_space'
     */
    public static final String TOTAL_SPACE_FIELD_DESCRIPTION = "Поле total_space должно присутствовать";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String ERROR_OAUTH_TOKEN = "OAuth invalid_token_12345";

    /**
     * Тест статуса 200
     */
    @Test
    @DisplayName("Статус 200")
    void getDiskInfo200() {
        Response response = client.getDiskInfo();

        assertEquals(200, response.getStatusCode());
        assertNotNull(TOTAL_SPACE_FIELD, TOTAL_SPACE_FIELD_DESCRIPTION);
    }

    /**
     * Тест статуса 401
     */
    @Test
    @DisplayName("Статус 401")
    void getDiskInfo401() {
        Response response = given()
                .baseUri(TestConfig.BASE_URL)
                .header(AUTHORIZATION_HEADER, ERROR_OAUTH_TOKEN)
                .when()
                .get();

        assertEquals(401, response.getStatusCode());
    }
}
