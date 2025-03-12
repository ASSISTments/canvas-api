package edu.ksu.canvas.requestOptions;

public class SubmitAssignmentPayload
{
  private SubmitAssignmentComment comment;
  private SubmitAssignmentSubmission submission;

  public SubmitAssignmentPayload(SubmitAssignmentComment comment, SubmitAssignmentSubmission submission)
  {
    this.comment    = comment;
    this.submission = submission;
  }

  /**
   * @return the comment
   */
  public SubmitAssignmentComment getComment()
  {
    return comment;
  }

  /**
   * @param comment the comment to set
   */
  public void setComment(SubmitAssignmentComment comment)
  {
    this.comment = comment;
  }

  /**
   * @return the submission
   */
  public SubmitAssignmentSubmission getSubmission()
  {
    return submission;
  }

  /**
   * @param submission the submission to set
   */
  public void setSubmission(SubmitAssignmentSubmission submission)
  {
    this.submission = submission;
  }

}
