package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.User;
import model.Workout;

public class MySQLWorkoutDAO implements WorkoutDAO {

    @Override
    public boolean save(Workout workout) throws ModelException {

        DBHandler db = new DBHandler();

        String sql = "INSERT INTO workout "
                + "(workout_name, duration_minutes, intensity, trainer, user_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        db.prepareStatement(sql);

        db.setString(1, workout.getWorkoutName());
        db.setInt(2, workout.getDurationMinutes());
        db.setString(3, workout.getIntensity());
        db.setString(4, workout.getTrainer());
        db.setInt(5, workout.getUser().getId());

        return db.executeUpdate() > 0;
    }

    @Override
    public boolean update(Workout workout) throws ModelException {

        DBHandler db = new DBHandler();

        String sql = "UPDATE workout SET "
                + "workout_name = ?, "
                + "duration_minutes = ?, "
                + "intensity = ?, "
                + "trainer = ?, "
                + "user_id = ? "
                + "WHERE id = ?";

        db.prepareStatement(sql);

        db.setString(1, workout.getWorkoutName());
        db.setInt(2, workout.getDurationMinutes());
        db.setString(3, workout.getIntensity());
        db.setString(4, workout.getTrainer());
        db.setInt(5, workout.getUser().getId());
        db.setInt(6, workout.getId());

        return db.executeUpdate() > 0;
    }

    @Override
    public boolean delete(int id) throws ModelException {

        DBHandler db = new DBHandler();

        String sql = "DELETE FROM workout WHERE id = ?";

        db.prepareStatement(sql);
        db.setInt(1, id);

        return db.executeUpdate() > 0;
    }

    @Override
    public List<Workout> listAll() throws ModelException {

        DBHandler db = new DBHandler();

        List<Workout> workouts = new ArrayList<>();

        String sql = "SELECT w.*, u.nome AS user_name "
                + "FROM workout w "
                + "INNER JOIN users u ON w.user_id = u.id "
                + "ORDER BY w.id";

        db.createStatement();
        db.executeQuery(sql);

        while (db.next()) {

            Workout workout = new Workout();

            workout.setId(db.getInt("id"));
            workout.setWorkoutName(db.getString("workout_name"));
            workout.setDurationMinutes(db.getInt("duration_minutes"));
            workout.setIntensity(db.getString("intensity"));
            workout.setTrainer(db.getString("trainer"));

            User user = new User();
            user.setId(db.getInt("user_id"));
            user.setName(db.getString("user_name"));

            workout.setUser(user);

            workouts.add(workout);
        }

        return workouts;
    }

    @Override
    public Workout findById(int id) throws ModelException {

        DBHandler db = new DBHandler();

        String sql = "SELECT * FROM workout WHERE id = ?";

        db.prepareStatement(sql);
        db.setInt(1, id);
        db.executeQuery();

        Workout workout = null;

        while (db.next()) {

            workout = new Workout();

            workout.setId(db.getInt("id"));
            workout.setWorkoutName(db.getString("workout_name"));
            workout.setDurationMinutes(db.getInt("duration_minutes"));
            workout.setIntensity(db.getString("intensity"));
            workout.setTrainer(db.getString("trainer"));

            User user = new User();
            user.setId(db.getInt("user_id"));

            workout.setUser(user);
        }

        return workout;
    }
}