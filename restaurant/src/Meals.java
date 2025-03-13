import java.util.Objects;

class Meals {
    String name;
    int priceToPay;
    int priceToCook;
    int timeToPrepare;
    boolean allergens;

    public Meals(String name, int priceToPay, int priceToCook, int timeToPrepare) {
        this.name = name;
        this.priceToPay = priceToPay;
        this.priceToCook = priceToCook;
        this.timeToPrepare = timeToPrepare;
    }

    public Meals(String name, int priceToPay, int priceToCook, int timeToPrepare, boolean allergens) {
        this.name = name;
        this.priceToPay = priceToPay;
        this.priceToCook = priceToCook;
        this.timeToPrepare = timeToPrepare;
        this.allergens = allergens;
    }

    void isPrepared() {
        System.out.println("   Ready!");
    }

    public static double usdToUah(int price) {
        return price * 41.46;
    }

    public void displayInfo() {
        System.out.print(name + " ");
        System.out.println(Math.round(usdToUah(priceToPay)) + "₴");
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Meals meal = (Meals) obj;
        return Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

abstract class Pizza extends Meals {
    public Pizza(String name, int priceToPay, int priceToCook, int timeToPrepare) {
        super(name, priceToPay, priceToCook, timeToPrepare);
    }

}

class Paperoni extends Pizza {
    public Paperoni() {
        super("Paperoni", 5, 2, 5);
    }
}

class BBQ extends Pizza {
    public BBQ() {
        super("BBQ", 4, 2,4);
    }
}

class Carbonara extends Pizza {
    public Carbonara() {
        super("Carbonara", 10, 3,7);
    }
}

abstract class Salad extends Meals {
    public Salad(String name, int priceToPay, int priceToCook, int timeToPrepare, boolean allergens) {
        super(name, priceToPay, priceToCook, timeToPrepare, allergens);
    }

    @Override
    public void displayInfo() {
        System.out.print(name + " ");
        System.out.print(Math.round(usdToUah(priceToPay)) + "₴");
        System.out.println(allergens ? " *Allergens*" : "");
    }
}

class Caesar extends Salad {
    public Caesar() {
        super("Caesar", 5, 2,4, false);
    }
}
class Tuna extends Salad {
    public Tuna() {
        super("Tuna", 6, 2,5, true);
    }
}
class Nuts extends Salad {
    public Nuts() {
        super("Nuts", 4, 1,3, true);
    }
}

abstract class Drink extends Meals {
    public Drink(String name, int priceToPay, int priceToCook, int timeToPrepare) {
        super(name, priceToPay, priceToCook, timeToPrepare);
    }
}

class Cola extends Drink {
    public Cola() {
        super("Cola", 1,1, 1);
    }
}
class Juice extends Drink {
    public Juice() {
        super("Juice", 2,0,1);
    }
}
class Water extends Drink {
    public Water() {
        super("Water", 0,0, 1);
    }
}