package c20181125;

public class Q2 {
    public int solution(int A) {
        String theStr = "" + A;
        String result = "";
        int length = theStr.length() - 1;
        for (int i = 0; i <= length / 2; i++)
            result += theStr.charAt(i) + "" + theStr.charAt(length - i);
        if (length % 2 != 0)
            result += theStr.charAt(length / 2);
        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        Q2 q2 = new Q2();
        System.out.println(q2.solution(123456));

    }
}
