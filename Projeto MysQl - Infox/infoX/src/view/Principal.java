package view;
//infox

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {
	//alterar de PRIVATE para PUBLIC para ficar visivel os botões
	private JPanel contentPane;
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	private JLabel lblData;

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
			}
		});
		setResizable(false);
		setTitle("InfoX - Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 405);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuario = new Usuarios ();
				usuario.setVisible(true);

			}
		});
		btnUsuarios.setBounds(20, 11, 128, 128);
		btnUsuarios.setToolTipText("Usu\u00E1rios");
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/users.png")));
		contentPane.add(btnUsuarios);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes cliente = new Clientes();
				cliente.setVisible(true);
			

			}
		});
		btnClientes.setBounds(182, 11, 128, 128);
		btnClientes.setToolTipText("Clientes");
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/clientes.png")));
		contentPane.add(btnClientes);

		JButton btnOs = new JButton("");
		btnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemServico ordemServicos = new OrdemServico();
				ordemServicos.setVisible(true);
			}
			 
		});
		btnOs.setBounds(20, 174, 128, 128);
		btnOs.setToolTipText("OS");
		btnOs.setIcon(new ImageIcon(Principal.class.getResource("/img/os.png")));
		contentPane.add(btnOs);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(348, 84, 212, 205);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/x.png")));
		contentPane.add(lblNewLabel);

		JButton btnSobre = new JButton("");
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBorder(null);
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setBounds(564, 11, 64, 64);
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/img/about.png")));
		contentPane.add(btnSobre);
		
		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorios.png")));
		btnRelatorios.setToolTipText("OS");
		btnRelatorios.setBounds(182, 174, 128, 128);
		contentPane.add(btnRelatorios);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(10, 323, 607, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("");
		lblData.setBounds(25, 11, 365, 14);
		panel.add(lblData);
	}//Fim do construtor
	
	/**
	 * Metodo Setar data
	 */
		private void setarData () {
			//Importar JavaUtil
			Date dataLabel = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			lblData.setText(formatador.format(dataLabel));
		}
} 

