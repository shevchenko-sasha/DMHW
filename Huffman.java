import java.util.*;

// Класс для представления узла дерева Хаффмана
class MinHeapNode {
    char data;       // Символ
    int freq;        // Частота
    MinHeapNode left, right; // Левый и правый дочерний узел

    public MinHeapNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = this.right = null;
    }
}

// Класс для построения дерева Хаффмана
public class Huffman {

    // Метод для вывода дерева Хаффмана в требуемом формате
    public static void printTree(MinHeapNode root, String indent) {
        if (root != null) {
            // Если это листовой узел
            if (root.left == null && root.right == null) {
                System.out.println(indent + root.data + "(" + root.freq + ")");
            } else {
                // Если это внутренний узел
                System.out.println(indent + "(" + root.freq + ")");
                printTree(root.left, indent + "   ");
                printTree(root.right, indent + "   ");
            }
        }
    }

    // Метод для вывода кодов Хаффмана
    public static void printCodes(MinHeapNode root, String code) {
        if (root == null) return;

        // Если это листовой узел, печатаем символ и его код
        if (root.left == null && root.right == null) {
            System.out.println(root.data + ": " + code);
        }

        // Рекурсивно для левого и правого дочерних узлов
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    // Метод для построения дерева Хаффмана
    public static MinHeapNode buildHuffmanTree(char[] data, int[] freq, int size) {
        // Создание приоритетной очереди (min-heap)
        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));

        // Создание узлов для каждого символа и добавление их в очередь
        for (int i = 0; i < size; i++) {
            minHeap.add(new MinHeapNode(data[i], freq[i]));
        }

        // Построение дерева Хаффмана
        while (minHeap.size() > 1) {
            // Извлекаем два узла с наименьшими частотами
            MinHeapNode left = minHeap.poll();
            MinHeapNode right = minHeap.poll();

            // Создаем новый внутренний узел с суммой частот
            MinHeapNode top = new MinHeapNode('$', left.freq + right.freq);

            // Делаем их дочерними узлами
            top.left = left;
            top.right = right;

            // Вставляем новый узел обратно в очередь
            minHeap.add(top);
        }

        // Возвращаем корень дерева Хаффмана
        return minHeap.poll();
    }

    // Главный метод
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем у пользователя ввод текста
        System.out.println("Введите текст для кодирования:");
        String text = scanner.nextLine();

        // Вычисление частоты каждого символа
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Массив символов и частот
        char[] data = new char[freqMap.size()];
        int[] freq = new int[freqMap.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            data[i] = entry.getKey();
            freq[i] = entry.getValue();
            i++;
        }

        int size = data.length;

        // Строим дерево Хаффмана
        MinHeapNode root = buildHuffmanTree(data, freq, size);

        // Выводим дерево Хаффмана в нужном формате
        System.out.println("\nДерево Хаффмана:");
        printTree(root, "");

        // Выводим коды Хаффмана
        System.out.println("\nКоды Хаффмана:");
        printCodes(root, "");
    }
}


