package org.eaticious.common;

import java.io.Serializable;

public interface Season extends Serializable {
	
	public SeasonDate getStart();
	
	public SeasonDate getEnd();
	
	public SeasonType getSeasontype();

}
