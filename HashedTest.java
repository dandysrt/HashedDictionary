import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HashedTest 
{

	HashedDictionary<Integer, String> myDict;
	@Before
	public void setUp() throws Exception 
	{
		myDict = new HashedDictionary<Integer, String>();
	}

	@After
	public void tearDown() throws Exception 
	{
		myDict = null;
	}

	@Test
	public void test() 
	{
		myDict.add(1, "Sam");
		myDict.add(2, "Sue");
		myDict.remove(1);
		assertTrue(myDict.contains(2));
		
	}

}
