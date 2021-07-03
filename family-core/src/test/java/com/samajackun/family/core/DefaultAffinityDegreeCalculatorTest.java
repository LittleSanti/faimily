package com.samajackun.family.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultAffinityDegreeCalculatorTest
{
	@Test
	public void kinshipDegreeBetweenSelfAndSelf()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, self);
		assertEquals(0, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndFather()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative father=self.getOrCreateFather();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, father);
		assertEquals(1, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndMother()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mother=self.getOrCreateMother();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, mother);
		assertEquals(1, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndSibling()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative sibling=self.getOrCreateSibling();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, sibling);
		assertEquals(2, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndUncle()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative uncle=self.getOrCreateFather().getOrCreateSibling();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, uncle);
		assertEquals(3, degree);
	}

	@Test
	public void kinshipDegreeBetweenUncleAndSelf()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative uncle=self.getOrCreateFather().getOrCreateSibling();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(uncle, self);
		assertEquals(3, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndSon()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative son=family.createChild(self, null);
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, son);
		assertEquals(1, degree);
	}

	@Test
	public void kinshipDegreeBetweenSonAndSelf()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative son=family.createChild(self, null);
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(son, self);
		assertEquals(1, degree);
	}

	@Test
	public void kinshipDegreeBetweenSonAndFather()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative son=family.createChild(self, null);
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(son, self.getOrCreateFather());
		assertEquals(2, degree);
	}

	@Test
	public void kinshipDegreeBetweenFatherAndSon()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative son=family.createChild(self, null);
		Relative father=self.getOrCreateFather();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(father, son);
		assertEquals(2, degree);
	}

	@Test
	public void kinshipDegreeBetweenFatherSiblingAndMotherSibling()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative fatherSibling=self.getOrCreateFather().getOrCreateSibling();
		Relative motherSibling=self.getOrCreateMother().getOrCreateSibling();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(fatherSibling, motherSibling);
		assertEquals(Integer.MAX_VALUE, degree);
	}

	@Test
	public void kinshipDegreeBetweenSonAndMate()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative uncle=self.getOrCreateFather().getOrCreateSibling();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, uncle);
		assertEquals(3, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndMotherOfMate()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mate=self.getOrCreateMate();
		Relative motherInLaw=mate.getOrCreateMother();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, motherInLaw);
		assertEquals(Integer.MAX_VALUE, degree);
	}

	@Test
	public void kinshipDegreeBetweenSelfAndMate()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mate=self.getOrCreateMate();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.kinshipDegreeTo(self, mate);
		assertEquals(Integer.MAX_VALUE, degree);
	}

	@Test
	public void affinityDegreeBetweenSelfAndMotherOfMate()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mate=self.getOrCreateMate();
		Relative motherInLaw=mate.getOrCreateMother();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.affinityDegreeTo(self, motherInLaw);
		assertEquals(1, degree);
	}

	@Test
	public void affinityDegreeBetweenSelfAndMate()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mate=self.getOrCreateMate();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.affinityDegreeTo(self, mate);
		assertEquals(0, degree);
	}

	@Test
	public void affinityDegreeBetweenMateAndSelf()
		throws AffinityDegreeCalculatorException
	{
		Family family=new Family();
		Relative self=family.getSelf();
		Relative mate=self.getOrCreateMate();
		DefaultAffinityDegreeCalculator calculator=DefaultAffinityDegreeCalculator.getInstance();
		int degree=calculator.affinityDegreeTo(mate, self);
		assertEquals(0, degree);
	}
}
