//package computils.structures;

public class SegmentTree {
  private SegmentTree lTree, rTree;
  private int lIdx, rIdx;
  private int min, sum;
  private int lazy;

  public SegmentTree(int l, int r, int arr[]){
    lIdx = l;
    rIdx = r;
    lazy = 0;
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
    if(lazy != 0){
      min += lazy;
      if(lTree != null){
        lTree.lazyPush(lazy);
      }
      if(rTree != null){
        rTree.lazyPush(lazy);
      }
      lazy = 0;
    }
    if(l > rIdx || r < lIdx){
      return Integer.MAX_VALUE;
    }
    if(l <= lIdx && r >= rIdx){
      return min;
    }
    return Math.min(lTree.minRangeQuery(l, r), rTree.minRangeQuery(l, r));
  }

  public int sumRangeQuery(int l, int r){
    if(lazy != 0){
      sum += (lazy * (r - l) + 1);
      if(lTree != null){
        lTree.lazyPush(lazy);
      }
      if(rTree != null){
        rTree.lazyPush(lazy);
      }
      lazy = 0;
    }
    if(l > rIdx || r < lIdx){
      return 0;
    }
    if(l <= lIdx && r >= rIdx){
      return sum;
    }
    return lTree.sumRangeQuery(l, r) + rTree.sumRangeQuery(l, r);
  }

  public void lazyPush(int val){
    lazy += val;
  }

  public void rangeUpdate(int l, int r, int inc){
    if(l > rIdx || r < lIdx){
      return;
    }
    if(l <= lIdx && r >= rIdx){
      sum += (inc * (r - l) + 1);
      min += inc;
      if(lTree != null){
        lTree.lazyPush(inc);
      }
      if(rTree != null){
        rTree.lazyPush(inc);
      }
    } else {
      lTree.rangeUpdate(l, r, inc);
      rTree.rangeUpdate(l, r, inc);
      min = Math.min(lTree.getMin(), rTree.getMin());
      sum = lTree.getSum() + rTree.getSum();
    }
  }

  public void pointUpdate(int pos, int elem){
    if(lIdx != rIdx){
      if(pos <= (rIdx + lIdx)/2){
        lTree.pointUpdate(pos, elem);
      } else {
        rTree.pointUpdate(pos, elem);
      }
      min = Math.min(lTree.getMin(), rTree.getMin());
      sum = lTree.getSum() + rTree.getSum();
    } else {
      min = elem;
      sum = elem;
    }
  }

}
