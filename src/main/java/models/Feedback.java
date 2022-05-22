package models;

public class Feedback {
    public static final int DEFAULTID = -1;

    private int id;
    private int user;
    private String topic;
    private String desc;

    public String toString() {
        return this.getId() + " | " +
                this.getUser() + " | " +
                this.getTopic() + " | " +
                this.getDesc();
    }

    public Feedback(int id, int user, String topic, String desk) {
        this.setId(id);
        this.setUser(user);
        this.setTopic(topic);
        this.setDesc(desk);
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
