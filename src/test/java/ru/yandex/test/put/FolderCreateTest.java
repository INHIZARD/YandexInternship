package ru.yandex.test.put;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.test.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Создание папки")
public class FolderCreateTest extends BaseTest {

    /**
     * Директория для теста
     */
    private static final String FOLDER_PATH = "disk:/test-autotest-folder";

    /**
     * Тип папки
     */
    public static final String DIR_TYPE = "dir";

    /**
     * Параметр типа
     */
    public static final String TYPE_PARAM = "type";

    /**
     * Описание теста создания папки
     */
    public static final String DIR_TYPE_DESCRIPTION = "Созданный ресурс должен иметь тип dir";

    /**
     * После каждого теста чистим созданную директорию
     */
    @AfterEach
    void tearDown() {
        client.deleteResource(FOLDER_PATH);
    }


    /**
     * Тест статуса 201
     */
    @Test
    @DisplayName("Статус 201 при создании новой папки")
    void createFolder_returns201() {
        Response response = client.createFolder(FOLDER_PATH);

        assertEquals(201, response.getStatusCode());
    }

    /**
     * Тест статуса 200
     */
    @Test
    @DisplayName("Статус 200 папка реально создана")
    void createFolder_folderActuallyExists() {
        client.createFolder(FOLDER_PATH);

        Response getResponse = client.getResource(FOLDER_PATH);

        assertEquals(200, getResponse.getStatusCode());
        assertEquals(DIR_TYPE, getResponse.jsonPath().getString(TYPE_PARAM), DIR_TYPE_DESCRIPTION);
    }

    /**
     * Тест статуса 409
     */
    @Test
    @DisplayName("Статус 409 при повторном создании той же папки")
    void createFolder_duplicate_returns409() {
        client.createFolder(FOLDER_PATH);

        Response response = client.createFolder(FOLDER_PATH);

        assertEquals(409, response.getStatusCode());
    }

    /**
     * Тест статуса 400
     */
    @Test
    @DisplayName("Статус 400 при пустом пути")
    void createFolder_emptyPath_returns400() {
        Response response = client.createFolder("");

        assertEquals(400, response.getStatusCode());
    }
}
