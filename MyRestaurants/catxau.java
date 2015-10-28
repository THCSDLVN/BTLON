	public String[] stringTokenizer(String input){
		int i,counter = 0;
		StringTokenizer token = new StringTokenizer(input,",");
		for(i = 0 ; i < input.length() ; i++){
			if(input.charAt(i) == ','){
				counter++;
			}
		}
		String stringResultArray[] = new String[counter + 1];
		counter = 0;
		while(token.hasMoreTokens()){
			stringResultArray[counter++] = new String(token.nextToken());
		}
		return stringResultArray;
	}