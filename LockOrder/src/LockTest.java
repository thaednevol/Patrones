public class LockTest {
  public static void main(String[] args) {
    Directory dir1  = new  Directory("origen");
    Directory dir2  = new  Directory("destino");
    
    //INCONSISTENTE
    FileSysUtil fs = new FileSysUtil();
    //CONSISTENTE
    //FileSysUtil_Rev fs = new FileSysUtil_Rev();
    MoverThread t1 = new MoverThread(dir1, dir2, fs);
    MoverThread t2 = new MoverThread(dir2, dir1, fs);
    t1.start();
    t2.start();
  }

}



