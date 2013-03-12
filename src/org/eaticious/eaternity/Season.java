package org.eaticious.eaternity;

import org.eaticious.common.*;

public interface Season {
	
	public SeasonDateImpl getStart();
	
	public SeasonDateImpl getStop();
	
	public Boolean hasSeason();
	
	public Region getRegion();

}
