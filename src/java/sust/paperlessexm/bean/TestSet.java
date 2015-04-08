package sust.paperlessexm.bean;

import java.util.Date;

/**
 *
 * @author Sm19
 */
public class TestSet {
    private String courseCode;
    private int courseSession;
    private Integer testId;
    private int marks;
    private String testTitle;
    private Date createDate;
    private Date updateDate;
    private Date testTime;
    private int testDuration;
    private String description;
    private int testNo;
    private String testDescription;
    private TeachesBean teachesId;
    private CourseBean courseId;

    public TestSet() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseSession() {
        return courseSession;
    }

    public Integer getTestId() {
        return testId;
    }

    public int getMarks() {
        return marks;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getTestTime() {
        return testTime;
    }

    public int getTestDuration() {
        return testDuration;
    }

    public String getDescription() {
        return description;
    }

    public int getTestNo() {
        return testNo;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public TeachesBean getTeachesId() {
        return teachesId;
    }

    public CourseBean getCourseId() {
        return courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseSession(int courseSession) {
        this.courseSession = courseSession;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public void setTestDuration(int testDuration) {
        this.testDuration = testDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public void setTeachesId(TeachesBean teachesId) {
        this.teachesId = teachesId;
    }

    public void setCourseId(CourseBean courseId) {
        this.courseId = courseId;
    }    
}
