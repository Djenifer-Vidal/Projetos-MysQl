package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private JTextField txtEmail;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setBackground(SystemColor.controlHighlight);
		setModal(true);
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/pc.png")));
		setResizable(false);
		setBounds(100, 100, 527, 402);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Evento "Disparado " ao digitar a caixa de texto
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 23, 242, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel btnPesquisar = new JLabel("");
		btnPesquisar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
		btnPesquisar.setBounds(276, 17, 32, 32);
		getContentPane().add(btnPesquisar);

		JLabel lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setBounds(10, 160, 46, 14);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(52, 157, 136, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha: ");
		lblNewLabel_1.setBounds(345, 197, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome: ");
		lblNewLabel_2.setBounds(216, 160, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(256, 157, 245, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(390, 194, 111, 20);
		getContentPane().add(txtSenha);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/adicionar.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(10, 265, 80, 80);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(112, 265, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/excluir.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(216, 265, 80, 80);
		getContentPane().add(btnExcluir);

		JLabel lblNewLabel_3 = new JLabel("E-mail: ");
		lblNewLabel_3.setBounds(10, 197, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(52, 194, 265, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 64, 491, 64);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 491, 64);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//evento disparado ao clicar com o mouse 
				setarCampos();
				setarSenha();
;			}
		});
		scrollPane.setViewportView(table);

		// Uso da biblioteca ATXY2K para validações
		RestrictedTextField nome = new RestrictedTextField(this.txtNome);
		nome.setLimit(50);

		RestrictedTextField email = new RestrictedTextField(this.txtEmail);
		email.setLimit(50);

		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
		senha.setLimit(250);

	}// Fim do Construtor

	// Criando o objeto para acessar a Classe DAO
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Metodo responsavel pela pesquisa do cliente com o uso da biblioteca rs2xml
	 */

	private void pesquisarCliente() {

		// ? --> paramentro
		String read = "select idcli as ID, nome as Cliente, email as Email from clientes where nome like  ?";
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

	/**
	 * Método reponsavel por adicionar um Cliente no banco
	 */
		private void adicionarCliente() {
		// Validação de campos obrigatarios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo E-mail", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEmail.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			// Inserir o cliente no banco
			String create = "insert into clientes (nome,email,senha) values (?,?,md5(?))";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtSenha.getText());
				
				//Criando uma variavel que irá executar a query e receber o valor 1 em cado positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cadastro realizado", "Messagem", JOptionPane.INFORMATION_MESSAGE);					
				}
				con .close();
				limpar();
				
			}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "E-mail já cadastrado\nCadastre um novo E-mail", "Atenção", JOptionPane.WARNING_MESSAGE);					
				txtEmail.setText(null);
				txtEmail.requestFocus();
				
				}catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	
	/**
	 * Metodo responsavel por setar os campos da tabela das caias no JFrame - formuçario
	 * 
	 */
	private void setarCampos() {
		// A linha abaixo obtem o conteudo da linha da tabela
		// int *indice - [0], [1]
		int setar = table.getSelectedRow();
		// Setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 2).toString());
		// Gerenciar botóes - não deixar adicionar clientes existentes.
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		
	}
	/**
	 * Metodo especifico para setar Senha
	 * 
	 */
	private void setarSenha () {
		String read2 = "select senha from clientes where idcli=?";
		
		try {
			
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1,txtId.getText());
			//A linha abaixo executra a instrução SQL e armazena o resultado
			ResultSet rs = pst.executeQuery();
			//A linha abaixo verificar se existe uma senha para idcli
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));			
				}
			}catch (Exception e) {
				System.out.println(e);
		}
	}
	/**
	 *Metodo responsavel pela edição dos dados do cliente
	 */
	private void editarCliente () {
		

			// Validação de campos obrigatarios
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Atenção", JOptionPane.WARNING_MESSAGE);
				txtNome.requestFocus();
			} else if (txtEmail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo E-mail", "Atenção", JOptionPane.WARNING_MESSAGE);
				txtEmail.requestFocus();
			} else if (txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção", JOptionPane.WARNING_MESSAGE);
				txtSenha.requestFocus();
			} else {
				// Editar os dados do cliente
				String update = "update clientes set nome=?,email=?,senha=md5(?) where idcli=?";

				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(update);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtEmail.getText());
					pst.setString(3, txtSenha.getText());
					pst.setString(4,txtId.getText());
					
					//Criando uma variavel que irá executar a query e receber o valor 1 em cado positivo (inserção do cliente no banco)
					//positivo (edição do cliente no banco)
					int confirma = pst.executeUpdate();
					if (confirma == 1) {
						JOptionPane.showMessageDialog(null, "Dados do cliente atualizados", "Messagem", JOptionPane.INFORMATION_MESSAGE);					
					}
					con .close();
					limpar();
					
				}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "E-mail já cadastrado\nCadastre um novo E-mail", "Atenção", JOptionPane.WARNING_MESSAGE);					
					txtEmail.setText(null);
					txtEmail.requestFocus();
					
					
				}
				
				catch (Exception e) {
					System.out.println(e);
				
				}
			}
	}
	
	private void excluirCliente () {
		//Confirmação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
        	//codigo principal
        	String delete ="delete from clientes where idcli=?";
        	try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso","Messagem", JOptionPane.INFORMATION_MESSAGE);
				}
				
				con.close();
				
        	}catch (java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui pedido em aberto.", "Atenção!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
        }
		
	}
	
	/**
	 * Limpar os campos
	 */ 
	private void limpar () {
		txtId.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtSenha.setText(null);
		txtPesquisar.setText(null);
		//Limpar a tabela
		((DefaultTableModel)table.getModel()).setRowCount(0);
		//gerenciar os botões (default)
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}
}
