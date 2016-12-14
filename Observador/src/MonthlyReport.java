import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MonthlyReport extends JFrame implements Observer {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static final String newline = "\n";

 // private JPanel panel;
  private JLabel lblTransactions;
  private JTextArea taTransactions;
  private ReportManager objReportManager;


  public MonthlyReport(ReportManager inp_objReportManager)
  throws Exception {
    super("Observer Pattern - Example");
    objReportManager = inp_objReportManager;

    // Create controls
   // panel = new JPanel();
    taTransactions = new JTextArea(5, 40);
    taTransactions.setFont(new Font("Serif", Font.PLAIN, 14));
    taTransactions.setLineWrap(true);
    taTransactions.setWrapStyleWord(true);

    //Create Labels
    lblTransactions =
      new JLabel("Current Month Transactions");

    //For layout purposes, put the buttons in a separate panel
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(lblTransactions);
    buttonPanel.add(taTransactions);

    Container contentPane = getContentPane();
    contentPane.add(buttonPanel, BorderLayout.CENTER);
//    try {
//      UIManager.setLookAndFeel(new WindowsLookAndFeel());
//      SwingUtilities.updateComponentTreeUI(
//        MonthlyReport.this);
//    } catch (Exception ex) {
//      System.out.println(ex);
//    }

    setSize(400, 300);
    setVisible(true);
    objReportManager.register(this);

  }

  public void refreshData(Observable subject) {
    if (subject == objReportManager) {
      //get subject's state
      String department = objReportManager.getDepartment();

      lblTransactions.setText(
        "Current Month Transactions - " +
        department);
      Vector<?> trnList =
        getCurrentMonthTransactions(department);
      String content = "";
      for (int i = 0; i < trnList.size(); i++) {
        content = content +
                  trnList.elementAt(i).toString() + "\n";
      }
      taTransactions.setText(content);
    }
  }
  private Vector<String> getCurrentMonthTransactions(String department
                                             ) {
    Vector<String> v = new Vector<String>();
    FileUtil futil = new FileUtil();
    Vector<?> allRows = futil.fileToVector("Transactions.dat");

    //current month
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int month = cal.get(Calendar.MONTH) + 1;

    String searchStr = department + "," + month + ",";
    int j = 1;
    for (int i = 0; i < allRows.size(); i++) {
      String str = (String) allRows.elementAt(i);
      if (str.indexOf(searchStr) > -1) {

        StringTokenizer st =
          new StringTokenizer(str, ",");
        st.nextToken();//bypass the department
        str = "   " + j + ". " + st.nextToken() + "/" +
              st.nextToken() + "~~~" +
              st.nextToken() + "Items" + "~~~" +
              st.nextToken() + " Dollars";
        j++;
        v.addElement(str);
      }
    }
    return v;
  }
}// end of class

