package com.samajackun.family.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

class GenericRelative implements Relative
{
	private static AtomicLong count=new AtomicLong();

	private final long id;

	private Relative father;

	private Relative mother;

	private Relative mate;

	private final Set<Relative> children=new HashSet<>(2);

	public GenericRelative()
	{
		this(null, null);
	}

	public GenericRelative(Relative father, Relative mother)
	{
		this.id=count.incrementAndGet();
		this.father=father;
		if (this.father != null)
		{
			this.father.addChild(this);
		}
		this.mother=mother;
		if (this.mother != null)
		{
			this.mother.addChild(this);
		}
	}

	@Override
	public Relative getFather()
	{
		return this.father;
	}

	@Override
	public Relative getOrCreateFather()
	{
		if (this.father == null)
		{
			GenericRelative father=new GenericRelative();
			this.father=father;
			this.father.addChild(this);
		}
		return this.father;
	}

	@Override
	public Relative getMother()
	{
		return this.mother;
	}

	@Override
	public Relative getOrCreateMother()
	{
		if (this.mother == null)
		{
			GenericRelative mother=new GenericRelative();
			this.mother=mother;
			this.mother.addChild(this);
		}
		return this.mother;
	}

	@Override
	public Relative getMate()
	{
		return this.mate;
	}

	@Override
	public Relative getOrCreateMate()
	{
		if (this.mate == null)
		{
			GenericRelative mate=new GenericRelative();
			this.mate=mate;
			mate.mate=this;
		}
		return this.mate;
	}

	@Override
	public Set<Relative> getChildren()
	{
		return this.children;
	}

	@Override
	public Relative getSibling()
	{
		Relative sibling=null;
		Set<Relative> siblings=this.father != null
			? this.father.getChildren()
			: this.mother != null
				? this.mother.getChildren()
				: Collections.emptySet();
		for (Iterator<Relative> iterator=siblings.iterator(); sibling == null && iterator.hasNext();)
		{
			Relative r=iterator.next();
			if (r != this)
			{
				sibling=r;
			}
		}
		return sibling;
	}

	@Override
	public Relative getOrCreateSibling()
	{
		Relative sibling=getOrCreateFather().getSiblingFor(this);
		if (sibling == null)
		{
			GenericRelative siblingNew=new GenericRelative(getFather(), getOrCreateMother());
			sibling=siblingNew;
		}
		return sibling;
	}

	@Override
	public Relative getSiblingFor(Relative child)
	{
		Relative sibling=null;
		for (Iterator<Relative> iterator=this.children.iterator(); sibling == null && iterator.hasNext();)
		{
			Relative r=iterator.next();
			if (r != child)
			{
				sibling=r;
			}
		}
		return sibling;
	}

	@Override
	public void addChild(Relative child)
	{
		this.children.add(child);
		if (this.mate != null)
		{
			if (!this.mate.getChildren().contains(child))
			{
				this.mate.addChild(child);
			}
		}
	}

	@Override
	public Relative getOrCreateChild()
	{
		Relative child;
		if (this.children.isEmpty())
		{
			child=new GenericRelative(this, this.mate);
		}
		else
		{
			child=this.children.iterator().next();
		}
		return child;
	}

	@Override
	public Map<Relative, Integer> getAncestors(int max)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result=prime * result + (int)(this.id ^ (this.id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		GenericRelative other=(GenericRelative)obj;
		if (this.id != other.id)
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "[id=" + this.id + "]";
	}
}
