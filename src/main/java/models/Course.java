package models;

public class Course {
    public static final int DEFAULTID = -1;

    private int id;
    private String name;
    private String desk;

    public String toString() {
        return this.getId() + " | " +
                this.getName() + " | " +
                this.getDesk();
    }

    public Course(int id, String name, String desk) {
        this.setId(id);
        this.setName(name);
        this.setDesk(desk);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}
