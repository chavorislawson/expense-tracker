package jar;

import javaTuts.interfaces.Calculator;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    // static StandardCalculator calc;
    public static void main(String[] args) {
        StandardCalculator calc = new StandardCalculator();
        calc.play();
        calc.raise(5, 6);
        System.out.println(calc.total);
    }
}

class StandardCalculator implements Calculator {

    int total;

    // make this more modular like adding being able to read multiple arguments like
    // the command line and reading symbols.
    public void play() {
        Scanner s = new Scanner(System.in);
        help();
        String input = "";
        
        while (!input.equalsIgnoreCase("exit")) {
            input = s.nextLine();
            if (input.equals("+")) {

                System.out.println("Enter a number: ");
                int a = Integer.parseInt(s.nextLine());
                System.out.println("Enter the next number to add: ");
                int b = Integer.parseInt(s.nextLine());
                add(a, b);
                System.out.println(this.total);
            } else if (input.equals("-")) {

                System.out.println("Enter a number: ");
                int a = Integer.parseInt(s.nextLine());
                System.out.println("Enter the next number to subtract: ");
                int b = Integer.parseInt(s.nextLine());
                subtract(a, b);
                System.out.println(this.total);
            } else if (input.equals("*")) {

                System.out.println("Enter a number: ");
                int a = Integer.parseInt(s.nextLine());
                System.out.println("Enter the next number to multiply: ");
                int b = Integer.parseInt(s.nextLine());
                multiply(a, b);
                System.out.println(this.total);
            } else if (input.equals("/")) {

                System.out.println("Enter a number: ");
                int a = Integer.parseInt(s.nextLine());
                System.out.println("Enter the next number to divide: ");
                int b = Integer.parseInt(s.nextLine());
                divide(a, b);
                System.out.println(this.total);
                
            } else if (input.equalsIgnoreCase("h")) {
                help();
            } else if (input.equalsIgnoreCase("exit")) {
                input = "exit";
            } else {
                System.out.println("That's not a valid command.");
            }
        }
    }

    public void help() {
        System.out.println(
                "Enter: + to add.\n\t - to subtract \n\t * to multiply \n\t \\ to divide \n\t h to repeat these options \n\t \"exit\" to exit");
    }

    public void total() {
        System.out.println(total);
    }

    public void add(int a, int b) {
        this.total = a + b;
    }

    public void add(int a) {
        total += a;
    }

    public void subtract(int a, int b) {
        total = a - b;
    }

    public void subtract(int a) {
        total -= a;
    }

    public void multiply(int a, int b) {
        total = a * b;
    }

    public void multiply(int a) {
        total *= a;
    }

    public void divide(int a, int b) {
        total = a / b;
    }

    public void divide(int a) {
        total /= a;
    }

    void raise(int a, int b) {
        total = a ^ b;
    }

    void raise(int a) {
        total ^= a;
    }

    public void clear() {
        total = 0;
    }

    public void turnOn() {

    }

    public void turnOff() {

    }
}