package ter;

public class DistanceHamming {

		// function to calculate Hamming distance
		static int hammingDist(String str1, String str2)
		{
		    int i = 0, count = 0;
		    while (i < str1.length())
		    {
		        if (str1.charAt(i) != str2.charAt(i))
		            count++;
		        i++;
		    }
		    return count;
		} 
		 
	
		
		 
		
}
