import java.util.ArrayList;
import java.util.List;
import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.Rootbeer;

public class CpuJobsExecuter {
  public int[][] execute(int[] srcArray) {
    int[][] ret = new int[1][srcArray.length];
    PrimeNumbers_CPU cpuApplication = new PrimeNumbers_CPU(srcArray, ret, 0);
    cpuApplication.compute();
    return ret;
  }
}