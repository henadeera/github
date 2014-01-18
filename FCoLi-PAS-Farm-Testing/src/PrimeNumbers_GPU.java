import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import java.util.ArrayList;

public class PrimeNumbers_GPU extends Application implements Kernel {
  public PrimeNumbers_GPU(int[] source, int[][] ret, int i) {
    super();
    this.source = source;
    this.ret = ret;
    this.index = i;
  }
  @Override() public void gpuMethod() {
    compute();
  }
}