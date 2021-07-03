package com.samajackun.family.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AncestorTree
{
	private int currentDegree;

	private final Map<Relative, Integer> visited=new HashMap<>();

	private Set<Relative> lastGeneration=new HashSet<>();

	public AncestorTree(Relative root)
	{
		putRelative(root);
	}

	protected void putRelative(Relative relative)
	{
		this.visited.put(relative, this.currentDegree);
		this.lastGeneration.add(relative);
	}

	public Set<Relative> getLastGeneration()
	{
		return this.lastGeneration;
	}

	public void nextGeneration()
	{
		this.currentDegree++;
		Set<Relative> newGeneration=new HashSet<>();
		for (Relative ancestor : this.lastGeneration)
		{
			if (ancestor.getFather() != null)
			{
				newGeneration.add(ancestor.getFather());
			}
			if (ancestor.getMother() != null)
			{
				newGeneration.add(ancestor.getMother());
			}
		}
		newGeneration.forEach(r -> this.visited.put(r, this.currentDegree));
		this.lastGeneration=newGeneration;
	}

	public boolean isLastGenerationEmpty()
	{
		return this.lastGeneration.isEmpty();
	}

	public boolean isPresent(Relative relative)
	{
		return this.visited.containsKey(relative);
	}

	public int getDegree(Relative relative)
	{
		Integer degree=this.visited.get(relative);
		if (degree == null)
		{
			throw new IllegalArgumentException();
		}
		return degree;
	}
}
