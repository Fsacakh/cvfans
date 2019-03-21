package com.auto.common;

import java.util.ArrayList;
import java.util.List;

import com.app.base.common.NameValuePair;
import com.auto.common.Variable.YorN;

public enum VariableList {
	Status{
		@Override
		public List<NameValuePair> getValues() {
			List<NameValuePair> options = new ArrayList<NameValuePair>();
			return options;
		}
	},
	
	YorN
	{
		@Override
		public List<NameValuePair> getValues() {
			List<NameValuePair> options = new ArrayList<NameValuePair>();
			for(YorN yOrN :Variable.YorN.values() ){
				options.add(new NameValuePair(yOrN.name,String.valueOf(yOrN.value)));
			}
			return options;
		}
	};
	
	protected abstract List<NameValuePair> getValues();
	
	public static List<NameValuePair> getValues(String category){
		for(VariableList list : VariableList.values()){
			if(list.toString().equalsIgnoreCase(category)){
				return list.getValues();
			}
		}
		
		return null;
	}
}