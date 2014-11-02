
public class IfCmd extends Line{
	private char var1, var2;
	private BoolOP_e boolOP;
	private Line line;
	
	public IfCmd(CommandLine_e type, int lineIndex, char var1, char var2, BoolOP_e boolOP, Line line) {
		super(type, lineIndex);
		this.var1 = var1;
		this.var2 = var2;
		this.boolOP = boolOP;
		this.line = line;
	}
	
	public int run(CodeContext codeContext) {
		Boolean bool = evalBooleanOP(codeContext, boolOP, var1, var2);
		if (bool)
		{
			line.run(codeContext);
		}
		
		return 1;	
	}
	
	public Boolean evalBooleanOP(CodeContext codeContext, BoolOP_e boolOP, char var1, char var2){
		
		int v1 = evalVar(codeContext, lineIndex, var1);
		int v2 = evalVar(codeContext, lineIndex, var2);
		Boolean res = false;
		
		switch (boolOP) {
		case BIG:
			if (v1 > v2) res = true;
			break;
		case SMALL:
			if (v1 < v2) res = true;	
			break;
		case BIG_EQ:
			if (v1 >= v2) res = true;
			break;
		case SMALL_EQ:
			if (v1 <= v2) res = true;
			break;
		case EQ:
			if (v1 == v2) res = true;
			break;
		case NOT_EQ:
			if (v1 != v2) res = true;
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

	public BoolOP_e getBoolOP() {
		return boolOP;
	}

	public void setBoolOP(BoolOP_e boolOP) {
		this.boolOP = boolOP;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	
}
