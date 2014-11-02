
public abstract class Line {

	protected int lineIndex;
	
	
	public Line(int lineIndex) {
		this.setLineIndex(lineIndex);
	}
	
	public abstract int run(CodeContext codeContext);

	/**
	 * @return the lineIndex
	 */
	public int getLineIndex() {
		return lineIndex;
	}

	/**
	 * @param lineIndex the lineIndex to set
	 */
	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}

}
