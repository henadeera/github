import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

 class Farm_CPU {
  int workers;
  long[][][] sourceArray;
  public List<long[][][]> split() {
    int arrayLength = sourceArray.length;
    int lengthToSplit;
    if((arrayLength % workers != 0)) 
      lengthToSplit = arrayLength / workers + 1;
    else 
      lengthToSplit = (arrayLength / workers);
    List<long[][][]> sourceArrayList = new ArrayList<long[][][]>();
    for(int i = 0; i < arrayLength; i = i + lengthToSplit) {
      if(arrayLength < i + lengthToSplit) {
        lengthToSplit = arrayLength - i;
      }
      long[][][] val = new long[lengthToSplit][][];
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
	    List<long[][][]>  splittedArrays = split();
	    for(int i = 0; i < splittedArrays.size(); i++) {
	      final long[][][] srcObjArray = splittedArrays.get(i);
	      futures.add(executor.submit(new Callable<Object>() {
	          @Override() public Object call() throws Exception {
	            Object retObjArray = callExecuter(srcObjArray);
	            return retObjArray;
	          }
	      }));
	    }
	    executor.shutdown();
	    while(!executor.isTerminated()){
	    }
	    return join(futures);
	  }
  public Object callExecuter(long[][][] srcObjArray) {
    CpuJobsExecuter gpuJobsExecuter = new CpuJobsExecuter();
    return gpuJobsExecuter.execute(srcObjArray);
  }
}