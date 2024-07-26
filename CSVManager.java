import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    public static List<String[]> readCSV(String filePath) {

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Name", "Username", "Email", "Password"});
        return data;
    }

    public static void appendRowToCSV(String filePath, String[] newRow) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String cell : newRow) {
                writer.write(cell + ",");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNewUser(String name, String user, String email, String pass) {
        String filePath = "DataBase/User.csv";
        String[] newRow = {name, user, email, pass};
        appendRowToCSV(filePath, newRow);

        //System.out.println("Reading updated CSV file:");
        List<String[]> updatedData = readCSV(filePath);
        for (String[] row : updatedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public static void addNewBook(String name, String author, String price, String quantity , String path) {
        String filePath = "DataBase/Movie.csv";
        String[] newRow = {name, author, price, quantity , path};
        appendRowToCSV(filePath, newRow);

        System.out.println("Reading updated CSV file:");
        List<String[]> updatedData = readCSV(filePath);
        for (String[] row : updatedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public static void purchaseBook(String username, String bookname, String price) {
        String filePath = "DataBase/MovieInBasketShop.csv";
        String[] newRow = {username, bookname, price};
        appendRowToCSV(filePath, newRow);

        System.out.println("Reading updated CSV file:");
        List<String[]> updatedData = readCSV(filePath);
        for (String[] row : updatedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public static void addNewBuy(String cardnumber, String emailAddress, String cvv2 , String password2 ,String phonenumber , String username ) {
        String filePath = "DataBase/Buy.csv";
        String[] newRow = {cardnumber, emailAddress, cvv2 , password2 , phonenumber , username};
        appendRowToCSV(filePath, newRow);

        System.out.println("Reading updated CSV file:");
        List<String[]> updatedData = readCSV(filePath);
        for (String[] row : updatedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


    public static void addNewBuySansCinema(String MovieName, String seatNumber, String sansTime , String HowmuchPay ) {
        String filePath = "DataBase/BuySansCinema.csv";
        String[] newRow = {MovieName, seatNumber, sansTime , HowmuchPay };
        appendRowToCSV(filePath, newRow);

        System.out.println("Reading updated CSV file:");
        List<String[]> updatedData = readCSV(filePath);
        for (String[] row : updatedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}