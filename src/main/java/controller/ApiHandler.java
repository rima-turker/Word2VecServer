package controller;
import static spark.Spark.get;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import model.Embeddings;
import spark.Request;

public class ApiHandler {
	
	public static void startApis() {
		get("similarity", "application/json", (request, response)->getSimilarity(request));
		get("wordvector", "application/json", (request, response)->getWordVector(request));
		get("sentencevector", "application/json", (request, response)->getSentenceVector(request));
		get("getMostSimilarWords", "application/json", (request, response)->getMostSimilarWords(request));
	}

	private static List<Double> getSentenceVector(Request request) {
		String entity1 = request.queryParams("entity1");
		final String[] split = entity1.split(" ");
		final double[] wordVector = Embeddings.getSentenceVector(Arrays.asList(split));
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static List<Double> getWordVector(Request request) {
		String entity1 = request.queryParams("entity1");
		String model = request.queryParams("model");
		final double[] wordVector = Embeddings.getWordVector(entity1,model);
		if(wordVector==null){
			return null;
		}
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static Collection<String> getMostSimilarWords(final Request request) {
		return Embeddings.getMostSimilarWords(request.queryParams("entity1"), request.queryParams("model"), Integer.parseInt(request.queryParams("number")));
	}
	private static double getSimilarity(Request request) {
		String entity1 = request.queryParams("entity1");
		String entity2 = request.queryParams("entity2");
		String model = request.queryParams("model");
		return Embeddings.getSimilarity(entity1, entity2,model);
	}
}
