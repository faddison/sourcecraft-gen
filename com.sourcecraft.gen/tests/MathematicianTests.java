package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Mathematician;

public class MathematicianTests {

	@Test
	public void testGet4thRootEqual() 
	{
		assertEquals(2, Mathematician.get4thRoot(16));
	}
	
	@Test
	public void testGet4thRootAbove() 
	{
		assertEquals(3, Mathematician.get4thRoot(17));
	}
	
	@Test
	public void testGet4thRootBelow() 
	{
		assertEquals(2, Mathematician.get4thRoot(15));
	}
	
	@Test
	public void testGet4thPowerEqual() 
	{
		assertEquals(2, Mathematician.get4thRoot(16));
	}
	
	@Test
	public void testGet4thPowerAbove() 
	{
		assertEquals(3, Mathematician.get4thRoot(17));
	}
	
	@Test
	public void testGet4thPowerBelow() 
	{
		assertEquals(2, Mathematician.get4thRoot(15));
	}

}
