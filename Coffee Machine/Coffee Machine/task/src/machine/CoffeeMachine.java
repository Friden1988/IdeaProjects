package machine;
import java.util.*;
public class CoffeeMachine {
    static int water, milk, beans, cups, money;
    public static void info() {
        System.out.printf("The coffee machine has:%n%d of water%n%d of milk%n%d of coffee beans%n%d of disposable cups%n%d of money%n", water, milk,beans, cups, money);
        System.out.println();
    }
    public static void buy() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int coff = scan.nextInt();
        if (coff == 1) {
            if (water > 250 && beans > 16) {
                water -= 250; beans -= 16; cups -= 1; money += 4;
            }
        }
        if (coff == 2) {
            if (water > 350 && milk > 75 && beans > 20) {
                water -= 350; milk -= 75; beans -= 20; cups -= 1; money += 7;
            }
        }
        if (coff == 3) {
            if (water > 200 && milk > 100 && beans > 12) {
                water -= 200; milk -= 100; beans -= 12; cups -= 1; money += 6;
            }
        }
        System.out.println();
    }
    public static void fill() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scan.nextInt();
    }
    public static void take() {
        System.out.printf("I gave you $%d", money);
        money = 0;
        System.out.println();
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        water = 400; milk = 540; beans = 120; cups = 9; money = 550;
        info();
        System.out.println("Write action (buy, fill, take):");
        String act = scan.next();
        switch (act) {
            case "buy": buy(); break;
            case "fill": fill(); break;
            case "take": take(); break;
        }
        info();
        /*int water, milk, cup, beans, resCup = 0;
        System.out.println("Write how many ml of water the coffee machine has:");
        water = scan.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milk = scan.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        beans = scan.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        cup = scan.nextInt();
        if (cup == 1 && water == 0 && milk == 0 && beans == 0) {
            System.out.println("No, I can make only 0 cup(s) of coffee");
        }
        if (cup == 0 && water == 0 && milk == 0 && beans == 0) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            while (water >= 0 && milk >= 0 && beans >= 0) {
                water -= 200;
                milk -= 50;
                beans -= 15;
                if (water >= 0 && milk >= 0 && beans >= 0) {
                    resCup++;
                }
            } //345640 43423 23234 1
            //1728 868 1548
            // подсчет количества чашек
        }

        if (resCup >= cup) {
            if (resCup == cup) {
                System.out.println("Yes, I can make that amount of coffee");
            } else {
                System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)",resCup-cup);
                }
//600 153 100 1
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", resCup);
        }
        //int water = 200 * cup;
        //int milk = 50 * cup;
        //int beans = cup * 15;
        //System.out.println();
        //System.out.printf("%d %d %d %d", water, milk, beans, resCup);*/
    }
}