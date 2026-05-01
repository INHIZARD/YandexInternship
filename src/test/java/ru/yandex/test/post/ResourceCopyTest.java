package ru.yandex.test.post;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import ru.yandex.test.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс с тестами копирования ресурса
 */
@DisplayName("Копирование файла или папки")
public class ResourceCopyTest extends BaseTest {

    /**
     * Путь до первоначальной папки
     */
    private static final String SOURCE = "disk:/test-autotest-folder";

    /**
     * Путь до копии папки
     */
    private static final String COPY   = "disk:/test-autotest-folder-copy";

    /**
     * Тип папки
     */
    public static final String DIR_TYPE = "dir";

    /**
     * Параметр типа
     */
    public static final String TYPE_PARAM = "type";

    /**
     * Описание теста копирования ресурса
     */
    public static final String DIR_TYPE_DESCRIPTION = "Скопированный ресурс должен иметь тип dir";

    /**
     * Несуществующий путь
     */
    public static final String DISK_NONEXISTENT = "disk:/nonexistent-xyz";

    /**
     * Создание папки перед каждым тестом
     */
    @BeforeEach
    void createSourceFolder() {
        client.createFolder(SOURCE);
    }

    /**
     * Удаление папки и ее копии после каждого теста
     */
    @AfterEach
    void tearDown() {
        client.deleteResource(SOURCE);
        client.deleteResource(COPY);
    }

    /**
     * Тест статуса 201
     */
    @Test
    @DisplayName("Статус 201 при копировании папки")
    void copyResource201() {
        Response response = client.copyResource(SOURCE, COPY);

        assertEquals(201, response.getStatusCode());
    }

    /**
     * Тест статуса 200
     */
    @Test
    @DisplayName("Статус 200 копия реально создана")
    void copyResource200() {
        client.copyResource(SOURCE, COPY);

        Response getResponse = client.getResource(COPY);

        assertEquals(200, getResponse.getStatusCode());
        assertEquals(DIR_TYPE, getResponse.jsonPath().getString(TYPE_PARAM), DIR_TYPE_DESCRIPTION);
    }

    /**
     * Тест статуса 409
     */
    @Test
    @DisplayName("Статус 409 при копировании в уже существующий путь")
    void copyResource409() {
        client.copyResource(SOURCE, COPY);

        Response response = client.copyResource(SOURCE, COPY);

        assertEquals(409, response.getStatusCode());
    }

    /**
     * Тест статуса 404
     */
    @Test
    @DisplayName("Статус 404 при копировании несуществующего ресурса")
    void copyResource404() {
        Response response = client.copyResource(DISK_NONEXISTENT, COPY);

        assertEquals(404, response.getStatusCode());
    }
}
