import java.util.*;

public class SeatManager {
    private Map<Integer, Student> seats;
    private final int totalSeats;
    private Scheduler scheduler;
    public static final long IDLE_LIMIT = 2 * 60 * 1000; // 2 min

    public SeatManager(int totalSeats, String mode) {
        this.totalSeats = totalSeats;
        seats = new HashMap<>();

        if (mode.equalsIgnoreCase("PRIORITY")) {
            scheduler = new PriorityScheduler();
        } else {
            scheduler = new FIFOScheduler();
        }
    }

    // ENTER
    public void enter(int id, int priority) {
        removeIdle();

        if (isPresent(id)) {
            System.out.println("Already present!");
            return;
        }

        Student s = new Student(id, priority);

        if (seats.size() < totalSeats) {
            int seatNo = getFreeSeat();
            seats.put(seatNo, s);
            System.out.println("Seat " + seatNo + " allocated to " + id);
        } else {
            scheduler.add(s);
            System.out.println("Added to waitlist: " + id);
        }
    }

    // LEAVE
    public void leave(int id) {
        removeIdle();

        int seatNo = findSeat(id);
        if (seatNo == -1) {
            System.out.println("Not found!");
            return;
        }

        seats.remove(seatNo);
        System.out.println("Student " + id + " left seat " + seatNo);

        allocateNext(seatNo);
    }

    // STATUS
    public void status() {
        removeIdle();

        if (seats.isEmpty()) {
            System.out.println("No occupied seats");
            return;
        }

        for (int seat : seats.keySet()) {
            System.out.println("Seat " + seat + " -> " + seats.get(seat).id);
        }
    }

    // REMOVE IDLE
    private void removeIdle() {
        List<Integer> remove = new ArrayList<>();

        for (var entry : seats.entrySet()) {
            if (System.currentTimeMillis() - entry.getValue().lastActiveTime > IDLE_LIMIT) {
                remove.add(entry.getKey());
            }
        }

        for (int seat : remove) {
            System.out.println("Seat " + seat + " freed (idle)");
            seats.remove(seat);
            allocateNext(seat);
        }
    }

    // HELPERS
    private boolean isPresent(int id) {
        for (Student s : seats.values()) {
            if (s.id == id) return true;
        }
        return false;
    }

    private int findSeat(int id) {
        for (var e : seats.entrySet()) {
            if (e.getValue().id == id) return e.getKey();
        }
        return -1;
    }

    private int getFreeSeat() {
        for (int i = 1; i <= totalSeats; i++) {
            if (!seats.containsKey(i)) return i;
        }
        return -1;
    }

    private void allocateNext(int seat) {
        if (!scheduler.isEmpty()) {
            Student s = scheduler.getNext();
            seats.put(seat, s);
            System.out.println("Seat " + seat + " -> " + s.id + " (from waitlist)");
        }
    }
}