import static spark.Spark.get;

import org.json.simple.JSONObject;

import spark.Request;

public class ApiHandler {
	
	public static void startApis() {
		get("similarity", "application/json", (request, response)->getAnswer(request));
		
//		get("xxx","application/json",(request, response)-> {
//			final JSONObject jsonObject = new JSONObject();
//			jsonObject.put("Farshad", "Hello World!");
//			String string = jsonObject.toJSONString();
//			return string;
//			
//		});
	}

	private static double getAnswer(Request request) {
		String entity1 = request.queryParams("entity1");
		String entity2 = request.queryParams("entity2");
		return ExampleAnnotation.getSimilarity(entity1, entity2);
	}
}
