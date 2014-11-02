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
				evalAns = codeContext.getVarHeap().get(this.expStr.charAt(0)); 
				return evalAns;
			} catch(NullPointerException e) {
				Logger.PrintError(lineIndex, 4);
				return -1; //run time error occurred stop execution.
			}

		}
		else // Compound expression
		{
			calculationStk = new Stack<String>();
			String[] 	symbols = this.expStr.split(" ");
			Expression 	argStr;
			int 		numOfArgs;
			int[] 		args = new int[2];
			String 		binOp;
			
			for(String symbol:symbols)
			{
				calculationStk.push(symbol);
			}
			while(calculationStk.size() > 1)
			{
				numOfArgs =0;
				while(numOfArgs < 2)
				{
					argStr = new Expression(calculationStk.pop());
					args[numOfArgs] = argStr.evalExpression(codeContext, lineIndex);
					numOfArgs++;
				}
				
				binOp = calculationStk.pop();
				
				switch(binOp)
				{
				case "+":
					evalAns = args[1] + args[0];
					break;
				case "-":
					evalAns = args[1] - args[0];
					break;
				case "*":
					evalAns = args[1] * args[0];
					break;
				case "/":
					evalAns = args[1] / args[0];
					break;
				}
				
				calculationStk.push(Integer.toString(evalAns));
				
			}
			return evalAns;
		}

	}

	private boolean isVariable() {
		if(this.expStr.length() == 1 && (Character.isAlphabetic(this.expStr.charAt(0))))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isNumber() {
		try {
			Integer.parseInt(this.expStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



}
