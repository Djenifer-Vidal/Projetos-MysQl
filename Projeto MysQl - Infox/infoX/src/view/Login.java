package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();			}
		});
		setTitle("InfoX- Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(10, 28, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(10, 90, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(68, 87, 179, 20);
		contentPane.add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar ");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEntrar.setBounds(68, 151, 89, 23);
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dberror.png")));
		lblStatus.setBounds(369, 151, 32, 32);
		contentPane.add(lblStatus);
		
		textField = new JTextField();
		textField.setBounds(68, 25, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}//fim do construtor
	
	/**
	 * Metodo responsavel por setar a data e hora no rodapé
	 */
	/*private void setarData() {
		// As linha abaixo são usadas para obter e formatar a hora do sistema
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		// A linha abaixo subtitui a Label pela data
		lblData.setText(formatador.format(dataLabel));
	}*/

	private void status() {
		// Criar um objeto de nome DAO para acessar a classe DAO
		DAO dao = new DAO();
		try {
			// Abre a conexao com o banco
			Connection con = dao.conectar();
			System.out.println(con);
			// Mudando o icone do rodapé no caso da conexão ser 'Sucedida'
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dberror.img")));

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dbok.png")));
			}
			// IMPORTANTE SEMPRE FECHAR A CONEXÃO
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}
}
