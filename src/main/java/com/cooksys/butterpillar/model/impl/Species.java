package com.cooksys.butterpillar.model.impl;

import java.util.Arrays;

import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;
import com.cooksys.butterpillar.model.ISpecies;

public class Species implements ISpecies {
	private String name;
	private IGrowthModel gm;
	private int indx;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public IGrowthModel getGrowthModel() {
		return gm;
	}

	@Override
	public void setGrowthModel(IGrowthModel growthModel) {
		this.gm = growthModel;
	}

	@Override
	public ICatterfly createCatterfly(double wingspan, double weight) {
		return new Catterfly(wingspan, weight);
	}

	@Override
	public IButterpillar createButterpillar(double length, double leavesEaten) {
		return new Butterpillar(length, leavesEaten);
	}

	@Override
	public ICatterfly[] convert(IButterpillar[] butterpillars) {
		ICatterfly[] ics = new ICatterfly[butterpillars.length];
		indx = 0;
		Arrays.stream(butterpillars).forEach(b -> {
			ics[indx++] = gm.butterpillarToCatterfly(b);
		});
		return ics;
	}

	@Override
	public IButterpillar[] convert(ICatterfly[] catterflies) {
		IButterpillar[] ibs = new IButterpillar[catterflies.length];
		indx = 0;
		Arrays.stream(catterflies).forEach(c -> {
			ibs[indx++] = gm.catterflyToButterpillar(c);
		});
		return ibs;
	}
	
}
