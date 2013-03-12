package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface Region extends Serializable, Cloneable {
	
	public String getFCLCode();
	
	public String getISOCode();
	
	public String getName();
	
	public String getName(Locale language);
	
	public Region getParent();
	
	public List<Region> getChildren();
	
	public List<Product> getProducedProducts();
	
	public Map<Product, Quantity> getProducedProductAmounts();
	
	public List<Product> getConsumedProducts();
	
	public Map<Product, Quantity> getConsumedProductAmounts();

}
