package com.samajackun.family.core;

import java.util.Iterator;

public class DefaultAffinityDegreeCalculator implements AffinityDegreeCalculator
{
	private static final DefaultAffinityDegreeCalculator INSTANCE=new DefaultAffinityDegreeCalculator();

	public static DefaultAffinityDegreeCalculator getInstance()
	{
		return INSTANCE;
	}

	private DefaultAffinityDegreeCalculator()
	{
	}

	@Override
	public int kinshipDegreeTo(Relative from, Relative to)
		throws AffinityDegreeCalculatorException
	{
		AncestorTree treeFrom=new AncestorTree(from);
		AncestorTree treeTo=new AncestorTree(to);
		return degreeTo(from, treeFrom, to, treeTo);
	}

	@Override
	public int affinityDegreeTo(Relative from, Relative to)
		throws AffinityDegreeCalculatorException
	{
		int degree;
		if (from.getMate() != null && from.getMate() == to)
		{
			degree=0;
		}
		else
		{
			AncestorTree treeFrom=new AffinityAncestorTree(from);
			AncestorTree treeTo=new AffinityAncestorTree(to);
			degree=degreeTo(from, treeFrom, to, treeTo);
		}
		return degree;
	}

	private int degreeTo(Relative from, AncestorTree treeFrom, Relative to, AncestorTree treeTo)
	{
		int degree;
		if (to == from)
		{
			degree=0;
		}
		else
		{
			Relative coincidence=null;
			while (coincidence == null && (!treeFrom.isLastGenerationEmpty() || !treeTo.isLastGenerationEmpty()))
			{
				treeFrom.nextGeneration();
				treeTo.nextGeneration();
				coincidence=getCoincidence(treeFrom, treeTo);
				if (coincidence == null)
				{
					coincidence=getCoincidence(treeTo, treeFrom);
				}
			}
			degree=sumDegrees(treeFrom, treeTo, coincidence);
		}
		return degree;
	}

	private Relative getCoincidence(AncestorTree treeFrom, AncestorTree treeTo)
	{
		Relative coincidence=null;
		for (Iterator<Relative> iterator=treeFrom.getLastGeneration().iterator(); coincidence == null && iterator.hasNext();)
		{
			Relative ancestorFrom=iterator.next();
			if (treeTo.isPresent(ancestorFrom))
			{
				coincidence=ancestorFrom;
			}
		}
		return coincidence;
	}

	private int sumDegrees(AncestorTree treeFrom, AncestorTree treeTo, Relative coincidence)
	{
		int degree;
		if (coincidence == null)
		{
			degree=Integer.MAX_VALUE;
		}
		else
		{
			degree=treeFrom.getDegree(coincidence) + treeTo.getDegree(coincidence);
		}
		return degree;
	}
}
