package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public static void save(Ticket t, String type, String clientDni, String cashId) {
        String sql = """
            INSERT OR REPLACE INTO ticket
            (id, status, ticket_type, client_id, cash_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try {
            PreparedStatement ps = DatabaseManager.getInstance()
                    .getConnection()
                    .prepareStatement(sql);

            ps.setString(1, t.getTicketId());
            ps.setString(2, t.getStatus().name());
            ps.setString(3, type);
            ps.setString(4, clientDni);
            ps.setString(5, cashId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarProductos(Ticket t, Product p) {
        try {
            String insert = """
            INSERT INTO ticket_product (ticket_id, product_id)
            VALUES (?, ?)
        """;

            Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(insert);

            ps.setString(1, t.getTicketId());
            ps.setString(2, p.getID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando producto en ticket", e);
        }
    }

    public List<Ticket> findAll(ProductDAO productDAO) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String clientId = rs.getString("client_id");

                Ticket t;
                if (clientId.matches("[A-Za-z][0-9]{8}")){
                    String type = rs.getString("ticket_type");
                    CompanyTicketTipe ticketType = switch (type){
                        case "-c" -> CompanyTicketTipe.PRODUCTS_AND_SERVICES;
                        case "-p" -> CompanyTicketTipe.PRODUCTS_ONLY;
                        case "-s" -> CompanyTicketTipe.SERVICES_ONLY;
                        default -> CompanyTicketTipe.PRODUCTS_ONLY;
                    };
                    t=new TicketEmpresa(rs.getString("id"), ticketType);
                }else {
                    t = new TicketComunes(rs.getString("id"));
                }
                t.setStatus(
                        TicketStatus.valueOf(rs.getString("status"))
                );

                cargarProductos(t, productDAO);
                tickets.add(t);
            }

        } catch (SQLException | TiendaUPMExcepcion e) {
            e.printStackTrace();
        }
        return tickets;
    }

    private void cargarProductos(Ticket t, ProductDAO productDAO) throws TiendaUPMExcepcion, SQLException {
        String sql = """
            SELECT product_id
            FROM ticket_product
            WHERE ticket_id = ?
        """;

        Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, t.getTicketId());
        ResultSet rs = ps.executeQuery();

        List<Product> allProducts = productDAO.findAll();

        while (rs.next()) {
            String pid = rs.getString("product_id");

            for (Product p : allProducts) {
                if (p.getID().equals(pid)) {
                    t.AddProduct(p);
                    break;
                }
            }
        }
    }

    public static void removeProductFromTicket(String ticketId, String productId) {
        String sql = """
        DELETE FROM ticket_product
        WHERE ticket_id = ? AND product_id = ?
    """;

        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ticketId);
            ps.setString(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String id) {
        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM ticket WHERE id = ?");

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeTicket(String ticketId, String newTicketId) {
        try {
            Connection conn = DatabaseManager.getInstance().getConnection();

            if (newTicketId != null && !newTicketId.equals(ticketId)) {
                String updateProductsSql = """
                    UPDATE ticket_product 
                    SET ticket_id = ? 
                    WHERE ticket_id = ?
                """;

                PreparedStatement psProducts = conn.prepareStatement(updateProductsSql);
                psProducts.setString(1, newTicketId);
                psProducts.setString(2, ticketId);
                psProducts.executeUpdate();

                String updateTicketSql = """
                    UPDATE ticket 
                    SET id = ?, status = ? 
                    WHERE id = ?
                """;

                PreparedStatement psTicket = conn.prepareStatement(updateTicketSql);
                psTicket.setString(1, newTicketId);
                psTicket.setString(2, TicketStatus.CLOSE.toString());
                psTicket.setString(3, ticketId);
                psTicket.executeUpdate();

            } else {
                String sql = "UPDATE ticket SET status = ? WHERE id = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, TicketStatus.CLOSE.toString());
                ps.setString(2, ticketId);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void openTicket(String ticketId) {
        String sql = "UPDATE ticket SET status = ? WHERE id = ?";

        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, TicketStatus.OPEN.toString());
            ps.setString(2, ticketId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}