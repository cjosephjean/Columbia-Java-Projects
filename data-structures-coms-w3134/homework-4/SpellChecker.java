import java.util.*;
import java.util.HashSet;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpellChecker implements SpellCheckerInterface {
    
    public HashSet<String> dictHashSet = new HashSet<String>();
    
    
    public SpellChecker(String filename) {
        try {
            File dictFile = new File(filename);
            Scanner dictReader = new Scanner(dictFile);
            while (dictReader.hasNextLine()) {
                String word = dictReader.nextLine();
                dictHashSet.add(word.toLowerCase());
            }
            dictReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to read the dictionary file.");
        }
    }
    
    public Set<String> addCharacters(Set<String> suggestions, String word) {
        if (word == "" || word.length() < 1) {
            return suggestions;
        }
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i=0; i<=word.length(); i++) {
    	   for (int j=0; j<alphabet.length; j++) {
    	       String newSuggestion = "";
        	   if (i==0) {
            	   newSuggestion = alphabet[j]+word;
                } else if (i==word.length()) {
            	   newSuggestion = word+alphabet[j];
                } else {
            	   newSuggestion = word.substring(0,i)+alphabet[j]+word.substring(i);
                }
                // only add suggestions that are dictionary words
                if (dictHashSet.contains(newSuggestion)) {
                    suggestions.add(newSuggestion);
                }
            }
        }
        return suggestions;
    }
    
    public Set<String> removeCharacters(Set<String> suggestions, String word) {
        if (word == "" || word.length() < 1) {
            return suggestions;
        }
        for (int i=0; i<word.length(); i++) {
            String newSuggestion = "";
            if (i==0) {
                newSuggestion = word.substring(1);
            } else if (i == word.length()-1) {
                newSuggestion = word.substring(0,i);
            } else {
                newSuggestion = word.substring(0,i)+word.substring(i+1);
            }
            // only add suggestions that are dictionary words
            if (dictHashSet.contains(newSuggestion)) {
                suggestions.add(newSuggestion);
            }
        }
        return suggestions;
    }
    
    public Set<String> swapCharacters(Set<String> suggestions, String word) {
        if (word == "" || word.length() <= 1) {
            return suggestions;
        }
        for (int i=0; i<word.length()-1; i++) {
            String newSuggestion = "";
            if (i < word.length() - 2) {
                newSuggestion = word.substring(0,i)+word.charAt(i+1)+word.charAt(i)+word.substring(i+2);
            } else {
                newSuggestion = word.substring(0,i)+word.charAt(i+1)+word.charAt(i); 
            }
            // only add suggestions that are dictionary words
            if (dictHashSet.contains(newSuggestion)) {
                suggestions.add(newSuggestion);
            }
        }
        return suggestions;
    } 
    
    public Set<String> getSuggestions(String word) {
        Set<String> suggestions = new HashSet<String>();
        suggestions = addCharacters(suggestions, word);
        suggestions = removeCharacters(suggestions, word);
        suggestions = swapCharacters(suggestions, word);
        return suggestions;
    }
    
    public String cleanLine(String line) {
        String noPunctLine = line.replaceAll("[^a-zA-Z0-9]", ""); 
        return noPunctLine.toLowerCase();
    }
    
    public String openWordFile(String filename) {
        String fileContent = "";
        try {
            File wordFile = new File(filename);
            Scanner fileReader = new Scanner(wordFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                fileContent += " " + line;
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to read the words file.");
        }
        return fileContent;
    }
    
    public List<String> getIncorrectWords(String filename) {
        List<String> fileTokensList = new ArrayList<>();
        String fileContent = openWordFile(filename);
        String[] fileTokens = fileContent.split(" ");
        for (int i=0; i<fileTokens.length; i++) {
            String newLine = cleanLine(fileTokens[i]);
            if (newLine != "" && newLine.length() > 0) {
                if (dictHashSet.contains(newLine)) {
                    //System.out.println("* " + newLine + " is in the dict!");
                } else {
                    fileTokensList.add(newLine); 
                }
            }
        }
        return fileTokensList;
    }
    
}