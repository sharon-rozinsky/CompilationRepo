
public class MountCmd extends Line {

	private char 		var;
	private Expression 	expression;
	public MountCmd(CommandLine_e type,int lineIndex, char var, String expStr) {
		super(CommandLine_e.MOUNT_CMD, lineIndex);
		this.var = var;
		this.expression = new Expression(expStr);
	}
	
	public int run(CodeContext codeContext) {
		
		int val = expression.evalExpression(codeContext, super.getLineIndex());
		if(val != -1)
		{
			codeContext.getVarHeap().put(this.var, val);
			return 1;
		}
		else
		{
			return -1;
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