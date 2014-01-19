import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
//CPU
public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	// TODO Auto-generated method stub]
		Date date0= new Date();
		long d0 = date0.getTime();
		  int side = 10;
			int m1_rows =side;
			int m1_cols = side;
			int m2_rows = side;
			int m2_cols = side;
	
	//m1
	long[][] m1 = new long[m1_rows][m1_cols];
	long[][] m2 = new long[m2_rows][m2_cols];
	long count = 0;
	for (int a = 0 ; a<m1_rows ; a++){
		for (int b = 0 ; b<m1_cols ; b++){
			count++;
			m1[a][b] = count;
			m2[a][b] = count;
		}
		
		
	}
	
	
	//long[][] result_matrix = new long[m1_cols][m2_rows];
	
      //splitting
	  long [][][] matrix_mult_couples = new long[m1_rows*m2_cols][2][ m1_rows];
	  for (int e = 0; e< m1_rows; e++){
		  
		  for (int d = 0 ; d<m2_cols; d++){
			  matrix_mult_couples[e*m1_rows + d][0]= m1[e];
			  long[] temp = new long [m2_rows];
			  for (int f = 0 ; f<m2_rows; f++){
				  temp[f] = m2[f][d];
			  }	
			  matrix_mult_couples[e*m1_rows + d][1] = temp;
			  
		  }
	  }
	  Farm_CPU f = new Farm_CPU();
		f.sourceArray = matrix_mult_couples;
		f.workers = 4;
		
		List<Object> list_object= f.main();
		Date date1= new Date();
		long d1 = date1.getTime();
		System.out.println(d1-d0);
        
        //print result
       for(int i = 0;i<list_object.size();i++ ){
    	   
        	long [][] result_2D= (long [][])(list_object.get(i));
        	for(int j= 0;j<result_2D.length;j++ ){
        		long[] result_1D = result_2D[j];
        		System.out.println();
        		for(int k= 0;k<result_1D.length;k++ ){
        			if(result_1D[k]!=0)
        			System.out.print(result_1D[k] +",");	
        		}
        		
        	}
        	
        }
}
	
}