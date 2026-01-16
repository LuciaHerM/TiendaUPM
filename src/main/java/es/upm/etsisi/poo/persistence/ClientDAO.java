package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {


    public static void save(Client c) {

        String sql = """
            INSERT OR REPLACE INTO client
            (id, name, email, cash_id)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, c.getCashId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCashId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Client> findAll(TicketDAO ticketDAO, ProductDAO productDAO) {

        ArrayList<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Statement stmt =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String id = rs.getString("id");
                Client c;

                if (id.matches("[A-Za-z][0-9]{8}")){
                    c = new BussinessClient(
                            rs.getString("name"),
                            id,
                            rs.getString("email"),
                            rs.getString("cash_id"));
                } else{
                    c = new NormalClient(
                            rs.getString("name"),
                            id,
                            rs.getString("email"),
                            rs.getString("cash_id")
                    );
                }

                clients.add(c);
            }

            /* Cargar tickets por cliente */
            List<Ticket> tickets = ticketDAO.findAll(productDAO);
            for (Client c : clients) {
                tickets.stream()
                        .filter(t ->
                                c.getCashId().equals(
                                        obtenerClienteTicket(t)))
                        .forEach(c::ticketAddClients);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public static void delete(String dni) {

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(
                                     "DELETE FROM client WHERE id = ?")) {

            ps.setString(1, dni);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String obtenerClienteTicket(Ticket t) {

        String sql = """
            SELECT client_id
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
                return rs.getString("client_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

