import java.util.Random;

public class Host {
	private Random rand = new Random ();
	private String[][] doors = { {"  1   ", " Goat "},
								{"  2   ", " Goat "},
								{"  3   ", " Goat "} };
	private boolean[] openDoors = {false,
								false,
								false};
	private int winningDoor;
	
	public Host() {
		this.winningDoor = setWinningDoor();
		this.doors[this.winningDoor][1] = "Prize*";
	}	
	
	private int setWinningDoor() {
		int prizeNum = rand.nextInt(3);		
		return prizeNum;
	}
	
	public int switchDoor(boolean[] openDoors, int pickedDoor) {
		for (int i = 0; i < 3; i++) {
			if (!openDoors[i] && pickedDoor != i) {
				pickedDoor = i;
				break;
			}
		}
		return pickedDoor;
	}
	
	public boolean[] openDoor(boolean[] openDoors, int pickedDoor) {
		if (pickedDoor == this.winningDoor) {			
			int door = rand.nextInt(3);
			int i = (pickedDoor == door) ? (door + 1) % 3 : door;
			openDoors[i] = true;
			
		} else {
			for (int i = 0; i < 3; i++) {
				if ( (i != pickedDoor) && (i != this.winningDoor) ) {
					openDoors[i] = true;
					break;
				}
			}
		}		
		return openDoors;
	}
	
	public int winningDoor() {
		return this.winningDoor;
	}
	
	public boolean[] isDoorOpen() {
		return this.openDoors;
	}
	
	public String[][] getDoors() {
		return this.doors;
	}	
}
