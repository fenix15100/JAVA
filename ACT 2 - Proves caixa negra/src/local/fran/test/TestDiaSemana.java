package local.fran.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import local.fran.main.DiaSemana;;

public class TestDiaSemana {
	
	@Parameter(0)
    public String result;
	
	@Parameter(1)
    public String expected;

	@Test
	public final void testDiaSemanaFECHASGENERICAS0() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=4;
		int dia=2;
		expected="LUNES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS1() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=5;
		int dia=8;
		expected="MARTES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS2() {
		DiaSemana tester=new DiaSemana();
		int ano=2019;
		int mes=1;
		int dia=9;
		expected="MIERCOLES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS3() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=6;
		int dia=7;
		expected="JUEVES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS4() {
		DiaSemana tester=new DiaSemana();
		int ano=2014;
		int mes=11;
		int dia=7;
		expected="VIERNES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS5() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=2;
		int dia=24;
		expected="SABADO";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaFECHASGENERICAS6() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=12;
		int dia=23;
		expected="DOMINGO";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT0() {
		DiaSemana tester=new DiaSemana();
		int ano=18;
		int mes=4;
		int dia=02;
		expected="LUNES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT1() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=05;
		int dia=8;
		expected="MARTES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT2() {
		DiaSemana tester=new DiaSemana();
		int ano=19;
		int mes=01;
		int dia=9;
		expected="MIERCOLES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT3() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=06;
		int dia=7;
		expected="JUEVES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT4() {
		DiaSemana tester=new DiaSemana();
		int ano=14;
		int mes=11;
		int dia=7;
		expected="VIERNES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT5() {
		DiaSemana tester=new DiaSemana();
		int ano=18;
		int mes=02;
		int dia=24;
		expected="SABADO";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaMIXINGDATEFORMATINPUT6() {
		DiaSemana tester=new DiaSemana();
		int ano=18;
		int mes=12;
		int dia=23;
		expected="DOMINGO";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE0() {
		DiaSemana tester=new DiaSemana();
		int ano=18;
		int mes=4;
		int dia=32;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE1() {
		DiaSemana tester=new DiaSemana();
		int ano=0000;
		int mes=055;
		int dia=8;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE2() {
		DiaSemana tester=new DiaSemana();
		int ano=19;
		int mes=1;
		int dia=50;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE3() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=06666;
		int dia=7;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE4() {
		DiaSemana tester=new DiaSemana();
		int ano=141;
		int mes=99;
		int dia=7;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE5() {
		DiaSemana tester=new DiaSemana();
		int ano=0000;
		int mes=00;
		int dia=00;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaOVERFLOWRANGE6() {
		DiaSemana tester=new DiaSemana();
		int ano=18;
		int mes=12;
		int dia=99;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaDATESBEFOREUNIXTIMESTAMP0() {
		DiaSemana tester=new DiaSemana();
		int ano=1968;
		int mes=1;
		int dia=1;
		expected="LUNES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaDATESBEFOREUNIXTIMESTAMP1() {
		DiaSemana tester=new DiaSemana();
		int ano=1968;
		int mes=1;
		int dia=9;
		expected="MARTES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	
	
	@Test
	public final void testDiaSemanaDATESJULIANMONTHS0() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=4;
		int dia=31;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaDATESJULIANMONTHS1() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=5;
		int dia=31;
		expected="JUEVES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	
	@Test
	public final void testDiaSemanaYEARCOUNT0() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=2;
		int dia=28;
		expected="MIERCOLES";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}
	
	
	@Test
	public final void testDiaSemanaYEARCOUNT1() {
		DiaSemana tester=new DiaSemana();
		int ano=2018;
		int mes=2;
		int dia=29;
		expected="ERROR";
		result=tester.getDiaSemana(dia, mes, ano);
		assertEquals(expected, result);
		
		
	}



}
