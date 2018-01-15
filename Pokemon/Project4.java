import java.util.Scanner;

public class Project4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to your new PokeDex!");
        System.out.print("How many Pokemon are in your region?: ");
        int size = input.nextInt();

        Pokedex pokedex = new Pokedex(size);
        System.out.println("\nYour new Pokedex can hold " + size + " Pokemon. Let's start using it!");

        int choice = 0;
        boolean done = false;
        while (!done) {
            System.out.println("\n1. List Pokemon\n2. Add Pokemon\n3. Check a Pokemon's Stats" +
                    "\n4. Sort Pokemon\n5. Exit");
            System.out.print("\nWhat would you like to do? ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    String[] pokemonList = pokedex.listPokemon();
                    if (pokemonList == null)
                        System.out.println("Empty");
                    else
                        for (int i = 0; i < pokemonList.length; i++) {
                            System.out.println((i+1) + ". " + pokemonList[i]);
                        }
                    break;
                
                case 2:
                    System.out.print("\nPlease enter the Pokemon's Species: ");
                    String species = input.next();
                    pokedex.addPokemon(species);
                    break;
                
                case 3:
                    System.out.print("\nPlease enter the Pokemon of interest: ");
                    species = input.next();
                    int[] attr = pokedex.checkStats(species);

                    if (attr == null)
                        System.out.println("Missing");
                    else {
                        System.out.println("\nThe stats for " + species + " are:");
                        System.out.println("Attack: " + attr[0]);
                        System.out.println("Defense: " + attr[1]);
                        System.out.println("Speed: " + attr[2]);

                    }
                    break;
                
                case 4:
                    pokedex.sortPokemon();
                    break;
                
                case 5:
                    done = true;
                    break;
               
                default:
                    System.out.println("\nThat's not a valid choice. Try again.\n");
                    break;
            }
        }
    }
}
