import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SearchManager extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String GET_SQL = "Get SQL";
  public static final String EXIT = "Exit";
  public static final String CANDIDATE_SRCH = 
    "Candidate Search";

  public static final String EMPLOYER_SRCH = "Employer Search";

  public static final String BLANK = "";

  private JComboBox<String> cmbSearchType;
  private JPanel pSearchCriteria;
  private JTextArea txtSQL;

  public SearchManager() throws Exception {
    super("Builder Pattern - Example");

    // Create controls
    cmbSearchType = new JComboBox<String>();
    txtSQL =
      new JTextArea(
        " The SQL statement will be displayed here.", 5, 25);
    txtSQL.setFont(new Font("Serif", Font.ITALIC, 12));
    txtSQL.setLineWrap(true);
    txtSQL.setWrapStyleWord(true);

    pSearchCriteria = new JPanel();

    cmbSearchType.addItem(SearchManager.BLANK);
    cmbSearchType.addItem(SearchManager.CANDIDATE_SRCH);
    cmbSearchType.addItem(SearchManager.EMPLOYER_SRCH);

    //Create Labels
    JLabel lblSearchType = new JLabel("Search:");
    JLabel lblWhereClause = new JLabel("SQL:");
    JLabel lblSearchCriteria =
      new JLabel("Search Criteria:");

    //Create the open button
    JButton btnGetWhereClause =
      new JButton(SearchManager.GET_SQL);
    btnGetWhereClause.setMnemonic(KeyEvent.VK_G);
    JButton btnExit = new JButton(SearchManager.EXIT);
    btnExit.setMnemonic(KeyEvent.VK_X);

    buttonHandler vf = new buttonHandler(this);

    btnGetWhereClause.addActionListener(vf);
    btnExit.addActionListener(vf);
    cmbSearchType.addActionListener(vf);

    //For layout purposes, put the buttons in a separate panel
    JPanel buttonPanel = new JPanel();

    //****************************************************
    GridBagLayout gridbag = new GridBagLayout();
    buttonPanel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();

    buttonPanel.add(lblSearchType);
    buttonPanel.add(cmbSearchType);
    buttonPanel.add(lblSearchCriteria);
    buttonPanel.add(pSearchCriteria);
    buttonPanel.add(lblWhereClause);
    buttonPanel.add(txtSQL);
    buttonPanel.add(btnGetWhereClause);
    buttonPanel.add(btnExit);

    gbc.insets.top = 5;
    gbc.insets.bottom = 5;
    gbc.insets.left = 5;
    gbc.insets.right = 5;

    gbc.anchor = GridBagConstraints.WEST;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gridbag.setConstraints(lblSearchType, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gridbag.setConstraints(cmbSearchType, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gridbag.setConstraints(lblSearchCriteria, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gridbag.setConstraints(pSearchCriteria, gbc);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gridbag.setConstraints(lblWhereClause, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridwidth = 3;
    gbc.gridheight = 5;
    gbc.gridx = 1;
    gbc.gridy = 3;
    gridbag.setConstraints(txtSQL, gbc);

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets.left = 2;
    gbc.insets.right = 2;
    gbc.insets.top = 40;
    gbc.gridx = 0;
    gbc.gridy = 8;
    gridbag.setConstraints(btnGetWhereClause, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 8;
    gridbag.setConstraints(btnExit, gbc);

    //****************************************************
    //Add the buttons and the log to the frame
    Container contentPane = getContentPane();
    contentPane.add(buttonPanel, BorderLayout.CENTER);

//    try {
//      UIManager.setLookAndFeel(new WindowsLookAndFeel());
//      SwingUtilities.updateComponentTreeUI(
//        SearchManager.this);
//    } catch (Exception ex) {
//      System.out.println(ex);
//    }


  }

  public void setSQL(String str) {
    txtSQL.setText(str);
  }

  public String getSearchType() {
    return (String) cmbSearchType.getSelectedItem();
  }
  public JComboBox<String> getSearchTypeCtrl() {
    return cmbSearchType;
  }

  public void displayNewUI(JPanel panel) {
    pSearchCriteria.removeAll();
    pSearchCriteria.add(panel);
    pSearchCriteria.validate();
    validate();
  }
  public static void main(String[] args) throws Exception {

    JFrame frame = new SearchManager();

    frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        }
                           );

    //frame.pack();
    frame.setSize(450, 400);
    frame.setVisible(true);
  }



}
class buttonHandler implements ActionListener {
  SearchManager manager;
  UIBuilder builder;

  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals(SearchManager.EXIT)) {
      System.exit(1);
    }
    if (e.getActionCommand().equals(SearchManager.GET_SQL)) {
      manager.setSQL(builder.getSQL());
    }
    if (e.getSource() == manager.getSearchTypeCtrl()) {
      String selection = manager.getSearchType();

      if (selection.equals("") == false) {
        BuilderFactory factory = new BuilderFactory();
        //create an appropriate builder instance
        builder = factory.getUIBuilder(selection);
        //configure the director with the builder
        UIDirector director = new UIDirector(builder);
        //director invokes different builder
        // methods
        director.build();
        //get the final build object
        JPanel UIObj = builder.getSearchUI();
        manager.displayNewUI(UIObj);
      }
    }

  }
  public buttonHandler() {
  }
  public buttonHandler(SearchManager inManager) {
    manager = inManager;
  }

} class MyUtil {

  public static String handleRecordLength(String str,
      int len) {
    int i;
    String retStr = "";

    if (str.length() > len) {
      retStr = str.substring(0, len);
    } else {
      i = len - str.length();
      for (int j = 0; j < i; j++) {
        str = str + " ";
      }
      retStr = str;
    }
    return retStr;
  }

}
class BuilderFactory {
  public UIBuilder getUIBuilder(String str) {
    UIBuilder builder = null;
    if (str.equals(SearchManager.CANDIDATE_SRCH)) {
      builder = new CandSrchBuilder();
    } else if (str.equals(SearchManager.EMPLOYER_SRCH)) {
      builder = new EmpSrchBuilder();
    }
    return builder;
  }
}
