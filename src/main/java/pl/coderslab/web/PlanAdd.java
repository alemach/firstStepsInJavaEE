package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/app/plan/add")
public class PlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 60);
        Admin admin = (Admin) session.getAttribute("admin");
        String name = request.getParameter("planName");
        String description = request.getParameter("planDescription");

        Plan plan = new Plan();
        plan.setName(name);
        plan.setDescription(description);
        plan.setCreated(new Timestamp(System.currentTimeMillis()));
        plan.setAdmin_id(admin.getId());

        PlanDao planDao = new PlanDao();
        planDao.create(plan);

        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-add-schedules.jsp").forward(request, response);
    }
}
