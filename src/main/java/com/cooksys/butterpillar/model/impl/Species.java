package com.cooksys.butterpillar.model.impl;

import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;
import com.cooksys.butterpillar.model.ISpecies;

public class Species implements ISpecies {
	private String name;
	private IGrowthModel gm;
	
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
		int indx = 0;
		for(Butterpillar b: (Butterpillar[]) butterpillars){
			ics[indx++] = gm.butterpillarToCatterfly(b);
		}
		return ics;
	}

	@Override
	public IButterpillar[] convert(ICatterfly[] catterflies) {
		IButterpillar[] ibs = new IButterpillar[catterflies.length];
		int indx = 0;
		for(Catterfly c: (Catterfly[]) catterflies){
			ibs[indx++] = gm.catterflyToButterpillar(c);
		}
		return ibs;
	}
	
}
