package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class PlanEdit extends HttpServlet {
    private int planId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();

        if (request.getParameter("id") != null) {
            planId = Integer.parseInt(request.getParameter("id"));
            Plan planToUpdate = planDao.read(planId);
            planToUpdate.setName(request.getParameter("name"));
            planToUpdate.setDescription(request.getParameter("description"));

            planDao.update(planToUpdate);

            response.sendRedirect(request.getContextPath() + "/app/plan/list");

        } else {
            planId = Integer.parseInt(request.getParameter("planId"));
            Plan editedPlan = planDao.read(planId);
            request.setAttribute("editedPlan", editedPlan);
            getServletContext().getRequestDispatcher("/app-edit-schedules.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
