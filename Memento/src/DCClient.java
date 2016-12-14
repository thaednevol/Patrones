public class DCClient {

  public static void main(String[] args) {
	  DataConverter objConverter = new DataConverter();
	  MementoHandler objMementoHandler = new MementoHandler();
    

    objConverter.setMemento(objMementoHandler.getMemento());

    if (!(objConverter.process())) {
      System.out.println("Description: Invalid data - " +
                         "Process Stopped");
      System.out.println("Please correct the Data and " +
                         "Run the Application Again");
      objMementoHandler.setMemento(
        objConverter.createMemento());
    }
  }
}
