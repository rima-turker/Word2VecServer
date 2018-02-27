package controller;

import java.util.concurrent.Executors;

import DumpFile.ConvertdataToVectors;
import model.Embeddings;

public class Main {
	public static void main(String[] args) {
		System.err.println("Loading model....");
		Embeddings.load();
		//System.err.println("Loading Apis....");
		//ApiHandler.startApis();
		//System.err.println("I am ready");
		Executors.newSingleThreadExecutor().submit(ConvertdataToVectors.run());
	}
}
