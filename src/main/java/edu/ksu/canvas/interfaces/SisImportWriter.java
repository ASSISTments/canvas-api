package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;
import edu.ksu.canvas.model.SisImport;
import edu.ksu.canvas.requestOptions.CreateSisImportOptions;

public interface SisImportWriter extends CanvasWriter<SisImport, SisImportWriter>{

    Optional<SisImport> createSisImport(CreateSisImportOptions options) throws IOException;

}
