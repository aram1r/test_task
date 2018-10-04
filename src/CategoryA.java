public class CategoryA {
    public String task1 (Double xCoords, Double yCoords) {
        Double xA = 1.0;
        Double yA = 1.0;
        Double xB = 0.0;
        Double yB = -1.0;
        Double xC = -1.0;
        Double yC = -1.0;

        /*Вычисление будет происходить с помощью определения центра тяжести исходного треугольника*/
        Double equation1 = (xA - xCoords)*(yB - yA) - (xB - xA)*(yA - yCoords);
        Double equation2 = (xB - xCoords)*(yC - yB) - (xC - xB)*(yB - yCoords);
        Double equation3 = (xC - xCoords)*(yA - yC) - (xA - xC)*(yC - yCoords);

        /*Проверяем не находится ли точка на поверхности одной из сторон*/
        if (equation1 == 0 || equation2 ==0 || equation3 == 0) {
            return "IN";
        }
        /*Проверяем не находится ли точка внутри треугольника*/
        else if ((equation1>0 && equation2 > 0 && equation3 > 0) || (equation1<0 && equation2<0 && equation3 <0)) {
            return "IN";
        }

        else
            return "OUT";
    }

    public Integer task2 () {
        int [][] matrix = new int[3][3];
        int result = 0;
        /*Инициализация двумерного массива*/
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int) (Math.random()*100);
            }
        }
        /*Находим разницу между диагоналями матрицы*/
        for (int i = 0, j = matrix.length-1; i < matrix.length; i++, j--) {
            result += matrix[i][i]-matrix[i][j];
        }
        return result;
    }

    public void task3 (int i) {
        char k = '#';
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < i; j++) {
            str.append('#');
            System.out.printf("%"+i+"S\n", str);
        }
    }


    public int task4 () {
        int [] massive = {1, 2, 3, 4, 5, 6};
        int divider = 3;
        int count = 0;
        /*Ищем данные перебором, суммируя каждый из элементов массива
        * со всеми следующими элементами поочереди и деля их с остатком на
        * делитель, если результат равен 0, инкрементируем переменную count*/
        for (int i = 0; i < massive.length; i ++) {
            for (int j = i +1; j<massive.length; j++) {
                if ((massive[i] + massive[j])%divider ==0) {
                    count++;
                }
            }
        }
        return count;
    }

    public String task5 () {
        int[][] mainMassive = {{1, 2, 2}, {1, 2, 2}, {6, 7, 8}};
        int[][] patternMassive = {{2, 2}, {2, 2}};
        int count = 0;
        String coordinates = "FAIL";

        for (int i = 0; i <= mainMassive.length - patternMassive.length; i++) {
            for (int j = 0; j <= mainMassive[0].length - patternMassive[0].length; j++) {
                /*Ищем первое совпадение, диапазон счётчика уменьшен, т.к. нам ни к чему
                * проверять последние элементы, в виду того что справа нового столбца или ещё
                * одной строки не появится.*/
                if (mainMassive[i][j] == patternMassive[0][0]) {
                    /*Поймав первое совпадение, проверяем соседние элементы
                    * на полное совпадение заданной матрице*/
                    for (int k = 0; k < patternMassive.length; k++) {
                        for (int l = 0; l < patternMassive[0].length; l++) {
                            if (mainMassive[i + l][j + k] == patternMassive[l][k]) {
                                coordinates = "(" + i + ", " + j + ")";
                            }
                        }
                    }
                }
            }
        }
        return coordinates;
    }
}
