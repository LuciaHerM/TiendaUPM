package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Comands.BussinessClient;
import es.upm.etsisi.poo.Comands.NormalClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {


    public void save(Client c) {

        String sql = """
            INSERT OR REPLACE INTO client
            (dni, name, email, cash_id)
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
                            rs.getString("cashId"));
                } else{
                    c = new NormalClient(
                            rs.getString("name"),
                            id,
                            rs.getString("email"),
                            rs.getString("cashId")
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


    public void delete(String dni) {

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(
                                     "DELETE FROM client WHERE dni = ?")) {

            ps.setString(1, dni);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String obtenerClienteTicket(Ticket t) {

        String sql = """
            SELECT client_dni
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
                return rs.getString("client_dni");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

