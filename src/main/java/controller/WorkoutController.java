package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.User;
import model.Workout;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.WorkoutDAO;

@WebServlet(urlPatterns = {
        "/workouts",
        "/workout/form",
        "/workout/insert",
        "/workout/update",
        "/workout/delete"
})
public class WorkoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private WorkoutDAO workoutDAO =
            DAOFactory.createDAO(WorkoutDAO.class);

    private UserDAO userDAO =
            DAOFactory.createDAO(UserDAO.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getRequestURI();

        switch (action) {

        case "/CRUDWorkoutDiary/workouts":
                listWorkouts(request);
                forward(request, response, "/workouts.jsp");
                break;

            case "/CRUDWorkoutDiary/workout/form":
                showForm(request);
                forward(request, response, "/form-workout.jsp");
                break;

            case "/CRUDWorkoutDiary/workout/update":
                showUpdateForm(request);
                forward(request, response, "/form-workout.jsp");
                break;

            default:
                response.sendError(404);
                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getRequestURI();

        switch (action) {

            case "/CRUDWorkoutDiary/workout/insert":
                insertWorkout(request, response);
                break;

            case "/CRUDWorkoutDiary/workout/update":
                updateWorkout(request, response);
                break;

            case "/CRUDWorkoutDiary/workout/delete":
                deleteWorkout(request, response);
                break;

            default:
                response.sendError(404);
                break;
        }
    }

    private void listWorkouts(
            HttpServletRequest request) {

        try {

            List<Workout> workouts =
                    workoutDAO.listAll();

            request.setAttribute(
                    "workouts",
                    workouts);

        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void showForm(
            HttpServletRequest request) {

        try {

            List<User> users =
                    userDAO.listAll();

            request.setAttribute(
                    "users",
                    users);

        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(
            HttpServletRequest request) {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            Workout workout =
                    workoutDAO.findById(id);

            List<User> users =
                    userDAO.listAll();

            request.setAttribute(
                    "workout",
                    workout);

            request.setAttribute(
                    "users",
                    users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertWorkout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            Workout workout = new Workout();

            workout.setWorkoutName(
                    request.getParameter("workoutName"));

            workout.setDurationMinutes(
                    Integer.parseInt(
                            request.getParameter("durationMinutes")));

            workout.setIntensity(
                    request.getParameter("intensity"));

            workout.setTrainer(
                    request.getParameter("trainer"));

            User user = new User();

            user.setId(
                    Integer.parseInt(
                            request.getParameter("userId")));

            workout.setUser(user);

            workoutDAO.save(workout);

            response.sendRedirect(
                    request.getContextPath()
                            + "/workouts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateWorkout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            Workout workout = new Workout();

            workout.setId(
                    Integer.parseInt(
                            request.getParameter("id")));

            workout.setWorkoutName(
                    request.getParameter("workoutName"));

            workout.setDurationMinutes(
                    Integer.parseInt(
                            request.getParameter("durationMinutes")));

            workout.setIntensity(
                    request.getParameter("intensity"));

            workout.setTrainer(
                    request.getParameter("trainer"));

            User user = new User();

            user.setId(
                    Integer.parseInt(
                            request.getParameter("userId")));

            workout.setUser(user);

            workoutDAO.update(workout);

            response.sendRedirect(
                    request.getContextPath()
                            + "/workouts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteWorkout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            workoutDAO.delete(id);

            response.sendRedirect(
                    request.getContextPath()
                            + "/workouts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forward(
            HttpServletRequest request,
            HttpServletResponse response,
            String path)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);

        dispatcher.forward(request, response);
    }
}