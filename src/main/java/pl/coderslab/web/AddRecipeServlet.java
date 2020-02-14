package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        sess.setMaxInactiveInterval(3600);
        Admin admin = (Admin) sess.getAttribute("admin");
        String name=request.getParameter("name");
        String description=request.getParameter("description");
        int preparation_time;
        if (request.getParameter("preparation_time").equals("")) {
            preparation_time=0;
        } else {
            preparation_time=Integer.parseInt(request.getParameter("preparation_time"));
        }
        String preparation=request.getParameter("preparation");
        String ingredients=request.getParameter("ingredients");

        Recipe newRecipe = new Recipe();
        newRecipe.setAdmin_id(admin.getId());  
        newRecipe.setName(name);
        newRecipe.setDescription(description);
        newRecipe.setPreparation_time(preparation_time);
        newRecipe.setPreparation(preparation);
        newRecipe.setIngredients(ingredients);

        RecipeDao rdao = new RecipeDao();
        rdao.create(newRecipe);

        response.sendRedirect(request.getContextPath() + "/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/addrecipe.jsp").forward(request, response);
    }
}
