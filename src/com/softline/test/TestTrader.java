package com.softline.test;

import java.util.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TestTrader {

	public static void main(String[] args) {
		Trader raoul = new Trader("Rauol", "Cambridge");
		
		Trader mario = new Trader("Mario", "Milan");
		
		Trader alan = new Trader("Alan", "Cambridge");
		
		Trader brian = new Trader("Brian", "Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2013, 700),
				new Transaction(alan, 2012, 950)
				
		);
		System.out.println("---找出2011年所有交易并按交易额排序---");
		List<Transaction> result = transactions.stream().filter(transaction->transaction.getYear()==2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		for (Transaction item : result) {
			System.out.println(item.toString());
		}
		
		System.out.println("---交易员都在哪些不同的城市工作过---");
		List<String> cities = transactions.stream().map(transaction->transaction.getTrader().getCity())
				.distinct()
				.collect(toList());
		for (String item : cities) {
			System.out.println(item);
		}
		
		
		System.out.println("---c查找所有来自剑桥的交易员，并按照姓名排序---");
		List<Trader> traders = transactions.stream().
				filter(transaction->transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getTrader).sorted(comparing(Trader::getName))
				.collect(toList());
		//先map再filter
		List<Trader> traders2 = transactions.stream().
				map(Transaction::getTrader)
				.filter(trader->trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
		for (Trader item : traders) {
			System.out.println(item.getName());
		}
		
		System.out.println("---返回所有交易员的姓名字符串，按字母顺序排序---");
		String traderstr = transactions.stream()
				.map(transaction->transaction.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("",(a,b)->a+b);
		System.out.println(traderstr);
		
		
		System.out.println("---有没有交易员是在米兰工作的---");
		boolean ismilan = transactions.stream()
				.anyMatch(transaction->transaction.getTrader().getCity().equals("Milan"));
		System.out.println(ismilan);
		
		System.out.println("---打印生活在剑桥的交易员的所有交易额---");
		//使用forEach
		transactions.stream()
				.filter(transaction->transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.forEach(System.out::println);
		
		System.out.println("---所有交易中，最高的交易额是多少---");
		Optional<Integer> highestValue = transactions.stream()
											.map(Transaction::getValue)
											.reduce(Integer::max);
		System.out.println(highestValue.get());
		
		System.out.println("---找到交易额最小的交易---");
		
		Optional<Transaction> smallestTransaction = transactions.stream()
								.reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2);
		
		//或者这样写
		Optional<Transaction> smallestTransaction2 = transactions.stream()
								.min(comparing(Transaction::getValue));
		
		
		
		
		
	}


}
