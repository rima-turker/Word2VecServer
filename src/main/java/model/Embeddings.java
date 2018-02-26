package model;
import java.util.Collection;
import java.util.List;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

import util.Config;

public class Embeddings 
{
	private static final String ADDRESS_OF_WORD2VEC_MODEL = Config.getString("ADDRESS_OF_WORD2VEC_MODEL","");
	private static final String ADDRESS_OF_LINE_FIRST_MODEL = Config.getString("ADDRESS_OF_LINE_FIRST_MODEL","");
	private static final String ADDRESS_OF_LINE_SENCOND_MODEL = Config.getString("ADDRESS_OF_LINE_SENCOND_MODEL","");
	private static final String ADDRESS_OF_LINE_COMBINED_MODEL = Config.getString("ADDRESS_OF_LINE_COMBINED_MODEL","");
	private static Word2Vec word2vecModel;
	private static Word2Vec lineFirstModel;
	private static Word2Vec lineSecondModel;
	private static Word2Vec lineCombinedModel;

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return
	 */
	public static double getSimilarity(String entity1,String entity2,String model) {
		final Model m = Model.valueOf(model);
		switch (m) {
		case WORD2VEC:
			return word2vecModel.similarity(entity1, entity2);
		case LINE_1st:
			return lineFirstModel.similarity(entity1, entity2);
		case LINE_2nd:
			return lineSecondModel.similarity(entity1, entity2);
		case LINE_COMBINED:
			return lineCombinedModel.similarity(entity1, entity2);
		default:
			throw new IllegalArgumentException("Model "+model+" does not exist");
		}
	}

	public static void load() {
		word2vecModel =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_WORD2VEC_MODEL);
		lineFirstModel =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_LINE_FIRST_MODEL);
		lineSecondModel =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_LINE_SENCOND_MODEL);
		lineCombinedModel =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_LINE_COMBINED_MODEL);
	}

	public static double[] getWordVector(String entity,String model) {
		final Model m = Model.valueOf(model);
		switch (m) {
		case WORD2VEC:
			return word2vecModel.getWordVector(entity);
		case LINE_1st:
			return lineFirstModel.getWordVector(entity);
		case LINE_2nd:
			return lineSecondModel.getWordVector(entity);
		case LINE_COMBINED:
			return lineCombinedModel.getWordVector(entity);
		default:
			throw new IllegalArgumentException("Model "+model+" does not exist");
		}
	}
	public static Collection<String>  getMostSimilarWords(String entity,String model,int n)
	{
		/*Collection<String> lst3 = vec.wordsNearest("man", 10);
        System.out.println(lst3);*/
		
		final Model m = Model.valueOf(model);
		switch (m) {
		case WORD2VEC:
			return word2vecModel.wordsNearest(entity,n);
		case LINE_1st:
			return lineFirstModel.wordsNearest(entity,n);
		case LINE_2nd:
			return lineSecondModel.wordsNearest(entity,n);
		case LINE_COMBINED:
			return lineCombinedModel.wordsNearest(entity,n);
		default:
			throw new IllegalArgumentException("Model "+model+" does not exist");
		}
		
	}
	public static double[] getSentenceVector(List<String> words) {
		System.err.println(word2vecModel.getWordVectorsMean(words));
		return null;
	}

}

