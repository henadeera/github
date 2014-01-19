import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
					
    int size = 32;
		int [] intArray = new int [size] ;
		List <int[]> l = new ArrayList<int[]>();
		
		for (int i = 0; i<intArray.length;i++){
			//preparing data
			intArray[i] = i+100000;
            
		}
		l.add(intArray);
			
		Date date0= new Date();
		long d = date0.getTime();
		
		//computation
		GpuJobsExecuter gpuJobsExecuter = new GpuJobsExecuter();
		int [][] c = gpuJobsExecuter.compute(l,size,2,2*size);
		
		 Date date1= new Date();
		 long d2 = date1.getTime();
		 
		 System.out.println("Time taken to gpu execution: " + Long.toString((d2-d))+" ms");
		 
		 //printing
		for (int  i = 0 ; i<c.length ;i++ ){
			System.out.println();
			for(int j=0;j<c[i].length;j++)
			   System.out.print(c[i][j] +",");
		}
				
	}
}
