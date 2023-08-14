public class Main {
    public static void main(String[] args)

    {

        int a = 62 * 5;//длина букв
        int b = 12 * 3;// длина пробелов
        int c = 500;// длина забора
        if (a + b <= c) {
            System.out.println("Надпись уместится на заборе");
        } else System.out.println("Надпись не уместится на заборе");
    }
}
