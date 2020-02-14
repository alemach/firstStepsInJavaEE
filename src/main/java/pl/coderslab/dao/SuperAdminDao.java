package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.SuperAdmin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SuperAdminDao extends AdminDao {
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT id, first_name, last_name, email, password, superadmin, enable FROM admins;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?,last_name=?,email=?,password=?, " +
            "superadmin=?, enable=? WHERE id = ?;";
    private static final String FIND_ALL_SUPERADMINS_QUERY = "SELECT id, first_name, last_name, email, password, superadmin, enable FROM admins WHERE superadmin = 1;";
    private static final String FIND_ALL_BUT_SUPERADMINS_QUERY = "SELECT id, first_name, last_name, email, password, " +
            "superadmin, enable FROM admins WHERE superadmin = 0;";

    public List<Admin> findAllNonSuperAdmins() {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BUT_SUPERADMINS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Admin> admins = new LinkedList<>();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                SuperAdmin.setEnable(admin, resultSet.getByte("enable"));
                SuperAdmin.setSuperAdmin(admin, resultSet.getByte("superadmin"));
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }
    public List<Admin> findAllSuperAdmins() {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SUPERADMINS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Admin> admins = new LinkedList<>();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                SuperAdmin.setEnable(admin, resultSet.getByte("enable"));
                SuperAdmin.setSuperAdmin(admin, resultSet.getByte("superadmin"));
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }

    public List<Admin> findAll() {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Admin> admins = new LinkedList<>();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                SuperAdmin.setEnable(admin, resultSet.getByte("enable"));
                SuperAdmin.setSuperAdmin(admin, resultSet.getByte("superadmin"));
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
        return null;
    }

    @Override
    public void update(Admin admin, int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getSuperadmin());
            preparedStatement.setInt(6, admin.getEnable());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono bazy");
        }
    }
}
