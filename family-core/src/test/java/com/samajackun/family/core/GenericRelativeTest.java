package com.samajackun.family.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GenericRelativeTest
{
	@Test
	public void father()
	{
		Relative self=new Family().getSelf();
		assertNull(self.getFather());
		Relative father=self.getOrCreateFather();
		assertNotNull(self.getFather());
		assertSame(father, self.getFather());
		assertSame(father, self.getOrCreateFather());
		assertTrue(father.getChildren().contains(self));
	}

	@Test
	public void mother()
	{
		Relative self=new Family().getSelf();
		assertNull(self.getMother());
		Relative mother=self.getOrCreateMother();
		assertNotNull(self.getMother());
		assertSame(mother, self.getMother());
		assertSame(mother, self.getOrCreateMother());
		assertTrue(mother.getChildren().contains(self));
	}

	@Test
	public void sibling()
	{
		Relative self=new Family().getSelf();
		assertNull(self.getSibling());
		Relative sibling=self.getOrCreateSibling();
		assertSame(sibling, self.getOrCreateSibling());
		assertSame(sibling, self.getSibling());
		assertNotSame(self, sibling);
		assertSame(self.getFather(), sibling.getFather());
		assertSame(self.getMother(), sibling.getMother());
	}

	@Test
	public void mate()
	{
		Relative self=new Family().getSelf();
		assertNull(self.getMate());
		Relative mate=self.getOrCreateMate();
		assertSame(mate, self.getOrCreateMate());
		assertSame(mate, self.getMate());
		assertSame(self, mate.getMate());
	}

	@Test
	public void hash()
	{
		Family family=new Family();
		Relative self=family.getSelf();
		assertEquals(0, self.getChildren().size());
		Relative child1=family.createChild(self, null);
		assertEquals(1, self.getChildren().size());
		self.getChildren().add(child1);
		assertEquals(1, self.getChildren().size());
		Relative child2=family.createChild(self, null);
		assertEquals(2, self.getChildren().size());
		self.getChildren().add(child2);
		assertEquals(2, self.getChildren().size());
	}
}
