package test;

import java.util.*;

public class AllCandidates {
  private Vector<Candidate> data;
  public AllCandidates() {
    initialize();
  }

  private void initialize() {
    /*
      Get data from db.
     */
    data = new Vector<Candidate>();
    FileUtil util = new FileUtil();

    Vector<?> dataLines = util.fileToVector("Candidates.txt");
    for (int i = 0; i < dataLines.size(); i++) {
      String str = (String) dataLines.elementAt(i);
      StringTokenizer st = new StringTokenizer(str, ",");
      data.add(
        new Candidate(st.nextToken(), st.nextToken(),
                      st.nextToken()));
    }
  }

  public Enumeration<Candidate> getAllCandidates() {
    return data.elements();
  }
  public Iterator<?> getCertifiedCandidates(String type) {
    return new CertifiedCandidates(this, type);
  }

}


