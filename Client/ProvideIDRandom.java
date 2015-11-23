package Client.provideIDRandom;

import java.util.List;
import java.util.Iterator;

public class ProvideIDRandom {
	public String ProvideIDRandom(List<List<String>> listInput, String typeID){
		int capacity = 0;
		StringBuffer newID = new StringBuffer();
		String numberPart = new String();

		newID.append(typeID);

		for (List<String> innerLs : listInput) {
			capacity++;
		}

		int arrayID[] = new int[capacity];
		capacity = 0;
		for(List<String> innerLs : listInput) {
			for (String str : innerLs) {
				arrayID[capacity++] = Integer.parseInt(str.substring(4, str.length()));
			}
		}
		for(int i = 0 ; i <= 9999 ; i++){
			try{
				binarySearch(arrayID,i,0,arrayID.length - 1);
			}
			catch(ItemNotFound inf){
				numberPart = Integer.toString(i);
				if(i < 10){
					newID.append("000");
				}
				else if(i >= 10 && i <= 99){
					newID.append("00");
				}
				else if(i >=100 && i <= 999){
					newID.append("0");
				}
				newID.append(numberPart);
				break;
			}
		}
		return newID.toString();
	}
	
	private int binarySearch(int [] a, int x, int low, int high) throws ItemNotFound{
		// low: index of the low value in the subarray
		// high: index of the highest value in the subarray
		
		// Base case 1: item not found
		if (low > high){
			throw new ItemNotFound("Not Found");
		}
		// Base case 2: item found
		int mid = (low + high) / 2;
		if (x > a[mid]){
			return binarySearch(a, x, mid + 1, high);
		}
		else if (x < a[mid]){
			return binarySearch(a, x, low, mid - 1);
		}
		else{
			return mid;
		}
	}
}

class ItemNotFound extends Exception{
	public ItemNotFound(String msg){
		super(msg);
	}
}