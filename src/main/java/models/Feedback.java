package models;

public class Feedback {
    public static final int DEFAULTID = -1;

    private int id;
    private int user;
    private String topic;
    private String desk;

    public String toString() {
        return this.getId() + " | " +
                this.getUser() + " | " +
                this.getTopic() + " | " +
                this.getDesk();
    }

    public Feedback(int id, int user, String topic, String desk) {
        this.setId(id);
        this.setUser(user);
        this.setTopic(topic);
        this.setDesk(desk);
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

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}
