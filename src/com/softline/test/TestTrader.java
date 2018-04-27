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
		System.out.println("---�ҳ�2011�����н��ײ������׶�����---");
		List<Transaction> result = transactions.stream().filter(transaction->transaction.getYear()==2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		for (Transaction item : result) {
			System.out.println(item.toString());
		}
		
		System.out.println("---����Ա������Щ��ͬ�ĳ��й�����---");
		List<String> cities = transactions.stream().map(transaction->transaction.getTrader().getCity())
				.distinct()
				.collect(toList());
		for (String item : cities) {
			System.out.println(item);
		}
		
		
		System.out.println("---c�����������Խ��ŵĽ���Ա����������������---");
		List<Trader> traders = transactions.stream().
				filter(transaction->transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getTrader).sorted(comparing(Trader::getName))
				.collect(toList());
		//��map��filter
		List<Trader> traders2 = transactions.stream().
				map(Transaction::getTrader)
				.filter(trader->trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
		for (Trader item : traders) {
			System.out.println(item.getName());
		}
		
		System.out.println("---�������н���Ա�������ַ���������ĸ˳������---");
		String traderstr = transactions.stream()
				.map(transaction->transaction.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("",(a,b)->a+b);
		System.out.println(traderstr);
		
		
		System.out.println("---��û�н���Ա��������������---");
		boolean ismilan = transactions.stream()
				.anyMatch(transaction->transaction.getTrader().getCity().equals("Milan"));
		System.out.println(ismilan);
		
		System.out.println("---��ӡ�����ڽ��ŵĽ���Ա�����н��׶�---");
		//ʹ��forEach
		transactions.stream()
				.filter(transaction->transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.forEach(System.out::println);
		
		System.out.println("---���н����У���ߵĽ��׶��Ƕ���---");
		Optional<Integer> highestValue = transactions.stream()
											.map(Transaction::getValue)
											.reduce(Integer::max);
		System.out.println(highestValue.get());
		
		System.out.println("---�ҵ����׶���С�Ľ���---");
		
		Optional<Transaction> smallestTransaction = transactions.stream()
								.reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2);
		
		//��������д
		Optional<Transaction> smallestTransaction2 = transactions.stream()
								.min(comparing(Transaction::getValue));
		
		
		
		
		
	}


}
