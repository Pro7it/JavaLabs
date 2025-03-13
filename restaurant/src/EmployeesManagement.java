import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeesManagement {

    static Scanner sc = new Scanner(System.in);
    static List<Employees> personnel = new ArrayList<>();

    public EmployeesManagement() {}

    void showEmployeesMenu() {
        System.out.print("""
                1)New employee
                2)Show available
                3)Lay off
                """);
        int res = sc.nextInt();
        switch (res) {
            case 1:
                newEmployee();
                break;
            case 2:
                showEmployeesList();
                break;
            case 3:
                removeEmployee();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }

     public void newEmployee() {
        System.out.print("Input salary for new employee: ");
        int newEmployeeSalary = sc.nextInt();
        System.out.print("Input position for new employee(C - Chef, W - waiter, D - delivery man): ");
        int newEmployeePosition = sc.next().charAt(0);

        Employees newEmployee;

        switch (newEmployeePosition) {
            case 'C':
                newEmployee = new Chef(newEmployeeSalary);
                break;
            case 'W':
                newEmployee = new Waiter(newEmployeeSalary);
                break;
            case 'D':
                newEmployee = new DeliveryMan(newEmployeeSalary);
                break;
            default:
                System.out.println("Invalid position!");
                return;
        }

        personnel.add(newEmployee);

        System.out.println("Added!");
        return;
    }


     public void showEmployeesList() {
        int i = 1;
        if(personnel.isEmpty()) {
            System.out.println("You have no employees!");
            return;
        }
        for(Employees employee : EmployeesManagement.personnel) {
            System.out.println(i + ")" + "Position: " + employee.position + "| salary: " + employee.salary  + "₴");
            i++;
        }

        System.out.println();
    }

    public void removeEmployee() {
        System.out.print("Enter the employee number: ");
        int index = sc.nextInt();
        index--;
        if (index < 1 || index >= personnel.size()) {
            System.out.println("Invalid index");
            return;
        }

        Employees removed = personnel.remove(index);
        System.out.println("Removed: " + removed.position + " | salary: " + removed.salary  + "₴");
    }
}
