package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        int adminId=admin.getId();

        RecipeDao rdao = new RecipeDao();
        int recipeCount = rdao.count(adminId);
        PlanDao pdao = new PlanDao();
        int planCount = pdao.count(adminId);

        Plan lastPlan = pdao.readLast(adminId);

        DayNameDao dndao = new DayNameDao();
        List<DayName> dayNames = dndao.findAll();

        List<List<Recipe>> listOfrecipesByDay=new ArrayList<>();
        for (int i=1; i<=7; i++) {
            listOfrecipesByDay.add(rdao.findAllByPlanDay(1, i));    //lastPlan.getId()
        }
        //wiem ze to nieeleganckie :)
        List<Recipe> recipesDay1 = rdao.findAllByPlanDay(lastPlan.getId(), 1);
        List<Recipe> recipesDay2 = rdao.findAllByPlanDay(lastPlan.getId(), 2);
        List<Recipe> recipesDay3 = rdao.findAllByPlanDay(lastPlan.getId(), 3);
        List<Recipe> recipesDay4 = rdao.findAllByPlanDay(lastPlan.getId(), 4);
        List<Recipe> recipesDay5 = rdao.findAllByPlanDay(lastPlan.getId(), 5);
        List<Recipe> recipesDay6 = rdao.findAllByPlanDay(lastPlan.getId(), 6);
        List<Recipe> recipesDay7 = rdao.findAllByPlanDay(lastPlan.getId(), 7);

        request.setAttribute("listOfRecipesByDay", listOfrecipesByDay);//get by dayname
        request.setAttribute("recipesDay1", recipesDay1);
        request.setAttribute("recipesDay2", recipesDay2);
        request.setAttribute("recipesDay3", recipesDay3);
        request.setAttribute("recipesDay4", recipesDay4);
        request.setAttribute("recipesDay5", recipesDay5);
        request.setAttribute("recipesDay6", recipesDay6);
        request.setAttribute("recipesDay7", recipesDay7);
        request.setAttribute("dayNames", dayNames);
        request.setAttribute("lastPlan", lastPlan);
        request.setAttribute("recipeCount", recipeCount);
        request.setAttribute("planCount", planCount);
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
