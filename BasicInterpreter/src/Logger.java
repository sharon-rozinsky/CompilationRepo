import java.util.HashMap;


public class Logger {
	// Errors map of type <line, code>
	public static HashMap<Integer, Integer> errors = new HashMap<Integer, Integer>();
	
	public static void PrintError(int line, int code){
		System.out.println("Error! Line:" + line + " Code:" + code);
	}
	
	public static void Print(int val){
		System.out.println("" + val);
	}
	
	public static void error(int line, int code){
		errors.put(line, code);
	}
	
	public static void printLabelErrors(){
		for(Integer line : errors.keySet()){
			PrintError(line, errors.get(line));
		}
	}
}
