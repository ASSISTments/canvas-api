package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import edu.ksu.canvas.model.Deposit;
import edu.ksu.canvas.model.File;

/**
 * This just allows you to upload a file once you've requested a file to be uploaded.
 * https://canvas.instructure.com/doc/api/file.file_uploads.html
 */
public interface FileWriter extends CanvasWriter<File, FileWriter> {

    Optional<File> upload(Deposit deposit, InputStream in, String filename) throws IOException;
}
