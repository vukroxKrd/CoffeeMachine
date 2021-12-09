package machine;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private static final Scanner scanner = new Scanner(System.in);

    public static class Builder {
        private int water = 400;
        private int milk = 540;
        private int beans = 120;
        private int cups = 9;
        private int money = 550;

        public Builder water(int val) {
            water = val;
            return this;
        }

        public Builder milk(int val) {
            milk = val;
            return this;
        }

        public Builder beans(int val) {
            beans = val;
            return this;
        }

        public Builder cups(int val) {
            cups = val;
            return this;
        }

        public Builder money(int val) {
            money = val;
            return this;
        }

        public CoffeeMachine build() {
            return new CoffeeMachine(this);
        }
    }

    private CoffeeMachine(Builder builder) {
        water = builder.water;
        milk = builder.milk;
        beans = builder.beans;
        cups = builder.cups;
        money = builder.money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    @Override
    public String toString() {
        return "The coffee machine has: \n"
                + water + " ml of water \n"
                + milk + " ml of milk \n"
                + beans + " g of coffee beans \n"
                + cups + " disposable cups \n"
                + "$" + money + " of money";
    }

    public static void controlMenu(CoffeeMachine machine) {
        System.out.println(machine);
        String command = buyFillOrTakeReq();
        System.out.println("> " + command);

        arbiter(command, machine);

    }

    private static String buyFillOrTakeReq() {
        System.out.println("Write action (buy, fill, take): ");
        return requestCommand();
    }

    private static CoffeeMachine arbiter(String command, CoffeeMachine machine) {
        CoffeeMachine coffeeMachine = null;
        switch (command) {
            case ("buy"):
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                int choice = reqInputNumberFromUser();
                System.out.println("> " + choice);
                coffeeMachine = calcConsmptnOfOneCup(choice, machine);
                System.out.println(coffeeMachine);
                return coffeeMachine;
            case ("fill"):
                System.out.println("Write how many ml of water you want to add: ");
                int water = reqInputNumberFromUser();
                System.out.println("> " + water);
                System.out.println("Write how many ml of milk you want to add: ");
                int milk = reqInputNumberFromUser();
                System.out.println("> " + milk);
                System.out.println("Write how many grams of coffee beans you want to add: ");
                int beans = reqInputNumberFromUser();
                System.out.println("> " + beans);
                System.out.println("Write how many disposable cups of coffee you want to add: ");
                int cups = reqInputNumberFromUser();
                System.out.println("> " + cups);

                coffeeMachine = new Builder()
                        .water(machine.getWater() + water)
                        .milk(machine.getMilk() + milk)
                        .beans(machine.getBeans() + beans)
                        .cups(machine.getCups() + cups)
                        .build();
                System.out.println(coffeeMachine);
                return coffeeMachine;
            case ("take"):
                System.out.println("I gave you $" + machine.getMoney());
                coffeeMachine = new Builder().money(0).build();
                System.out.println();
                System.out.println(coffeeMachine);
                return coffeeMachine;
            default:
                return machine;
        }
    }

    public static int reqInputNumberFromUser() {

        int input;
        do {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                input = reqInputNumberFromUser();
            }
        } while (input < 0);

        return input;
    }

    public static String requestCommand() {

        String[] options = {"buy", "fill", "take"};
        String input;

        boolean shallContinue = true;
        do {
            try {
                input = scanner.nextLine();
            } catch (InputMismatchException e) {
                input = requestCommand();
            }
            for (String option : options) {
                if (option.equalsIgnoreCase(input)) {
                    shallContinue = false;
                    break;
                }
            }
        } while (shallContinue);

        return input;
    }

    public static void calculateIngredients(int cups) {
        /**One cup of coffee made on this coffee machine contains
         * 200 ml of water,
         * 50 ml of milk, and
         * 15 g of coffee beans.*/
        System.out.println("> " + cups);
        System.out.println("For " + cups + " cups of coffee you will need:");
        int mlWater = cups * 200;
        int mlMilk = cups * 50;
        int grCoffeeBans = cups * 15;
//        System.out.printf("%d ml of water\n %d ml of milk \n %d g of coffee beans", mlWater, mlMilk,grCoffeeBans);
        System.out.println(mlWater + " ml of water");
        System.out.println(mlMilk + " ml of milk");
        System.out.println(grCoffeeBans + " g of coffee beans");
    }

    public static CoffeeMachine calcConsmptnOfOneCup(int coffeeType, CoffeeMachine coffeeMachine) {

        CoffeeMachine machine;
        switch (coffeeType) {

            /*1 - espresso  */
            /**espresso
             250 ml of water
             16 g of coffee beans.
             It costs $4.*/
            case (1):
                EspressoPortion espresso = new EspressoPortion();
                machine = new CoffeeMachine.Builder()
                        .water(coffeeMachine.getWater() - espresso.getWater())
                        .beans(coffeeMachine.getBeans() - espresso.getBeans())
                        .money(coffeeMachine.getMoney() + espresso.getMoney())
                        .cups(coffeeMachine.getCups() - 1)
                        .build();

                return machine;

            /*2- latte: */
            /** 350 ml of water,
             * 75 ml of milk,
             * 20 g of coffee beans.
             * costs $7.*/
            case (2):
                LattePortion latte = new LattePortion();
                machine = new CoffeeMachine.Builder()
                        .water((coffeeMachine.getWater()) - (latte.getWater()))
                        .beans(coffeeMachine.getBeans() - latte.getBeans())
                        .milk(coffeeMachine.getMilk() - latte.getMilk())
                        .money(coffeeMachine.getMoney() + latte.getMoney())
                        .cups(coffeeMachine.getCups() - 1)
                        .build();
                return machine;

            /*3 - cappuccino:*/
            /**200 ml water,
             * 100 ml milk,
             * 12 g coffee beans.
             * It costs $6.*/
            case (3):
                CappuccinoPortion cappuccino = new CappuccinoPortion();
                machine = new CoffeeMachine.Builder()
                        .water(coffeeMachine.getWater() - cappuccino.getWater())
                        .beans(coffeeMachine.getBeans() - cappuccino.getBeans())
                        .milk(coffeeMachine.getMilk() - cappuccino.getMilk())
                        .money(coffeeMachine.getMoney() + cappuccino.getMoney())
                        .cups(coffeeMachine.getCups() - 1)
                        .build();
                return machine;
            default:
                return coffeeMachine;
        }
    }


    private static Portion calculatePortionWanted(int cups) {
        return new Portion(cups);
    }

    public static void calculateCupsVsIngredients(int water, int milk, int grBeans, int cups) {
        /*200 ml of water, 50 ml of milk, and 15 g of  beans to make one cup of coffee.*/
        //"Yes, I can make that amount of coffee"
        Portion portionsWanted = calculatePortionWanted(cups);
        Portion portionAvailable = new Portion(water, milk, grBeans);
        if (portionAvailable.getCups() == portionsWanted.getCups()) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (portionAvailable.getCups() > portionsWanted.getCups()) {
            System.out.println("Yes, I can make that amount of coffee (and even " + (portionAvailable.getCups() - portionsWanted.getCups()) + " more than that)");
        } else {
            System.out.println("No, I can make only " + portionAvailable.getCups() + " cup(s) of coffee");
        }
    }
}

class Portion {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public Portion() {
    }

    public Portion(int cups) {
        this.water = water * cups;
        this.milk = milk * cups;
        this.beans = beans * cups;
        this.money = money * cups;
        this.cups = cups;
    }

    //This constructor is to calculate the number of cups,
    // that is possible to produce based on the minimal ingredient.
    public Portion(int water, int milk, int beans) {
        this.water = water / 200;
        this.milk = milk / 50;
        this.beans = beans / 15;
        int[] arr = {this.water, this.milk, this.beans};
        Arrays.sort(arr);
        this.cups = arr[0];
    }

    public Portion(int water, int milk, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }
}

/**
 * espresso
 * 250 ml of water
 * 16 g of coffee beans.
 * It costs $4.
 */
class EspressoPortion extends Portion {
    private int water = 250;
    private int beans = 16;
    private int money = 4;

    public EspressoPortion(int cups) {
        super(cups);
    }

    public EspressoPortion() {
    }

    public EspressoPortion(int consWater, int consBeans, int moneyEarned) {
        this.water = consWater;
        this.beans = consBeans;
        this.money = moneyEarned;
    }

    @Override
    public int getWater() {
        return water;
    }

    @Override
    public int getBeans() {
        return beans;
    }

    @Override
    public int getMoney() {
        return money;
    }
}

/**
 * 350 ml of water,
 * 75 ml of milk,
 * 20 g of coffee beans.
 * costs $7.
 */
class LattePortion extends Portion {
    private int water = 350;
    private int milk = 75;
    private int beans = 20;
    private int money = 7;

    public LattePortion(int cups) {
        super(cups);
    }

    public LattePortion(int water, int milk, int beans, int money) {
        super(water, milk, beans, money);
    }

    public LattePortion() {
    }

    @Override
    public int getWater() {
        return water;
    }

    @Override
    public int getMilk() {
        return milk;
    }

    @Override
    public int getBeans() {
        return beans;
    }

    @Override
    public int getMoney() {
        return money;
    }
}

/**
 * 200 ml water,
 * 100 ml milk,
 * 12 g coffee beans.
 * It costs $6.
 */
class CappuccinoPortion extends Portion {
    private int water = 200;
    private int milk = 100;
    private int beans = 12;
    private int money = 6;

    public CappuccinoPortion() {
    }

    public CappuccinoPortion(int cups) {
        super(cups);
    }

    public CappuccinoPortion(int water, int milk, int beans, int money) {
        super(water, milk, beans, money);
    }

    @Override
    public int getWater() {
        return water;
    }

    @Override
    public int getMilk() {
        return milk;
    }

    @Override
    public int getBeans() {
        return beans;
    }

    @Override
    public int getMoney() {
        return money;
    }
}
