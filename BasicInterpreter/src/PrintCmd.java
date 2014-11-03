
public class PrintCmd extends Line {
	
	private Expression expression;
	public PrintCmd(int lineIndex, String expStr) {
		super(lineIndex);
		this.expression = new Expression(expStr);
	}
	
	public int run(CodeContext codeContext) {
		int val;
		try {
			val = this.expression.evalExpression(codeContext, super.getLineIndex());
			Logger.Print(val);
			codeContext.setNextCmd(codeContext.getNextCmd()+1);
			return 0;
		} catch (Exception e) {
			//run time error occurred stop execution.
			return 1;
		}
		
	}

}
