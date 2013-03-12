package org.eaticious.eaternity;


import java.io.Serializable;


public interface RecipeComment extends Serializable, Cloneable{

	public String getComment();
	
	public Double getNumber();
}