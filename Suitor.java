import java.util.LinkedList;
import java.util.Scanner;

public class Suitor {

	public static void start (Scanner scnr) {

		System.out.println("\n");
		System.out.println("Select the suitor.");

		System.out.print("Enter the number of suitors: ");
        int n = scnr.nextInt();
        scnr.nextLine();
        
        LinkedList<Integer> suitors = new LinkedList<>();
        String[] names = new String[n];
        
        for (int i = 0; i < n; i++) {
            suitors.add(i + 1);
            System.out.print("Enter name of Suitor #" + (i + 1) + ": ");
            names[i] = scnr.nextLine();
        }
        
        int currentIndex = 0; 
        int eliminationStep = 3;
        
        while (suitors.size() > 1) {
            currentIndex = (currentIndex + eliminationStep - 1) % suitors.size();
            int eliminatedSuitor = suitors.remove(currentIndex); 
            
            
            System.out.println("Suitor #" + eliminatedSuitor + ", " + names[eliminatedSuitor - 1] + ", eliminated.");
        }
        
        int selectedSuitor = suitors.getFirst();
        System.out.println("\nThe correct suitor was #" + selectedSuitor + ", " + names[selectedSuitor - 1] + ".");
	}
}




