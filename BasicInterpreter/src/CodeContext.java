import java.util.HashMap;
import java.util.Map;


public class CodeContext {
	
	private Map<Character, Integer> varHeap;
	private Map<Integer, Integer> labelsMapping;
	private int nextCmd;

	public CodeContext(Map<Integer, Integer> labelsMapping) {
		this.setVarHeap(new HashMap<Character,Integer>());
		this.setLabelsMapping(labelsMapping);
		this.setNextCmd(1);
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
	
	public int getNextCmd() {
		return nextCmd;
	}

	public void setNextCmd(int nextCmd) {
		this.nextCmd = nextCmd;
	}

	/**
	 * @return the labelsMapping
	 */
	public Map<Integer, Integer> getLabelsMapping() {
		return labelsMapping;
	}

	/**
	 * @param labelsMapping the labelsMapping to set
	 */
	public void setLabelsMapping(Map<Integer, Integer> labelsMapping) {
		this.labelsMapping = labelsMapping;
	}
}
