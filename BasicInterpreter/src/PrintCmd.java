
public class PrintCmd extends Line {
	
	private Expression expression;
	public PrintCmd(int lineIndex, String expStr) {
		super(lineIndex);
		this.expression = new Expression(expStr);
	}
	
	public int run(CodeContext codeContext) {
		int val = this.expression.evalExpression(codeContext, super.getLineIndex());
		if(val != -1)
		{
			Logger.Print(val);
			codeContext.setNextCmd(codeContext.getNextCmd()+1);
			return 0;
		}
		else
		{
			return 1;
		}
		
	}

}
