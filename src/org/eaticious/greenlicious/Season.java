package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface Season extends Serializable, Cloneable {
	
	public Date getStart();
	
	public Date getEnd();
	
	public SeasonType getSeasontype();

}
