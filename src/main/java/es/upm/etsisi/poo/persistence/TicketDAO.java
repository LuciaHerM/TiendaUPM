package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {


    public void save(Ticket t, String clientDni, String cashId) {

        String sql = """
            INSERT OR REPLACE INTO ticket
            (id, status, total_price, total_discount, client_dni, cash_id)
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

            guardarProductos(t);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void guardarProductos(Ticket t) throws SQLException {

        String delete = "DELETE FROM ticket_product WHERE ticket_id = ?";
        String insert = """
            INSERT INTO ticket_product(ticket_id, product_id)
            VALUES (?, ?)
        """;

        Connection conn = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement del = conn.prepareStatement(delete);
             PreparedStatement ins = conn.prepareStatement(insert)) {

            del.setString(1, t.getTicketId());
            del.executeUpdate();

            for (Product p : t.getCart().values()) {
                ins.setString(1, t.getTicketId());
                ins.setString(2, p.getID());
                ins.executeUpdate();
            }
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


    public void delete(String id) {
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
}

