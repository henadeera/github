

import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.RootbeerGpu;
//import edu.syr.pcpratts.rootbeer.runtime.RootbeerGpu;


public class PrimeNumbers_GPU extends Application implements Kernel {
	//int count=0;
  public PrimeNumbers_GPU(int[] source , int [][] ret , int i) {
 
    this.source = source;
    this.ret =ret;
    this.index = i;
   
    
  }
 
  public void gpuMethod() {
	  int[] s = source;
	  int l= s.length;
	  int[][] r = ret;
	  int i= index; 
	  int count=0;
	  
	  int a = RootbeerGpu.getThreadIdxx();
	  int b = RootbeerGpu.getBlockDimx();
    int c = RootbeerGpu.getBlockIdxx();
	    
	   //for(int k = 0; k<l ;k++){ 
	    	//int num = s[k];
      
      		int num = s[(b*c)+a];	
      		if (num< l) {
	    	if(num% 2!= 0 ) {  		
	    		for(int j = 3; j < num; j = j + 2) {
	    	        if(num % j == 0) {
	    	          break ;
	    	        }
	    	        if(num == j + 2){ 	        	
	    	        	r[i][count]= num;   	        	
	    	        	count++;    	        	
	    	        }		    		
	    		}
	    	}   
	    }
	    ret =r;
  }
 
}
