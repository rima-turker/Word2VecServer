package util;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;

public class MyStanfordCoreNLP {
	private final static TokenizerFactory<Word> TF;
	static {
		TF = PTBTokenizer.factory();
	}

	
	/**
	 * Tokenize the sentence to Words
	 * @param sentence
	 * @return
	 */
	public static List<Word> tokenize(final String sentence){
		return TF.getTokenizer(new StringReader(sentence)).tokenize();
	}
}
