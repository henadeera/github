import java.util.ArrayList;
import java.util.List;
import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.Rootbeer;

public class GpuJobsExecuter {
  public long[][] execute(List<long[][][]> srcArraysList) {
    List<Kernel> jobs = new ArrayList<Kernel>();
    long [][] ret = new long [750][750];
    for(int i = 0; i < srcArraysList.size(); i++) 
      jobs.add(new MatrixMult_GPU(srcArraysList.get(i), ret, i));
    Rootbeer rootbeer = new Rootbeer();
    rootbeer.setThreadConfig(500, 1, 500);
    rootbeer.runAll(jobs);
    return ret;
  }
}