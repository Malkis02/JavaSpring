package hw5.Rick.and.Morty.Service;

import hw5.Rick.and.Morty.Domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import hw5.Rick.and.Morty.Domain.Character;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceApiImpl implements ServiceApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Value("${character.api.url}")
    private String CHARACTER_API;

    @Override
    public List<Result> getAllCharacters() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Character> response = restTemplate.exchange(CHARACTER_API, HttpMethod.GET, entity,
                Character.class);
        return response.getBody().getResults();
    }


    @Override
    public List<Result> getRandomCharacters(int count) {
        List<Result> allCharacters = getAllCharacters(); // Получение всех персонажей
        Collections.shuffle(allCharacters); // Перемешиваем список
        return allCharacters.subList(0, count); // Возвращаем указанное количество случайных персонажей
    }


    @Override
    public Result getCharacterById(Integer id) {
        String characterUrl = CHARACTER_API + "/" + id;
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Result> response = restTemplate.exchange(characterUrl, HttpMethod.GET, entity, Result.class);
        return response.getBody();
    }

    @Override
    public List<Result> getNextCharacters(int page) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Character> response = restTemplate.exchange(CHARACTER_API + "/?page=" + page, HttpMethod.GET,
                entity,
                Character.class);
        return response.getBody().getResults();
    }

    @Override
    public List<Result> getPreviousCharacters(int page) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Character> response = restTemplate.exchange(CHARACTER_API + "/?page=" + page, HttpMethod.GET,
                entity,
                Character.class);
        return response.getBody().getResults();
    }
}

