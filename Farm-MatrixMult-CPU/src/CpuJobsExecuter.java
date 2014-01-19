import java.util.ArrayList;
import java.util.List;


public class CpuJobsExecuter {
  public long [][] execute(long[][][] srcArray ){
	long [][] ret = new long [10][10];
	
    MatrixMult_CPU cpuApplication = new MatrixMult_CPU(srcArray, ret,0);
  
    cpuApplication.compute();
    return ret;
  }
}