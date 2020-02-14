package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecipePlanDao {
    private static final String PLAN_DETAILS_QUERY = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String ADD_RECIPE_TO_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan WHERE recipe_id = ?;";
    private static final String DELETE_RECIPE_PLAN_BY_PLANID_QUERY = "DELETE FROM recipe_plan WHERE plan_id = ?;";
    private static final String FIND_ALL_PLANS_BY_RECIPE_QUERY = "SELECT * FROM recipe_plan WHERE recipe_id = ?;";
    private static final String DELET_RECIPE_FROM_PLAN_QUERY = "DELETE from recipe_plan WHERE plan_id = ? AND " +
            "recipe_id = ?;";

    public static void delete(int planId, int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELET_RECIPE_FROM_PLAN_QUERY);
            preparedStatement.setInt(1, planId);
            preparedStatement.setInt(2, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nieznaleziono bazy");

        }
    }

    public RecipePlan details(int planId) {
        RecipePlan recipePlan = new RecipePlan();
        Map<String, List<String[]>> planDetails = new HashMap<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PLAN_DETAILS_QUERY);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] meal = {resultSet.getString(2),
                        resultSet.getString(3)};
                List<String[]> meals = new LinkedList<>();

                if (planDetails.get(resultSet.getString(1)) != null) {
                    meals = planDetails.get(resultSet.getString(1));
                    meals.add(meal);
                } else {
                    meals.add(meal);
                }
                planDetails.put(resultSet.getString(1), meals);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nieznaleziono bazy");
        }
        recipePlan.setPlanDetails(planDetails);
        return recipePlan;
    }

    public void addRecipeToPlan(int recipeId, String mealName, int displayOrder, int dayNameId, int planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(ADD_RECIPE_TO_PLAN_QUERY)) {
            insertStm.setInt(1, recipeId);
            insertStm.setString(2, mealName);
            insertStm.setInt(3, displayOrder);
            insertStm.setInt(4, dayNameId);
            insertStm.setInt(5, planId);
            insertStm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAllByRecipe(Integer recipeId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_BY_RECIPE_QUERY)) {

            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Plan planToAdd = new Plan();

                    planList.add(planToAdd);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlan(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_BY_PLANID_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
