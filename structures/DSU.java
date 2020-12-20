class DSU {
  private int[] adj;
  private int[] maxes;
  private int[] sizes;

  public DSU(int size){
    adj = new int[size];
    maxes = new int[size];
    sizes = new int[size];
    for(int idx = 0; idx < size; idx++){
      adj[idx] = maxes[idx] = idx;
      sizes[idx] = 1;
    }
  }

  public int find(int idx){
    while(adj[idx] != idx){
      idx = adj[idx];
    }
    return idx;
  }

  public int getSize(int idx){
    return sizes[find(idx)];
  }

  public int getMax(int idx){
    return maxes[find(idx)];
  }

  public void merge(int a, int b){
    int aHead = find(a);
    int bHead = find(b);
    if(aHead == bHead){
      return;
    }
    if(sizes[bHead] > sizes[aHead]){
      adj[aHead] = bHead;
      maxes[bHead] = Math.max(maxes[aHead], maxes[bHead]);
      sizes[bHead] += sizes[aHead];
    } else {
      adj[bHead] = aHead;
      maxes[aHead] = Math.max(maxes[aHead], maxes[bHead]);
      sizes[aHead] += sizes[bHead];
    }
  }

}
