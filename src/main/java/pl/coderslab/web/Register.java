package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        AdminDao adminDao = new AdminDao();
        Admin admin = new Admin();
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);

        while (!admin.setEmail(email)) {
            request.setAttribute("errorEmail", "Podaj właściwy adres email");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }
        while (adminDao.doesExist(email)) {
            request.setAttribute("errorEmail", "Użytkownik o tym adresie e-mail już jest zarejestrowany.<a " +
                    "href=\"/login\"> Zaloguj&nbspsię</a>");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }
        request.setAttribute("email", email);

        while (!admin.setNewPassword(password)) {
            request.setAttribute("errorPwd", "Hasło musi składać się z minimum 8 znaków, w tym jedenj wielkiej " +
                    "litery, jednej małej litery i jednej cyfry.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        while (!password.equals(repassword)) {
            request.setAttribute("errorPwd", "Podane hasła muszą się zgadzać");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

        }

        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin = adminDao.create(admin);
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
