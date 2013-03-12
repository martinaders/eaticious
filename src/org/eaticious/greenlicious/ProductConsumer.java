package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Map;

public interface ProductConsumer extends Serializable, Cloneable {
	
	public Region getRegion();
	
	public Product getProduct();
	
	public Quantity getConsumedAmount();
	
	public Map<ProductProducer, Quantity> getProducers();

}
