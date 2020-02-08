package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class ProductDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private void regenerateDB() {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("warehouse.db.sql"));
            String sqlQuery = "";
            while (input.hasNext()) {
                sqlQuery += input.nextLine();
                if ( sqlQuery.length() > 1 && sqlQuery.charAt( sqlQuery.length()-1 ) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlQuery);
                        sqlQuery = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ProductDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:warehouse.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
        } catch (SQLException e) {
            regenerateDB();
            try {
                PreparedStatement getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }



    }
}
