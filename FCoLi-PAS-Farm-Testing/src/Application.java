abstract class Application {
  int[] source;
  int[][] ret;
  int index;
  public void compute() {
    int count = 0;
    for(int a = 0; a < source.length; a++) {
      if(source[a] % 2 != 0) {
        for(int j = 3; j < (int)source[a]; j = j + 2) {
          if((int)source[a] % j == 0) {
            break ;
          }
          if(source[a] == j + 2) {
            ret[index][count] = source[a];
            count++;
          }
        }
      }
    }
  }
}