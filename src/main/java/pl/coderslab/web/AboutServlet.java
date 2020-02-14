package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {
    int allRecipes;
    int allUsers;
    int allPlans;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao rdao = new RecipeDao();
        allRecipes=rdao.findAll().size();
        AdminDao adao = new AdminDao();
        allUsers=adao.findAllNotComplete().size();
        PlanDao pdao = new PlanDao();
        allPlans=pdao.findAll().size();

        request.setAttribute("allRecipes", allRecipes);
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allPlans", allPlans);
        getServletContext().getRequestDispatcher("/about.jsp").forward(request, response);
    }
}
