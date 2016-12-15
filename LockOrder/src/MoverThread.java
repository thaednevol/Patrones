
public class MoverThread extends Thread {
	  private Directory dir1;
	  private Directory dir2;
	  private FileSysUtils fs;

	 MoverThread(Directory dir1, Directory dir2, FileSysUtils fs ) {
	    this.dir1 = dir1;
	    this.dir2 = dir2;
	    this.fs = fs;
	  }

	  public void run() {
	    this.fs.moveContents(dir1, dir2);
	  }
	}
