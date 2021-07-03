package com.samajackun.family.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Locale;

import org.junit.Test;

public class AffinityCalculatorPanelTest
{
	@Test
	public void test()
	{
		AffinityCalculatorInput input=new AffinityCalculatorInput();
		input.setFrom("mi hermano");
		input.setTo("mi hija");
		input.setLocale(new Locale("es"));
		AffinityCalculatorOutput output=new AffinityCalculatorOutput();
		try
		{
			AffinityCalculatorService.getInstance().calculateAffinity(input, output);
			assertEquals(3, output.getDegree());
		}
		catch (AffinityCalculatorException | AffinityCalculatorFailure e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
