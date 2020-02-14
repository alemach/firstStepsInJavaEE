package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.SuperAdmin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins (first_name, last_name, email, password, " +
            "superadmin, enable) VALUES (?,?,?,?,0,1);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id=?;";
    private static final String GET_ADMIN_QUERY = "SELECT id, first_name, last_name, email, password, superadmin, " +
            "enable FROM admins WHERE  id=?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?,last_name=?,email=?,password=? " +
            "WHERE id = ?;";
    private static final String GET_ALL_EMAILS = "SELECT email FROM admins;";
    private static final String CHECK_IF_ADMIN_QUERY = "SELECT id, first_name, last_name, email, password, superadmin, enable FROM admins WHERE  email=?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";

    public boolean doesExist(String email) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMAILS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                if (email.equals(resultSet.getString("email"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return false;
    }

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }

    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
    }

    public Admin get(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Admin admin = new Admin();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                SuperAdmin.setEnable(admin, resultSet.getByte("enable"));
                SuperAdmin.setSuperAdmin(admin, resultSet.getByte("superadmin"));
                return admin;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }

    public void update(Admin admin, int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
    }

    public Admin get(String email) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IF_ADMIN_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            Admin admin = new Admin();
            if (resultSet.next()) {
                SuperAdmin superAdmin = new SuperAdmin();
                admin.setId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                SuperAdmin.setEnable(admin, resultSet.getByte("enable"));
                SuperAdmin.setSuperAdmin(admin, resultSet.getByte("superadmin"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }

    public List<Admin> findAllNotComplete() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin admin = new Admin();
                adminList.add(admin);
            }
            return adminList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}



