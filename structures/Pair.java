package computils.structures;

public class Pair<A extends Comparable, B extends Comparable> implements Comparable<Pair<A, B>>{

	public A f;
	public B s;

	public Pair(A f, B s){
		this.f = f;
		this.s = s;
	}
	@Override
	public int compareTo(Pair<A, B> o){

		if(o.f == this.f) {
			return this.s.compareTo(o.s);
		} else {
			return this.f.compareTo(o.f);
		}
	}

	@Override
	public String toString(){
		return "[" + f.toString() + ", "  + s.toString() + "]";
	}

}
