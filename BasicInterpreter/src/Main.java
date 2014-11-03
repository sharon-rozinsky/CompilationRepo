import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		BufferedReader inProgramTxtStream = null;
		List<String> programTxt = new ArrayList<String>();
		
		//Read Program text file.
		try {   
			inProgramTxtStream = new BufferedReader(new FileReader(args[0]));
			String str;
			while ((str = inProgramTxtStream.readLine()) != null) {
				programTxt.add(str);
			}

			inProgramTxtStream.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Create Parser object and start Lexicographic analysis.
		
		Parser parser = new Parser(programTxt);
		if(parser.LexicalAnalysis())
		{ //Passed compilation continue to execution.
			Executer exe = new Executer(new CodeContext(parser.getLabelsMapping()), parser.getLines());
			exe.execute();
		}
		else //Compilation errors print errors from logger.
		{
			
		}
	}

}
