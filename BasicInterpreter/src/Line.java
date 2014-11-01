
public class Line implements iLine{

	private CommandLine_e	type;
	private int				lineIndex;
	
	
	public Line(CommandLine_e type, int lineIndex) {
		this.setType(type);
		this.setLineIndex(lineIndex);
	}
	
	@Override
	public int run(CodeContext codeContext) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the type
	 */
	public CommandLine_e getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CommandLine_e type) {
		this.type = type;
	}

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
