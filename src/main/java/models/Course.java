package models;

public class Course {
    public static final int DEFAULTID = -1;

    private int id;
    private String name;
    private String[] desc;

    public String toString() {
        return this.getId() + " | " +
                this.getName() + " | " +
                this.getDescOne();
    }

    public Course(int id, String name, String desc) {
        this.setId(id);
        this.setName(name);
        this.setDesc(desc);
    }

    public Course(int id, String name, String[] desc) {
        this.setId(id);
        this.setName(name);
        this.setDesc(desc);
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

    public String[] getDesc() {
        return desc;
    }

    public String getDescOne() {
        return String.join("\\n", desc);
    }

    public void setDesc(String desc) {
        this.desc = desc.split("\n");
    }

    public void setDesc(String[] desc) {
        this.desc = desc;
    }
}
