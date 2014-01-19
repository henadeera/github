


public class MatrixMult_CPU extends Application  {
  public MatrixMult_CPU(long[][][] source, long[][] ret, int i) {
    super();
    this.source = source;
    this.ret = ret;
    this.index = i;
  }
  public void compute() {
	  for(int i = 0 ; i<source.length; i++){
			 long sum =0;
			 for(int j = 0;j<source[i][0].length;j++){
				   sum = sum + source[i][0][j]*source[i][1][j];
				    
			}
			 ret[index +(i/ret.length)][i%ret.length]  = sum ;
			 
		 }
  }
}