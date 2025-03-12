package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;
import edu.ksu.canvas.model.Progress;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.requestOptions.MultipleSubmissionsOptions;
import edu.ksu.canvas.requestOptions.SubmitAssignmentParams;

public interface SubmissionWriter extends CanvasWriter<Submission, SubmissionWriter>
{
  /**
   * Make a submission for an assignment. You must be actively enrolled as a student in the course/section to do this.
   * Concluded and pending enrollments are not permitted.
   * 
   * 
   * @param  params      Parameters object containing parameters such as: course_id, assignment_id, text_comment,
   *                       group_comment, submission_type, body, url, file_ids, media_comment_id, media_comment_type,
   *                       user_id, annotatable_attachment_id, submitted_at.
   * 
   * @return             The submission object created by Canvas
   * 
   * @throws IOException
   */
  Optional<Submission> submitAssignment(SubmitAssignmentParams params) throws IOException;

  /**
   * Update the grading and comments on multiple student's assignment submissions in an asynchronous job. By section.
   *
   * @param  options     Parameters object containing parameters such as: section_id, assignment_id, student_id,
   *                       posted_grade, text_comment, group_comment, excuse, media_comment_id, media_comment_type.
   * 
   * @return             The progress object created by Canvas
   * 
   * @throws IOException If there is an error talking to Canvas
   */
  Optional<Progress> gradeMultipleSubmissionsBySection(MultipleSubmissionsOptions options) throws IOException;

  /**
   * Update the grading and comments on multiple student's assignment submissions in an asynchronous job. By Course.
   *
   * @param  options     Parameters object containing parameters such as: course_id, assignment_id, student_id,
   *                       posted_grade, text_comment, group_comment, excuse, media_comment_id, media_comment_type.
   * 
   * @return             The progress object created by Canvas
   * 
   * @throws IOException If there is an error talking to Canvas
   */
  public Optional<Progress> gradeMultipleSubmissionsByCourse(MultipleSubmissionsOptions options) throws IOException;
}
