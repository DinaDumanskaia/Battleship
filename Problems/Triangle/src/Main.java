import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aA = scanner.nextInt();
        int bB = scanner.nextInt();
        int cC = scanner.nextInt();

        printResult(checkTheTriangle(aA, bB, cC));

    }

    private static void printResult(boolean checkTheTriangle) {
        if(checkTheTriangle) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkTheTriangle(int aA, int bB, int cC) {
        boolean isOk = true;
        if (!((aA + bB) > cC && (bB + cC) > aA && (aA + cC) > bB)) {
            isOk = false;
        }
        return isOk;
    }
}