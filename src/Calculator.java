import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;
    private double result;

    private enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
    }

    public Calculator() {
        scanner = new Scanner(System.in);
        result = 0;
    }

    /**
     * Start the calculator. The calculator has a precision of 4 decimals.
     */
    public void run() {
        System.out.println("Välkommen till kalkylatorn!");
        System.out.println("Lägg ihop två nummer med en av följande operatorer: +, -, / eller *");
        System.out.println("Ni kan när som helst skriva \"exit\" för att avsluta programmet");

        while (true) {
            System.out.print("Nummer 1: ");
            double num1 = nextNumber();

            System.out.print("Nummer 2: ");
            double num2 = nextNumber();

            System.out.print("Operator (+, -, /, *): ");
            Operator operator = nextOperator();

            switch (operator) {
                case ADD:
                    result = num1 + num2;
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    break;
                case MULTIPLY:
                    result = Math.floor((num1 * num2) * 10000) / 10000;
                    break;
                case DIVIDE:
                    result = Math.floor((num1 / num2) * 10000) / 10000;
                    break;
            }

            System.out.println("Resultat: " + result);
            System.out.println("Vill ni fortsätta? (Y/N)");

            if (!promptKeepGoing()) {
                System.exit(0);
            }
        }
    }

    /**
     * Prompts user for a number. If a number is not provided, the user will be
     * prompted to try again until they succeed, or exits the application.
     *
     * @return The user input as a double.
     */
    private double nextNumber() {
        while (!scanner.hasNextDouble()) {
            maybeExit(scanner.next());
            System.out.println(" Det där är inte ett nummer! >:(");
            scanner.nextLine();
        }

        return scanner.nextDouble();
    }

    /**
     * Prompts user for a mathematical operator (+, -, /, *). If not provided,
     * the user will be prompted to try again until they succeed, or exits the
     * application.
     *
     * @return The operator as an {@link Operator}.
     */
    private Operator nextOperator() {
        while (true) {
            String input = scanner.next();

            switch (input) {
                case "+": return Operator.ADD;
                case "-": return Operator.SUBTRACT;
                case "*": return Operator.MULTIPLY;
                case "/": return Operator.DIVIDE;
                default: {
                    maybeExit(input);
                    System.out.println("Det där är inte en giltig operator! (+, -, /, *)");
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * Prompts the user if they want to continue with another calculation
     * (Y or N). The user has to either type "y" to continue, or "n" to quit
     * the application. The user can also enter "exit" to quit the application.
     *
     * @return "false" if the user wants to quit, "true" if the want to
     * continue.
     */
    private boolean promptKeepGoing() {
        while (true) {
            String input = scanner.next();

            switch (input.toLowerCase()) {
                case "n": return false;
                case "y": return true;
                default: {
                    maybeExit(input);
                    System.out.println("Vänligen skriv ett giltigt svar: Y eller N");
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * Checks user input for the "exit" command, and exists the application
     * if encountered.
     */
    private void maybeExit(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}
