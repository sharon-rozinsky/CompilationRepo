import java.util.HashMap;
import java.util.Map;


public class CodeContext {
	
	private Map<Character, Integer> varHeap;

	public CodeContext() {
		this.setVarHeap(new HashMap<Character,Integer>());
	}

	/**
	 * @return the varHeap
	 */
	public Map<Character, Integer> getVarHeap() {
		return varHeap;
	}

	/**
	 * @param varHeap the varHeap to set
	 */
	public void setVarHeap(Map<Character, Integer> varHeap) {
		this.varHeap = varHeap;
	}

}
