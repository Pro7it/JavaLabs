import java.util.Objects;

public class Employees {
    int salary;
    char position;

    public Employees(int salary, char position) {
        this.salary = salary;
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Employees employer = (Employees) obj;
        return Objects.equals(position, employer.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public char position() {
        return position;
    }
}

interface forChef {
    public void cookProcess(Meals meal);
}

interface forDeliveryMan {
    public void deliverOrder(int distanceTime);
}

class Chef extends Employees implements forChef {

    public Chef(int salary) {
        super(salary, 'C');
    }

    @Override
    public void cookProcess(Meals meal) {
        for(int i = 0; i < meal.timeToPrepare; i++) {
            System.out.print("*");
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        meal.isPrepared();
    }
}

class Waiter extends Employees {
    public Waiter(int salary) {
        super(salary, 'W');
    }
}

class DeliveryMan extends Employees implements forDeliveryMan{
    public DeliveryMan(int salary) {
        super(salary, 'D');
    }

    @Override
    public void deliverOrder(int distanceTime) {
        for(int i = 0; i < distanceTime; i++) {
            System.out.print("=");
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("   Delivered!");
    }
}