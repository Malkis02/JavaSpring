package ru.gb.resource.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.io.InputStream;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceControllerTest {

    @Test
    public void testGetImage() throws Exception {
        // Создаем экземпляр контроллера для тестирования
        ResourceController controller = new ResourceController();
        // Создаем экземпляр MockMvc для отправки запросов к контроллеру
        MockMvc mockMvc = standaloneSetup(controller).build();

        // Читаем изображение из ресурсов проекта
        InputStream inputStream = getClass().getResourceAsStream("/FingerUp.jpg");
        byte[] bytes = inputStream.readAllBytes();
        // Создаем объект MockMultipartFile для передачи изображения в запросе
        MockMultipartFile file = new MockMultipartFile("file", "FingerUp.jpg", MediaType.IMAGE_JPEG_VALUE, bytes);

        // Отправляем multipart GET запрос на /finger с изображением
        mockMvc.perform(MockMvcRequestBuilders.get("/finger"))
                // Проверяем, что статус ответа равен 200 (OK)
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Проверяем, что тип контента в ответе соответствует JPEG изображению
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.IMAGE_JPEG_VALUE));
    }
}
