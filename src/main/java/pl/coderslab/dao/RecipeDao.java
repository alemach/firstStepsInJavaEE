package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(created,updated,name,ingredients,description,preparation_time," +
            "preparation, admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE	recipe SET updated = ?, name = ? , ingredients = ?, description = ?," +
            " preparation_time = ?, preparation = ? WHERE id = ?;";
    private static final String COUNT_RECIPES_QUERY = "SELECT COUNT(*) AS count FROM recipe WHERE admin_id=?;";
    private static final String FIND_ALL_RECIPES_DESC_QUERY = "SELECT * FROM recipe ORDER BY created DESC;";
    private static final String READ_RECIPE_BY_NAME_QUERY = "SELECT * from recipe where name = ?;";
    private static final String READ_LAST_RECIPE_QUERY = "SELECT * FROM recipe WHERE admin_id = ? ORDER BY created DESC LIMIT 1;";
    private static final String FIND_ALL_RECIPES_BY_PLANDAY = "SELECT recipe.* FROM recipe JOIN recipe_plan ON recipe.id=recipe_plan.recipe_id " +
            "WHERE plan_id = ? AND day_name_id = ?;";
    private static final String FIND_RECIPES_BY_NAME_QUERY = "SELECT * from recipe where name LIKE ?";
    private static final String READ_ABSOLUTE_LAST_RECIPE_QUERY = "SELECT * FROM recipe ORDER BY created DESC LIMIT 1;";

    /**
     * Return all recipes by planId
     *
     * @return
     * @params planId, dayNameId
     */
    public List<Recipe> findAllByPlanDay(Integer planId, Integer dayNameId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_BY_PLANDAY)) {


            statement.setInt(1, planId);
            statement.setInt(2, dayNameId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipeToAdd = new Recipe();
                    recipeToAdd.setId(resultSet.getInt("id"));
                    recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                    recipeToAdd.setDescription(resultSet.getString("description"));
                    recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipeToAdd.setPreparation(resultSet.getString("preparation"));
                    recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                    recipeList.add(recipeToAdd);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    /**
     * Get last recipe by adminId
     *
     * @param adminId
     * @return
     */
    public Recipe readLast(Integer adminId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_RECIPE_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public Recipe readAbsoluteLast() {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ABSOLUTE_LAST_RECIPE_QUERY)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public static Recipe read(String name) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_BY_NAME_QUERY)
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    /**
     * Return all recipes
     *
     * @return
     */
    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }
            return recipeList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Return all recipes in descending order
     *
     * @return
     */
    public List<Recipe> findAllDesc() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_DESC_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    /**
     * Create recipe
     *
     * @param recipe
     * @return
     */
    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

            insertStm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            insertStm.setString(3, recipe.getName());
            insertStm.setString(4, recipe.getIngredients());
            insertStm.setString(5, recipe.getDescription());
            insertStm.setInt(6, recipe.getPreparation_time());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdmin_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove recipe by id
     *
     * @param recipeId
     */
    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
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

    /**
     * Update recipe
     *
     * @param recipe
     */
    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(7, recipe.getId());
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getIngredients());
            statement.setString(4, recipe.getDescription());
            statement.setInt(5, recipe.getPreparation_time());
            statement.setString(6, recipe.getPreparation());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get recipe count by adminId
     *
     * @param adminId
     * @return
     */
    public int count(Integer adminId) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_RECIPES_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getRecipeIdByName(String recipeName) {
        List<Recipe> recipes = findAll();
        int recipeId = 0;
        for (Recipe recipe : recipes) {
            if (recipeName.equals(recipe.getName())) {
                recipeId = recipe.getId();
            }
        }
        return recipeId;
    }


    public List<Recipe> findRecipesByName(String name) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_RECIPES_BY_NAME_QUERY)) {
            statement.setString(1, '%' + name + '%');

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Recipe recipeToAdd = new Recipe();
                    recipeToAdd.setId(resultSet.getInt("id"));
                    recipeToAdd.setName(resultSet.getString("name"));
                    recipeToAdd.setDescription(resultSet.getString("description"));
                    recipeList.add(recipeToAdd);
                }
            }
            return recipeList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
