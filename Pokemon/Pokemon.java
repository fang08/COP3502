
public class Pokemon {
	//Set variables
	private String species;
	private int attack, defense, speed;
	
	//Constructor
	public Pokemon(String species){
		this.species = species;
		this.attack = (species.length() * 4) + 2;
		this.defense = (species.length() * 2) + 7;
		this.speed = (species.length() * 3) + 5;
	}
	
	//Methods
	//increase attack
	public void grow(int boost){
		attack = attack + boost;
	}
	
	//increase defense
	public void harden(int boost){
		defense = defense + boost;
	}
	
	//increase speed
	public void haste(int boost){
		speed = speed + boost;
	}
	
	//Getter/Setter methods
	public String getSpecies(){
		return species;
	}
	
	public void setSpecies(String spc){
		species = spc;
	}
	
	public int getAttack(){
		return attack;
	}
	
	public void setAttack(int atk){
		attack = atk;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public void setDefense(int def){
		defense = def;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int spd){
		speed = spd;
	}
}
