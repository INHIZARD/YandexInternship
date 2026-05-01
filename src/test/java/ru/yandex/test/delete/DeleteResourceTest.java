package ru.yandex.test.delete;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.test.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс с тестами удаления ресурса
 */
@DisplayName("Удаление файла или папки")
public class DeleteResourceTest extends BaseTest {

    /**
     * Путь удаляемой папки
     */
    private static final String FOLDER_PATH = "disk:/test-autotest-folder";

    /**
     * Описание статуса 404 при удалении папки
     */
    public static final String NO_FOLDER_DESCRIPTION = "После удаления папки GET должен вернуть 404";

    /**
     * Несуществующая папка
     */
    public static final String DISK_NONEXISTENT_FOLDER = "disk:/nonexistent-folder";

    /**
     * Пустой путь
     */
    public static final String EMPTY_PATH = "";

    /**
     * Удаление ресурса если тест упадет
     */
    @AfterEach
    void tearDown() {
        client.deleteResource(FOLDER_PATH);
    }

    /**
     * Тест статуса 204
     */
    @Test
    @DisplayName("Статус 204 при удалении существующей папки")
    void deleteResource204() {
        client.createFolder(FOLDER_PATH);

        Response response = client.deleteResource(FOLDER_PATH);

        assertEquals(204, response.getStatusCode());
    }

    /**
     * Тест статуса 404 при вызове GET запроса, после удаления ресурса
     */
    @Test
    @DisplayName("Статус 404 ресурс действительно удалён (GET возвращает 404)")
    void deleteResourceGet404() {
        client.createFolder(FOLDER_PATH);
        client.deleteResource(FOLDER_PATH);

        Response getResponse = client.getResource(FOLDER_PATH);

        assertEquals(404, getResponse.getStatusCode(), NO_FOLDER_DESCRIPTION);
    }

    /**
     * Тест статуса 404
     */
    @Test
    @DisplayName("Статус 404 при удалении несуществующего ресурса")
    void deleteResource404() {
        Response response = client.deleteResource(DISK_NONEXISTENT_FOLDER);

        assertEquals(404, response.getStatusCode());
    }

    /**
     * Тест статуса 400
     */
    @Test
    @DisplayName("Статус 400 при пустом пути")
    void deleteResource400() {
        Response response = client.deleteResource(EMPTY_PATH);

        assertEquals(400, response.getStatusCode());
    }
}
