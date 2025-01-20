import java.util.Random;

/* Generates a random integer between 0 and 100. */
public class RandomInteger {
    public static void main(String[] args) {
        Random r = new Random();

        int value = r.nextInt(100) + 1;

        System.out.println(value);
    }
}