import java.util.*;

public class FIFOScheduler implements Scheduler {
    Queue<Student> queue = new LinkedList<>();

    @Override public void add(Student s) {
        queue.add(s);
    }

    @Override public Student getNext() {
        return queue.poll();
    }

    @Override public boolean isEmpty() {
        return queue.isEmpty();
    }
}