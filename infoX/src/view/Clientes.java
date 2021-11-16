package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Clientes extends JDialog {
	private JComboBox cboUf;

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
		getContentPane().setBackground(SystemColor.scrollbar);
		setTitle("Titulo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/pc.png")));
		setBounds(150, 150, 733, 502);
		getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(56, 40, 5, 22);
		getContentPane().add(textArea);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(302, 36, 32, 32);
		getContentPane().add(lblNewLabel);

		txtPesquisar2 = new JTextArea();
		txtPesquisar2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}

		});
		txtPesquisar2.setBorder(new CompoundBorder());
		txtPesquisar2.setBounds(10, 40, 271, 22);
		getContentPane().add(txtPesquisar2);

		JLabel lblNewLabel_1 = new JLabel("*Campos obrigatorios");
		lblNewLabel_1.setBounds(475, 40, 204, 19);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(10, 229, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("*Nome:");
		lblNewLabel_3.setBounds(145, 229, 76, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("*Fone:");
		lblNewLabel_4.setBounds(509, 229, 46, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("CEP:");
		lblNewLabel_5.setBounds(10, 267, 46, 14);
		getContentPane().add(lblNewLabel_5);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(192, 263, 89, 23);
		getContentPane().add(btnCep);

		JLabel lblNewLabel_6 = new JLabel("*Endere\u00E7o:");
		lblNewLabel_6.setBounds(10, 310, 66, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("*N\u00BA:");
		lblNewLabel_7.setBounds(406, 310, 46, 14);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Complemento:");
		lblNewLabel_8.setBounds(509, 310, 123, 14);
		getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("*Cidade:");
		lblNewLabel_9.setBounds(244, 354, 51, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblUF = new JLabel("*UF");
		lblUF.setBounds(449, 354, 46, 14);
		getContentPane().add(lblUF);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(478, 350, 46, 22);
		getContentPane().add(cboUf);

		JLabel lblNewLabel_11 = new JLabel("*Bairro:");
		lblNewLabel_11.setBounds(10, 354, 46, 14);
		getContentPane().add(lblNewLabel_11);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(45, 388, 64, 64);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnEditar.setBounds(131, 388, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(221, 388, 64, 64);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(30, 100, 660, 82);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 660, 82);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		txtNomecli = new JTextField();
		txtNomecli.setBounds(199, 223, 300, 20);
		getContentPane().add(txtNomecli);
		txtNomecli.setColumns(10);

		txtFonecli = new JTextField();
		txtFonecli.setBounds(558, 223, 149, 20);
		getContentPane().add(txtFonecli);
		txtFonecli.setColumns(10);

		txtCep3 = new JTextField();
		txtCep3.setBounds(40, 264, 105, 20);
		getContentPane().add(txtCep3);
		txtCep3.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(76, 307, 320, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(432, 307, 66, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(593, 307, 114, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(59, 351, 175, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(302, 351, 137, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		txtIdcli = new JTextField();
		txtIdcli.setEditable(false);
		txtIdcli.setBounds(35, 226, 86, 20);
		getContentPane().add(txtIdcli);
		txtIdcli.setColumns(10);

		RestrictedTextField nome = new RestrictedTextField(this.txtNomecli);
		nome.setLimit(50);
		RestrictedTextField fone = new RestrictedTextField(this.txtFonecli);
		fone.setLimit(15);
		RestrictedTextField endereco = new RestrictedTextField(this.txtEndereco);
		endereco.setLimit(50);
		RestrictedTextField numero = new RestrictedTextField(this.txtNumero);
		numero.setLimit(12);
		RestrictedTextField complemento = new RestrictedTextField(this.txtComplemento);
		complemento.setLimit(30);
		RestrictedTextField bairro = new RestrictedTextField(this.txtBairro);
		bairro.setLimit(50);
		RestrictedTextField cidade = new RestrictedTextField(this.txtCidade);
		cidade.setLimit(50);

	}// fim do construtor

	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep3.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Criando o objeto para acessar a Classe DAO
	DAO dao = new DAO();

	private JTable table;
	private JTextArea txtPesquisar2;
	private JTextField txtNomecli;
	private JTextField txtFonecli;
	private JTextField txtCep3;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtIdcli;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnEditar;

	private void pesquisarCliente() {
		// ? --> paramentro
		String read = "select idcli as ID, nomecli as Cliente, fonecli as Fone,cepcli as CEP,endcli as Endereço,  numcli as Numero, complementocli as Complemento, bairrocli as Bairro, cidadecli as Cidade, ufcli as UF from clientes where nomecli like  ?";
		try {
			// abrir 	a conexao com o banco
			Connection con = dao.conectar();

			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);

			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 --> Parametro (?)
			pst.setString(1, txtPesquisar2.getText() + "%");

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
		if (txtNomecli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Nome", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNomecli.requestFocus();
		} else if (txtFonecli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Fone", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtFonecli.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Endereço", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencher o campo Cidade", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencher o campo UF", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {
			String create = "insert into clientes (nomecli,fonecli,cepcli,endcli,numcli,complementocli,bairrocli,cidadecli,ufcli) values (?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNomecli.getText());
				pst.setString(2, txtFonecli.getText());
				pst.setString(3, txtCep3.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cadastro Realizado", "Messagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void setarCampos() {
		// A linha abaixo obtem o conteudo da linha da tabela
		// int *indice - [0], [1]
		int setar = table.getSelectedRow();
		// Setar os campos
		txtIdcli.setText(table.getModel().getValueAt(setar,0).toString());
		txtNomecli.setText(table.getModel().getValueAt(setar,1).toString());
		txtFonecli.setText(table.getModel().getValueAt(setar,2).toString());
		txtCep3.setText(table.getModel().getValueAt(setar,3).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar,4).toString());
		txtNumero.setText(table.getModel().getValueAt(setar,5).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar,6).toString());
		txtBairro.setText(table.getModel().getValueAt(setar,7).toString());
		txtCidade.setText(table.getModel().getValueAt(setar,8).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar,9).toString());
		
		// Gerenciar botóes - não deixar adicionar clientes existentes.
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	/**
	 * Metodo responsavel pela edição dos dados do cliente
	 */
	private void editarCliente() {

		// Validação de campos obrigatarios
		if (txtNomecli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNomecli.requestFocus();
		} else if (txtFonecli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtFonecli.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Endereço", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {
			// Editar os dados do cliente
			String update = "update clientes set nomecli=?,fonecli=?,cepcli=?,endcli=?,numcli=?,complementocli=?, bairrocli=?,cidadecli=?,ufcli=? where idcli=?";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				
				pst.setString(1, txtNomecli.getText());
				pst.setString(2, txtFonecli.getText());
				pst.setString(3, txtCep3.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtIdcli.getText());
				// Criando uma variavel que irá executar a query e receber o valor 1 em cado
				// positivo (inserção do cliente no banco)
				// positivo (edição do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do cliente atualizados", "Messagem",JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
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
				pst.setString(1, txtIdcli.getText());
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

	private void limpar() {
		txtIdcli.setText(null);
		txtNomecli.setText(null);
		txtFonecli.setText(null);
		txtCep3.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botões (default)
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}
}
