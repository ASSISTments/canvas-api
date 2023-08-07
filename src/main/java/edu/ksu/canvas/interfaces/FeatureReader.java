package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import edu.ksu.canvas.model.Feature;

public interface FeatureReader extends CanvasReader<Feature, FeatureReader> {

    List<Feature> getCourseFeatures(String courseId) throws IOException;

    List<Feature> getAccountFeatures(String accountId) throws IOException;

    List<Feature> getUserFeatures(String userId) throws IOException;

}
