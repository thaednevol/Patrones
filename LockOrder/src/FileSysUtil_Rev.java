public class FileSysUtil_Rev implements FileSysUtils{


  public void moveContents(Directory src, Directory dest) {
    if (src.hashCode() > dest.hashCode()) {
      synchronized (src) {
        synchronized (dest) {
          System.out.println("Contents Moved Successfully" );
        }
      }
    } else {
      synchronized (dest) {
        synchronized (src) {
          System.out.println("Contents Moved Successfully");
        }
      }
    }
  }
}
