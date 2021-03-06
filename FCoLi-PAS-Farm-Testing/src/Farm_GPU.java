import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

abstract class Farm_GPU {
  int workers;
  int[] sourceArray;
  public List<int[]> split() {
    int arrayLength = sourceArray.length;
    int lengthToSplit;
    if((arrayLength % workers != 0)) 
      lengthToSplit = arrayLength / workers + 1;
    else 
      lengthToSplit = (arrayLength / workers);
    List<int[]> sourceArrayList = new ArrayList<int[]>();
    for(int i = 0; i < arrayLength; i = i + lengthToSplit) {
      if(arrayLength < i + lengthToSplit) {
        lengthToSplit = arrayLength - i;
      }
      int[] val = new int[lengthToSplit];
      for(int j = 0; j < lengthToSplit; j++) 
        val[(i + j) % lengthToSplit] = sourceArray[i + j];
      sourceArrayList.add(val);
    }
    return sourceArrayList;
  }
  public List<Object> join(ArrayList<Future<Object>> futures) throws InterruptedException, ExecutionException {
    List<Object> result = new ArrayList<Object>();
    for(int i = 0; i < futures.size(); i++) {
      result.add(futures.get(i).get());
    }
    return result;
  }
  public List<Object> main() throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(workers);
    ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
    final List<int[]> splittedArrays = split();
    futures.add(executor.submit(new Callable<Object>() {
        @Override() public Object call() throws Exception {
          Object retObjArray = callExecuter(splittedArrays);
          return retObjArray;
        }
    }));
    executor.shutdown();
    while(!executor.isTerminated()){
    }
    return join(futures);
  }
  public Object callExecuter(List<int[]> srcObjArray) {
    GpuJobsExecuter gpuJobsExecuter = new GpuJobsExecuter();
    return gpuJobsExecuter.execute(srcObjArray);
  }
}