package classes;

public class Captcha {
    private static final int MIN_ANSWER = 1;
    private static final int MAX_ANSWER = 30;
    private int first;
    private int second;
    private int answer;

    public Captcha () {
        this.setAnswer((int) (Math.random() * MAX_ANSWER + MIN_ANSWER));
        this.setSecond((int) (Math.random() * MAX_ANSWER / 2 + MIN_ANSWER));
        this.setFirst(getAnswer() + getSecond());
    }

    public int getFirst() {
        return first;
    }

    private void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    private void setSecond(int second) {
        this.second = second;
    }

    public int getAnswer() {
        return answer;
    }

    private void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return getFirst() + " - " + getSecond() + " =";
    }
}
