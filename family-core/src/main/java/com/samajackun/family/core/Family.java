package com.samajackun.family.core;

public class Family
{
	private final Relative self=new GenericRelative();

	public Relative getSelf()
	{
		return this.self;
	}

	public Relative createChild(Relative father, Relative mother)
	{
		Relative child=new GenericRelative(father, mother);
		return child;
	}
}
