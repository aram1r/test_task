import java.util.*;

public class CategoryB {
    public String task1() {
        String str = "([ ] [{ }] ) [ ({}) ]({[]}) {[ ()] }";
        if (str.isEmpty())
            return "SUCCES";
        //Создаём стэк символов
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++)
        {
            char current = str.charAt(i);
            //Если текущий символ - открывающая скобка, добавляем её в стэк
            if (current == '{' || current == '(' || current == '[')
            {
                stack.push(current);
            }
            //Если текущий символ - закрывающая скобка, проводим проверку
            if (current == '}' || current == ')' || current == ']')
            {
                //Если стэк был до этого пуст, значит результат FAIL
                if (stack.isEmpty())
                    return "FAIL";

                char last = stack.peek();
                //Если символ до этого был открывающей скобкой этого же типа выкидываем открывающую скобку
                if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
                    stack.pop();
                else
                    return "FAIL";
            }

        }
        //Если в итоге стэк оказался пустым, значит скобочное выражение верно
        if (stack.isEmpty()) {
            return "SUCCES";
        }
        else {
            return "FAIL";
        }
    }


    public void task2(int n) {
        int dimensionMatrix = n; //Размерность матрицы
        int[][] matrix = new int[n][n];
        Random random = new Random();
        /*Заполняем двумерный массив случайными числами*/
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        /*Сортируем двумерный массив пузырьковым методом*/
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int lastR = j + 1;
                for (int lastJ = i; lastJ < matrix.length; lastJ++) {
                    while (lastR < matrix[lastJ].length) {
                        if (matrix[lastJ][lastR] < matrix[i][j]) {
                            int tmp = matrix[i][j];
                            matrix[i][j] = matrix[lastJ][lastR];
                            matrix[lastJ][lastR] = tmp;
                        }
                        lastR++;
                    }
                    lastR = 0;
                }
            }
        }


        int[][] copyMatrix = new int[matrix.length][matrix[0].length];
        /*Копирование двумерного массива*/
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }

        /*Заполняем верхнюю левую часть*/
        int x;
        int y;
        int l, m;
        int k = 1; //Номер диагонали в которой происходит замена, берётся вторая, т.к. первый элемент менять не нужно;
        l = 0; //Счётчик строк для скопированного массива
        m = 1; //Счётчик столбцов для скопированого массива

        for (int i = 1; i < matrix.length; i++) {
            //Координаты первого заменяемого элемента
            x = 0;
            y = i;
            while (x >= 0 && y >= 0 && (x + y) == k) {
                //Заполнение нечётных диагоналей
                if (k % 2 == 1) {
                    matrix[y][x] = copyMatrix[l][m];
                }
                //Заполнение чётных диагоналей
                else if (k % 2 == 0) {
                    matrix[x][y] = copyMatrix[l][m];
                }
                //Перемещение координат заменяемого элемента
                x++;
                y--;
                //Перемещение счётчиков скопированного массива в прямом направлении
                m++;
                if (m == matrix.length) {
                    l++;
                    m = 0;
                }
            }
            k++;

        }
        /*Заполняем правую нижнюю часть*/
        l = matrix.length-1; //Счётчик строк для скопированного массива
        m = matrix.length-2; //Счётчик столбцов для скопированого массива
        k = matrix[0].length + matrix.length -3; //Номер диагонали в которой происходит замена, берётся предпоследняя, т.к. последний элемент нам менять не нужно;
        for (int i = matrix.length - 2; i > 0; i--) {
            //Координаты предпоследнего заменяемого элемента
            x = matrix.length-1;
            y = i;
            while (x >=0 && (y <=matrix.length-1) && (x + y) == k) {
                //Заполнение нечётных диагоналей
                if (k % 2 == 1) {
                    matrix[y][x] = copyMatrix[l][m];
                }
                //Заполнение чётных диагоналей
                else if (k % 2 == 0) {
                    matrix[x][y] = copyMatrix[l][m];
                }
                //Перемещение координат заменяемого элемента
                x--;
                y++;
                //Перемещение счётчиков скопированного массива в обратном направлении
                m--;
                if (m == -1) {
                    l--;
                    m = matrix.length-1;
                }
            }
            //Изменение номера текущей диагонали
            k--;
        }


        /*Вывод в консоль элементов двумерного массива*/
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
    }

    public void task3(int n) {
        //Поиск наименьшей суммы чисел по пути к финальной клетке будем искать с помощью волнового алгоритма Ли

        Random random = new Random();
        //Создаём массив на одну клетку больше, чтобы избежать лишних проверок в дальнейшем
        int[][] matrix = new int[n+2][n+2];
        /*Заполняем двумерный массив случайными числами*/
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
        //Забиваем минусовым значением клетки по контуру массива
        for (int i =0; i<matrix.length; i++) {
                matrix[i][0] = -1;
                matrix[i][matrix.length-1] = -1;
                matrix[0][i] = -1;
                matrix[matrix.length-1][i] =-1;
        }

        int x, y; //Координата метки
        //Устанавливаем координату на стартовую точку
        x=1;
        y=1;
        //Добавляем к числу суммы значение стартовой клетки
        int sum = matrix[x][y];
        //Список в котором будут храниться точки
        LinkedSet<Point> results = new LinkedSet<>();

        for (int i = 0; i < matrix.length*matrix.length*matrix.length; i++) {
            if (results.size() != 0) {
                for (Point point : results) {
                    if (!point.isChecked()) {
                        if (matrix[point.getX()+1][point.getY()]!=-1) {
                            results.add(new Point(point.getX() + 1, point.getY(), point.getSum() + matrix[point.getX() + 1][point.getY()]));
                        }
                        if (matrix[point.getX()][point.getY()+1]!=-1) {
                            results.add(new Point(point.getX(), point.getY() + 1, point.getSum() + matrix[point.getX()][point.getY() + 1]));
                        }
                        point.setChecked();
                    }
                }
            } else {
                results.add(new Point(x + 1, y, matrix[x + 1][y] + sum));
                results.add(new Point(x, y + 1, matrix[x][y + 1] + sum));
            }
        }
        //Сбрасываем сумму на ноль, для отбора значений из списка
        sum = 0;
        //Ищем точку с минимальной суммой
        for(Point point : results) {
            if(point.getX()==matrix.length-2 && point.getY()==matrix.length-2) {
                if (sum ==0) {
                    sum = point.getSum();
                }
                else if (point.getSum()<sum) {
                    sum = point.getSum();

                }
            }
        }

        /*Вывод в консоль элементов двумерного массива*/
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
        }

        System.out.println();
        System.out.println();
        //Выводим минимальное значение
        System.out.println(sum);
    }

    //Класс точки
    class Point {
    private int x;
    private int y;
    private int value;
    private boolean checked;

    Point(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.value = sum;
        this.checked = false;
    }

    public void setChecked() {
        checked = true;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getSum() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        return (((Point) o).getX() == x) && (((Point) o).getY() == y);
    }

    @Override
    public String toString() {
        return "x: " + Integer.valueOf(x).toString() + " y:" + Integer.valueOf(y).toString();
    }
}
    //Класс списка, создаём для того чтобы не переписывать итератор, т.к. в стандартной реализации
    //изменение длины списка введёт к ConcurrentModificationException
    class LinkedSet<E> extends AbstractSet<E> {

        private class LinkedElement<E> {
            E value;

            boolean exists;

            LinkedElement<E> prev;
            LinkedElement<E> next;
        }

        private Map<E, LinkedElement<E>> map = new HashMap<>();

        private LinkedElement<E> placeholder = new LinkedElement<>();
        private LinkedElement<E> head = placeholder;

        @Override
        public boolean isEmpty() { return head == placeholder; }

        @Override
        public int size() { return map.size(); }

        @Override
        public boolean contains(Object o) { return map.containsKey(o); }

        @Override
        public boolean add(E e) {
            LinkedElement<E> element = map.putIfAbsent(e, placeholder);

            if (element != null) {
                return false;
            }

            element = placeholder;
            element.exists = true;
            element.value = e;

            placeholder = new LinkedElement<>();
            placeholder.prev = element;

            element.next = placeholder;

            return true;
        }

        @Override
        public boolean remove(Object o) {
            LinkedElement<E> removedElement = map.remove(o);

            if (removedElement == null) {
                return false;
            }

            removeElementFromLinkedList(removedElement);

            return true;
        }

        private void removeElementFromLinkedList(LinkedElement<E> element) {
            element.exists = false;
            element.value = null;

            element.next.prev = element.prev;

            if (element.prev != null) {
                element.prev.next = element.next;
                element.prev = null;
            } else {
                head = element.next;
            }
        }

        @Override
        public Iterator<E> iterator() {
            return new ElementIterator();
        }

        private class ElementIterator implements Iterator<E> {
            LinkedElement<E> next = head;
            LinkedElement<E> current = null;

            LinkedElement<E> findNext() {
                LinkedElement<E> n = next;

                while (!n.exists && n.next != null) {
                    next = n = n.next;
                }

                return n;
            }

            @Override
            public boolean hasNext() {
                return findNext().exists;
            }

            @Override
            public E next() {
                LinkedElement<E> n = findNext();

                if (!n.exists) {
                    throw new NoSuchElementException();
                }

                current = n;
                next = n.next;

                return n.value;
            }

            @Override
            public void remove() {
                if (current == null) {
                    throw new IllegalStateException();
                }

                if (map.remove(current.value, current)) {
                    removeElementFromLinkedList(current);
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
    }
}



