//package provideAIDRandom;

public class ProvideAIDRandom {
	public String ProvideAID(String input){
		int numberPart;
		String numberPartString = new String();
		StringBuffer output = new StringBuffer();
		
		for(int i = 0 ; i < input.length() ; i++){
			if(input.charAt(i) >= '0' && input.charAt(i) <= '9'){
				numberPartString = input.substring(i,input.length());
				break;
			}
		}

		numberPart = Integer.parseInt(numberPartString.toString());
		if(numberPart <= 9999 && numberPart >= 0){
			numberPart ++;
			numberPartString = Integer.toString(numberPart);
			if(numberPart >= 1000){ 
				output.append("ACC" + numberPartString);
			}
			else if(numberPart <= 9){
				output.append("ACC000" + numberPartString);	
			}
			else if(numberPart >=10 && numberPart <= 99){
				output.append("ACC00" + numberPartString);	
			}
			else if(numberPart >= 100 && numberPart <= 999 ){
				output.append("ACC0" + numberPartString);	
			}
			return output.toString();
		}
		return input;
	}

	public static void main(String[] args) {
		ProvideAIDRandom test = new ProvideAIDRandom();
		System.out.println(test.ProvideAID("ACC1000"));
	}
}