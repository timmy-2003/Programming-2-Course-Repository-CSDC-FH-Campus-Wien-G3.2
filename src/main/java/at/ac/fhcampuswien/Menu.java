package at.ac.fhcampuswien;


import java.util.Scanner;

public class Menu {

    private AppController controller = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Try again!";
    private static final String EXIT_MESSAGE = "See you soon!";


    public void start() {
        Scanner scan = new Scanner(System.in);
        while (true){
            printMenu();
            String userInput;
            userInput = scan.next();
            handleInput(userInput);
        }
    }

    private void handleInput(String input) {
        switch (input){
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

    private void getArticleCount(AppController ctrl) {
        System.out.println("Number of articles: " + ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        for (int i = 0; i<ctrl.getTopHeadlinesAustria().size(); i++){
            System.out.println(ctrl.getTopHeadlinesAustria().get(i).toString());
        }
    }

    private void getAllNewsBitcoin(AppController ctrl) {
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    private static void printMenu() {

    }


}
