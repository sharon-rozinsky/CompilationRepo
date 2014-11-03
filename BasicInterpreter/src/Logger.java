import java.util.HashMap;


public class Logger {
	// Errors map of type <line, code>
	public static HashMap<Integer, Integer> errors = new HashMap<Integer, Integer>();
	private static boolean isCompileError = false;
	
	public static void PrintError(int line, int code){
		System.out.println("Error! Line:" + line + " Code:" + code);
		isCompileError = true;
	}
	
	public static void Print(int val){
		System.out.println("" + val);
	}
	
	public static void error(int line, int code){
		errors.put(line, code);
		isCompileError = true;
	}
	
	public static void printLabelErrors(){
		for(Integer line : errors.keySet()){
			PrintError(line, errors.get(line));
		}
	}
	
	public static boolean compileErrors(){
		return isCompileError;
	}
}
