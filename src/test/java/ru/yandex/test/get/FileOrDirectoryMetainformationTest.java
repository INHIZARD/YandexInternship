package ru.yandex.test.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.test.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс с тестами получения метаинформации о файле или папке
 */
@DisplayName("Метаинформация о файле или папке")
public class FileOrDirectoryMetainformationTest extends BaseTest {

    /**
     * Путь к главной папке
     */
    public static final String DIR_PATH = "disk:/";

    /**
     * Тип папки
     */
    public static final String DIR_TYPE = "dir";

    /**
     * Параметр типа
     */
    public static final String TYPE_PARAM = "type";

    /**
     * Описание теста метаинформации директории
     */
    public static final String DIR_TYPE_DESCRIPTION = "Тип корневого ресурса должен быть dir";

    /**
     * Несуществующий путь
     */
    public static final String DISK_NONEXISTENT_PATH = "disk:/nonexistent-path";

    /**
     * Тест статуса 200
     */
    @Test
    @DisplayName("Статус 200 для ресурса на диске")
    void getDiskMetainformationInfo200() {
        Response response = client.getDiskMetainformationInfo(DIR_PATH);

        assertEquals(200, response.getStatusCode());
        assertEquals(DIR_TYPE, response.jsonPath().getString(TYPE_PARAM),
                DIR_TYPE_DESCRIPTION);
    }

    /**
     * Тест статуса 404
     */
    @Test
    @DisplayName("Статус 404 для несуществующего ресурса")
    void getResource_nonExistent_returns404() {
        Response response = client.getDiskMetainformationInfo(DISK_NONEXISTENT_PATH);

        assertEquals(404, response.getStatusCode());
    }
}
