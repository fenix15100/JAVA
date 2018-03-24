package local.fran.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import local.fran.main.Mayor3;

public class TestMayor3 {
	
	@Parameter(0)
    public int result;
	
	@Parameter(1)
    public int expected;

	@Test
	public final void testMayor3_numero_mayor_a() {
		Mayor3 tester=new Mayor3();
		int a=10;
		int b=9;
		int c=4;
		expected=a;
		result=tester.numero_mayor(a, b, c);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testMayor3_numero_mayor_b() {
		Mayor3 tester=new Mayor3();
		int a=5;
		int b=9;
		int c=4;
		expected=b;
		result=tester.numero_mayor(a, b, c);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testMayor3_numero_mayor_c() {
		Mayor3 tester=new Mayor3();
		int a=10;
		int b=9;
		int c=30;
		expected=c;
		result=tester.numero_mayor(a, b, c);
		assertEquals(expected, result);
		
		
	}

}
