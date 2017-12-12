import static spark.Spark.get;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import spark.Request;

public class ApiHandler {
	
	public static void startApis() {
		get("similarity", "application/json", (request, response)->getSimilarity(request));
		get("wordvector", "application/json", (request, response)->getWordVector(request));
		get("sentencevector", "application/json", (request, response)->getSentenceVector(request));
		
//		get("xxx","application/json",(request, response)-> {
//			final JSONObject jsonObject = new JSONObject();
//			jsonObject.put("Farshad", "Hello World!");
//			String string = jsonObject.toJSONString();
//			return string;
//			
//		});
	}

	private static List<Double> getSentenceVector(Request request) {
		String entity1 = request.queryParams("entity1");
		final String[] split = entity1.split(" ");
		final double[] wordVector = ExampleAnnotation.getSentenceVector(Arrays.asList(split));
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static List<Double> getWordVector(Request request) {
		String entity1 = request.queryParams("entity1");
		final double[] wordVector = ExampleAnnotation.getWordVector(entity1);
		if(wordVector==null){
			return null;
		}
		return Arrays.asList(ArrayUtils.toObject(wordVector));
	}

	private static double getSimilarity(Request request) {
		String entity1 = request.queryParams("entity1");
		String entity2 = request.queryParams("entity2");
		return ExampleAnnotation.getSimilarity(entity1, entity2);
	}
}
