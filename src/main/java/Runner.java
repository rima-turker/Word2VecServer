public class Runner {

	public void execute() {
		System.err.println("Loading model....");
		ExampleAnnotation.load();
		System.err.println("Loading Apis....");
		ApiHandler.startApis();
		System.err.println("I am ready");
	}
}
