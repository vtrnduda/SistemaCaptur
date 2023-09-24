import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AplicacaoCaptur {

	private JFrame frmCapturTurismo;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoCaptur window = new AplicacaoCaptur();
					window.frmCapturTurismo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AplicacaoCaptur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCapturTurismo = new JFrame();
		frmCapturTurismo.setTitle("Captur Turismo");
		frmCapturTurismo.setBounds(100, 100, 450, 300);
		frmCapturTurismo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCapturTurismo.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(24, 27, 46, 14);
		frmCapturTurismo.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 24, 86, 20);
		frmCapturTurismo.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(223, 24, 86, 20);
		frmCapturTurismo.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
