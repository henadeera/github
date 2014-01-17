
import java.util.ArrayList;
import java.util.List;

import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.Rootbeer;

public class GpuJobsExecuter {
  public GpuJobsExecuter(){}
  public int[][] compute(List<int [] >srcArraysList,int a,int b,int c ) {
    List<Kernel> jobs = new ArrayList<Kernel>();
    int[][]ret = new int [srcArraysList.size()][srcArraysList.get(0).length];
    
    for(int i = 0 ; i<srcArraysList.size();i++)
      jobs.add(new PrimeNumbers_GPU(srcArraysList.get(i), ret,i));
    
    Rootbeer rootbeer = new Rootbeer();
    //rootbeer.setThreadConfig(a, c);
    rootbeer.setThreadConfig(a,b,c); //Rootbeer-1.0.51
    rootbeer.runAll(jobs);
    return ret;
  }
}
