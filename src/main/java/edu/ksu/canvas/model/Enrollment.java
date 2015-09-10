package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class to represent Canvas enrollments.
 * See <a href="https://canvas.instructure.com/doc/api/enrollments.html#Enrollment">Canvas Enrollment</a> documentation.
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private Integer courseId;
    private String sisCourseId;
    private String courseIntegrationId;
    private String courseSectionId;
    private String sectionIntegrationId;
    private String sisSectionId;
    private String enrollmentState;
    private Boolean limitPrivilegesToCourseSection;
    private String sisImportId;
    private Integer rootAccountId;
    private String type;
    private Integer userId;
    private Integer associatedUserId;
    private String role;
    private Date updatedAt;
    private Date startAt;
    private Date endAt;
    private Date lastActivityAt;
    private long totalActivityTime;
    private String htmlUrl;
    private Grade grades;
    private String user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
    }

    public String getCourseIntegrationId() {
        return courseIntegrationId;
    }

    public void setCourseIntegrationId(String courseIntegrationId) {
        this.courseIntegrationId = courseIntegrationId;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public String getSectionIntegrationId() {
        return sectionIntegrationId;
    }

    public void setSectionIntegrationId(String sectionIntegrationId) {
        this.sectionIntegrationId = sectionIntegrationId;
    }

    public String getSisSectionId() {
        return sisSectionId;
    }

    public void setSisSectionId(String sisSectionId) {
        this.sisSectionId = sisSectionId;
    }

    public String getEnrollmentState() {
        return enrollmentState;
    }

    public void setEnrollmentState(String enrollmentState) {
        this.enrollmentState = enrollmentState;
    }

    public Boolean getLimitPrivilegesToCourseSection() {
        return limitPrivilegesToCourseSection;
    }

    public void setLimitPrivilegesToCourseSection(Boolean limitPrivilegesToCourseSection) {
        this.limitPrivilegesToCourseSection = limitPrivilegesToCourseSection;
    }

    public String getSisImportId() {
        return sisImportId;
    }

    public void setSisImportId(String sisImportId) {
        this.sisImportId = sisImportId;
    }

    public Integer getRootAccountId() {
        return rootAccountId;
    }

    public void setRootAccountId(Integer rootAccountId) {
        this.rootAccountId = rootAccountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(Integer associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(Date lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public long getTotalActivityTime() {
        return totalActivityTime;
    }

    public void setTotalActivityTime(long totalActivityTime) {
        this.totalActivityTime = totalActivityTime;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Grade getGrades() {
        return grades;
    }

    public void setGrades(Grade grades) {
        this.grades = grades;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}