package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {


    public static void save(Ticket t, String clientDni, String cashId) {

        String sql = """
            INSERT OR REPLACE INTO ticket
            (id, status, total_price, total_discount, client_id, cash_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, t.getTicketId());
            ps.setString(2, t.getStatus().name());
            ps.setDouble(3, t.getTotalPrice());
            ps.setDouble(4, t.getTotalDiscount());
            ps.setString(5, clientDni);
            ps.setString(6, cashId);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void guardarProductos(Ticket t, Product p, int quantity) {

        try {
            String insert = """
            INSERT INTO ticket_product (ticket_id, product_id, quantity)
            VALUES (?, ?, ?)
        """;

            Connection conn = DatabaseManager.getInstance().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setString(1, t.getTicketId());
                ps.setString(2, p.getID());
                ps.setInt(3, quantity);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando producto en ticket", e);
        }
    }


    public List<Ticket> findAll(ProductDAO productDAO) {

        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        try (Statement stmt =
                     DatabaseManager.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Ticket t = new Ticket(rs.getString("id"));
                t.setStatus(
                        TicketStatus.valueOf(rs.getString("status"))
                );

                cargarProductos(t, productDAO);
                tickets.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    private void cargarProductos(Ticket t, ProductDAO productDAO)
            throws SQLException {

        String sql = """
            SELECT product_id
            FROM ticket_product
            WHERE ticket_id = ?
        """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, t.getTicketId());
            ResultSet rs = ps.executeQuery();

            List<Product> allProducts = productDAO.findAll();

            while (rs.next()) {
                String pid = rs.getString("product_id");

                allProducts.stream()
                        .filter(p -> p.getID().equals(pid))
                        .findFirst()
                        .ifPresent(t::AddProduct);
            }
        }
    }

    public static void removeProductFromTicket(String ticketId, String productId) {

        String sql = """
        DELETE FROM ticket_product
        WHERE ticket_id = ? AND product_id = ?
    """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, ticketId);
            ps.setString(2, productId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void delete(String id) {
        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(
                                     "DELETE FROM ticket WHERE id = ?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeTicket(String ticketId) {

        String sql = "UPDATE ticket SET status = 'CLOSE' WHERE id = ?";

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, ticketId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

