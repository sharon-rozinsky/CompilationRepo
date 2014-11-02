
import java.util.HashMap;


public class gotoCmd {

	private int label;
	HashMap<Integer,Integer> Hash = new HashMap<Integer,Integer>();
	
	public gotoCmd(int label) {
		super();
		this.label = label;
	}
	
	public int evalGoto() {
		int index;
		
		index = Hash.get(this.label);
		return index;
	}
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
}
