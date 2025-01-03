package com.example.tabelog.util;

import lombok.Data;

@Data
public class LombokTest {
	 private String name;
	    private int age;
	    
	    public static void main(String[] args) {
	    	LombokTest test = new LombokTest();
	        test.setName("Test");
	        test.setAge(30);
	        System.out.println(test.getName());
	        System.out.println(test.getAge());
	    }
}
