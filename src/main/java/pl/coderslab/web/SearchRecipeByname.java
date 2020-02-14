package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchrecipebyname")
public class SearchRecipeByname extends HttpServlet {

    RecipeDao recipeDao = new RecipeDao();
    List<Recipe> recipes;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchedName = request.getParameter("searchedname");
        recipes = recipeDao.findRecipesByName(searchedName);
        request.setAttribute("recipes", recipes);
        getServletContext().getRequestDispatcher("/search-recipe-by-name-list.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/search-recipe-by-name-list.jsp").forward(request, response);

    }
}
