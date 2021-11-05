package br.senai.sp.jandira.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.senai.sp.jandira.model.Periodo;

public class FrameCadastroAluno extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;

	
	public FrameCadastroAluno() {
		setTitle("Cadastro de Alunos ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 303);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatricula.setBounds(10, 39, 73, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(85, 36, 86, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 81, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(66, 80, 157, 20);
		contentPane.add(txtNome);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeriodo.setBounds(10, 116, 73, 14);
		contentPane.add(lblPeriodo);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBounds(10, 160, 124, 39);
		contentPane.add(btnSalvar);
		
		JComboBox comboPeriodo = new JComboBox();
		comboPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
		comboPeriodo.setBounds(98, 111, 73, 28);
		contentPane.add(comboPeriodo);
		
		JLabel lblListaAlunos = new JLabel("Lista de Alunos:");
		lblListaAlunos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListaAlunos.setBounds(303, 41, 139, 14);
		contentPane.add(lblListaAlunos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(303, 70, 173, 168);
		contentPane.add(scrollPane);
		
		JList listAlunos = new JList();
		scrollPane.setViewportView(listAlunos);
	}
}
