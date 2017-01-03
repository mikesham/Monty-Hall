import java.util.Random;
import java.util.Scanner;

public class Guesser {
	private Scanner scanner = new Scanner(System.in);
	private Random rand = new Random();
	private Host host;
	private String[] DOOR_CONTROL = {"closed",
									" open ",
									"picked"};
	private boolean[] pickedDoorBool = {false,
										false,
										false};
	private boolean[] openDoors;
	private int winningDoor;
	private int pickedDoor = -1; // set to -1 == did not pick a door yet
	private String switchDoor = "zero"; // set to zero == did not have a chase to switch
	
	public boolean winPrize;

	public Guesser(Host host) {
		this.host = host;
		this.openDoors = host.isDoorOpen(); // initialize closed door
		this.winningDoor = host.winningDoor(); // set winning door
	}	
	
	public void getDoors(boolean automated, String staySwitch) {
		this.showDoor();
		this.showDoorOptions();
		this.showPickedDoor(automated, staySwitch);	

	}	
	
	private void showPickedDoor(boolean automated, String staySwitch) {
		// if a door wasn't picked, pick a door
		if (this.pickedDoor < 0) { 			
			pickDoor(automated);
			this.openDoors = host.openDoor(this.openDoors, this.pickedDoor);
			for(int i = 0; i < 3; i++) {
				if(this.openDoors[i]) {
					System.out.printf("%nMonty opens door number %d,%nrevealing a Goat.%n",
										i+1);					
				}
			}
		} 
		// if a door is picked do you want to switch?
		else if (this.switchDoor.equals("zero")) {
			switchDoor(automated, staySwitch);
		// open picked door
		} else {
			openPickedDoor();
		}
	}
	
	private void openPickedDoor() {
		this.openDoors[this.pickedDoor] = true; 
		System.out.printf("%nOpening your door number %d%n",
						this.pickedDoor+1);
		
		this.showDoor();
		this.showDoorOptions();
		
		if (this.pickedDoor == this.winningDoor) {
			this.winPrize = true;
			System.out.println("\nYou win the prize!\n");			
		} else {
			this.winPrize = false;
			switch(this.switchDoor) {				
				case "y":
					System.out.println("\nYou switched to the goat\n");
					break;
				default:
					System.out.println("\nYou should have switched\n");
					break;			
			}
		}
	}
	
	private void switchDoor(boolean automated, String staySwitch) {		
		if (automated) {
			this.switchDoor = staySwitch;
			switch(this.switchDoor) {
				case "y":
					System.out.println("\nAuto switch\n");
					break;
				default:
					System.out.println("\nAuto stay\n");
					break;
				}
		} else {			
			System.out.println("\nSwitch door? (y)/(n)\n");
			this.switchDoor = scanner.next();
			this.switchDoor.toLowerCase();
		}
		switch(this.switchDoor) {
			case "y":
				this.pickedDoorBool[this.pickedDoor] = false;
				this.pickedDoor = host.switchDoor(this.openDoors, this.pickedDoor);				
				this.pickedDoorBool[this.pickedDoor] = true;
				break;
			default:
				break;
		}		
	}
	
	private void pickDoor(boolean automated) {
		if (!automated) {			
			System.out.println("\nPick a door\n");		
			this.pickedDoor = scanner.nextInt();
			this.pickedDoor--;
			this.pickedDoorBool[this.pickedDoor] = true;
		} else {
			this.pickedDoor = rand.nextInt(3);
			System.out.printf("%nAuto pick door %d",
							this.pickedDoor+1);
			this.pickedDoorBool[this.pickedDoor] = true;
		}
	}
		
	private void showDoorOptions() {		
		int doorNum;
		for (int i = 0; i < openDoors.length; i++) {
			doorNum = 0;
			if (this.openDoors[i]) {
				doorNum = 1;
			}
			if (this.pickedDoorBool[i]) {
				doorNum = 2;
			}
				System.out.printf(" | %s |", DOOR_CONTROL[doorNum]);			
		}
	}
	
	private void showDoor() {
		int openOrClosed;
		boolean[] openDoor = host.isDoorOpen();
		String[][] doors = this.host.getDoors();
		for (int i = 0; i < doors.length; i++) {
			openOrClosed = 0;
			if (openDoor[i]) {
				openOrClosed = 1;
			}
			System.out.printf(" | %s |", doors[i][openOrClosed]);
		}
		System.out.println();
	}
}
