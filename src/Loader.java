public class Loader {
    public static void main(String[] args) {
        CategoryA categoryA = new CategoryA();

        System.out.println(categoryA.task1(1.0, 2.0));
        System.out.println();

        System.out.println(categoryA.task2());
        System.out.println();
        categoryA.task3(5);
        System.out.println();
        System.out.println(categoryA.task4());
        System.out.println();
        System.out.println(categoryA.task5());
        System.out.println();
        CategoryB categoryB = new CategoryB();


        System.out.println(categoryB.task1());
        System.out.println();
        categoryB.task2(6);
        System.out.println();
        categoryB.task3(4);

        CategoryC categoryC = new CategoryC();
    }
}
