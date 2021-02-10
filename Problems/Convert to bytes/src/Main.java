import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        byte[] bytes = reader.readLine().getBytes();
//        FileInputStream f = new FileInputStream(reader.readLine());
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }
    }
}