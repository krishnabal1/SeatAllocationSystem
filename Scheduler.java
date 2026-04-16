public interface Scheduler {
    void add(Student s);
    Student getNext();
    boolean isEmpty();
}