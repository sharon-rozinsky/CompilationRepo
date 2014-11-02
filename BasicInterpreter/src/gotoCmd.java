
public class gotoCmd {

	private int label;
	
	public gotoCmd(int label) {
		super();
		this.label = label;
	}
	
	public int run(CodeContext codeContext) {
		int index;
		
		index = codeContext.getLineToIdx().get(this.label);
		codeContext.setNextCmd(index);
		return 1;
	}
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
}
