package com.samajackun.family.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.samajackun.family.core.Family;
import com.samajackun.family.core.Relative;

public class RelativePathParserTest
{
	@Test
	public void self()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="yo";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void myMother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="mi madre";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void myChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="mi hijo";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getChildren().contains(relative));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void myGranChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="mi nieto";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getOrCreateChild().getChildren().contains(relative));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfSelf()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de yo";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMyMother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la mi madre";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theFatherOfMyMother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="el padre de mi madre";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMother().getFather(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMyFather()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de mi padre";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getFather().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMyMate()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de mi mujer";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMate().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theFatherOfMyMate()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="el padre de mi mujer";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getMate().getFather(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void myGrandChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="mi nieto";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getChildren().iterator().next().getChildren().iterator().next(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfBrother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer de mi hermano";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getSibling().getMate().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfBrotherOfGrandchild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer del hermano de mi nieto";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getChildren().iterator().next().getChildren().contains(relative.getChildren().iterator().next().getMate()));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMateOfMyBrother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la mujer de mi hermano";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getSibling().getMate(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfMyBrother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer de mi hermano";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getSibling().getMate().getMother(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theSiblingOfMotherOfMateOfMyBrother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la hermana de la madre de la mujer de mi hermano";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertSame(family.getSelf().getSibling().getMate().getMother().getSibling(), relative);
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfBrotherOfMyChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer del hermano de mi hijo";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getChildren().contains(relative.getChildren().iterator().next().getMate()));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfBrotherOfChildOfMyChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer del hermano del hijo de mi hijo";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getOrCreateChild().getChildren().contains(relative.getChildren().iterator().next().getMate()));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theMotherOfMateOfBrotherOfMyGrandChild()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="la madre de la mujer del hermano de mi nieto";
		Family family=new Family();
		try
		{
			Relative relative=RelativePathParser.getInstance().parse(src, allowedLocales, family);
			assertTrue(family.getSelf().getOrCreateChild().getChildren().contains(relative.getChildren().iterator().next().getMate()));
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void myFatherOfMyMother()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="mi padre de mi madre";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected PathEndedException");
		}
		catch (PathEndedException e)
		{
			assertEquals(Token.Type.OF, e.getToken().getType());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void ofMyFather()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="de mi padre";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected UnexpectedTokenException");
		}
		catch (UnexpectedTokenException e)
		{
			assertEquals(Token.Type.OF, e.getToken().getType());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theBrotherOfTheSelfOfMyFather()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="el hermano de la yo de mi padre";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected UnexpectedTokenException");
		}
		catch (UnexpectedTokenException e)
		{
			assertEquals(Token.Type.SELF, e.getToken().getType());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theBrotherMyFather()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="el hermano mi padre";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected UnexpectedTokenException");
		}
		catch (UnexpectedTokenException e)
		{
			assertEquals(Token.Type.MY, e.getToken().getType());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theBrotherOfOfMyFather()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale("es"));
		String src="el hermano de de mi padre";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected UnexpectedTokenException");
		}
		catch (UnexpectedTokenException e)
		{
			assertEquals(Token.Type.OF, e.getToken().getType());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void strangeWord()
	{
		Locale locale=new Locale("es");
		List<Locale> allowedLocales=Arrays.asList(locale);
		String src="mi primo";
		Family family=new Family();
		try
		{
			RelativePathParser.getInstance().parse(src, allowedLocales, family);
			fail("Expected UnknownTermException");
		}
		catch (UnknownTermException e)
		{
			assertEquals("Unknown term 'primo' in locale es", e.getMessage());
			assertEquals("primo", e.getTerm());
			assertEquals(new Locale("es"), e.getLocale());
		}
		catch (RelativePathParseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
