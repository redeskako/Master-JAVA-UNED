package utils;

import java.util.ArrayList;
import java.util.List;

public class Limiter {

	public static <T> List<T> limit(List<T> inputList,int page,int elementsByPage){
		List<T> out = new ArrayList<T>();
		
		int initialPosition = page * elementsByPage;
		
		for(int i= initialPosition; i < inputList.size() && i < elementsByPage;i++){
			out.add(inputList.get(i));
		}
		
		return out;
	}
}
