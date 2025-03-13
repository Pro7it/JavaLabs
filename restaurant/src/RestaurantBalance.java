public class RestaurantBalance {
    private int balance;

    public RestaurantBalance(int balance) {
        this.balance = balance;
    }

    public void getMoney(int amountOfMoney) {
        balance += amountOfMoney;
    }
    public void getMoney(int amountOfMoney, int extraMoney) {
        balance += amountOfMoney;
        balance += extraMoney;
    }

    public int spendMoney() {
        int salaryPayment = 0;
        for(Employees employee : EmployeesManagement.personnel) {
            salaryPayment += employee.salary;
        }

        return  salaryPayment;
    }

    public int showMoney() {
        return balance;
    }
}
