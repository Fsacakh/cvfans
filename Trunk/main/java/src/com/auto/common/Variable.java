package com.auto.common;


public interface Variable {
	public enum Role implements IEnum<Role> {
		Driver(1), Station(2), Enterprise(3),Admin(4);
		
		public int code;
		
		private Role(int code){
			this.code = code;
		}

		@Override
		public Role get(int Code) {
			Role[] values = values();
			
			for(Role value : values){
				if(value.code == code)
					return value;
			}
			
			return null;
		}

		@Override
		public Role[] getValues() {
			return values();
		}
	};
	
	public enum YorN
	{
		YES("是",1),NO("否",0);
		
		public String name;
		public int value;
		private  YorN(String name,int value)
		{
			this.value=value;
			this.name=name;
			
		}
	}
	
	public enum Boolean{
		TRUE(1), FALSE(0);
		
		public int code;
		
		private Boolean(int code){
			this.code = code;
		}
	};
	
	public enum Guarantee{
		Noapplication(0), Application(1), Guaranteed(2),Unguaranteed(3);
		
		public int code;
		
		private Guarantee(int code){
			this.code = code;
		}
	};
}