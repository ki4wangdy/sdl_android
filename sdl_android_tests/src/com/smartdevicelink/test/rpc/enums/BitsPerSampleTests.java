package com.smartdevicelink.test.rpc.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.smartdevicelink.proxy.rpc.enums.BitsPerSample;

public class BitsPerSampleTests extends TestCase {

	public void testValidEnums () {	
		String example = "8_BIT";
		BitsPerSample enum8Bit = BitsPerSample.valueForString(example);
		example = "16_BIT";
		BitsPerSample enum16Bit = BitsPerSample.valueForString(example);
		
		assertNotNull("8_BIT returned null", enum8Bit);
		assertNotNull("16_BIT returned null", enum16Bit);
	}

	public void testInvalidEnum () {
		String example = "8_biT";
		try {
		    BitsPerSample temp = BitsPerSample.valueForString(example);
            assertNull("Result of valueForString should be null.", temp);
		}
		catch (IllegalArgumentException exception) {
            fail("Invalid enum throws IllegalArgumentException.");
		}
	}
	
	public void testNullEnum () {
		String example = null;
		try {
		    BitsPerSample temp = BitsPerSample.valueForString(example);
            assertNull("Result of valueForString should be null.", temp);
		}
		catch (NullPointerException exception) {
            fail("Null string throws NullPointerException.");
		}
	}
	
	public void testListEnum() {
 		List<BitsPerSample> enumValueList = Arrays.asList(BitsPerSample.values());

		List<BitsPerSample> enumTestList = new ArrayList<BitsPerSample>();
		enumTestList.add(BitsPerSample._8_BIT);
		enumTestList.add(BitsPerSample._16_BIT);

		assertTrue("Enum value list does not match enum class list", 
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
	
}