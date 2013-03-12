package org.eaticious.common;

import java.io.Serializable;

public interface Season extends Serializable, Cloneable {
	
	public SeasonDate getStart();
	
	public SeasonDate getEnd();
	
	public SeasonType getSeasontype();

}
