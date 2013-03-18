package org.eaticious.common;

public class SeasonImpl implements Season {
	
	private static final long serialVersionUID = -2858323468921887438L;

	private SeasonDate start;
	private SeasonDate stop;
	
	private SeasonType seasonType;
	
	/**
	 * Empty Constructor needed for objectify
	 */
	public SeasonImpl() {}
	
	public SeasonImpl(SeasonDate start, SeasonDate stop, SeasonType seasonType) {
		this.start = start;
		this.stop = stop;
		this.seasonType = seasonType;
	}
	
	/**
	 * Copy constructor
	 */
	public SeasonImpl(Season other) {
		start = new SeasonDateImpl(other.getBeginning());
		stop = new SeasonDateImpl(other.getEnd());
		seasonType = other.getSeasonType();
	}
	
	
	@Override
	public SeasonDate getBeginning() {
		return start;
	}

	@Override
	public SeasonDate getEnd() {
		return stop;
	}

	@Override
	public SeasonType getSeasonType() {
		return seasonType;
	}


	@Override
	public void setBeginning(SeasonDate beginning) {
		this.start = beginning;
	}

	@Override
	public void setEnd(SeasonDate end) {
		this.stop = end;
	}

	@Override
	public void setSeasonType(SeasonType type) {
		this.seasonType = type;
	}

}
