package es.upm.etsisi.poo.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static DatabaseManager instance;
    private static Connection connection;
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
            price REAL,

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
            id TEXT,
            cash_id TEXT,
            client_id TEXT,
            status TEXT NOT NULL,
            ticket_type TEXT NOT NULL DEFAULT "-p"
        );
    """);

        // TICKET_PRODUCT
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS ticket_product (
            ticket_id TEXT,
            product_id TEXT,
            FOREIGN KEY (product_id) REFERENCES product(id)
        );
    """);
    }
    public static void deleteAll() {

        try (Statement stmt = connection.createStatement()) {

            // Desactivar FK temporalmente
            stmt.execute("PRAGMA foreign_keys = OFF");

            // Borrar tablas (orden inverso)
            stmt.execute("DROP TABLE IF EXISTS ticket_product");
            stmt.execute("DROP TABLE IF EXISTS ticket");
            stmt.execute("DROP TABLE IF EXISTS product");
            stmt.execute("DROP TABLE IF EXISTS client");
            stmt.execute("DROP TABLE IF EXISTS cash");

            // Reactivar FK
            stmt.execute("PRAGMA foreign_keys = ON");

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando la base de datos", e);
        }
    }


}
