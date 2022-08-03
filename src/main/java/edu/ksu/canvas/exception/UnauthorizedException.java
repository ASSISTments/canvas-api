package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 403 response.
 * Indicates that the user does not have permission to access the
 * requested resource. 
 */
public class UnauthorizedException extends CanvasException {
    private static final long serialVersionUID = 1L;
    
    public UnauthorizedException() {
      super();
  }

  public UnauthorizedException(String canvasErrorString, String url) {
      super(canvasErrorString, url);
  }
}
