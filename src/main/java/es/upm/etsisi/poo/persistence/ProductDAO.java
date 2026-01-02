package es.upm.etsisi.poo.persistence;
import es.upm.etsisi.poo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void save(Product p) {

        String sql = """
            INSERT OR REPLACE INTO product (
                id, name, price, product_type,
                category,
                category_service,
                expiration_day, max_participants, num_person, type_event,
                max_pers, personalizations
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(sql)) {

            ps.setString(1, p.getID());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());

            /* ===== DISCRIMINADOR ===== */
            if (p instanceof Events) {
                ps.setString(4, "EVENT");
            } else if (p instanceof Services) {
                ps.setString(4, "SERVICE");
            } else if (p instanceof Personalized) {
                ps.setString(4, "CUSTOM");
            } else {
                ps.setString(4, "BASIC");
            }

            /* ===== Product_Basic ===== */
            if (p instanceof Product_Basic) {
                ps.setString(5, ((Product_Basic) p).getCategory().name());
            } else {
                ps.setNull(5, Types.VARCHAR);
            }

            /* ===== Services ===== */
            if (p instanceof Services) {
                ps.setString(6, ((Services) p).getCategory_service().name());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }

            /* ===== Events ===== */
            if (p instanceof Events e) {
                ps.setString(7, e.getExpiration_day());
                ps.setInt(8, e.getMAX_PARTICIPANTS());
                ps.setInt(9, e.getNum_person());
                ps.setString(10, e.getTypeEvent().name());
            } else {
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.INTEGER);
                ps.setNull(9, Types.INTEGER);
                ps.setNull(10, Types.VARCHAR);
            }

            /* ===== Personalizado ===== */
            if (p instanceof Personalized per) {
                ps.setInt(11, per.getMax_pers());
                ps.setString(12, String.join(",", per.getPersonalizaciones()));
            } else {
                ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.VARCHAR);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Product> findAll() {

        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Statement stmt =
                     DatabaseManager.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String type = rs.getString("product_type");
                Product p;

                switch (type) {

                    case "EVENT" -> {
                        p = new Events(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("expiration_day"),
                                rs.getInt("num_person"),
                                TypeEvent.valueOf(rs.getString("type_event"))
                        );
                        ((Events) p).setNum_person(rs.getInt("num_person"));
                    }

                    case "SERVICE" -> p = new Services(
                            rs.getString("expiration_day"),
                            switch (rs.getString("category_service")) {
                                case "SHOW" -> Category_service.SHOW;
                                case "INSURANCE" -> Category_service.INSURANCE;
                                case "TRANSPORT" -> Category_service.TRANSPORT;
                                default -> null;
                            },
                            rs.getString("id")
                    );

                    case "CUSTOM" -> {
                        p = new Personalized(
                                rs.getString("id"),
                                rs.getString("name"),
                                switch (rs.getString("category")) {
                                    case "MERCH" -> Category.MERCH;
                                    case "STATIONERY" -> Category.STATIONERY;
                                    case "CLOTHES" -> Category.CLOTHES;
                                    case "BOOK" -> Category.BOOK;
                                    case "ELECTRONICS" -> Category.ELECTRONICS;
                                    default -> Notifier.UnexpectecValue(rs.getString("category"));
                                },
                                rs.getDouble("price"),
                                rs.getInt("max_pers")
                        );
                        String pers = rs.getString("personalizations");
                        if (pers != null)
                            ((Personalized) p)
                                    .setPersonalizaciones(pers.split(","));
                    }

                    default -> p = new Product_Basic(
                            rs.getString("id"),
                            rs.getString("name"),
                            Category.valueOf(rs.getString("category")),
                            rs.getDouble("price")
                    );
                }

                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public void delete(String id) {
        try (PreparedStatement ps =
                     DatabaseManager.getInstance()
                             .getConnection()
                             .prepareStatement(
                                     "DELETE FROM product WHERE id = ?")) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
