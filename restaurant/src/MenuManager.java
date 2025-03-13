import java.util.Scanner;

public class MenuManager {
    static Scanner sc = new Scanner(System.in);

    public MenuManager() {}

     void pizzaChose(OrderManager orderManager) {
        Pizza[] pizzas = {new Paperoni(), new BBQ(), new Carbonara()};
        for(int i = 0; i < pizzas.length; i++) {
            System.out.print(i + 1 + ")");
            pizzas[i].displayInfo();
        }
        int pizzaNum = sc.nextInt() - 1;
        if(pizzaNum < 0 || pizzaNum >= pizzas.length) {
            System.out.println("Invalid index");
        } else {
            orderManager.addToOrder(pizzas[pizzaNum]);
        }

        return;
    }

     void drinkChose(OrderManager orderManager) {
        Drink[] drinks = {new Cola(), new Juice(), new Water()};
        for(int i = 0; i < drinks.length; i++) {
            System.out.print(i + 1 + ")");
            drinks[i].displayInfo();
        }
        int drinkNum = sc.nextInt() - 1;
        if(drinkNum < 0 || drinkNum >= drinks.length) {
            System.out.println("Invalid index");
        } else {
            orderManager.addToOrder(drinks[drinkNum]);
        }

        return;
    }

     void saladChose(OrderManager orderManager) {
        Salad[] salads = {new Caesar(), new Tuna(), new Nuts()};
        for(int i = 0; i < salads.length; i++) {
            System.out.print(i + 1 + ")");
            salads[i].displayInfo();
        }
        int saladNum = sc.nextInt() - 1;
        if(saladNum < 0 || saladNum >= salads.length) {
            System.out.println("Invalid index");
        } else {
            orderManager.addToOrder(salads[saladNum]);
        }

        return;
    }

     void choseMenu(OrderManager orderManager) {
        System.out.print(
                """
                        1)Pizzas
                        2)Salads
                        3)Drinks
                        """
        );

        int res = sc.nextInt();
        switch (res) {
            case 1:
                pizzaChose(orderManager);
                break;
            case 2:
                saladChose(orderManager);
                break;
            case 3:
                drinkChose(orderManager);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
