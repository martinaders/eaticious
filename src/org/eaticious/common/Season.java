package org.eaticious.common;

import java.io.Serializable;

public interface Season extends Serializable {
	
	SeasonDate getBeginning();
	
	void setBeginning(SeasonDate beginning);
	
	SeasonDate getEnd();
	
	void setEnd(SeasonDate end);
	
	SeasonType getSeasonType();
	
	void setSeasonType(SeasonType type);

}
