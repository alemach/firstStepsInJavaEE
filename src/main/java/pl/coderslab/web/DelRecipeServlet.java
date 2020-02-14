package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/delrecipe")
public class DelRecipeServlet extends HttpServlet {
    private int recipeId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            recipeId = Integer.parseInt(request.getParameter("id"));
        }

        RecipePlanDao rpdao = new RecipePlanDao();
        if (recipeId != 0 && rpdao.findAllByRecipe(recipeId).isEmpty()) {
            RecipeDao rdao = new RecipeDao();
            rdao.delete(recipeId);
            response.sendRedirect("/app/recipe/list?msg=true");
            System.out.println("msg=true");
        } else {
            request.setAttribute("failed", "true");
            response.sendRedirect("/app/recipe/list?failed=true");

        }
    }
}
