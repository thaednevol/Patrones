import java.util.*;

public class CodeBookCypher implements EncryptionStrategy {

  HashMap<String, String> codeContents = new HashMap<String, String>();

  private void populateCodeEntries() {
    codeContents.put("This","Design");
    codeContents.put("is","Patterns");
    codeContents.put("a","are");
    codeContents.put("true","really");
    codeContents.put("statement","useful");
    //.........
    //........
  }

  public String encrypt(String inputData) {

    populateCodeEntries();

    String outStr = "";

    StringTokenizer st = new StringTokenizer(inputData);
    while (st.hasMoreTokens()) {
      outStr = outStr + " " +
               codeContents.get(st.nextToken());
    }

    return new String(outStr);
  }

}
