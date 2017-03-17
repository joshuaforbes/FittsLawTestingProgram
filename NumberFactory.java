package application;

import java.util.Random;

/* This class generates random numbers based on bounds. 
 */

public class NumberFactory {

	//Our bounds for the factory
	int upperBound;
	int lowerBound;
	
	//Our random number generator
	Random gen;
	
	//Initialize data members
	NumberFactory(int u, int l){
		
		upperBound = u;
		lowerBound = l;
		gen = new Random();
		
	}
	
	//To get a number
	double getNumber() {

		int intHalf = gen.nextInt(upperBound - lowerBound + 1) + lowerBound;
		double doubleHalf = gen.nextDouble();
		return (doubleHalf + intHalf);
		
	}
	
}
