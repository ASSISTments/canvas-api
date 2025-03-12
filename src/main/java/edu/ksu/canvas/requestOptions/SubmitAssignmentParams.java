package edu.ksu.canvas.requestOptions;

public class SubmitAssignmentParams
{
  private String courseId;
  private String assignmentId;
  private SubmitAssignmentPayload payload;

  public SubmitAssignmentParams(String courseId, String assignmentId, SubmitAssignmentPayload payload)
  {
    this.courseId     = courseId;
    this.assignmentId = assignmentId;
    this.payload      = payload;
  }

  /**
   * @return the courseId
   */
  public String getCourseId()
  {
    return courseId;
  }

  /**
   * @param courseId the courseId to set
   */
  public void setCourseId(String courseId)
  {
    this.courseId = courseId;
  }

  /**
   * @return the assignmentId
   */
  public String getAssignmentId()
  {
    return assignmentId;
  }

  /**
   * @param assignmentId the assignmentId to set
   */
  public void setAssignmentId(String assignmentId)
  {
    this.assignmentId = assignmentId;
  }

  /**
   * @return the options
   */
  public SubmitAssignmentPayload getPayload()
  {
    return payload;
  }

  /**
   * @param options the options to set
   */
  public void setPayload(SubmitAssignmentPayload payload)
  {
    this.payload = payload;
  }
}
