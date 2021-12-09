package machine;

public class Main {
    public static void main(String[] args) {
        /**Step 1*/
//        System.out.println("Starting to make a coffee\n" +
//                "Grinding coffee beans\n" +
//                "Boiling water\n" +
//                "Mixing boiled water with crushed coffee beans\n" +
//                "Pouring coffee into the cup\n" +
//                "Pouring some milk into the cup\n" +
//                "Coffee is ready!");
        /**Step 2*/
//        int cupsOfCoffee = requestUserInput();
//        calculateIngredients(cupsOfCoffee);

        /**step3*/
//        System.out.println("Write how many ml of water the coffee machine has: ");
//        int water = requestUserInput();
//        System.out.println("> " + water);
//
//        System.out.println("Write how many ml of milk the coffee machine has: ");
//        int milk = requestUserInput();
//        System.out.println("> " + milk);
//
//        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
//        int grBeans = requestUserInput();
//        System.out.println("> " + grBeans);
//        System.out.println("Write how many cups of coffee you will need: ");
//        int cups = requestUserInput();
//
//        calculateCupsVsIngredients(water, milk, grBeans, cups);

        /**Step 4*/
        CoffeeMachine coffeeMachine = new CoffeeMachine.Builder().build();
        CoffeeMachine.controlMenu(coffeeMachine);
    }
}
