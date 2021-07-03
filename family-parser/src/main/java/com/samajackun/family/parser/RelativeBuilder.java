package com.samajackun.family.parser;

import java.util.Deque;

import com.samajackun.family.core.Family;
import com.samajackun.family.core.Relative;

public class RelativeBuilder
{
	private static final RelativeBuilder INSTANCE=new RelativeBuilder();

	public static RelativeBuilder getInstance()
	{
		return INSTANCE;
	}

	private RelativeBuilder()
	{
	}

	public Relative build(Deque<Token> tokens, Family family)
	{
		Relative relative=family.getSelf();
		for (Token token : tokens)
		{
			relative=stepForward(relative, family, token);
		}
		return relative;
	}

	private Relative stepForward(Relative relative, Family family, Token token)
	{
		switch (token.getType())
		{
			case SELF:
				break;
			case CHILD:
				relative=relative.getOrCreateChild();
				break;
			case FATHER:
				relative=relative.getOrCreateFather();
				break;
			case MOTHER:
				relative=relative.getOrCreateMother();
				break;
			case MATE:
				relative=relative.getOrCreateMate();
				break;
			case SIBLING:
				relative=relative.getOrCreateSibling();
				break;
			case GRANDCHILD:
				relative=relative.getOrCreateChild().getOrCreateChild();
				break;
			default:
				throw new IllegalArgumentException();
		}
		return relative;
	}
}
