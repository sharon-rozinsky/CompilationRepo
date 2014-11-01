
public class Expression {
	
	private String expStr;

	public Expression(String expStr) {
		this.expStr = expStr;
	}
	
	public int evalExpression(Expression exp, CodeContext codeContext) {
		
		int evalAns = -1;
		
		if(exp.isNumber())
		{
			return Integer.parseInt(exp.expStr);
		}
		else if(exp.isVariable()) 
		{
			evalAns = codeContext.getVarHeap().get(exp.expStr); 
			if (evalAns)
			{
				return 
			}
		}
		else // Compound expression
		{
			
			
			
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
