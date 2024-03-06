package ru.gb.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Base64;

/**
 * Контроллер клиента.
 */
@Controller
public class ClientController {
    /**
     * Объект для получения токена авторизации.
     */
    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;

    /**
     * Получение представления .
     * @param model модель для передачи данных в представление.
     * @param principal объект авторизации.
     * @return пердставление пальца.
     */
    @GetMapping
    public String getImage(Model model, Principal principal) {
        // форма запроса
        RestTemplate template = new RestTemplate();
        // Получение access токена
        String accessToken = authorizedClientService
                .loadAuthorizedClient("reg-client", principal.getName())
                .getAccessToken().getTokenValue();
        // Создаем заголовок и помещаем в него токен авторизации
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        // Отправляем запрос
        ResponseEntity<byte[]> response =
                template.exchange("http://localhost:8081/finger",
                        HttpMethod.GET, entity, byte[].class);
        // Преобразование изображения
        String base64Image = Base64.getEncoder().encodeToString(response.getBody());

        model.addAttribute("finger",base64Image);

        return "page";
    }
}
