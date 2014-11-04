import java.util.HashMap;


public class Logger {
	// Errors map of type <line, code>
	private static HashMap<Integer, Integer> errors = new HashMap<Integer, Integer>();
	private static boolean isCompileError = false;
	
	/**
	 * Print an error for the given line and code.
	 * This method is used in compile time so we flag for a compilation error.
	 * If the error is type 1 we remove the existing error which would be error 2/3.
	 * @param line
	 * @param code
	 */
	public static void PrintError(int line, int code){
		System.out.println("Error! Line:" + line + " Code:" + code);
		isCompileError = true;
		 
		if(code == 1){
			errors.remove(line);
		}
	}
	
	/**
	 * This method is used to print the value of a "print" command.
	 * @param val
	 */
	public static void Print(int val){
		System.out.println("" + val);
	}
	
	/**
	 * Add an error to the errors list. The type should be anything other than 1
	 * and the error would be printed after errors of type 1.
	 * @param line
	 * @param code
	 */
	public static void error(int line, int code){
		errors.put(line, code);
		isCompileError = true;	
	}
	
	/**
	 * Print all the non type 1 errors
	 */
	public static void printLabelErrors(){
		for(Integer line : errors.keySet()){
			PrintError(line, errors.get(line));
		}
	}
	
	/**
	 * This is a getter for the isCompileError flag to indicate whether a compilation
	 * error took place, and if so do continue with execution.
	 * @return
	 */
	public static boolean compileErrors(){
		return isCompileError;
	}
}
