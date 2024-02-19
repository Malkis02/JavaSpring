package hw5.Rick.and.Morty.Controllers;

import hw5.Rick.and.Morty.Domain.Result;
import hw5.Rick.and.Morty.Service.ServiceApi;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ControllerApi {

    private final ServiceApi apiService;


    /**
     * Метод вывода всех персонажей из API (~20)
     *
     * @param model
     * @return
     */
    @GetMapping("/start")
    public String showCharacters(Model model) {
        List<Result> characters = apiService.getAllCharacters(); // Изменение здесь
        model.addAttribute("characters", characters);
        return "characters";
    }


    @GetMapping("/")
    public String showRandomCharacters(Model model) {
        List<Result> characters = apiService.getRandomCharacters(20); // Получение 20 случайных персонажей
        model.addAttribute("characters", characters);
        return "characters"; // Возвращает страницу characters.html
    }

    @GetMapping("/next")
    public String showNextCharacters(Model model, HttpSession session) {
        Integer nextPage = (Integer) session.getAttribute("nextPage");
        if (nextPage == null) {
            nextPage = 1; // Если nextPage не установлен, устанавливаем его значение по умолчанию
        }
        List<Result> characters = apiService.getNextCharacters(nextPage);
        model.addAttribute("characters", characters);
        session.setAttribute("nextPage", nextPage + 1); // Увеличиваем nextPage на 1 для следующего запроса
        return "characters";
    }

    @GetMapping("/prev")
    public String showPreviousCharacters(Model model, HttpSession session) {
        Integer prevPage = (Integer) session.getAttribute("prevPage");
        if (prevPage == null) {
            prevPage = 1; // Если prevPage не установлен, устанавливаем его значение по умолчанию
        }
        List<Result> characters = apiService.getPreviousCharacters(prevPage);
        model.addAttribute("characters", characters);
        session.setAttribute("prevPage", prevPage - 1); // Уменьшаем prevPage на 1 для предыдущего запроса
        return "characters";
    }



    /**
     * Метод поиска персонажа по id
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String showCharacterDetails(@PathVariable("id") Integer id, Model model) {
        Result character = apiService.getCharacterById(id); // Изменение здесь
        model.addAttribute("character", character);
        return "character";
    }
}
