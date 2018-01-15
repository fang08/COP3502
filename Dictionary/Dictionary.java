import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {
	private ArrayList<String> dictionary = new ArrayList<>();
	
	//Returns the number of words in the loaded dictionary
	public int getVocabularySize(){
		return dictionary.size();
	}
	
	//Load the dictionary file, if file was found return true; otherwise return false
	public boolean loadDictionaryFromFile(String filePath){
		File file = new File(filePath);
		try{
			if (file.exists()){
				Scanner input = new Scanner(file);
				while (input.hasNext()){
					dictionary.add(input.next());
				}
				input.close();
				return true;
			}
			else
				return false;
		}
		catch (FileNotFoundException ex){
			return false;
		}
	}
	
	//check whether a given word is in the dictionary
	public boolean isWord(String word){
		if (dictionary.contains(word.trim().toLowerCase())|| word.equalsIgnoreCase("i"))
			return true;
		else
			return false;
	}
}