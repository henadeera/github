import edu.syr.pcpratts.rootbeer.runtime.Kernel;


public class MatrixMult_GPU extends Application implements Kernel {
  public MatrixMult_GPU(long[][][] source, long[][] ret, int i) {
    super();
    this.source = source;
    this.ret = ret;
    this.index = i;
  }
  @Override() public void gpuMethod() {
	  long[][][] s = source;
	  long[][] r = ret;
	  int ind = index;
	  for(int i = 0 ; i<s.length; i++){
			 long sum =0;
			 for(int j = 0;j<s[i][0].length;j++){
				   sum = sum + s[i][0][j]*s[i][1][j];
				    
			}
			 r[ind +(i/r.length)][i%r.length]  = sum ;
			 
		 }
	  ret= r;
  }
}