package computils;
import java.io.*;

public class FastScanner {
  private BufferedReader bf;
  private InputStreamReader read;

  //stream can be System.in, a FileInputStream, etc..
  public FastScanner(InputStream stream){
    read = new InputStreamReader(stream);
    bf = new BufferedReader(read);
  }
  /*
    Gives the next token in the buffer
  */
  public String next(){
    String res = "";
    try {
      int read = bf.read();
      //32 is whitespace, -1 EOF, 10 line feed
      while(read == 32 || read == 10){
        read = bf.read();
      }
      while(read != 10 && read != 32 && read != -1){
        res += Character.toString((char)read);
        read = bf.read();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }
  public long nextLong(){
    return Long.parseLong(next());
  }

  public boolean hasNext(){
    try {
      return bf.ready();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public int[] getArrInt(int numToks){
    int res[] = new int[numToks];
      try {
        for(int idx = 0; idx < numToks; idx++){
          res[idx] = nextInt();
        }
        return res;
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
