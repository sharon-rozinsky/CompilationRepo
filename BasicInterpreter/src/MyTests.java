import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyTests {

	@Test
	public void test() {

	}
	
	@Test
	public void testOperators(){
		String reg = "(\\<|\\>|\\<=|\\>\\=|\\=\\=|\\!\\=)";
		assertTrue("<".matches(reg));
		assertTrue(">".matches(reg));
		assertTrue("<=".matches(reg));
		assertTrue(">=".matches(reg));
		assertTrue("==".matches(reg));
		assertTrue("!=".matches(reg));
	}
	
	@Test
	public void testLexicalAnalysis(){
		List<String> file = new ArrayList<String>();
		file.add("10: x:= z;");
		file.add("10: x:= - * / 15 - 7 + 1 1 3 + 2 - 1 1;");
		file.add("12: goto 22;");
		file.add("123: print(x);");
		file.add("50: if(x > y) if(x >= z) goto 12;");
		Parser p = new Parser(file);
		p.LexicalAnalysis();
	}

}
