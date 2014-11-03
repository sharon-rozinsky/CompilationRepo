import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	public static final String LINE_PATTERN = "(\\d*)\\s:(.*);";
	public static final String BINARY_OPERATOR = "(\\+|\\*|\\-|\\/)";
	public static final String VARIABLE = "([a-z])";
	public static final String NUMBER = "([1-9][0-9]*|0)";
	public static final String NUMBER_OR_VARIABLE = NUMBER + "|" + VARIABLE;
	public static final String BOOLEAN_OPERATOR = "(\\<|\\>|\\<=|\\>\\=|\\=\\=|\\!\\=)";
	
	public static final String BINARY_EXP = BINARY_OPERATOR + "\\s(.*)\\s(.*)";
	public static final String ASSIGNMENT = VARIABLE + "\\s*:=\\s*" + "(.*)";
	public static final String GOTO = "goto\\s" + "(" + NUMBER + ")";
	public static final String PRINT = "print\\((.*)\\)";
	public static final String IF = "if\\(" + VARIABLE + "\\s" + BOOLEAN_OPERATOR + "\\s" + VARIABLE + "\\)\\s(.*)";  
	
	private List<String> file;
	private ArrayList<Integer> labels = new ArrayList<Integer>();
	private HashMap<Integer, Integer> labelsMapping = new HashMap<Integer, Integer>();
	private HashMap<Integer, Line> lines = new HashMap<Integer, Line>();
	private int currentLabel;
	private int currentLine = 1;
	
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
				currentLabel = Integer.parseInt(matcher.group(1).trim());
				String lineValue = matcher.group(2).trim();
				if(validateLine(lineValue) == null){
					Logger.PrintError(currentLine, 1);
				}
			} else{
				Logger.PrintError(currentLine, 1);
			}
			labelsMapping.put(currentLabel, currentLine);
			currentLine++;
		}
		Logger.printLabelErrors();
		if(Logger.compileErrors()){
			return false;
		}
		return true;
	}
	
	/**
	 * Create a list of all labels in the given code.
	 * This will be used to log errors of types 2 and 3.
	 */
	private void createLabelsList(){
		int lineNumber = 1;
		for(String line : file){
			Matcher matcher = getMatcher(LINE_PATTERN, line);
			if(matcher.matches()){
				Integer label = Integer.parseInt(matcher.group(1).trim());
				if(!labels.isEmpty()){
					if(label <= labels.get(labels.size() - 1)){
						Logger.error(lineNumber, 3);
					}
				}
				labels.add(label);
			}
			lineNumber++;
		}
	}
	
	/**
	 * given a code line, check if it matches any type of commands.
	 * @param line
	 * @return
	 */
	public Line validateLine(String line){ 
		Line lineObj = checkAssignment(line);
		if(lineObj != null){
			addLineObject(currentLine, lineObj);
			return lineObj;
		}
		
		lineObj = checkGoto(line);
		if(lineObj != null){
			addLineObject(currentLine, lineObj);
			return lineObj;
		}
		
		lineObj = checkPrint(line);
		if(lineObj != null){
			addLineObject(currentLine, lineObj);
			return lineObj;
		}
		
		lineObj = checkIf(line);
		if(lineObj != null){
			return lineObj;
		}
		
		return null;
	}

	/**
	 * Check an assignment command. 
	 * @param line
	 * @return
	 */
	private Line checkAssignment(String line) {
		boolean legalAssignment = false;
		String variable, assignmentValue;
		
		Matcher matcher = getMatcher(ASSIGNMENT, line);
		if(matcher.matches()){
			variable = matcher.group(1).trim();
			assignmentValue = matcher.group(2).trim();
			matcher = getMatcher(NUMBER_OR_VARIABLE, assignmentValue);
			if(matcher.matches()){
				legalAssignment = true; 
			} else if(checkBinaryOperation(assignmentValue)){
					legalAssignment = true;
		 	}
			
			if(legalAssignment){
				return new MountCmd(currentLine, variable.charAt(0), assignmentValue);
			}
		}
		return null;
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
	private Line checkGoto(String line) {
		Matcher matcher = getMatcher(GOTO, line);
		if(matcher.matches()){
			Integer label = Integer.parseInt(matcher.group(1).trim());
			if(!labels.contains(label)){
				Logger.PrintError(currentLine, 2);
			}
			return new gotoCmd(currentLine, label);
		}
		return null;
	}
	
	/**
	 * Check if the given line is a print command.
	 * @param line
	 * @return
	 */
	private Line checkPrint(String line) {
		Matcher matcher = getMatcher(PRINT, line);
		if(matcher.matches()){
			String printValue = matcher.group(1);
			if(checkBinaryOperation(printValue)){
				return new PrintCmd(currentLine, printValue);
			} else{
				Logger.error(currentLine, 1);
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Check if the given line is an if statement.
	 * This method calls validateLine to evaluate the command after the if statement. 
	 * @param line
	 * @return
	 */
	private Line checkIf(String line) {
		Matcher matcher = getMatcher(IF, line);
		if(matcher.matches()){
			String lValue = matcher.group(1);
			String boolOp = matcher.group(2);
			String rValue = matcher.group(3);
			String cmdValue = matcher.group(4);
			//TODO: change bool
			IfCmd ifLine = new IfCmd(currentLine, lValue.charAt(0), rValue.charAt(0), boolOp);
			addLineObject(currentLine, ifLine);
			ifLine.setLine(validateLine(cmdValue));
			return ifLine;
		}
		return null;
	}
	
	/**
	 * Add a line object to the lines map.
	 * If a line with the same index already exists it
	 * means it's a nested if command
	 * @param lineNumber
	 * @param line
	 */
	private void addLineObject(int lineNumber, Line line){
		if(!lines.containsKey(lineNumber)){
			lines.put(lineNumber, line);
		} else{
			Line originalLine = lines.get(lineNumber);
			Line innerLine;
			while(originalLine != null){
				innerLine = ((IfCmd)originalLine).getLine();
				if(innerLine != null){
					originalLine = innerLine;
				} else{
					break;
				}
			}
			((IfCmd)originalLine).setLine(line);
		}
	}
	
	/**
	 * get the lines-labels hash map
	 * @return
	 */
	public HashMap<Integer, Integer> getLabelsMapping(){
		return labelsMapping;
	}
	
	/**
	 * get the lines-lines objects map
	 * @return
	 */
	public HashMap<Integer, Line> getLines(){
		return lines;
	}
}

