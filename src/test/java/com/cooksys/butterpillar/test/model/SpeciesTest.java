package com.cooksys.butterpillar.test.model;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;

import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;
import com.cooksys.butterpillar.model.ISpecies;
import com.cooksys.butterpillar.model.impl.Butterpillar;
import com.cooksys.butterpillar.model.impl.Catterfly;
import com.cooksys.butterpillar.model.impl.GrowthModel;

public class SpeciesTest {

	private Reflections reflections;
	private Set<Class<? extends ISpecies>> implementations;
	private Class<? extends ISpecies> implementation;

	private ISpecies species;
	private IGrowthModel model;
	private IButterpillar[] butterpillars;
	private ICatterfly[] catterflies;

	@BeforeClass
	public void beforeClass() {
		this.reflections = new Reflections("com.cooksys");
		this.implementations = reflections.getSubTypesOf(ISpecies.class);
		for (Class<? extends ISpecies> implementation : this.implementations) {
			this.implementation = implementation;
		}
	}

	@AfterClass
	public void afterClass() {
		this.reflections = null;
		this.implementations.clear();
		this.implementations = null;
		this.implementation = null;
	}

	@Before
	public void before() throws InstantiationException, IllegalAccessException {

		this.model = new GrowthModel();
		this.model.setLengthToWingspan(1.2);
		this.model.setLeavesEatenToWeight(0.25);

		this.species = implementation.newInstance();
		this.species.setName("Test Species");
		this.species.setGrowthModel(this.model);

		this.butterpillars = new Butterpillar[5];
		this.butterpillars[0] = this.species.createButterpillar(0.5,20);
		this.butterpillars[1] = this.species.createButterpillar(0.6,17);
		this.butterpillars[2] = this.species.createButterpillar(0.5,21);
		this.butterpillars[3] = this.species.createButterpillar(0.4,20);
		this.butterpillars[4] = this.species.createButterpillar(0.7,18);

		this.catterflies = new Catterfly[5];
		this.catterflies[0] = this.species.createCatterfly(0.6,5);
		this.catterflies[1] = this.species.createCatterfly(0.72,4.25);
		this.catterflies[2] = this.species.createCatterfly(0.6,5.25);
		this.catterflies[3] = this.species.createCatterfly(0.48,5.0);
		this.catterflies[4] = this.species.createCatterfly(0.84,4.5);
	}

	@After
	public void after() {
		this.model = null;
		this.butterpillars = null;
		this.catterflies = null;
	}

	@Test
	public void testSingleImplementation() {
		Assert.assertEquals("There should be a single implementation of com.cooksys.butterpillar.model.ISpecies", 1,
				this.implementations.size());
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("Test Species", this.species.getName());
	}

	@Test
	public void testGetGrowthModel() {
		Assert.assertEquals(this.model, this.species.getGrowthModel());
	}

	@Test
	public void testConvertIButterpillarArray() {
		Assert.assertArrayEquals(this.catterflies, this.species.convert(this.butterpillars));
	}

	@Test
	public void testConvertICatterflyArray() {
		Assert.assertArrayEquals(this.butterpillars, this.species.convert(this.catterflies));
	}

}
