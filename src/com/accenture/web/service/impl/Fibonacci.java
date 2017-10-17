package com.accenture.web.service.impl;

import java.math.BigInteger;

import org.apache.log4j.Logger;

public class Fibonacci {
	
	Logger logger  =  Logger.getLogger(Fibonacci.class ); 
    
	/**
	 * Achieve fibonacci sequence,just input a length,but the max length of fibonacciFunction is 92.When n>92,we use another function to achieve the rest of sequence.
	 * @param n the length of fibonacci sequence
	 * @return a fibonacci sequence
	 */
	public long fibonacciFunction(int n){
		if(n==0){
			return 0;
		}
		else if(n>1){
			long a, b = 1;  
            n--;  
            a = n & 1;  
            n /= 2;  
            while (n-- > 0) {  
                a += b;  
                b += a;  
            }  
			return b;
		}
		return n;
	}	
	
	/**
	 * Achieve fibonacci sequence when n>92.
	 * @param n the length of fibonacci sequence
	 * @return a fibonacci sequence
	 */
    public BigInteger fibonacciBigN(int n) {  
        if (n > 92) {  
            int m = (n / 2) + (n & 1);  
            BigInteger fm = fibonacciBigN(m);  
            BigInteger fm_1 = fibonacciBigN(m - 1);  
            if ((n & 1) == 1) {  
                return fm.pow(2).add(fm_1.pow(2));  
            } else {  
                return fm_1.shiftLeft(1).add(fm).multiply(fm);  
            }  
        }  
        return BigInteger.valueOf(fibonacciFunction(n));  
    }

}
