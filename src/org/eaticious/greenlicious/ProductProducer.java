package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Map;

public interface ProductProducer extends Serializable, Cloneable{

	public Region getRegion();

	public Product getProduct();

	public Quantity getProducedAmount();

	public Map<ProductConsumer, Quantity> getConsumers();
}
