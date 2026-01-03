package es.upm.etsisi.poo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:data/app.db";

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL);
            crearTablas();
        } catch (SQLException e) {
            throw new RuntimeException("Error conectando a SQLite", e);
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void crearTablas() throws SQLException {
        Statement stmt = connection.createStatement();

        // CASH
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS cash (
                id TEXT PRIMARY KEY,
                name TEXT,
                email TEXT
            );
        """);

        // CLIENT
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS client (
                id TEXT PRIMARY KEY,
                name TEXT,
                email TEXT,
                cash_id TEXT
                FOREIGN KEY(cash_id) REFERENCES cash(id),
            );
        """);


        // PRODUCT
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS product (
                id TEXT PRIMARY KEY,
                name TEXT,
                price REAL,

                -- Discriminador de herencia
                product_type ENUM('EVENT','SERVICE','CUSTOM','BASIC') NOT NULL,

                -- Product_Basic
                category ENUM('MERCH','STATIONERY','CLOTHES','BOOK','ELECTRONICS'),

                -- Services
                expiration_day TEXT,
                category_service ENUM('TRANSPORT','SHOW','INSURANCE'),

                -- Events
                expiration_day TEXT,
                max_participants INTEGER,
                num_person INTEGER,
                type_event ENUM('FOOD','MEETING'),

                -- Personalized
                max_pers INTEGER,
                personalizations TEXT
            );
        """);

        // TICKET
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS ticket (
                id TEXT PRIMARY KEY,
                cash_id TEXT,
                client_dni TEXT,
                status ENUM('EMPTY','OPEN','CLOSE') NOT NULL,
                total_price REAL,
                total_discount REAL,
                FOREIGN KEY(cash_id) REFERENCES cash(id),
                FOREIGN KEY(client_dni) REFERENCES client(dni)
            );
        """);

        // TICKET_PRODUCT (N..M)
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS ticket_product (
                ticket_id TEXT,
                product_id TEXT,
                PRIMARY KEY(ticket_id, product_id),
                FOREIGN KEY(ticket_id) REFERENCES ticket(id),
                FOREIGN KEY(product_id) REFERENCES product(id)
            );
        """);
    }
}
