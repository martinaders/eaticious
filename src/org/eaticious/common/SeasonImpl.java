package org.eaticious.common;

public class SeasonImpl implements Season {
	
	private static final long serialVersionUID = -2858323468921887438L;

	private SeasonDate beginning;
	private SeasonDate end;
	
	private SeasonType seasonType;
	
	/**
	 * Empty Constructor needed for objectify
	 */
	public SeasonImpl() { /* needed for objectify */}
	
	public SeasonImpl(final SeasonDate start, final SeasonDate stop, final SeasonType seasonType) {
		this.beginning = start;
		this.end = stop;
		this.seasonType = seasonType;
	}
	
	/**
	 * Copy constructor
	 */
	public SeasonImpl(final Season other) {
		this.beginning = new SeasonDateImpl(other.getBeginning());
		this.end = new SeasonDateImpl(other.getEnd());
		seasonType = other.getSeasonType();
	}
	
	
	@Override
	public SeasonDate getBeginning() {
		return this.beginning;
	}

	@Override
	public SeasonDate getEnd() {
		return this.end;
	}

	@Override
	public SeasonType getSeasonType() {
		return seasonType;
	}


	@Override
	public void setBeginning(final SeasonDate beginning) {
		this.beginning = beginning;
	}

	@Override
	public void setEnd(final SeasonDate end) {
		this.end = end;
	}

	@Override
	public void setSeasonType(final SeasonType type) {
		this.seasonType = type;
	}

}
