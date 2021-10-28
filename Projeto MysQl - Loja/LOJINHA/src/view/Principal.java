package view;
//lojinha
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private JLabel lblData;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				// Metodo vai mudar o icone do banco
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/pc.png")));
		setTitle("Lojinha - Sistema de Gest\u00E3o de E-commerce");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 450);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 365, 680, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/icones/dbof.png")));
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(10, 11, 32, 32);
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setBackground(SystemColor.text);
		lblData.setForeground(SystemColor.menu);
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblData.setBounds(370, 5, 300, 32);
		panel.add(lblData);

		JButton btnEstoque = new JButton("");
		btnEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstoque.setBackground(SystemColor.inactiveCaption);
		btnEstoque.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEstoque.setToolTipText("Estoque");
		btnEstoque.setIcon(new ImageIcon(Principal.class.getResource("/icones/estoque.png")));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEstoque.setBounds(36, 27, 140, 128);
		contentPane.add(btnEstoque);

		JButton btnRelatorios = new JButton("");
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnRelatorios.setBackground(SystemColor.inactiveCaption);
		btnRelatorios.setToolTipText("Relat\u00F3rios");
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnRelatorios.setBounds(203, 27, 140, 128);
		contentPane.add(btnRelatorios);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes cliente = new Clientes();
				cliente.setVisible(true);
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClientes.setBackground(SystemColor.inactiveCaption);
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(36, 198, 140, 128);
		contentPane.add(btnClientes);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clicar no botão
				// Criar objeto
				Sobre sobre = new Sobre();
				sobre.setVisible(true); // exibir oJDiaog Sobre
			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBackground(SystemColor.inactiveCaption);
		btnSobre.setBorder(null);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(516, 27, 140, 128);
		contentPane.add(btnSobre);

		JLabel lblNewLabel_1 = new JLabel(" Lojinha");
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setBounds(591, 322, 89, 32);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
	}// fim do construtor100

	/**
	 * Metodo responsavel por setar a data e hora no rodapé
	 */
	private void setarData() {
		// As linha abaixo são usadas para obter e formatar a hora do sistema
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		// A linha abaixo subtitui a Label pela data
		lblData.setText(formatador.format(dataLabel));
	}

	private void status() {
		// Criar um objeto de nome DAO para acessar a classe DAO
		DAO dao = new DAO();
		try {
			// Abre a conexao com o banco
			Connection con = dao.conectar();
			System.out.println(con);
			// Mudando o icone do rodapé no caso da conexão ser 'Sucedida'
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbof.png")));

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbon.png")));
			}
			// IMPORTANTE SEMPRE FECHAR A CONEXÃO
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}
}
