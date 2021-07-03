package com.samajackun.family.parser;

public class Token
{
	public enum Type {
		ARTICLE, OF, MY, FATHER, MOTHER, SIBLING, CHILD, SELF, MATE, GRANDCHILD
	};

	private final Type type;

	private final String image;

	public Token(Type type, String image)
	{
		super();
		this.type=type;
		this.image=image;
	}

	public Type getType()
	{
		return this.type;
	}

	public String getImage()
	{
		return this.image;
	}

	@Override
	public String toString()
	{
		return "Token [type=" + this.type + ", image=" + this.image + "]";
	}
}
