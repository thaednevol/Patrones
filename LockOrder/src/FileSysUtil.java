public class FileSysUtil implements FileSysUtils{

  public void moveContents(Directory src, Directory dest) {
    synchronized (src) {
      synchronized (dest) {
        System.out.println("Contents Moved Successfully");
      }
    }
  }
}
