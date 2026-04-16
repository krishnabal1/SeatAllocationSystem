import java.util.*;

public class PriorityScheduler implements Scheduler {
    PriorityQueue<Student> pq;

    public PriorityScheduler() {
        pq = new PriorityQueue<>((a, b) -> a.priority - b.priority);
    }

    @Override public void add(Student s) {
        pq.add(s);
    }

    @Override public Student getNext() {
        return pq.poll();
    }

    @Override public boolean isEmpty() {
        return pq.isEmpty();
    }
}