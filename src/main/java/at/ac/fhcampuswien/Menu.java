package at.ac.fhcampuswien;


import java.util.Scanner;

public class Menu {

    private AppController controller = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Try again!";
    private static final String EXIT_MESSAGE = "See you soon!";


    /***
     * launches the program cycle
     */
    public void start() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            printMenu();
            String userInput;
            userInput = scan.next();
            handleInput(userInput);
        }
    }

    /***
     * handle the input of the user
     * @param input
     */
    private void handleInput(String input) {
        switch (input) {
            default:
                printInvalidInputMessage();
                break;
            case "a":
                getTopHeadlinesAustria(controller);
                break;
            case "b":
                getAllNewsBitcoin(controller);
                break;
            case "q":
                printExitMessage();
                System.exit(0);
                break;
            case "y":
                getArticleCount(controller);
                break;
        }
    }

    /***
     * get the count of the articles
     * @param ctrl
     */
    private void getArticleCount(AppController ctrl) {
        System.out.println("Number of articles: " + ctrl.getArticleCount());
    }

    /***
     * gets headlines from austria
     * @param ctrl
     */
    private void getTopHeadlinesAustria(AppController ctrl) {
        for (int i = 0; i < ctrl.getTopHeadlinesAustria().size(); i++) {
            System.out.println(ctrl.getTopHeadlinesAustria().get(i).toString());
        }
    }

    /***
     * get headlines of bitcoin
     * @param ctrl
     */
    private void getAllNewsBitcoin(AppController ctrl) {
        for (int i = 0; i < ctrl.getAllNewsBitcoin().size(); i++) {
            System.out.println(ctrl.getAllNewsBitcoin().get(i).toString());
        }
    }

    /***
     * exit message when the program closes
     */
    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /***
     * invalid message if something is tipped false
     */
    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    /***
     * our menu printed before user tips something in
     */
    private static void printMenu() {
        // Header
        System.out.print("**************************" + System.lineSeparator() +
                "*   Welcome to NewsApp   *" + System.lineSeparator() +
                "**************************" + System.lineSeparator());
        // Actions/Options
        System.out.println("Enter what you wanna do:" + System.lineSeparator() +
                "a: Get top headlines austria" + System.lineSeparator() +
                "b: Get all news about bitcoin" + System.lineSeparator() +
                "y: Count articles" + System.lineSeparator() +
                "q: Quit program");
    }
}