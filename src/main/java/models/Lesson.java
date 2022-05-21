package models;

public class Lesson {
    public static final int DEFAULTID = -1;

    private int id;
    private int course;
    private String name;
    private String desc;
    private String url;

    public String toString() {
        return this.getId() + " | " +
                this.getCourse() + " | " +
                this.getName() + " | " +
                this.getDesc() + " | " +
                this.getUrl();
    }

    public Lesson(int id, int course, String name, String desc, String url) {
        this.setId(id);
        this.setCourse(course);
        this.setName(name);
        this.setDesc(desc);
        this.setUrl(url);
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
}
