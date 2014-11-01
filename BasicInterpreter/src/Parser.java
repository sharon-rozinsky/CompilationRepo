import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	public static final String LINE_PATTERN = "(\\d*)\\s*:(.*);";
	public static final String BINARY_OPERATOR = "(\\+|\\*|\\-|\\/)";
	public static final String VARIABLE = "[a-z]";
	public static final String NUMBER = "[1-9][0-9]*|0";
	public static final String NUMBER_OR_VARIABLE = NUMBER + "|" + VARIABLE;
	public static final String BOOLEAN_OPERATOR = "(\\<|\\>|\\<=|\\>\\=|\\=\\=|\\!\\=)";
	
	public static final String BINARY_EXP = BINARY_OPERATOR + "\\s(.*)\\s(.*)";
	public static final String ASSIGNMENT = VARIABLE + "\\s*:=\\s*" + "(.*)";
	public static final String GOTO = "goto\\s" + "(" + NUMBER + ")";
	public static final String PRINT = "print\\((.*)\\)";
	public static final String IF = "if\\(" + VARIABLE + "\\s" + BOOLEAN_OPERATOR + "\\s" + VARIABLE + "\\)\\s(.*)";  
	
	public List<String> file;
	public ArrayList<Integer> labels = new ArrayList<Integer>();
	public int currentLine;
	
	public Parser(List<String> file){
		this.file = file;
		createLabelsList();
	}
	
	/**
	 * Get a matcher object for a given pattern and line
	 * @param pattern
	 * @param line
	 * @return Matcher object
	 */
	private Matcher getMatcher(String pattern, String line){
		Pattern p = Pattern.compile(pattern);
		return p.matcher(line);
	}
	
	/**
	 * Perform the lexical analysis for the given file
	 * @return
	 */
	public boolean LexicalAnalysis(){
		for(String line : file){
			Matcher matcher = getMatcher(LINE_PATTERN, line);
			if(matcher.matches()){
				currentLine = Integer.parseInt(matcher.group(1).trim());
				String lineValue = matcher.group(2).trim();
				if(!validateLine(lineValue)){
					Logger.PrintError(currentLine, 1);
				}
			}	
		}
		Logger.printLabelErrors();
		return true;
	}
	
	/**
	 * Create a list of all labels in the give code.
	 * This will be used to log errors of types 2 and 3.
	 */
	private void createLabelsList(){
		for(String line : file){
			Matcher matcher = getMatcher(LINE_PATTERN, line);
			if(matcher.matches()){
				Integer label = Integer.parseInt(matcher.group(1).trim());
				if(!labels.isEmpty()){
					if(label <= labels.get(labels.size()-1)){
						Logger.error(label, 3);
					}
				}
				labels.add(label);
			}
		}
	}
	
	/**
	 * given a code line, check if it matches any type of commands.
	 * @param line
	 * @return
	 */
	public boolean validateLine(String line){ 
		if(checkAssignment(line)){
			return true;
		} else if(checkGoto(line)){
			return true;
		} else if(checkPrint(line)){
			return true;
		} else if(checkIf(line)){
			return true;
		}
		
		return false;
	}

	/**
	 * Check an assignment command. 
	 * @param line
	 * @return
	 */
	private boolean checkAssignment(String line) {
		Matcher matcher = getMatcher(ASSIGNMENT, line);
		if(matcher.matches()){
			String assignmentValue = matcher.group(1).trim();
			matcher = getMatcher(NUMBER_OR_VARIABLE, assignmentValue);
			if(matcher.matches()){
				return true;
			} else{
				 return checkBinaryOperation(assignmentValue); 
			}
		}
		return false;
	}

	/**
	 * Check if the given line represents a binary operation.
	 * @param line
	 * @return
	 */
	private boolean checkBinaryOperation(String line) {
		String[] tokens = line.split(" ");
		int counter = 0;
		for(int i=tokens.length - 1; i >=0; i--){
			if(tokens[i].matches(NUMBER_OR_VARIABLE)){
				counter++;
			}else if(tokens[i].matches(BINARY_OPERATOR)){
				counter--;
			}else{
				return false;
			}
			if(counter < 0){
				return false;
			}
		}
		if(counter == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Check if the given line is a goto command.
	 * If the label of the goto doesn't exist, log the error.
	 * @param line
	 * @return
	 */
	private boolean checkGoto(String line) {
		Matcher matcher = getMatcher(GOTO, line);
		if(matcher.matches()){
			//TODO: build line object
			Integer label = Integer.parseInt(matcher.group(1).trim());
			if(!labels.contains(label)){
				Logger.PrintError(currentLine, 2);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the given line is a print command.
	 * @param line
	 * @return
	 */
	private boolean checkPrint(String line) {
		Matcher matcher = getMatcher(PRINT, line);
		if(matcher.matches()){
			String printValue = matcher.group(1);
			//TODO: build line object
			return checkBinaryOperation(printValue);
		}
		return false;
	}
	
	/**
	 * Check if the given line is an if statement.
	 * This method calls validateLine to evaluate the command after the if statment. 
	 * @param line
	 * @return
	 */
	private boolean checkIf(String line) {
		Matcher matcher = getMatcher(IF, line);
		if(matcher.matches()){
			String cmdValue = matcher.group(2);
			//TODO: build line object
			return validateLine(cmdValue);
		}
		return false;
	}
}
