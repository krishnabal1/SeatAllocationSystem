public class Student {
    int id;
    long lastActiveTime;
    int priority; // smaller = higher priority

    public Student(int id, int priority) {
        this.id = id;
        this.priority = priority;
        this.lastActiveTime = System.currentTimeMillis();
    }

    public void updateActivity() {
        this.lastActiveTime = System.currentTimeMillis();
    }
}