package br.senai.sp.jandira.repository;

import br.senai.sp.jandira.model.Aluno;

public class AlunoRepository {

	private Aluno[] turma;
	
	public AlunoRepository(){
		turma = new Aluno[32];
	}
	
	public AlunoRepository(int quantideDeAlunos) {
		turma = new Aluno[quantideDeAlunos];
	}
	
	public void gravar(Aluno aluno, int posicao) {
		turma[posicao] = aluno;
	}
	public Aluno listarALuno(int posicao) {
		return turma[posicao];
	}
	public Aluno[] listarTodosAlunos(){
		return turma;
	}
}
