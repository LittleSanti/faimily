package com.samajackun.family.parser;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Test;

import com.samajackun.family.core.Family;
import com.samajackun.family.core.Relative;

public class RelativeBuilderTest
{
	@Test
	public void empty()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>();
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf(), relative);
	}

	@Test
	public void self()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.SELF, "myself")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf(), relative);
	}

	@Test
	public void father()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.FATHER, "dad")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf().getFather(), relative);
	}

	@Test
	public void mother()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.MOTHER, "mom")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf().getMother(), relative);
	}

	@Test
	public void child()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.CHILD, "sonny")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertTrue(family.getSelf().getChildren().contains(relative));
	}

	@Test
	public void grandchild()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.GRANDCHILD, "niece")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertTrue(family.getSelf().getChildren().iterator().next().getChildren().contains(relative));
	}

	@Test
	public void mate()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.MATE, "wife")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf().getMate(), relative);
	}

	@Test
	public void sibling()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.SIBLING, "wife")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf().getSibling(), relative);
	}

	@Test
	public void matherOfFather()
	{
		Family family=new Family();
		Deque<Token> tokens=new ArrayDeque<>(Arrays.asList(new Token(Token.Type.FATHER, "dad"), new Token(Token.Type.MOTHER, "mom")));
		Relative relative=RelativeBuilder.getInstance().build(tokens, family);
		assertSame(family.getSelf().getFather().getMother(), relative);
	}
}
