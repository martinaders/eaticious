package org.eaticious.common;

import java.io.Serializable;

public interface Season extends Serializable {
	
	public SeasonDate getBeginning();
	
	public void setBeginning(SeasonDate beginning);
	
	public SeasonDate getEnd();
	
	public void setEnd(SeasonDate end);
	
	public SeasonType getSeasonType();
	
	public void setSeasonType(SeasonType type);

}
