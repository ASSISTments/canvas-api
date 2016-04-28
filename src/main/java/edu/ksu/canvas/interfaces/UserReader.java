package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.User;

import java.io.IOException;
import java.util.List;

public interface UserReader extends CanvasReader<User, UserReader> {
    List<User> getUsersInCourse(String courseId) throws OauthTokenRequiredException, IOException;
}