package com.samajackun.family.parser;

public class WordTokenizer
{
	private String src;

	private int currentIndex;

	public WordTokenizer(String src)
	{
		super();
		this.src=src;
		matchBeginOfWord();
	}

	private void matchBeginOfWord()
	{
		while (currentIndex < src.length() && Character.isWhitespace(src.charAt(currentIndex)))
		{
			currentIndex++;
		}
	}

	public String nextWord()
	{
		int p0=this.currentIndex;
		String word=null;
		while (currentIndex < src.length() && word == null)
		{
			char c=src.charAt(currentIndex);
			if (Character.isWhitespace(c))
			{
				word=src.substring(p0, this.currentIndex);
			}
			else if (c == '\'')
			{
				word=src.substring(p0, 1 + this.currentIndex);
			}
			currentIndex++;
		}
		if (word == null && p0 < currentIndex)
		{
			word=src.substring(p0);
		}
		matchBeginOfWord();
		return word;
	}
}
