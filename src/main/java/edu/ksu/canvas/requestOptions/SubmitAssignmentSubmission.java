package edu.ksu.canvas.requestOptions;

import java.util.Date;
import java.util.List;

// Differs a bit from edu.ksu.canvas.model.assignment.Submission
public class SubmitAssignmentSubmission
{
  // Whether or not this comment should be sent to the entire group (defaults to false). Ignored if this is not a
  // group assignment or if no text_comment is provided.
  private Boolean groupComment;
  // REQUIRED. The type of submission being made. The assignment submission_types must include this submission type as
  // an allowed option, or the submission will be rejected with a 400 error. The submission_type given determines
  // which of the following parameters is used. For instance, to submit a URL, submission [submission_type] must be
  // set to “online_url”, otherwise the submission [url] parameter will be ignored. “basic_lti_launch” requires the
  // assignment submission_type “online” or “external_tool”
  // Allowed values:
  // online_text_entry, online_url, online_upload, media_recording, basic_lti_launch, student_annotation
  private String submissionType;
  // Submit the assignment as an HTML document snippet. Note this HTML snippet will be sanitized using the same
  // ruleset as a submission made from the Canvas web UI. The sanitized HTML will be returned in the response as the
  // submission body. Requires a submission_type of “online_text_entry”.
  private String body;
  // Submit the assignment as a URL. The URL scheme must be “http” or “https”, no “ftp” or other URL schemes are
  // allowed. If no scheme is given (e.g. “www.example.com”) then “http” will be assumed. Requires a submission_type
  // of “online_url” or “basic_lti_launch”.
  private String url;
  // Submit the assignment as a set of one or more previously uploaded files residing in the submitting user’s files
  // section (or the group’s files section, for group assignments). To upload a new file to submit, see the
  // submissions Upload a file API. Requires a submission_type of “online_upload”.
  private List<Long> fileIds;
  // The media comment id to submit. Media comment ids can be submitted via this API, however, note that there is not
  // yet an API to generate or list existing media comments, so this functionality is currently of limited use.
  // Requires a submission_type of “media_recording”.
  private String mediaCommentId;
  // The type of media comment being submitted.
  // Allowed values:
  // audio, video
  private String mediaCommentType;
  // Submit on behalf of the given user. Requires grading permission.
  private Long userId;
  // The Attachment ID of the document being annotated. This should match the annotatable_attachment_id on the
  // assignment. Requires a submission_type of “student_annotation”.
  private Long annotatableAttachmentId;
  private Date submittedAt;

  public SubmitAssignmentSubmission(Boolean groupComment, String submissionType, String body, String url,
    List<Long> fileIds, String mediaCommentId, String mediaCommentType, Long userId, Long annotatableAttachmentId,
    Date submittedAt)
  {
    this.groupComment            = groupComment;
    this.submissionType          = submissionType;
    this.body                    = body;
    this.url                     = url;
    this.fileIds                 = fileIds;
    this.mediaCommentId          = mediaCommentId;
    this.mediaCommentType        = mediaCommentType;
    this.userId                  = userId;
    this.annotatableAttachmentId = annotatableAttachmentId;
    this.submittedAt             = submittedAt;
  }

  public Boolean getGroupComment()
  {
    return groupComment;
  }

  public void setGroupComment(Boolean groupComment)
  {
    this.groupComment = groupComment;
  }

  public String getSubmissionType()
  {
    return submissionType;
  }

  public void setSubmissionType(String submissionType)
  {
    this.submissionType = submissionType;
  }

  public String getBody()
  {
    return body;
  }

  public void setBody(String body)
  {
    this.body = body;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public List<Long> getFileIds()
  {
    return fileIds;
  }

  public void setFileIds(List<Long> fileIds)
  {
    this.fileIds = fileIds;
  }

  public String getMediaCommentId()
  {
    return mediaCommentId;
  }

  public void setMediaCommentId(String mediaCommentId)
  {
    this.mediaCommentId = mediaCommentId;
  }

  public String getMediaCommentType()
  {
    return mediaCommentType;
  }

  public void setMediaCommentType(String mediaCommentType)
  {
    this.mediaCommentType = mediaCommentType;
  }

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public Long getAnnotatableAttachmentId()
  {
    return annotatableAttachmentId;
  }

  public void setAnnotatableAttachmentId(Long annotatableAttachmentId)
  {
    this.annotatableAttachmentId = annotatableAttachmentId;
  }

  public Date getSubmittedAt()
  {
    return submittedAt;
  }

  public void setSubmittedAt(Date submittedAt)
  {
    this.submittedAt = submittedAt;
  }
}
