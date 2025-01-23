import java.util.*;

public class PowerSet {

    // Метод для генерации всех подмножеств
    public static List<List<?>> generatePowerSet(List<?> set) {
        List<List<?>> powerSet = new ArrayList<>();
        int totalSubsets = (int) Math.pow(2, set.size()); // Количество подмножеств 2^n

        // Генерируем подмножества с помощью битовых комбинаций
        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Object> subset = new ArrayList<>();
            for (int i = 0; i < set.size(); i++) {
                // Если соответствующий бит в текущей комбинации равен 1, добавляем элемент в подмножество
                if ((mask & (1 << i)) != 0) {
                    subset.add(set.get(i));
                }
            }
            powerSet.add(subset);
        }

        return powerSet;
    }

    // Метод для форматирования подмножеств в строку
    public static String formatPowerSet(List<List<?>> powerSet) {
        StringBuilder sb = new StringBuilder();
        sb.append("Булеан множества {");

        // Проходим по всем подмножествам
        for (int i = 0; i < powerSet.size(); i++) {
            List<?> subset = powerSet.get(i);
            if (i > 0) sb.append(", "); // Разделитель между подмножествами
            if (subset.isEmpty()) {
                sb.append("(-0-)"); // Пустое множество
            } else {
                sb.append("(");
                sb.append(String.join(", ", subset.stream().map(String::valueOf).toArray(String[]::new)));
                sb.append(")");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите множество чисел через пробел:");
        String input = scanner.nextLine();

        // Преобразуем строку ввода в список целых чисел
        String[] elements = input.split("\\s+");
        List<Integer> set = new ArrayList<>();
        for (String elem : elements) {
            set.add(Integer.parseInt(elem));
        }

        System.out.println("Введите порядок булеана (например, 1 для обычного булеана, 2 для булеана 2-го порядка и т.д.):");
        int order = scanner.nextInt();

        // Генерируем булеан n-го порядка
        List<?> currentSet = set;
        for (int i = 1; i <= order; i++) {
            currentSet = generatePowerSet((List<?>) currentSet);
            System.out.println("Булеан " + i + "-го порядка: ");
            System.out.println(formatPowerSet((List<List<?>>) currentSet));
        }
    }
}