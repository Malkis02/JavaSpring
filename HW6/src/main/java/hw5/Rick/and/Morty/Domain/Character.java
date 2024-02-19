package hw5.Rick.and.Morty.Domain;

import lombok.Data;

import java.util.List;

@Data
public class Character {
    private Info info;
    private List<Result> results; // Изменение здесь
}
