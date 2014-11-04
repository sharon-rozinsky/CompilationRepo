
public class IfCmd extends Line{
	private char var1, var2;
	private String boolOP;
	private Line line;
	
	public IfCmd(int lineIndex, char var1, char var2, String boolOP, Line line) {
		super(lineIndex);
		this.var1 = var1;
		this.var2 = var2;
		this.boolOP = boolOP;
		this.line = line;
	}
	
	public IfCmd(int lineIndex, char var1, char var2, String boolOP) {
		this(lineIndex, var1, var2, boolOP, null);
	}
	
	public int run(CodeContext codeContext) {
		int bool = evalBooleanOP(codeContext, boolOP, var1, var2);
		if (bool==1)
		{
			line.run(codeContext);
		}
		else if (bool==2)
		{
			codeContext.setNextCmd(codeContext.getNextCmd()+1);
		}
		else // bool = 0
		{
			//run time error occurred stop execution.
			return 1;
		}
		return 0;	
	}
	
	public int evalBooleanOP(CodeContext codeContext, String boolOP, char var1, char var2){
		
		int v1 = evalVar(codeContext, lineIndex, var1);
		int v2 = evalVar(codeContext, lineIndex, var2);
		if ((v1 == -1) || (v2 == -1))
		{
			return 0;
		}
		int res = 2;
		switch (boolOP) {
		case ">":
			if (v1 > v2) res = 1;
			break;
		case "<":
			if (v1 < v2) res = 1;	
			break;
		case ">=":
			if (v1 >= v2) res = 1;
			break;
		case "<=":
			if (v1 <= v2) res = 1;
			break;
		case "==":
			if (v1 == v2) res = 1;
			break;
		case "!=":
			if (v1 != v2) res = 1;
			break;
		default:
			break;
		}
		
		return res;
	}
	
	public int evalVar(CodeContext codeContext, int lineIndex, char var){
		int evalAns = -1;
		
		try {
			evalAns = codeContext.getVarHeap().get(var); 
			return evalAns;
		} catch(NullPointerException e) {
			Logger.PrintError(lineIndex, 4);
			return -1; //run time error occurred stop execution.
		}	
	}
	
	public char getVar1() {
		return var1;
	}

	public void setVar1(char var1) {
		this.var1 = var1;
	}

	public char getVar2() {
		return var2;
	}

	public void setVar2(char var2) {
		this.var2 = var2;
	}

	public String getBoolOP() {
		return boolOP;
	}

	public void setBoolOP(String boolOP) {
		this.boolOP = boolOP;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	
}
