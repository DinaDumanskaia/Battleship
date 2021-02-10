import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();
        String[] arrayFromSentence = /*sentence.trim()*/"".split("\\s+");
        // собрать в коллекцию только те, что не пустые
        // посчитать их

//        long count = Stream.of(reader.readLine().split(" "))
//                .filter(s -> !s.isEmpty())
//                .map(String::trim)
//                .count();
//
//        System.out.println(count);

        if (arrayFromSentence.length == 0 || arrayFromSentence[0].isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(arrayFromSentence.length);
        }
        reader.close();
    }
}