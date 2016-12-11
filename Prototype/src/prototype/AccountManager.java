package src.prototype;

import java.io.*;
import java.util.*;

public class AccountManager {

  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    /*
     	Create Prototypical Objects
     */
    Vector<Permission> supervisorPermissions =
      getPermissionsFromFile("supervisor.txt");
    UserAccount supervisor = new UserAccount();
    supervisor.setPermissions(supervisorPermissions);

    Vector<Permission> accountRepPermissions =
      getPermissionsFromFile("accountrep.txt");
    UserAccount accountRep = new UserAccount();
    accountRep.setPermissions(accountRepPermissions);

    AccountPrototypeFactory factory =
      new AccountPrototypeFactory(supervisor,
          accountRep);

    /* 	Using protype objects to create other user accounts */

    
	UserAccount newSupervisor = factory.getSupervisor();
    newSupervisor.setUserName("PKuchana");
    newSupervisor.setPassword("Everest");
    System.out.println(newSupervisor);

    UserAccount anotherSupervisor = factory.getSupervisor();
    anotherSupervisor.setUserName("SKuchana");
    anotherSupervisor.setPassword("Everest");
    System.out.println(anotherSupervisor);

    UserAccount newAccountRep = factory.getAccountRep();
    newAccountRep.setUserName("VKuchana");
    newAccountRep.setPassword("Vishal");
    System.out.println(newAccountRep);
  }

  public static Vector<Permission> getPermissionsFromFile(String fileName) {
    Vector<Permission> v = new Vector<Permission>();
    String inputLine;
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      while ((inputLine = br.readLine()) != null) {
        StringTokenizer st =
          new StringTokenizer(inputLine, ",");
        String resource = st.nextToken();
        String permission = st.nextToken();
        v.addElement(
          new Permission(resource, permission));
      }
      br.close();
    } // Try
    catch (FileNotFoundException ex) {
      //
    }
    catch (IOException ex) {
      //
    }
    return (v);
  }

}
