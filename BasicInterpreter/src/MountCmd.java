
public class MountCmd extends Line {

	private char 		var;
	private Expression 	expression;
	public MountCmd(CommandLine_e type) {
		super(CommandLine_e.MOUNT_CMD);
		// TODO Auto-generated constructor stub
	}
	
	public int run() {
		
		return 0;
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
