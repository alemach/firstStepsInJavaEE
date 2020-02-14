package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/edit")
public class RecipeEdit extends HttpServlet {
    private int recipeId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao rdao = new RecipeDao();

        if (request.getParameter("id")!=null) {
            recipeId=Integer.parseInt(request.getParameter("id"));
            Recipe recipeToUpdate=rdao.read(recipeId);
            recipeToUpdate.setName(request.getParameter("name"));
            recipeToUpdate.setDescription(request.getParameter("description"));
            recipeToUpdate.setPreparation_time(Integer.parseInt(request.getParameter("preparation_time")));
            recipeToUpdate.setPreparation(request.getParameter("preparation"));
            recipeToUpdate.setIngredients(request.getParameter("ingredients"));
            rdao.update(recipeToUpdate);

            response.sendRedirect(request.getContextPath() + "/app/recipe/list");
        } else {
            recipeId=Integer.parseInt(request.getParameter("recipeId"));
            Recipe editedRecipe=rdao.read(recipeId);
            request.setAttribute("editedRecipe", editedRecipe);
            getServletContext().getRequestDispatcher("/app-edit-recipe.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
