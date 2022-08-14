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
            File file = new File(PATH, "text" + i);//В цикле создаем 100 файлов
            System.out.println(file.getName());
            try (FileWriter writer = new FileWriter(file, false)) {
                for (int j = 0; j < 100000; j++) {
                    String text = getRandomYear() + "||" + getRandomLatinSymbols(10) + "||"
                            + getRandomCyrillicSymbols(10) + "||" + getRandomPositiveEvenInteger(100000000)
                            + "||" + getRandomPositiveInteger() + "||\n";//В файл в цикле записываем 100000 строк, каждая
                    // из которых состоит из даты за последние 5 лет, 10 латинских символов, 10 русских символов,
                    // случайного положительного четного целочисленного числа в диапазоне от 1 до 100 000 000,
                    // случайного положительного числа с 8 знаками после запятой в диапазоне от 1 до 20
                    writer.write(text);//записываем строку в файл
                }
                writer.flush();
                file.createNewFile();//создаем файл
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }

        }

    }

    public static int randBetween(int start, int end) {//метод для получения случайного числа в нужных границах
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String getRandomYear() {//Метод, который создает случайную дату в пределах последних 5 лет
        GregorianCalendar gc = new GregorianCalendar();//Объект календаря
        int currentYear = LocalDate.now().getYear();//Получаем текущую дату
        int year = randBetween(currentYear - 5, currentYear);// случайный год в диапазоне
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));//Случайный день
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String month = String.valueOf((gc.get(gc.MONTH) + 1));//Случайный месяц
        String monthString = month.length()==2? month : "0"+month;//Переменные предназначены для отображения двузначной
        //даты (09 вместо 9)
        String day = String.valueOf(gc.get(gc.DAY_OF_MONTH));
        String dayString = day.length()==2? day : "0"+day;
        return  dayString+ "." + monthString + "." + gc.get(gc.YEAR);
    }

    public static String getRandomLatinSymbols(int number) {//метод для получения нужного количества случайных латинских символов
        String string = new String();
        for (int i = 0; i < number; i++) {
            int rand = new Random().nextInt(52);
            char start = (rand < 26) ? 'A' : 'a';
            string += (char) (start + rand % 26);
        }
        return string;
    }

    public static String getRandomCyrillicSymbols(int number) {//метод для получения нужного количества случайных русских символов
        String string = new String();
        for (int i = 0; i < number; i++) {
            int rand = new Random().nextInt(255);
            char start = (rand < 192) ? 'А' : 'а';
            string += (char) (start + rand % 33);
        }
        return string;
    }

    public static int getRandomPositiveEvenInteger(int bound) {//метод для получения случайного положительного четного числа
        int rand = new Random().nextInt(bound);
        while (rand % 2 != 0) {//проверка на четность
            rand = new Random().nextInt(bound);
        }
        return rand;
    }

    public static String getRandomPositiveInteger() {//случайное положительное число с 8 знаками после запятой в диапазоне от 1 до 20
        double a = Math.random() * 19 + 1;
        String formattedDouble = String.format("%.8f", a);//ограничение 8 знаков после запятой
        return formattedDouble;
    }
}
