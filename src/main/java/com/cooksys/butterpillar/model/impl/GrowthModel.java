package com.cooksys.butterpillar.model.impl;

import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;

public class GrowthModel implements IGrowthModel {

	private double lengthToWingspan;
	private double leavesEatenToWeight;

	public GrowthModel() {
		super();
	}

	public GrowthModel(double lengthToWingspan, double leavesEatenToWeight) {
		super();
		this.lengthToWingspan = lengthToWingspan;
		this.leavesEatenToWeight = leavesEatenToWeight;
	}

	public ICatterfly butterpillarToCatterfly(IButterpillar butterpillar) {
		return new Catterfly(butterpillar.getLength() * this.getLengthToWingspan(),
				butterpillar.getLeavesEaten() * this.getLeavesEatenToWeight());
	}

	public IButterpillar catterflyToButterpillar(ICatterfly catterfly) {
		return new Butterpillar(catterfly.getWingspan() / this.getLengthToWingspan(),
				catterfly.getWeight() / this.getLeavesEatenToWeight());
	}

	public double getLengthToWingspan() {
		return lengthToWingspan;
	}

	public void setLengthToWingspan(double lengthToWingspan) {
		this.lengthToWingspan = lengthToWingspan;
	}

	public double getLeavesEatenToWeight() {
		return leavesEatenToWeight;
	}

	public void setLeavesEatenToWeight(double leavesEatenToWeight) {
		this.leavesEatenToWeight = leavesEatenToWeight;
	}

	@Override
	public String toString() {
		return "GrowthModel [lengthToWingspan=" + lengthToWingspan + ", leavesEatenToWeight=" + leavesEatenToWeight
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(leavesEatenToWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lengthToWingspan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrowthModel other = (GrowthModel) obj;
		if (Double.doubleToLongBits(leavesEatenToWeight) != Double.doubleToLongBits(other.leavesEatenToWeight))
			return false;
		if (Double.doubleToLongBits(lengthToWingspan) != Double.doubleToLongBits(other.lengthToWingspan))
			return false;
		return true;
	}

}
