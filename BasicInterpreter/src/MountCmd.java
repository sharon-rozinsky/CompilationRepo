
public class MountCmd extends Line {

	private char 		var;
	private Expression 	expression;
	public MountCmd(int lineIndex, char var, String expStr) {
		super(lineIndex);
		this.var = var;
		this.expression = new Expression(expStr);
	}
	
	public int run(CodeContext codeContext) {
		
		int val;
		try {
			val = expression.evalExpression(codeContext, super.getLineIndex());
			codeContext.getVarHeap().put(this.var, val);
			codeContext.setNextCmd(codeContext.getNextCmd()+1);
			return 0;
		} catch (Exception e) {
			//run time error occurred stop execution.
			return 1;
		}
	}
	
	/**
	 * @return the var
	 */
	public char getVar() {
		return var;
	}
	/**
	 * @param var the var to set
	 */
	public void setVar(char var) {
		this.var = var;
	}
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
