import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    String id;
    String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Attendance {
    String employeeId;
    String clockInTime;
    String clockOutTime;

    public Attendance(String employeeId, String clockInTime) {
        this.employeeId = employeeId;
        this.clockInTime = clockInTime;
        this.clockOutTime = null;
    }

    public void clockOut(String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }
}

public class AttendanceManagementSystem {
    private static Map<String, Employee> employees = new HashMap<>();
    private static Map<String, ArrayList<Attendance>> attendanceRecords = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Attendance Management System");
            System.out.println("1. Register Employee");
            System.out.println("2. Clock In");
            System.out.println("3. Clock Out");
            System.out.println("4. View Attendance Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerEmployee(scanner);
                    break;
                case 2:
                    clockIn(scanner);
                    break;
                case 3:
                    clockOut(scanner);
                    break;
                case 4:
                    viewAttendanceReport(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void registerEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        Employee employee = new Employee(id, name);
        employees.put(id, employee);

        System.out.println("Employee registered successfully!");
    }

    private static void clockIn(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter Clock-In Time (HH:mm): ");
        String clockInTime = scanner.nextLine();

        Attendance attendance = new Attendance(id, clockInTime);
        attendanceRecords.computeIfAbsent(id, k -> new ArrayList<>()).add(attendance);

        System.out.println("Clock-In recorded successfully!");
    }

    private static void clockOut(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        ArrayList<Attendance> records = attendanceRecords.get(id);
        if (records == null || records.isEmpty()) {
            System.out.println("No clock-in record found!");
            return;
        }

        Attendance lastAttendance = records.get(records.size() - 1);
        if (lastAttendance.getClockOutTime() != null) {
            System.out.println("You have already clocked out!");
            return;
        }

        System.out.print("Enter Clock-Out Time (HH:mm): ");
        String clockOutTime = scanner.nextLine();
        lastAttendance.clockOut(clockOutTime);

        System.out.println("Clock-Out recorded successfully!");
    }

    private static void viewAttendanceReport(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        ArrayList<Attendance> records = attendanceRecords.get(id);
        if (records == null || records.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }

        System.out.println("Attendance Report for " + employees.get(id).getName() + ":");
        for (Attendance attendance : records) {
            System.out.println("Clock-In: " + attendance.getClockInTime() + ", Clock-Out: " + (attendance.getClockOutTime() != null ? attendance.getClockOutTime() : "N/A"));
        }
    }
}
