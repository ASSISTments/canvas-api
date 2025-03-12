package edu.ksu.canvas.requestOptions;

public class SubmitAssignmentComment
{
  // Include a textual comment with the submission.
  private String textComment;

  public SubmitAssignmentComment(String textComment)
  {
    this.textComment = textComment;
  }

  public String getTextComment()
  {
    return textComment;
  }

  public void setTextComment(String textComment)
  {
    this.textComment = textComment;
  }
}
