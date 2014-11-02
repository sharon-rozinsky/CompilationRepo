
public class IfCmd {
	private char var1, var2;
	private String boolOP;
	
	public IfCmd(char var1, char var2, String boolOP) {
		this.var1 = var1;
		this.var2 = var2;
		this.boolOP = boolOP;
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
	
	
}
