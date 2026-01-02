package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashDAO {


    public void save(Cash c) {

        String sql = "INSERT OR IGNORE INTO cash(id,name,email) VALUES (?,?,?)";

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, c.getCashId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Cash> findAll(TicketDAO ticketDAO, ProductDAO productDAO) {

        ArrayList<Cash> cashes = new ArrayList<>();
        String sql = "SELECT * FROM cash";

        try (Statement stmt =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cash c = new Cash(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                cashes.add(c);
            }

            /* Cargar tickets por caja */
            List<Ticket> tickets = ticketDAO.findAll(productDAO);
            for (Cash c : cashes) {
                tickets.stream()
                        .filter(t ->
                                c.getCashId().equals(
                                        obtenerCashTicket(t)))
                        .forEach(c::ticketAddCash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashes;
    }


    public void delete(String id) {

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(
                                     "DELETE FROM cash WHERE id = ?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String obtenerCashTicket(Ticket t) {

        String sql = """
            SELECT cash_id
            FROM ticket
            WHERE id = ?
        """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, t.getTicketId());
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getString("cash_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


