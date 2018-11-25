package c20181125;

public class Q1 {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        int[] a = {-6, -91, 1011, -100, 84, -22, 0, 473};
        int b = solution(a);
        System.out.println(b);
    }

    public static int solution(int[] A) {
        int min = A[0];
        for (int i : A) {

            if ((i % 2 == 1 || i % 2 == -1) && i < min)

                min = i;
        }
        return min;
    }
}
