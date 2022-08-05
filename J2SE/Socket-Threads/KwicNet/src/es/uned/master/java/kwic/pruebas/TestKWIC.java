package es.uned.master.java.kwic.pruebas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TestKWIC {
	
	@Test
	public void testKWIC(){
		List<String> stopwords = new ArrayList<String>();
		stopwords.add("an");
		stopwords.add("for");
		stopwords.add("the");
		stopwords.add("is");
		stopwords.add("in");
		
		KWICAlgorithm kwic = new KWICAlgorithm(" ", stopwords, 3);
		kwic.kwic("KWIC is an acronym for Keyword In Context, the most common format for concordance lines.");
	}

}