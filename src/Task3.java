import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Task3 {
    public static final String PATH = "C:/Курс 3/Тестовые задания/B1_1/src/files";
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");//Подключаем драйвер для работы с бд
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        String URL = "jdbc:mysql://localhost:3306/b1db";
        String USER = "root";
        String PASSWORD = "1234";
        String TABLE_NAME = "strings";
        int lines = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {//Создаем объект подключения к
            // бд. В конструктор передаем логин, пароль и адрес бд
            Statement statement = connection.createStatement();//Создаем объект для выполнения sql запросов

            try (BufferedReader br = new BufferedReader(new FileReader(PATH+"/"+"text1"));) {//Считаем количество
                // строк в файле

                while (br.readLine() != null) lines++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(PATH+"/"+"text1"));) {//Открываем
                // файл из которого будут записываться данные в бд

                String s;
                int id=1;
                while ((s = br.readLine()) != null) {
                    String[] columns = s.split("\\|"+"\\|");//Разделяем строку по || на несколько строк


                    String SQL = "insert into " + TABLE_NAME + " (Date, Latin, Cyrill,RandInteger,RandReal) values(\""+columns[0] +"\",\""+columns[1]+"\",\""+columns[2]+"\","
                            +Integer.valueOf(columns[3])+","+Double.valueOf(columns[4].replace(",","."))+");";
                    //Создаем sql запрос для вставки полученных строк в бд
                    System.out.println("Импортировано "+id+"строк. Осталось "+(lines-id)+" строк.");//В процессе импорта строк
                    // в бд, Выводим в консоль сколько строк осталось импортировать и сколько уже импортировано
                    statement.executeUpdate(SQL);//Выполняем sql команду
                    id++;
                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
}

