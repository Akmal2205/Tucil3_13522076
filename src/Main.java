import Utils.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input_option = new Scanner(System.in);
        System.out.println("Welcome to word ladder solver~!!");
        System.out.println("Algorithms:");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.print("Option (1/2/3) : ");
        int option = input_option.nextInt();

        while (option != 1 && option != 2 && option != 3) {
            Scanner input_option_ = new Scanner(System.in);
            System.out.println("====================");
            System.out.println("Please Choose between these options!");
            System.out.println("Algorithms:");
            System.out.println("1. Uniform Cost Search");
            System.out.println("2. Greedy Best First Search");
            System.out.println("3. A*");
            System.out.print("Option (1/2/3) : ");
            option = input_option.nextInt();
        }

        Scanner input_start = new Scanner(System.in);
        Scanner input_end = new Scanner(System.in);
        System.out.print("Start word : ");
        String start = input_start.nextLine();
        System.out.print("End word : ");
        String end = input_end.nextLine();
        WordValidator w = new WordValidator(start);

        while ((!w.isWordValid(start) || !w.isWordValid(end)) && start.length() != end.length()) {
            Scanner input_start_ = new Scanner(System.in);
            Scanner input_end_ = new Scanner(System.in);
            System.out.println("===================");
            System.out.println("Input invalid!");
            System.out.print("Start word : ");
            start = input_start_.nextLine();
            System.out.print("End word : ");
            end = input_end_.nextLine();
        }

        System.out.print("Path : ");
        if (option == 1) { // UCS
            UCS ucs = new UCS(start, end);
            ucs.mainUCS();
        } else if (option == 2) { // GreedyBFS
            GreedyBFS g = new GreedyBFS(start, end);
            g.mainGBFS();
        } else { // A star
            Astar as = new Astar(start, end);
            as.mainAstar();
        }

    }

    public String[] MainGUI(String option, String start, String end) {
        WordValidator w = new WordValidator(start);
        String[] res = { "Invalid Input!", "-", "-" };
        if (w.isWordValid(start) && w.isWordValid(end)) {
            if (option.equalsIgnoreCase("UCS")) { // UCS
                UCS ucs = new UCS(start, end);
                String[] result = ucs.mainUCS();
                res = result;
            } else if (option.equalsIgnoreCase("GreedyBFS")) { // GreedyBFS
                GreedyBFS g = new GreedyBFS(start, end);
                String[] result = g.mainGBFS();
                res = result;
            } else { // A star
                Astar as = new Astar(start, end);
                as.mainAstar();
                String[] result = as.mainAstar();
                res = result;
            }
        }
        return res;
    }
}
