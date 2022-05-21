package models;

public class Lesson {
    /* Days to unlock next lesson */
    public static final int PERIOD = 7;
    public static final int DEFAULTID = -1;

    private int id;
    private int course;
    private String name;
    private String desc;
    private String url;
    private long daysToUnlock;

    public String toString() {
        return this.getId() + " | " +
                this.getCourse() + " | " +
                this.getName() + " | " +
                this.getDesc() + " | " +
                this.getUrl() + " | " +
                this.getDaysToUnlock();
    }

    public Lesson(int id, int course, String name, String desc, String url, long daysToUnlock) {
        this.setId(id);
        this.setCourse(course);
        this.setName(name);
        this.setDesc(desc);
        this.setUrl(url);
        this.setDaysToUnlock(daysToUnlock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDaysToUnlock() {
        return daysToUnlock;
    }

    public void setDaysToUnlock(long daysToUnlock) {
        this.daysToUnlock = daysToUnlock;
    }
}
