package es.uned.master.java.kwic.pruebas;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class KWICAlgorithm {

	private String delimiter;
	private List<String> stopwords;
	private int contextLength;
	
	public KWICAlgorithm(String delimiter, List<String> stopwords, int contextLength) {
		this.delimiter = delimiter;
		this.stopwords = stopwords;
		this.contextLength = contextLength;
	}
	
	public void kwic(String sentence){
		String[] wordList = sentence.split(delimiter);
		List<String> cleanedWordList = removeStopwords(wordList);
		
		Collections.sort(cleanedWordList, Collator.getInstance());
		
		print(cleanedWordList, Arrays.asList(wordList));
	}
	
	private List<String> removeStopwords(String[] wordList){
		List<String> cleanedWordList = new ArrayList<String>(Arrays.asList(wordList));
		cleanedWordList.removeAll(stopwords);
		return cleanedWordList;
	}
	
	private void print(List<String> cleanedWordList, List<String> originalWordList){
		System.out.println(cleanedWordList);
		System.out.println(originalWordList + "\n\n");
		
		for(String word : cleanedWordList){
			int wordIndex = originalWordList.indexOf(word);
			
			int startIndex = wordIndex - contextLength;
			int endIndex = wordIndex + contextLength < originalWordList.size() ? wordIndex + contextLength: originalWordList.size() - 1;
			
			System.out.println("******* Start");
			for(int i = startIndex; i <= endIndex; i++){
				if(i >= 0){
					if(originalWordList.get(i).equals(word)){
						System.out.print("- "); //higlights and prints the index word.
					}
					System.out.print(originalWordList.get(i)); // prints a context word
				}
				System.out.println();
			}
			System.out.println("******* End");
			System.out.println();
			System.out.println();
		}
	}
}