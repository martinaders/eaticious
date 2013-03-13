package org.eaticious.common;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public interface Region extends Serializable {
	
	public String getFCLCode();
	
	public String getISOCode();
	
	public String getName();
	
	public String getName(Locale language);
	
	public Region getParent();
	
	public List<Region> getChildren();

}
