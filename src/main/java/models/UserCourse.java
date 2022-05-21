package models;

public class UserCourse {
    public static final int DEFAULTID = -1;

    private int id;
    private int user;
    private int course;
    private String startDate;

    public String toString() {
        return this.getId() + " | " +
                this.getUser() + " | " +
                this.getCourse() + " | " +
                this.getStartDate();
    }

    public UserCourse(int id, int user, int course, String startDate) {
        this.setId(id);
        this.setUser(user);
        this.setCourse(course);
        this.setsSartDate(startDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setsSartDate(String startDate) {
        this.startDate = startDate;
    }
}
