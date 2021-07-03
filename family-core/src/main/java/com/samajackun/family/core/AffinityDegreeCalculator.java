package com.samajackun.family.core;

public interface AffinityDegreeCalculator
{
	public int kinshipDegreeTo(Relative from, Relative to)
		throws AffinityDegreeCalculatorException;

	public int affinityDegreeTo(Relative from, Relative to)
		throws AffinityDegreeCalculatorException;
}
