import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;
    private double total;

    /**
     * Represents the different mathematical operators this application
     * supports.
     */
    private enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULUS
    }

    public Calculator() {
        scanner = new Scanner(System.in);
        total = 0;
    }

    /**
     * Start the calculator. The calculator has a precision of 4 decimals.
     */
    public void run() {
        System.out.println("\nVälkommen till kalkylatorn!");
        System.out.println("Välj ett räknesätt och gör din kalkyl!");
        System.out.println("När ni skriver in nummer, separera dom med mellanslag: 1 2 3");
        System.out.println("Ni kan när som helst skriva \"exit\" för att avsluta programmet.");

        while (true) {
            System.out.println(); // Empty line for formatting.
            Operator operator = nextOperator();

            switch (operator) {
                case ADD:
                    total = addNumbers(nextNumbers());
                    break;
                case SUBTRACT:
                    total = subtractNumbers(nextNumbers());
                    break;
                case MULTIPLY:
                    total = multiplyNumbers(nextNumbers());
                    break;
                case DIVIDE:
                    total = divideNumbers(nextNumbers());
                    break;
                case MODULUS:
                    total = modulusNumbers(nextNumbers());
                    break;
            }

            System.out.println("Resultat: " + total);

            if (!promptKeepGoing()) {
                System.exit(0);
            }
        }
    }

    /**
     * Performs addition on a list of numbers.
     *
     * @param numbers The numbers to add together.
     * @return The result of adding all numbers together.
     */
    private double addNumbers(double[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    /**
     * Performs sequential subtraction on an array of numbers.
     *
     * @param numbers The numbers to perform subtraction on.
     * @return the result of sequential subtraction starting from the first
     * number, or 0 if the array is empty
     */
    private double subtractNumbers(double[] numbers) {
        double result = 0;

        if (numbers.length > 0) {
            result = numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            result -= numbers[i];
        }

        return result;
    }

    /**
     * Multiplies a list of numbers.
     *
     * @param numbers The numbers to multiply together.
     * @return The result of the multiplication.
     */
    private double multiplyNumbers(double[] numbers) {
        double result = 0;

        if (numbers.length > 0) {
            result = numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            result *= numbers[i];
        }

        return toPrecision(result);
    }

    /**
     * Divides a list of numbers.
     *
     * @param numbers The numbers to divide together.
     * @return The result of the division.
     */
    private double divideNumbers(double[] numbers) {
        double result = 0;

        if (numbers.length > 0) {
            result = numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            result /= numbers[i];
        }

        return toPrecision(result);
    }

    /**
     * Sequentially performs the modulus operation on a list of numbers.
     *
     * @param numbers The numbers to perform modulus on.
     * @return The result of the sequential modulus operations.
     */
    private double modulusNumbers(double[] numbers) {
        double result = 0;

        if (numbers.length > 0) {
            result = numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            result %= numbers[i];
        }

        return toPrecision(result);
    }

    /**
     * Takes a number and cuts of any decimals after the fourth.
     *
     * @param num The number to cut down to precision.
     * @return The result of cutting of any decimals after the fourth.
     */
    private double toPrecision(double num) {
        return Math.floor((num) * 10000) / 10000;
    }

    /**
     * Prompts the user to enter new numbers in the console. Each number must
     * be separated by a space. The user will continue to be prompted until
     * they either exit the application or give the correct input.
     *
     * @return A list of numbers parsed from user input.
     */
    private double[] nextNumbers() {
        double[] numbers = null;

        while (true) {
            System.out.print("Skriv in nummer: ");
            String input = scanner.nextLine();

            try {
                numbers = parseInput(input);
                break;
            } catch (Exception e) {
                // Do nothing. The loop will continue and prompt the user until
                // the input is correct.
                maybeExit(input);
            }
        }

        return numbers;
    }

    /**
     * Parses user input into a double[].
     *
     * @param line Input to parse.
     * @return A list of double values that was parsed from the input.
     */
    private double[] parseInput(String line) {
        String[] splits = line.trim().split("\\s+");
        double[] result = new double[splits.length];

        for (int i = 0; i < splits.length; i++) {
            result[i] = Double.parseDouble(splits[i]);
        }

        return result;
    }

    /**
     * Prompts user for a mathematical operator (+, -, /, *). If not provided,
     * the user will be prompted to try again until they succeed, or exits the
     * application.
     *
     * @return The operator as an {@link Operator}.
     */
    private Operator nextOperator() {
        Operator operator = null;

        while (operator == null) {
            System.out.print("Välj en operator (+, -, /, *, %): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "+":
                    operator = Operator.ADD;
                    break;
                case "-":
                    operator = Operator.SUBTRACT;
                    break;
                case "*":
                    operator = Operator.MULTIPLY;
                    break;
                case "/":
                    operator = Operator.DIVIDE;
                    break;
                case "%":
                    operator = Operator.MODULUS;
                    break;
                default: {
                    maybeExit(input);
                }
            }
        }

        return operator;
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
            System.out.print("Vill ni fortsätta? (Y/N): ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "n":
                    return false;
                case "y":
                    return true;
                default: {
                    maybeExit(input);
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
