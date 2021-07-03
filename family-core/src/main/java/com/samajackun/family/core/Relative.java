package com.samajackun.family.core;

import java.util.Map;
import java.util.Set;

public interface Relative
{
	public Relative getOrCreateFather();

	public Relative getFather();

	public Relative getOrCreateMother();

	public Relative getMother();

	public Relative getMate();

	public Relative getOrCreateMate();

	public Set<Relative> getChildren();

	public Relative getSibling();

	public Relative getOrCreateSibling();

	/**
	 * Browses the tree upwards up to the max level.
	 *
	 * @param max Max level (0: self, 1:parents, 2:grand-parents...)
	 * @return Map of relatives and relationship degrees.
	 */
	public Map<Relative, Integer> getAncestors(int max);

	public void addChild(Relative child);

	public Relative getSiblingFor(Relative child);

	public Relative getOrCreateChild();
}