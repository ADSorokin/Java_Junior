package src;

import java.util.Arrays;
import java.util.List;

public class AverageEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(33, 56, 78, 88, 98, 237, 51, 78);
        double average = numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
        System.out.println("Среднее значение четных чисел: " + average);
    }
}
