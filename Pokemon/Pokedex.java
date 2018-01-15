
public class Pokedex {
	private Pokemon [] pokedex;
	private int numberOfPokemon;
	
	//Constructor
	public Pokedex(int size){
		pokedex = new Pokemon[size];
		numberOfPokemon = 0;
	}
	
	//Methods
	/* returns an array of strings containing the names of every Pokemon stored in the Pokedex;
	 * if the Pokedex is currently empty, return null */
	public String[] listPokemon(){
		if (numberOfPokemon != 0){
			String [] namesOfPokemon = new String[numberOfPokemon];
			for (int i = 0; i < numberOfPokemon; i++){
				namesOfPokemon[i] = pokedex[i].getSpecies();
			}
			return namesOfPokemon;
		}
		else
			return null;
	}
	
	/* add a new pokemon to pokedex, return true;
	 * don't add new pokemon if the pokedex is already the max size, return false;
	 * and if the pokemon is already exist print duplicate, return false*/
	public boolean addPokemon(String species){
		if (numberOfPokemon < pokedex.length){
			boolean contains = false;
			for (int i = 0; i < numberOfPokemon; i++){
				if (species.equalsIgnoreCase(pokedex[i].getSpecies()))
					contains = true;
			}
			
			if (contains == false){
				Pokemon pokemon = new Pokemon(species);
				pokedex[numberOfPokemon] = pokemon;
				numberOfPokemon ++;
				return true;
			}
			else {
				System.out.println("Duplicate");
				return false;
			}	
		}
		else {
			System.out.println("Max");
			return false;
		}
	}
	
	/* returns an array of 3 integers containing the attack, defense, and speed of the pokemon;
	 * if the Pokemon is not in the deck, return null*/
	public int[] checkStats(String species){
		int [] statsOfPokemon = new int[3];
		int indexOfPokemon = 0;
		boolean contains = false;
		for (int i = 0; i < numberOfPokemon; i++){
			if (species.equalsIgnoreCase(pokedex[i].getSpecies())){
				contains = true;
				indexOfPokemon = i;
				break;
			}
		}
		if (contains) {
			statsOfPokemon[0] = pokedex[indexOfPokemon].getAttack();
			statsOfPokemon[1] = pokedex[indexOfPokemon].getDefense();
			statsOfPokemon[2] = pokedex[indexOfPokemon].getSpeed();
			return statsOfPokemon;
		}
		else
			return null;
	}
	
	/*sorting existing pokemon based on alphabetical order*/
	public void sortPokemon(){
		for (int i = 0; i< numberOfPokemon - 1; i++) {
			Pokemon currentMin = pokedex[i];
			int currentMinIndex = i;
			
			//find the minimum object in the pokedex based on alphabetical order of the Pokemon’s names
			for (int j = i+1; j < numberOfPokemon; j++) {
				String s1 = currentMin.getSpecies();
				String s2 = pokedex[j].getSpecies();
				int compareResult = s1.compareToIgnoreCase(s2);
				if (compareResult > 0) {
					currentMin = pokedex[j];
					currentMinIndex = j;
				}
			}
			//swap the minimum if necessary
			if (currentMinIndex != i){
				pokedex[currentMinIndex] = pokedex[i];
				pokedex[i] = currentMin;
			}
		}
	}
}