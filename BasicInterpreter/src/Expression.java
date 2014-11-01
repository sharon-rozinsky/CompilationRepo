import java.util.Stack;


public class Expression {

	private String expStr;

	public Expression(String expStr) {
		this.expStr = expStr;
	}

	public int evalExpression(CodeContext codeContext, int lineIndex) {

		int evalAns = -1;
		Stack<String> calculationStk;

		if(this.isNumber())
		{
			return Integer.parseInt(this.expStr);
		}
		else if(this.isVariable()) 
		{
			try {
				evalAns = codeContext.getVarHeap().get(this.expStr); 
				return evalAns;
			} catch(NullPointerException e) {
				Logger.PrintError(lineIndex, 4);
				return -1; //run time error occurred stop execution.
			}

		}
		else // Compound expression
		{
			calculationStk = new Stack<String>();
			String[] symbols = this.expStr.split(" ");
			for(String symbol:symbols)
			{
				calculationStk.push(symbol);
			}
			
			//TODO Complete by Guy.
			return evalAns;
		}

	}

	private boolean isVariable() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isNumber() {
		// TODO Auto-generated method stub
		return false;
	}



}
