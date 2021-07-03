package com.samajackun.family.core;

public class AffinityAncestorTree extends AncestorTree
{
	public AffinityAncestorTree(Relative root)
	{
		super(root);
		if (root.getMate() != null)
		{
			super.putRelative(root.getMate());
		}
	}

}
