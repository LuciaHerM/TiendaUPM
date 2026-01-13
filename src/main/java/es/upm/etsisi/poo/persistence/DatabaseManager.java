package es.upm.etsisi.poo.persistence;

import java.io.File;
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
            Class.forName("org.sqlite.JDBC");

            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            connection = DriverManager.getConnection(URL);
            Statement stmt = connection.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON");
            crearTablas();

        } catch (Exception e) {
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
            cash_id TEXT,
            FOREIGN KEY (cash_id) REFERENCES cash(id)
        );
    """);

        // PRODUCT
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS product (
            id TEXT PRIMARY KEY,
            name TEXT,
            price REAL NOT NULL,

            -- discriminador
            product_type TEXT NOT NULL,

            -- BASIC
            category TEXT,

            -- SERVICE
            expiration_day TEXT,
            category_service TEXT,

            -- EVENT
            max_participants INTEGER,
            num_person INTEGER,
            type_event TEXT,

            -- CUSTOM
            max_pers INTEGER,
            personalizations TEXT
        );
    """);

        // TICKET
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS ticket (
            id TEXT PRIMARY KEY,
            cash_id TEXT,
            client_id TEXT,
            status TEXT NOT NULL,
            total_price REAL,
            total_discount REAL,
            FOREIGN KEY (cash_id) REFERENCES cash(id),
            FOREIGN KEY (client_id) REFERENCES client(id)
        );
    """);

        // TICKET_PRODUCT
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS ticket_product (
            ticket_id TEXT,
            product_id TEXT,
            PRIMARY KEY (ticket_id, product_id),
            FOREIGN KEY (ticket_id) REFERENCES ticket(id),
            FOREIGN KEY (product_id) REFERENCES product(id)
        );
    """);
    }

}
