
public class Main {

    public static void main(String[] args) {
        Secret[] secrets = Secret.values();
        String compare = "STAR";
        int counter = findCompareInSecrets(secrets, compare);
        System.out.println(counter);
    }

    private static int findCompareInSecrets(Secret[] secrets, String compare) {
        int counter = 0;
        for (Secret secret : secrets) {
            if (secret.toString().substring(0, 4).equals(compare)) {
                counter++;
            }
        }
        return counter;
    }
}

//   enum Secret {
//    STAR, CRASH, START, // ...
//}
