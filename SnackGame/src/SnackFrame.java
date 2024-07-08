import javax.swing.JFrame;

public class SnackFrame extends JFrame {
	SnackFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new SnackPanel());
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
