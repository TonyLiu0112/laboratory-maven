package com.liuboyu.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		List<Model> list = new ArrayList<Model>(){{
			add(new Model(7, 4, "6s"));
			add(new Model(1, 2, "1s"));
			add(new Model(2, 2, "3s"));
			add(new Model(7, 3, "7s"));
			add(new Model(2, 1, "2s"));
			add(new Model(1, 5, "0s"));
			add(new Model(2, 7, "8s"));
			add(new Model(1, 9, "1s"));
		}};
		Collections.sort(list,new Comparator<Model>() {

			public int compare(Model o1, Model o2) {
				if (o1.getId() > o2.getId())
					return 1;
				else 
					return 0;
			}
			
		});
		
		Collections.sort(list,new Comparator<Model>() {

			public int compare(Model o1, Model o2) {
				if (o1.getId()==o2.getId() && o1.getCount() > o2.getCount())
					return 1;
				else 
					return 0;
			}
			
		});
		
		for (Model model : list) {
			System.out.println("id:" + model.getId() + " count:" + model.getCount());
		}
	}
	
}
