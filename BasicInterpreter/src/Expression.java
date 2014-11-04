import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Expression {

	private String expStr;

	public Expression(String expStr) {
		this.expStr = expStr;
	}

	public int evalExpression(CodeContext codeContext, int lineIndex) throws Exception{

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
			} catch(Exception e) {
				Logger.PrintError(lineIndex, 4);
				throw e; //run time error occurred stop execution.
			}

		}
		else // Compound expression
		{
			calculationStk = new Stack<String>();
			String[] 		symbols = this.expStr.split(" ");
			Expression 		argStr;
			int 			argsIndex;
			List<Integer> 	args;
			String 			binOp;

			for(String symbol:symbols)
			{
				calculationStk.push(symbol);
			}
			while(calculationStk.size() > 1)
			{
				argsIndex =0;
				args = new ArrayList<Integer>();
				while(true)
				{
					argStr = new Expression(calculationStk.pop());
					if(argStr.isBinaryOp())
					{
						argsIndex--;
						break;
					}
					try {
						args.add(argStr.evalExpression(codeContext, lineIndex));
						argsIndex++;
					} catch (Exception e) {
						//run time error occurred stop execution.
						throw e;
					}
				}

				binOp = argStr.expStr;

				switch(binOp)
				{
				case "+":
					evalAns = args.get(argsIndex) + args.get(argsIndex-1);
					break;
				case "-":
					evalAns = args.get(argsIndex) - args.get(argsIndex-1);
					break;
				case "*":
					evalAns = args.get(argsIndex) * args.get(argsIndex-1);
					break;
				case "\\":
					evalAns = args.get(argsIndex) / args.get(argsIndex-1);
					break;
				default:
					System.out.println("Debug error");
				}

				calculationStk.push(Integer.toString(evalAns));
				
				for(int i=argsIndex-2;i>=0;i--)
				{
					calculationStk.push(args.get(i).toString());
				}

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
	
	private boolean isBinaryOp() {
		switch(this.expStr)
		{
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "\\":
			return true;
		default:
			return false;
		}
	}



}
