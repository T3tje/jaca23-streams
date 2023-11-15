package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        // EVEN NUMBERS
        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .toList();

        System.out.println(evenNumbers);

        //DOUBLE NUMBERS
        List<Integer> doubleNumbers = evenNumbers.stream()
                .map(number -> number * 2)
                .toList();

        System.out.println(doubleNumbers);

        //REVERSE ORDER LIST
        List<Integer> reverseOrderList = doubleNumbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(reverseOrderList);

        //REDUCE FOR ADDITION
        int sum = reverseOrderList.stream()
                .reduce(0, (x,y) -> x+y);

        System.out.println(sum);

        // FOR EACH TO OUTPUT EACH NUMBER
        reverseOrderList
                .forEach(System.out::println);

        List<Student> exportCsvToObjects = Files.lines(Path.of("students.csv"))
                .filter(line -> !line.contains("ID"))
                .filter(line -> !line.isEmpty())
                .map(Main::mapToStudent)
                .toList();

        System.out.println(exportCsvToObjects);

    }
    private static Student mapToStudent(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int postalCode = Integer.parseInt(parts[2]);
        int age = Integer.parseInt(parts[3]);

        return new Student(id, name, postalCode, age);
    }
}