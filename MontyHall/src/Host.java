import java.util.Random;

public class Host {
	private String[][] doors = { {"  1   ", " Goat "},
								{"  2   ", " Goat "},
								{"  3   ", " Goat "} };
	private int winningDoor;
	private boolean[] openDoors = {false,
								false,
								false};
	
	public Host() {
		this.winningDoor = setWinningDoor();
		this.doors[this.winningDoor][1] = "Prize*";
	}	
	
	private int setWinningDoor() {
		Random rand = new Random();
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
		Random rand = new Random ();
		if (pickedDoor == this.winningDoor) {
			switch(pickedDoor) {
				case 0:
					openDoors[rand.nextInt(2) + 1] = true;
					break;
				case 1:
					int roll = rand.nextInt(2);
					if(roll == 1) {
						roll++;
					}
					openDoors[roll] = true;
					break;
				case 2:
					openDoors[rand.nextInt(2)] = true;
					break;
			}			
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
