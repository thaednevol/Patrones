import java.util.*;


public class DirComponent extends FileSystemComponent {
  Vector<FileSystemComponent> dirContents = new Vector<FileSystemComponent>();
  //individual files/sub folders collection

  public DirComponent(String cName) {
    super(cName);
  }

  public void addComponent(FileSystemComponent fc)
  throws CompositeException {
    dirContents.add(fc);
  }

  public FileSystemComponent getComponent(int location)
  throws CompositeException {
    return dirContents.elementAt(
             location);
  }

  public long getComponentSize() {
    long sizeOfAllFiles = 0;
    Enumeration<FileSystemComponent> e = dirContents.elements();
    while (e.hasMoreElements()) {
      FileSystemComponent component =
        e.nextElement();
      sizeOfAllFiles = sizeOfAllFiles +
                       (component.getComponentSize());
    }
    return sizeOfAllFiles;
  }

} // End of class

