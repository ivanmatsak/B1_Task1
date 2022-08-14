import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Random;

public class Task1 {
    public static final String PATH = "C:/Курс 3/Тестовые задания/B1_1/src/files";

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 101; i++) {
            File file = new File(PATH, "text" + i);
            System.out.println(file.getName());
            try (FileWriter writer = new FileWriter(file, false)) {
                for (int j = 0; j < 100000; j++) {
                    String text = getRandomYear() + "||" + getRandomLatinSymbols(10) + "||"
                            + getRandomCyrillicSymbols(10) + "||" + getRandomPositiveEvenInteger(100000000)
                            + "||" + getRandomPositiveInteger() + "||\n";
                    writer.write(text);
                }
                writer.flush();
                file.createNewFile();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }

        }

    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String getRandomYear() {
        GregorianCalendar gc = new GregorianCalendar();
        int currentYear = LocalDate.now().getYear();
        int year = randBetween(currentYear - 5, currentYear);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String month = String.valueOf((gc.get(gc.MONTH) + 1));
        String monthString = month.length()==2? month : "0"+month;
        String day = String.valueOf(gc.get(gc.DAY_OF_MONTH));
        String dayString = day.length()==2? day : "0"+day;
        return  dayString+ "." + monthString + "." + gc.get(gc.YEAR);
    }

    public static String getRandomLatinSymbols(int number) {
        String string = new String();
        for (int i = 0; i < number; i++) {
            int rand = new Random().nextInt(52);
            char start = (rand < 26) ? 'A' : 'a';
            string += (char) (start + rand % 26);
        }
        return string;
    }

    public static String getRandomCyrillicSymbols(int number) {
        String string = new String();
        for (int i = 0; i < number; i++) {
            int rand = new Random().nextInt(255);
            char start = (rand < 192) ? 'А' : 'а';
            string += (char) (start + rand % 33);
        }
        return string;
    }

    public static int getRandomPositiveEvenInteger(int bound) {
        int rand = new Random().nextInt(bound);
        while (rand % 2 != 0) {
            rand = new Random().nextInt(bound);
        }
        return rand;
    }

    public static String getRandomPositiveInteger() {
        double a = Math.random() * 19 + 1;
        String formattedDouble = String.format("%.8f", a);
        return formattedDouble;
    }
}
