package br.senai.sp.jandira.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAluno extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private int posicao;

	public FrameCadastroAluno() {

//Explicação do professor sobre vetores e laço for each
//		String[] diasDaSemana = {"domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sabado"};
//		for(String dia : diasDaSemana) {
//			System.out.println(dia);
//		}
//		

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
		DefaultComboBoxModel<String> modelPeriodo = new DefaultComboBoxModel<String>();

		for (Periodo p : Periodo.values()) {
			modelPeriodo.addElement(p.getDescricao());
		}

		comboPeriodo.setModel(modelPeriodo);
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
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);
		scrollPane.setViewportView(listAlunos);

		JButton btnMostrarAlunos = new JButton("Exibir Alunos");
		btnMostrarAlunos.setBounds(10, 215, 124, 38);
		contentPane.add(btnMostrarAlunos);

		AlunoRepository turma = new AlunoRepository(3);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());

				// Definir o horario que o aluno estuda
				aluno.setPeriodo(determinarPeriodo(comboPeriodo.getSelectedIndex()));

				turma.gravar(aluno, posicao);

				posicao++;

				// Adicionar o nome do aluno ao model da lista
				listaModel.addElement(aluno.getNome());

				if (posicao == turma.getTamanho()) {
					btnSalvar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A turma está cheia", "Cheio", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnMostrarAlunos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Aluno aluno : turma.listarTodosAlunos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println(aluno.getPeriodo().getDescricao());
					System.out.println("___________________");
				}

			}
		});

		listAlunos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				Aluno aluno = turma.listarALuno(listAlunos.getSelectedIndex());
				txtNome.setText(aluno.getNome());
				txtMatricula.setText(aluno.getMatricula());
				
				comboPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());

			}
		});
	}

	private Periodo determinarPeriodo(int periodoSelecionado) {
		if (periodoSelecionado == 0) {
			return Periodo.MANHA;
		} else if (periodoSelecionado == 1) {
			return Periodo.TARDE;
		} else if (periodoSelecionado == 2) {
			return Periodo.NOITE;
		} else if (periodoSelecionado == 3) {
			return Periodo.SABADO;
		} else {
			return Periodo.ONLINE;
		}

	}
}
