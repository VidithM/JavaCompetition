package computils.structures;

public class SegmentTree {
  private SegmentTree lTree, rTree;
  private int lIdx, rIdx;
  private int min, sum;

  public SegmentTree(int l, int r, int arr[]){
    lIdx = l;
    rIdx = r;
    if(l == r){
      min = arr[l];
      sum = arr[l];
    } else {
      lTree = new SegmentTree(l, (r + l)/2, arr);
      rTree = new SegmentTree((r + l)/2 + 1, r, arr);
      min = Math.min(lTree.getMin(), rTree.getMin());
      sum = lTree.getSum() + rTree.getSum();
    }
  }

  public int getMin(){
    return min;
  }

  public int getSum(){
    return sum;
  }

  public int minRangeQuery(int l, int r){
    if(l > rIdx || r < lIdx){
      return Integer.MAX_VALUE;
    }
    if(l <= lIdx && r >= rIdx){
      return min;
    }
    return Math.min(lTree.minRangeQuery(l, r), rTree.minRangeQuery(l, r));
  }

  public int sumRangeQuery(int l, int r){
    if(l > rIdx || r < lIdx){
      return 0;
    }
    if(l <= lIdx && r >= rIdx){
      return sum;
    }
    return lTree.sumRangeQuery(l, r) + rTree.sumRangeQuery(l, r);
  }

  public void update(int pos, int elem){
    if(lIdx != rIdx){
      if(pos <= (rIdx + lIdx)/2){
        lTree.update(pos, elem);
      } else {
        rTree.update(pos, elem);
      }
      min = Math.min(lTree.getMin(), rTree.getMin());
      sum = lTree.getSum() + rTree.getSum();
    } else {
      min = elem;
      sum = elem;
    }
  }

}
