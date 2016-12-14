package test;

import javax.swing.JButton;

import test.FTPGUI.CommandInterface;
import test.FTPGUI.Mediator;


public class DownloadButton extends JButton implements CommandInterface {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4124657174628749934L;
	Mediator mdtr;
    public void processEvent() {
      mdtr.DownloadItem();
    }
    public DownloadButton(String name, Mediator inp_mdtr) {
      super(name);
      mdtr = inp_mdtr;
      mdtr.registerDownloadButton(this);
    }
  }