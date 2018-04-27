package com.softline.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream {
	public static void main(String[] args) {
		//”≥…‰
		List<String> words = Arrays.asList("JAVA 8","Lambdas","In","Action");
		List<Integer> wordsLengths = words.stream().map(String::length).collect(Collectors.toList());
		for (int i = 0; i < wordsLengths.size(); i++) {
			System.out.println(wordsLengths.get(i));
		}
		System.out.println("-------------");
		int[] a = {1,2,3};
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		List<Integer> list2 = list.stream().map(n->n*n).collect(Collectors.toList());
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
		System.out.println("-------------");
		List<Integer> list3 = Arrays.asList(1,2,3);
		
		List<Integer> list4 = Arrays.asList(4,5);
		
		List<int[]> pairs = list3.stream().flatMap(i->list4.stream()
				.filter(j->(i+j)%3 == 0)
				.map(j->new int[] {i,j}))
				.collect(Collectors.toList());
		for (int i = 0; i < pairs.size(); i++) {
			System.out.println(pairs.get(i));
		}
		
		System.out.println("-------------findFirst");
		
		List<Integer> someNumber = Arrays.asList(1,2,3,4,5);
		Optional<Integer> fisrtSquareDivibleByThree = 
				someNumber.stream().map(x->x*x).filter(x->x%3 == 0 )
				.findFirst();
		System.out.println(fisrtSquareDivibleByThree.isPresent());		
		
		System.out.println("-------------reduce");
		
		int sum = someNumber.stream().reduce(0, (e,f)->e+f);
		int sum1 = someNumber.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		System.out.println(sum1);
		
		
		
		
		
		
		
		
		
		
		
	}
}
