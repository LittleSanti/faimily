package com.samajackun.family.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordTokenizerTest
{

	@Test
	public void empty()
	{
		String src="";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void oneBlank()
	{
		String src=" ";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void twoBlanks()
	{
		String src="  ";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void oneLetter()
	{
		String src="a";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("a", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void oneWord()
	{
		String src="january";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("january", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void twoWords()
	{
		String src="january february";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("january", word);
		word=tokenizer.nextWord();
		assertEquals("february", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void oneWordWithTrailingBlanks()
	{
		String src=" january";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("january", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void oneWordWithEndingBlanks()
	{
		String src="january ";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("january", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void someWordsWithBlanks()
	{
		String src=" january  february   march    april     ";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("january", word);
		word=tokenizer.nextWord();
		assertEquals("february", word);
		word=tokenizer.nextWord();
		assertEquals("march", word);
		word=tokenizer.nextWord();
		assertEquals("april", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void twoWordsJointWithQuote()
	{
		String src="d'may";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("d'", word);
		word=tokenizer.nextWord();
		assertEquals("may", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}

	@Test
	public void twoWordsJointWithQuoteAndBlank()
	{
		String src="d' may";
		WordTokenizer tokenizer=new WordTokenizer(src);
		String word;
		word=tokenizer.nextWord();
		assertEquals("d'", word);
		word=tokenizer.nextWord();
		assertEquals("may", word);
		word=tokenizer.nextWord();
		assertNull(word);
	}
}
