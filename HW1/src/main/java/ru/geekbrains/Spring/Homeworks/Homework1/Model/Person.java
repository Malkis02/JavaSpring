package ru.geekbrains.Spring.Homeworks.Homework1.Model;

import com.google.gson.Gson;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Домашнее задание
 * Условие:
 * Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.
 * Пример решения:
 * 1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
 * 2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
 * 3. Создайте класс Person с полями firstName, lastName и age.
 * 4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
 * 5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
 **/

public class Person {
    /**
     * @firstname Имя
     * @lastName Фамилия
     * @age Возраст
     */
    private String firstName;
    private String lastName;
    private int age;

    /*
    Конструктор объекта без параметров
     */
    public Person() {
    }

    /*
    Конструктор объекта с параметрами
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    //Сериализация объекта в формат Json
    public void saveToJsonFile(String filePath) {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try {
            Files.writeString(Path.of(filePath), json);
            System.out.println("JSON saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Десериализация объекта из формата Json
    public static Person readFromJsonFile(String filePath) {
        try {
            String json = Files.readString(Path.of(filePath));
            System.out.println("Read JSON from file: " + json);

            Gson gson = new Gson();
            return gson.fromJson(json, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
