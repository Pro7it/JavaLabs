import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class OrderManager {
    private static final Map<Meals, Integer> basket = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

//    RestaurantBalance restaurantBalance = new RestaurantBalance(1000);

    public OrderManager() {}

     public void addToOrder(Meals meal) {
        basket.put(meal, basket.getOrDefault(meal, 0) + 1);
        System.out.println("Added!");
    }

    public void getBasket() {
        if (basket.isEmpty()) {
            System.out.println("Basket is empty!");
        } else {
            for (Map.Entry<Meals, Integer> entry : basket.entrySet()) {
                System.out.println(entry.getKey().name + ": " + entry.getValue());
            }
        }
        System.out.println();
    }

     public void clearTable() {
        basket.clear();
    }

     public void finishOrder(RestaurantBalance restaurantBalance) {
        System.out.println("Order with delivery y/n?");
        int salaryPayment = restaurantBalance.spendMoney();
        char c = sc.next().charAt(0);
        int[] price;
        switch (c) {
            case 'y':
                price = calculatePrice();
                showPrice(restaurantBalance, price[0],price[1],salaryPayment,true);
                break;
            case 'n':
                price = calculatePrice();
                showPrice(restaurantBalance, price[0], price[1], salaryPayment);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private int[] calculatePrice() {
        int priceToGet = 0;
        int priceToPay = 0;
        for(Map.Entry<Meals, Integer> entry : basket.entrySet()) {
            priceToGet += entry.getKey().priceToPay * entry.getValue();
            priceToPay += entry.getKey().priceToCook * entry.getValue();
        }

        return new int[] {priceToGet, priceToPay};
    }

    private boolean getTheMealsReady() {
        Chef chef = null;
        Waiter waiter = null;
        for(Employees employee : EmployeesManagement.personnel) {
            if(employee.position() == 'C') {
                chef = (Chef) employee;
            } else if (employee.position() == 'W') {
                waiter = (Waiter) employee;
            }
        }

        if(chef == null) {
            System.out.println("You cant make orders without chef");
            return false;
        } else if (waiter == null) {
            System.out.println("You cant make orders without waiter");
            return false;
        }
        for(Map.Entry<Meals, Integer> entry : basket.entrySet()) {
            chef.cookProcess(entry.getKey());
        }

        return true;
    }

    private void showPrice(RestaurantBalance restaurantBalance,int priceToGet, int priceToPay, int salaryPayment) {
        if(priceToGet > 0) {
            System.out.println("Order price:" + Math.round(Meals.usdToUah(priceToGet)) + "₴");
            System.out.println("Salary payment:" + salaryPayment + "₴");
            System.out.println("Price to get:" + (int)Math.round(Meals.usdToUah(priceToGet - priceToPay) - salaryPayment)  + "₴");
            if(getTheMealsReady()) {
                restaurantBalance.getMoney((int)Math.round(Meals.usdToUah(priceToGet - priceToPay) - salaryPayment));
            }
            clearTable();
        } else {
            System.out.println("Basket is empty!");
            return;
        }
    }

    private void showPrice(RestaurantBalance restaurantBalance,int priceToGet, int priceToPay, int salaryPayment,boolean delivery) {
        if(priceToGet > 0) {
            System.out.println("Enter your home position");
            int x = sc.nextInt();
            int y = sc.nextInt();
            int distance = countDistance(x,y);
            int distanceTime = countDistanceTime(distance);
            int distancePrice = countDistancePrice(distance);
            System.out.println("Order price:" + Math.round(Meals.usdToUah(priceToGet)) + "₴");
            System.out.println("Delivery price:" + Math.round(Meals.usdToUah(distancePrice)) + "₴");
            System.out.println("Salary payment:" + salaryPayment + "₴");
            System.out.println("Price to get:" + (int)Math.round(Meals.usdToUah(priceToGet - priceToPay + distancePrice) - salaryPayment)  + "₴");
            DeliveryMan deliveryMan = isDeliver();
            if(deliveryMan == null) {
                System.out.println("You cant make orders without delivery man");
                return;
            }
            if(getTheMealsReady()){
                deliveryMan.deliverOrder(distanceTime);
                restaurantBalance.getMoney((int)Math.round(Meals.usdToUah(priceToGet - priceToPay)) - salaryPayment, (int)Math.round(Meals.usdToUah(distancePrice)));
            }
            clearTable();
        } else {
            System.out.println("Basket is empty!");
            return;
        }
    }

    static private int countDistance(int x,int y) {
        return (int)Math.round(Math.sqrt(x*x + y*y));
    }

    private static int countDistanceTime(int distance) {
        if(distance > 100) {
            return 15;
        } else if (distance > 25) {
            return 10;
        } else {
            return 5;
        }
    }

    private static int countDistancePrice(int distance) {
        if(distance > 100) {
            return 5;
        } else if (distance > 25) {
            return 2;
        } else {
            return 0;
        }
    }

    private DeliveryMan isDeliver() {
        DeliveryMan deliveryMan = null;
        for(Employees employee : EmployeesManagement.personnel) {
            if (employee.position() == 'D') {
                deliveryMan = (DeliveryMan) employee;
                break;
            }
        }

        return deliveryMan;
    }
}
