import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Task3 {
    public static final String PATH = "C:/Курс 3/Тестовые задания/B1_1/src/files";
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        String URL = "jdbc:mysql://localhost:3306/b1db";
        String USER = "root";
        String PASSWORD = "1234";
        String TABLE_NAME = "strings";
        int lines = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            try (BufferedReader br = new BufferedReader(new FileReader(PATH+"/"+"text1"));) {

                while (br.readLine() != null) lines++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(PATH+"/"+"text1"));) {

                String s;
                int id=1;
                while ((s = br.readLine()) != null) {
                    String[] columns = s.split("\\|"+"\\|");


                    String SQL = "insert into " + TABLE_NAME + " (Date, Latin, Cyrill,RandInteger,RandReal) values(\""+columns[0] +"\",\""+columns[1]+"\",\""+columns[2]+"\","
                            +Integer.valueOf(columns[3])+","+Double.valueOf(columns[4].replace(",","."))+");";

                    System.out.println("Импортировано "+id+"строк. Осталось "+(lines-id)+" строк.");
                    statement.executeUpdate(SQL);
                    id++;
                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
}

