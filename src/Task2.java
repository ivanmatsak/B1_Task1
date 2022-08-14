import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Task2 {
    public static final String PATH = "C:/Курс 3/Тестовые задания/B1_1/src/files";
    public static final String OUTPUT = "C:/Курс 3/Тестовые задания/B1_1/src/merged";

    public static void main(String[] args) throws IOException {
        File dir = new File(PATH);//Файл со сгенерированными файлами из первого задания
        File[] arrFiles = dir.listFiles();
        List<File> list = Arrays.asList(arrFiles);

        mergeFiles(list, "DAF");//Передаем в метод список файлов, которые нужно соединить в один, и
        // последовательность символов
    }

    public static void mergeFiles(List<File> files, String sequence) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT,true))) {//Заранее создаем объект,
            //отвечающий за запись данных в финальный файл
            int i = 1;
            int deletedStrings = 0;
            for (File file : files) {//Проходимся по переданному списку файлов
                String file1 = file.getAbsolutePath();

                try (BufferedReader br = new BufferedReader(new FileReader(file1));) {//Открываем файл
                    String s;
                    while ((s = br.readLine()) != null) {//Пока в файле есть строки выполняем действия
                        if (s.contains(sequence)) {//Если строка содержит последовательность символов, пропускаем строку
                            deletedStrings++;
                            continue;
                        }
                        bw.write(s + "\n");//Записываем строку в финальный файл

                        System.out.println(i + " : " + s);
                        i++;
                    }
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            }
            System.out.println(deletedStrings + " lines with " + sequence + " sequence have been deleted");//Количество строк,
            // которые не вошли в финальный файл
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
