import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> numbers = new MyArrayList<>();
        Random random = new Random();
        Integer[] array = new Integer[8];

        System.out.println("Вносим в массив 8 случайных целых.");
        Arrays.setAll(array, i -> random.nextInt(1000));
        numbers.addAll(Arrays.asList(array));
        System.out.println(numbers);

        System.out.println("Добавляем в конец элемент '2222'.");
        numbers.add(2222);
        System.out.println(numbers);

        System.out.println("Добавляем в конец элемент '3333'.");
        numbers.add(3333);
        System.out.println(numbers);

        System.out.println("Добавляем в позицию с индексом 3 элемент '4444'. Должна увеличиться capacity.");
        numbers.add(3, 4444);
        System.out.println(numbers);

        System.out.println("Вносим в массив еще 10 случайных целых от 0 до 99. Должна увеличиться capacity");
        array = new Integer[10];
        Arrays.setAll(array, i -> random.nextInt(100));
        numbers.addAll(Arrays.asList(array));
        System.out.println(numbers);

        System.out.println("Удаляем из позиции с индексом 3 элемент '4444').");
        numbers.remove(3);
        System.out.println(numbers);

        Comparator integerComparator = (o1, o2) -> (Integer) o2 - (Integer) o1;

        System.out.println("Выполняем сортировку по убыванию.");
        numbers.sort(integerComparator);
        System.out.println(numbers);

        System.out.println("Удаляем элемент '2222'.");
        numbers.remove((Object) 2222);
        System.out.println(numbers);

        System.out.println("Получаем элемент с индексом 4.");
        System.out.println(numbers.get(4));

        System.out.println("Получаем isEmpty()");
        System.out.println(numbers.isEmpty());

        System.out.println("Очищаем список.");
        numbers.clear();
        System.out.println(numbers);

        System.out.println("Получаем isEmpty()");
        System.out.println(numbers.isEmpty());

        System.out.println("Получаем элемент с индексом 4.");
        System.out.println(numbers.get(4));
    }
}
