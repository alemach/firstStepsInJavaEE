package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.SuperAdminDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.SuperAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/action")
public class AppAdminAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enable = Integer.parseInt(request.getParameter("enable"));
        int id = Integer.parseInt(request.getParameter("id"));
        AdminDao adminDao = new AdminDao();
        Admin admin =adminDao.get(id);
        SuperAdmin.setEnable(admin, (byte) enable);
        SuperAdminDao superAdminDao = new SuperAdminDao();
        superAdminDao.update(admin, id);
        response.sendRedirect("/app/admin/editusers");
    }
}
