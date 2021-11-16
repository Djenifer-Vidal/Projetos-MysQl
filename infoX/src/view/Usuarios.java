package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Usuarios extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTable table;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JComboBox cboPerfil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				chkSenha.setVisible(false);
			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		setBounds(150, 150, 778, 420);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setBounds(25, 22, 214, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(278, 16, 33, 33);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(553, 22, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(477, 25, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(25, 91, 695, 50);
		getContentPane().add(desktopPane);

		JScrollPane Table = new JScrollPane();
		Table.setBounds(0, 0, 695, 50);
		desktopPane.add(Table);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
				
			}
		});
		Table.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("Usu\u00E1rio: ");
		lblNewLabel_2.setBounds(28, 180, 69, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Login: ");
		lblNewLabel_2_1.setBounds(28, 225, 69, 14);
		getContentPane().add(lblNewLabel_2_1);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(107, 177, 214, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(107, 222, 214, 20);
		getContentPane().add(txtLogin);

		JLabel lblNewLabel_3 = new JLabel("Senha: ");
		lblNewLabel_3.setBounds(364, 225, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(420, 222, 198, 20);
		getContentPane().add(txtSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(35, 279, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "administrador", "operador" }));
		cboPerfil.setBounds(107, 275, 181, 22);
		getContentPane().add(cboPerfil);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(503, 300, 64, 64);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkSenha.isSelected()) {
					editarUsuario();
				}else {
					editarUsuarioPersonalizado();
				}
		

			}
		});
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnEditar.setBounds(599, 300, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(688, 296, 64, 64);
		getContentPane().add(btnExcluir);
		
		chkSenha = new JCheckBox("Confirmar Altera\u00E7\u00E3o da Senha");
		chkSenha.setBounds(94, 322, 246, 23);
		getContentPane().add(chkSenha);

	}// fim do construtor

	/**
	 * Método reponsavel por adicionar um Cliente no banco
	 */
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void pesquisarUsuario() {
		// ? --> paramentro
		String read = "select * from usuarios where usuario like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);

			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 --> Parametro (?)
			pst.setString(1, txtPesquisar.getText() + "%");

			// Executar a query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();

			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void setarCampos() {
		// A linha abaixo obtem o conteudo da linha da tabela
		// int *indice - [0], [1]
		int setar = table.getSelectedRow();
		// Setar os campos
	
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		txtSenha.setText(table.getModel().getValueAt(setar, 3).toString());
		cboPerfil.setSelectedItem(table.getModel().getValueAt(setar, 4).toString());

		// Gerenciar botóes - não deixar adicionar clientes existentes.
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		chkSenha.setVisible(true);

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void setarSenha() {
		String read2 = "select senha from usuarios where id=?";

		try {

			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// A linha abaixo executra a instrução SQL e armazena o resultado
			ResultSet rs = pst.executeQuery();
			// A linha abaixo verificar se existe uma senha para idcli
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void adicionarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Usuario", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Login", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Perfil", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String create = "insert into usuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cadastro Realizado", "Messagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void editarUsuario() {

		// Validação de campos obrigatarios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			// Editar os dados do cliente
			String update = "update usuarios set usuario=?,login=?,senha=md5(?),perfil=? where id=?";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				// Criando uma variavel que irá executar a query e receber o valor 1 em cado
				// positivo (inserção do cliente no banco)
				// positivo (edição do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuario atualizados", "Messagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void editarUsuarioPersonalizado() {

		// Validação de campos obrigatarios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			// Editar os dados do cliente
			String update = "update usuarios set usuario=?,login=?,perfil=? where id=?";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				// Criando uma variavel que irá executar a query e receber o valor 1 em cado
				// positivo (inserção do cliente no banco)
				// positivo (edição do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuario atualizados", "Messagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void excluirUsuario() {
		// Confirmação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// codigo principal
			String delete = "delete from usuarios where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso", "Messagem",
							JOptionPane.INFORMATION_MESSAGE);
				}

				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui pedido em aberto.",
						"Atenção!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botões (default)
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}
	
}
