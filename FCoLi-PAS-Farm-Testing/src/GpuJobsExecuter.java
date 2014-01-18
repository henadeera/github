import java.util.ArrayList;
import java.util.List;
import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.Rootbeer;

public class GpuJobsExecuter {
  public int[][] execute(List<int[]> srcArraysList) {
    List<Kernel> jobs = new ArrayList<Kernel>();
    int[][] ret = new int[srcArraysList.size()][srcArraysList.get(0).length];
    for(int i = 0; i < srcArraysList.size(); i++) 
      jobs.add(new PrimeNumbers_GPU(srcArraysList.get(i), ret, i));
    Rootbeer rootbeer = new Rootbeer();
    rootbeer.runAll(jobs);
    return ret;
  }
}