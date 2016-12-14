import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReportManager extends JFrame 
  implements Observable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static final String newline = "\n";
  public static final String SET_OK = "OK";
  public static final String EXIT = "Exit";

 // private JPanel pSearchCriteria;
  private JComboBox<String> cmbDepartmentList;
  private JButton btnOK, btnExit;
  private Vector<Observer> observersList;
  private String department;

  public ReportManager() throws Exception {
    super("Observer Pattern - Example");

    observersList = new Vector<Observer>();

    // Create controls
    cmbDepartmentList = new JComboBox<String>();
    btnOK = new JButton(ReportManager.SET_OK);
    btnOK.setMnemonic(KeyEvent.VK_S);
    btnExit = new JButton(ReportManager.EXIT);
    btnExit.setMnemonic(KeyEvent.VK_X);

   // pSearchCriteria = new JPanel();

    //Create Labels
    JLabel lblDepartmentList =
      new JLabel("Select a Department:");

    ButtonHandler vf = new ButtonHandler(this);

    btnOK.addActionListener(vf);
    btnExit.addActionListener(vf);

    JPanel buttonPanel = new JPanel();

    //----------------------------------------------
    GridBagLayout gridbag = new GridBagLayout();
    buttonPanel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();
    buttonPanel.add(lblDepartmentList);
    buttonPanel.add(cmbDepartmentList);
    buttonPanel.add(btnOK);
    buttonPanel.add(btnExit);

    gbc.insets.top = 5;
    gbc.insets.bottom = 5;
    gbc.insets.left = 5;
    gbc.insets.right = 5;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gridbag.setConstraints(lblDepartmentList, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gridbag.setConstraints(cmbDepartmentList, gbc);

    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets.left = 2;
    gbc.insets.right = 2;
    gbc.insets.top = 40;
    gbc.gridx = 0;
    gbc.gridy = 6;
    gridbag.setConstraints(btnOK, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 6;
    gridbag.setConstraints(btnExit, gbc);

    Container contentPane = getContentPane();
    contentPane.add(buttonPanel, BorderLayout.CENTER);
//    try {
//      UIManager.setLookAndFeel(new WindowsLookAndFeel());
//      SwingUtilities.updateComponentTreeUI(
//        ReportManager.this);
//    } catch (Exception ex) {
//      System.out.println(ex);
//    }

    initialize();
    setSize(250, 200);
    setVisible(true);
  }
  private void initialize()  {
    // fill some test data here into the listbox.
    cmbDepartmentList.addItem("HardWare");
    cmbDepartmentList.addItem("Electronics");
    cmbDepartmentList.addItem("Furniture");
  }

  public void register(Observer obs) {
    // Add to the list of Observers
    observersList.addElement(obs);
  }
  public void unRegister(Observer obs) {
    // remove from the list of Observers

  }
  public void notifyObservers() {
    // Send notify to all Observers
    for (int i = 0; i < observersList.size(); i++) {
      Observer observer =
        (Observer) observersList.elementAt(i);
      observer.refreshData(this);
    }
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String dept) {
    department = dept;
  }

  class ButtonHandler implements ActionListener {
    ReportManager subject;

    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals(ReportManager.EXIT)) {
        System.exit(1);
      }
      if (e.getActionCommand().equals(ReportManager.SET_OK)) {
        String dept = (String)
                      cmbDepartmentList.getSelectedItem();
        //change in state
        subject.setDepartment(dept);
        subject.notifyObservers();
      }
    }

    public ButtonHandler() {
    }
    public ButtonHandler(ReportManager manager) {
      subject = manager;
    }

  }

}// end of class

