package model.dao;

import java.util.List;

import model.ModelException;
import model.Workout;

public interface WorkoutDAO {
    boolean save(Workout workout) throws ModelException;
    boolean update(Workout workout) throws ModelException;
    boolean delete(int id) throws ModelException;
    List<Workout> listAll() throws ModelException;
    Workout findById(int id) throws ModelException;
}