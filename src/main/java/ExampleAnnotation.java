import java.util.List;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class ExampleAnnotation 
{
	private static final String ADDRESS_OF_MODEL = Config.getString("ADDRESS_OF_MODEL","");
	private static Word2Vec modelEntities;

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return
	 */
	public static double getSimilarity(String entity1,String entity2) {
		return modelEntities.similarity(entity1, entity2);
	}

	public static void load() {
		modelEntities =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_MODEL);
	}

	public static double[] getWordVector(String entity1) {
		return modelEntities.getWordVector(entity1);
	}

	public static double[] getSentenceVector(List<String> words) {
		System.err.println(modelEntities.getWordVectorsMean(words));
		return null;
	}

}
