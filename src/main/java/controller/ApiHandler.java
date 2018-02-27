package controller;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import edu.stanford.nlp.ling.Word;
import model.Embeddings;
import spark.Request;

public class ApiHandler {
	
	public static void startApis() {
		get("/similarity", (request, response)->getSimilarity(request));
		get("/wordvector", (request, response)->getWordVector(request));
		get("/sentencevector", (request, response)->getSentenceVector(request));
		get("/getMostSimilarWords", (request, response)->getMostSimilarWords(request));

		
		post("/sentencevector", (request, response)-> getSentenceVector(request));
	}

	private static List<Double> getSentenceVector(Request request) {
		String entity1 = request.queryParams("entity1");
		List<Word> tokenize = util.MyStanfordCoreNLP.tokenize(entity1);
		
		final String[] split = new String[tokenize.size()];
		
		for(int i=0;i<tokenize.size();i++) {
			split[i] = tokenize.get(i).value();
		}
		
		final double[] wordVector = Embeddings.getSentenceVector(Arrays.asList(split));
		if(wordVector==null) {
			return null;
		}
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static List<Double> getWordVector(Request request) {
		String entity1 = request.queryParams("entity1");
		final double[] wordVector = Embeddings.getWordVector(entity1);
		if(wordVector==null){
			return null;
		}
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static Collection<String> getMostSimilarWords(final Request request) {
		return Embeddings.getMostSimilarWords(request.queryParams("entity1"), Integer.parseInt(request.queryParams("number")));
	}
	private static double getSimilarity(Request request) {
		String entity1 = request.queryParams("entity1");
		String entity2 = request.queryParams("entity2");
		return Embeddings.getSimilarity(entity1, entity2);
	}
}
