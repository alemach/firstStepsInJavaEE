package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/user/editdetails")
public class AppUserEditDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Admin newAdmin = new Admin();
        AdminDao adminDao = new AdminDao();

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (!newAdmin.setEmail(email)) {
            request.setAttribute("errorMsg", "Podaj właściwy adres email");
            request.setAttribute("admin", admin);
            getServletContext().getRequestDispatcher("/app-user-edit-details.jsp").forward(request, response);
        }
        if (adminDao.get(email) != null && !adminDao.get(email).getEmail().equals(admin.getEmail())) {
            request.setAttribute("errorMsg", "Użytkownik o takim adresie e-mail już istnieje");
            request.setAttribute("admin", admin);
            getServletContext().getRequestDispatcher("/app-user-edit-details.jsp").forward(request, response);
        }
        newAdmin.setFirstName(firstName);
        newAdmin.setLastName(lastName);
        newAdmin.setEmail(email);

        newAdmin.setPassword(admin.getPassword());
        adminDao.update(newAdmin, admin.getId());
        Admin updatedAdmin = adminDao.get(admin.getId());
        request.setAttribute("admin", updatedAdmin);
        session.setAttribute("admin", adminDao.get(admin.getId()));
        response.sendRedirect("/app/dashboard");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        request.setAttribute("admin", admin);
        getServletContext().getRequestDispatcher("/app-user-edit-details.jsp").forward(request, response);

    }
}
