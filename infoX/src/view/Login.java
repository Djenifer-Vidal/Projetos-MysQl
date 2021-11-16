package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	private JTextField txtLogin;

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
				status();	
				
			}
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
		txtSenha.setBounds(68, 87, 258, 20);
		contentPane.add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar ");
		btnEntrar.setBounds(68, 151, 89, 23);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(369, 151, 32, 32);
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dberror.png")));
		contentPane.add(lblStatus);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(68, 25, 258, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
	}//fim do construtor
	

	
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
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	DAO dao = new DAO ();
	private void logar() {
		if(txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preenchar o campo Login", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();	
		}else if(txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preenchar o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
				txtSenha.requestFocus();
		}else {
			try {
				String read = "select * from usuarios where login=? and senha =md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				// A linha abaixo executa a query(instruções SQL) armazenando o resultado no objeto rs
				ResultSet rs = pst.executeQuery();
				//Se existir Login e Senha correspondente
				if (rs.next()) {
					String perfil = rs.getString(5);
					System.out.println(perfil);
	
					//Tratamento de perfil Usuario
					if (perfil.equals("administrador")) {
						Principal principal = new Principal();
						principal.setVisible(true);
						//Liberar Botões
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						this.dispose();											
					} else {
						Principal principal = new Principal();
						principal.setVisible(true);
						this.dispose();				
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Login e/ou Senha Inválido", "Atenção",JOptionPane.WARNING_MESSAGE);
					txtSenha.requestFocus();
					
				}con.close();
				
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

	



