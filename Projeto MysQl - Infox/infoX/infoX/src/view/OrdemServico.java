package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

public class OrdemServico extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTable table;
	private JTextField txtData;
	private JTextField txtOS;
	// Vriavel de apoio ai uso do checkbox
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemServico dialog = new OrdemServico();
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
	public OrdemServico() {
		setResizable(false);
		setTitle("Ordem Servi\u00E7o");
		setBounds(100, 100, 781, 432);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(430, 23, 98, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(546, 17, 33, 33);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID: ");
		lblNewLabel_1.setBounds(606, 26, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(662, 23, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(430, 69, 318, 90);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 318, 90);
		desktopPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(27, 23, 344, 136);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Data: ");
		lblNewLabel_2.setBounds(192, 27, 46, 14);
		panel.add(lblNewLabel_2);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(248, 24, 86, 20);
		panel.add(txtData);
		txtData.setColumns(10);

		txtOS = new JTextField();
		txtOS.setBounds(10, 24, 109, 20);
		panel.add(txtOS);
		txtOS.setColumns(10);

		chkOrcamento = new JCheckBox("Or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		buttonGroup.add(chkOrcamento);
		chkOrcamento.setBounds(10, 67, 97, 23);
		panel.add(chkOrcamento);

		chkServico = new JCheckBox("Servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		buttonGroup.add(chkServico);
		chkServico.setBounds(10, 92, 97, 23);
		panel.add(chkServico);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(169, 96, 46, 14);
		panel.add(lblNewLabel_3);

		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(
				new String[] { "", "Aguardando Aprova\u00E7\u00E3o", "Na bancada", "Aguardando Retirada" }));
		cboStatus.setBounds(225, 92, 109, 22);
		panel.add(cboStatus);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(390, 318, 64, 64);
		getContentPane().add(btnAdicionar);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/read.png")));
		btnPesquisar.setBounds(464, 318, 64, 64);
		getContentPane().add(btnPesquisar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOS();
			}
		});
		btnEditar.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/update.png")));
		btnEditar.setBounds(536, 318, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOS();
			}
		});
		btnExcluir.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(610, 318, 64, 64);
		getContentPane().add(btnExcluir);

		btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(OrdemServico.class.getResource("/img/print.png")));
		btnImprimir.setBounds(684, 318, 64, 64);
		getContentPane().add(btnImprimir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados da OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(136, 180, 612, 127);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(112, 26, 247, 20);
		panel_1.add(txtEquipamento);
		txtEquipamento.setColumns(10);
		
		txtTecnico = new JTextField();
		txtTecnico.setBounds(111, 57, 207, 20);
		panel_1.add(txtTecnico);
		txtTecnico.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(496, 26, 106, 20);
		panel_1.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Equipamento: ");
		lblNewLabel_4.setBounds(10, 29, 105, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tecn\u00EDco:");
		lblNewLabel_5.setBounds(10, 60, 67, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Valor final:");
		lblNewLabel_6.setBounds(420, 29, 66, 14);
		panel_1.add(lblNewLabel_6);
		
		txtDefeito = new JTextField();
		txtDefeito.setBounds(112, 88, 391, 20);
		panel_1.add(txtDefeito);
		txtDefeito.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Defeito:");
		lblNewLabel_7.setBounds(10, 91, 46, 14);
		panel_1.add(lblNewLabel_7);

	}// Fim do construtor

	DAO dao = new DAO();	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chkOrcamento;
	private JTextField txtEquipamento;
	private JTextField txtTecnico;
	private JTextField txtValor;
	private JTextField txtDefeito;
	private JComboBox cboStatus;
	private JCheckBox chkServico;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	private JButton btnImprimir;

	private void pesquisarCliente() {
		// ? --> paramentro
		String read = "select idcli as ID, nomecli as Cliente, fonecli as Fone,cepcli as CEP from clientes where nomecli like  ?";
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
	 * Metodo responsavel pela pesquisa da OS
	 */
	private void pesquisarOs() {
		// tecnica usada para capturar
		String numOs = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from tbos where os=" + numOs;
		try {
		Connection con = dao.conectar();
		PreparedStatement pst = con.prepareStatement(read);
		// A linha abaixo, ResultSet, trás a info do banco de dados
		ResultSet rs = pst.executeQuery();

		 if (rs.next()) {
		if (tipo == "Serviço") {
		chkServico.setSelected(true);
		tipo = "Serviço";
		} else {
		chkOrcamento.setSelected(true);
		tipo = "Orçamento";
		}
		txtId.setText(rs.getString(9));
		txtOS.setText(rs.getString(1));
		txtData.setText(rs.getString(2));
		txtEquipamento.setText(rs.getString(5));
		txtTecnico.setText(rs.getString(7));
		txtValor.setText(rs.getString(8));
		txtDefeito.setText(rs.getString(6));
		cboStatus.setSelectedItem(rs.getString(4).toString());
		txtPesquisar.setEnabled(false);

		 } else {
		JOptionPane.showMessageDialog(null, "O.S Não Localizada!!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
		}

		 } catch (Exception e) {
		System.out.println(e);

		 }

		 }

	/**
	 * Metodo responsavel por emitir OS
	 */
	private void emitirOs() {
		if (txtId.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o ID do cliente ", "Atenção", JOptionPane.WARNING_MESSAGE);
		txtId.requestFocus();			
		} else if (tipo == null) {		
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS ", "Atenção", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			String create = "insert into clientes (os, dataos, statusos, equipamentoos, defeitoos, tecnicoos, valoros, idcli) values (?,?,?,?,?,?,?,?,?)";
		
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtOS.getText());
				pst.setString(2, txtData.getText());
				pst.setString(4, cboStatus.getSelectedItem().toString());
				pst.setString(5, txtEquipamento.getText());
				pst.setString(6, txtDefeito.getText());
				pst.setString(7, txtTecnico.getText());
				pst.setString(8, txtValor.getText());
				pst.setString(9, txtId.getText());
			

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

		
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	private void editarOS () {
		

		// Validação de campos obrigatarios
		if (txtData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Data", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtData.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a opção Status ", "Atenção", JOptionPane.WARNING_MESSAGE);
			cboStatus.requestFocus();
		} else {
			// Editar os dados do cliente
			String update = "update tbos set os=?, dataos=?, statusos=?, equipamentosos=?, defeitosos=?, tecnicoos=?, valoros=?  where idcli=?";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1,txtOS.getText());
				pst.setString(2,txtData.getText());
				pst.setString(3,chkOrcamento.getText());
				pst.setString(4,chkServico.getText());
				pst.setString(5,cboStatus.getSelectedItem().toString());
				pst.setString(6,txtEquipamento.getText());
				pst.setString(7,txtDefeito.getText());
				pst.setString(8,txtTecnico.getText());
				pst.setString(9,txtValor.getText());
				pst.setString(10,txtId.getText());
				
				
				
				//Criando uma variavel que irá executar a query e receber o valor 1 em cado positivo (inserção do cliente no banco)
				//positivo (edição do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do cliente atualizados", "Messagem", JOptionPane.INFORMATION_MESSAGE);					
				}
				con .close();
				limpar();					
			}
			catch (Exception e) {
				System.out.println(e);
			
			}
		}
}
	private void excluirOS () {
		//Confirmação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desta Ordem de Serviço?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
        	//codigo principal
        	String delete ="delete from tbos where os=?";
        	try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOS.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Ordem de Serviço excluido com sucesso","Messagem", JOptionPane.INFORMATION_MESSAGE);
				}
				
				con.close();
				
        	}catch (java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Exclusão não realizada", "Atenção!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
        }
		
	}

	private void limpar() {
		txtId.setText(null);
		txtOS.setText(null);
		txtData.setText(null);
		chkOrcamento.setText(null);
		chkServico.setText(null);
		txtValor.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtTecnico.setText(null);
		cboStatus.setSelectedItem(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botões (default)
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnPesquisar.setEnabled(true);

	}
}


