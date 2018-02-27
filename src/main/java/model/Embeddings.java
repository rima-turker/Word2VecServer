package model;
import java.util.Collection;
import java.util.List;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;

import util.Config;

public class Embeddings 
{
	private static final String ADDRESS_OF_WORD2VEC_MODEL = Config.getString("ADDRESS_OF_WORD2VEC_MODEL","");
	private static Word2Vec word2vecModel;

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return
	 */
	public static double getSimilarity(String entity1,String entity2) {
		return word2vecModel.similarity(entity1, entity2);
	}

	public static void load() {
		word2vecModel =  WordVectorSerializer.readWord2VecModel(ADDRESS_OF_WORD2VEC_MODEL);
	}

	public static double[] getWordVector(String entity) {
		final double[] wordVector = word2vecModel.getWordVector(entity);
		return wordVector;
	}
	public static Collection<String>  getMostSimilarWords(String entity,int n)
	{
		final Collection<String> result = word2vecModel.wordsNearest(entity,n);
		return result;

	}
	public static double[] getSentenceVector(List<String> words) {		
		INDArray a = null;
		try{
			a = word2vecModel.getWordVectorsMean(words);
		}catch(Exception e) {
			return null;
		}
		int cols = a.columns();
		double[] result = new double[cols];
		for(int i=0;i<cols;i++) {
			result[i] = a.getDouble(i);
		}
		return result;
	}
}

