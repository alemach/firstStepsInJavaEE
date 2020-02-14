package pl.coderslab.web;

import pl.coderslab.dao.SuperAdminDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.SuperAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/editusers")
public class AppAdminEditUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SuperAdminDao superAdminDao = new SuperAdminDao();
        List<Admin> admins = superAdminDao.findAllNonSuperAdmins();
        request.setAttribute("admins", admins);
        getServletContext().getRequestDispatcher("/app-admin-edit-users.jsp").forward(request, response);

    }
}
