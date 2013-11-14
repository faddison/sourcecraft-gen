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
	
	@Test
	public void testCeil2ndPowerEqual() 
	{
		assertEquals(64, Mathematician.ceil2ndPower(64));
	}
	
	@Test
	public void testCeil2ndPowerBelow() 
	{
		assertEquals(64, Mathematician.ceil2ndPower(56));
	}
	
	@Test
	public void testCeil2ndPowerAbove() 
	{
		assertEquals(128, Mathematician.ceil2ndPower(72));
	}
	
	@Test
	public void testCeilLog2Equal() 
	{
		assertEquals(6, Mathematician.ceilLog2(64));
	}
	
	@Test
	public void testCeilLog2Below() 
	{
		assertEquals(6, Mathematician.ceilLog2(56));
	}
	
	@Test
	public void testCeilLog2Above() 
	{
		assertEquals(7, Mathematician.ceilLog2(72));
	}
	
	@Test
	public void testFloorLog2Equal() 
	{
		assertEquals(6, Mathematician.floorLog2(64));
	}
	
	@Test
	public void testFloorLog2Below() 
	{
		assertEquals(5, Mathematician.floorLog2(56));
	}
	
	@Test
	public void testFloorLog2Above() 
	{
		assertEquals(6, Mathematician.floorLog2(72));
	}

}
