package ru.geekbrains.Spring.Homeworks.Homework1;

import ru.geekbrains.Spring.Homeworks.Homework1.Model.Person;



public class Program {
    public static void main(String[] args) {
        /**
         * Пример работы методов
         */
        Person person = new Person("Sergey", "Sergeev", 45 );
        System.out.println(person);
        person.saveToJsonFile("person.json");

        Person deserializedPerson = Person.readFromJsonFile("person.json");
        System.out.println("Deserialized Person from file: " + deserializedPerson);
    }
}
