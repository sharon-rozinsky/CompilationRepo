
public class Line implements iLine{

	private CommandLine_e type;
	
	
	public Line(CommandLine_e type) {
		this.setType(type);
	}
	
	@Override
	public int run() {
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

}
