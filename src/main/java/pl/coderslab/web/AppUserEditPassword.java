package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/user/editpassword")
public class AppUserEditPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String oldpassword = request.getParameter("oldpassword");
        HttpSession session = request.getSession();
        Admin newAdmin = (Admin) session.getAttribute("admin");
        AdminDao adminDao = new AdminDao();
        while (!newAdmin.comparePassword(oldpassword)) {
            request.setAttribute("errorPwd", "Podane hasła muszą się zgadzać");
            getServletContext().getRequestDispatcher("/app-user-edit-password.jsp").forward(request, response);

        }
        while (!newAdmin.setNewPassword(password)) {
            request.setAttribute("errorPwd", "Hasło musi składać się z minimum 8 znaków, w tym jedenj wielkiej " +
                    "litery, jednej małej litery i jednej cyfry.");
            getServletContext().getRequestDispatcher("/app-user-edit-password.jsp").forward(request, response);
        }
        while (!password.equals(repassword)) {
            request.setAttribute("errorPwd", "Podane hasła muszą się zgadzać");
            getServletContext().getRequestDispatcher("/app-user-edit-password.jsp").forward(request, response);

        }
        adminDao.update(newAdmin, newAdmin.getId());
        session.invalidate();
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-user-edit-password.jsp").forward(request, response);

    }
}
