import java.util.Scanner;

public class StatTracker {
	private Scanner scanner = new Scanner(System.in);
	private Host host;
	private Guesser guesser;
	private int totalGames;
	private int wins;
	private int gameCounter;	
	private boolean automated;
	private String staySwitch;
	
	
	public StatTracker() {
		this.totalGames = 0;
		this.gameCounter = 0;
		this.wins = 0;
		this.automated = false;
		this.staySwitch = "n";
	}
	
	public void playGame() {		
		automation();
		this.gameCounter = getTotalGames();
		while (this.gameCounter != 0){
			getGuesserInfo();
			wins();
			this.gameCounter--;				
		}
	}
	
	public void getGuesserInfo() {
		this.host = new Host();
		this.guesser = new Guesser(host);
		int gameMaxTurns = 3;
		
		while(gameMaxTurns > 0) {		
			this.guesser.getDoors(this.automated, this.staySwitch);	
			gameMaxTurns--;
		}
	}

	private void automation() {
		System.out.println("Enter # of game to automate\nor 0 to play");
		this.totalGames = scanner.nextInt();
		
		if (this.totalGames > 0) {
			this.automated = true;
		}	
		if (automated) {			
			System.out.println("Switch or Stay? (y) to switch"); 
			this.staySwitch = scanner.next();
		}		
	}
	
	private void wins() {
		if (guesser.winPrize) {
			this.wins++;				
		}
		int currentGamesPlayed = (this.totalGames - this.gameCounter + 1);
		System.out.printf("You have %d wins out of %d games played%n",
				wins,currentGamesPlayed, (wins/currentGamesPlayed) );		
	}
	
	private int getTotalGames() {
		if (this.totalGames == 0) {
			System.out.println("\nHow many games?");
			this.totalGames = scanner.nextInt();			
		}
		return this.totalGames;
	}	
}
