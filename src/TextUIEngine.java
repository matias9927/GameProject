import java.util.Scanner;

public class TextUIEngine implements IUIEngine{
	
	Scanner input = new Scanner(System.in);
	
	public void updateScreen() {
		//
	}
	
	public void displayDialogue(String d) {
		System.out.println(d);
	}
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}

}
