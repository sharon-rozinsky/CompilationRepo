import java.util.HashMap;


public class Executer {

	private CodeContext codeContext;
	private HashMap<Integer, Line> codeLines;

	public Executer(CodeContext codeContext, HashMap<Integer, Line> codeLines) {
		this.setCodeContext(codeContext);
		this.setCodeLines(codeLines);
	}

	public int execute() {
		while(codeLines.containsKey(codeContext.getNextCmd()))
		{
			if(codeLines.get(codeContext.getNextCmd()).run(codeContext) == 1)
			{
				return 1;
			}
		}
		return 0;
	}

	/**
	 * @return the codeLines
	 */
	public HashMap<Integer, Line> getCodeLines() {
		return codeLines;
	}

	/**
	 * @param codeLines the codeLines to set
	 */
	public void setCodeLines(HashMap<Integer, Line> codeLines) {
		this.codeLines = codeLines;
	}

	/**
	 * @return the codeContext
	 */
	public CodeContext getCodeContext() {
		return codeContext;
	}

	/**
	 * @param codeContext the codeContext to set
	 */
	public void setCodeContext(CodeContext codeContext) {
		this.codeContext = codeContext;
	}


}
