import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter seats: ");
        int n = sc.nextInt();

        System.out.print("Mode (FIFO / PRIORITY): ");
        String mode = sc.next();

        SeatManager sm = new SeatManager(n, mode);

        OUTER:
        while (true) {
            System.out.println("Choices: \nenter <id> <priority>\n leave <id>\n status\n exit");
            
            String cmd = sc.next();
            switch (cmd) {
                case "enter" -> {
                    int id = sc.nextInt();
                    int p = sc.nextInt();
                    sm.enter(id, p);
                }
                case "leave" -> sm.leave(sc.nextInt());
                case "status" -> sm.status();
                case "exit" -> {
                    break OUTER;
                }
                default -> {
                }
            }
        }

        sc.close();
    }
}