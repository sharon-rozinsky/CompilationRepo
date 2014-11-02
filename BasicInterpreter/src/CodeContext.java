import java.util.HashMap;
import java.util.Map;


public class CodeContext {
	
	private Map<Character, Integer> varHeap;
	private Map<Integer, Integer> lineToIdx;
	private int nextCmd;

	public CodeContext() {
		this.setVarHeap(new HashMap<Character,Integer>());
		this.setLineToIdx(new HashMap<Integer,Integer>());
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

	public Map<Integer, Integer> getLineToIdx() {
		return lineToIdx;
	}

	/**
	 * @param varHeap the varHeap to set
	 */
	public void setLineToIdx(Map<Integer, Integer> lineIdx) {
		this.lineToIdx = lineIdx;
	}

	public int getNextCmd() {
		return nextCmd;
	}

	public void setNextCmd(int nextCmd) {
		this.nextCmd = nextCmd;
	}
}
