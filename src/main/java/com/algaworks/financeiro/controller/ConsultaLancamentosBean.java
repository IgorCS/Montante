package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;
	
	@Inject 
	private Usuarios usuarios;
	
	
	

	private List<Lancamento> lancamentos;

	private BigDecimal total;

	private BigDecimal lucro;

	private BigDecimal saldoNegativos;

	public BigDecimal getSaldoNegativos() {
		return saldoNegativos;
	}

	public void setSaldoNegativos(BigDecimal saldoNegativos) {
		this.saldoNegativos = saldoNegativos;
	}

	private Lancamento lancamentoSelecionado;

	private TipoLancamento tipoLancamento;

	private Lancamento lancamento;

	// private String nome;

	/* PEGA PELO NOME PESSOA */

	private Pessoa pessoa;
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	private Usuario usuario;
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	// POR NOME
	/*
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 */



	private List<Pessoa> todasPessoas;
	
	private List<Usuario> todosUsuarios;

	
	
	
	public void buscaLanc() {
		this.todasPessoas = this.pessoas.todas();
		
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}
	
	

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();

			context.addMessage(null, new FacesMessage(
					"Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void consultar() {
		
		//this.lancamentos = lancamentosRepository.lancePessoa(pessoa);
		this.lancamentos = lancamentosRepository.lancamentos(usuario);		
		this.todasPessoas = this.pessoas.todasPessoas();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.usuario = new Usuario();
	}
	
	public void extrato() {

		this.lancamentos = lancamentosRepository.lancePessoa(usuario);
		//this.lancamentos = lancamentosRepository.todosLanc();		
		this.todasPessoas = this.pessoas.todasPessoas();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.usuario = new Usuario();

	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	/*
	 * public List<String> pesquisarDescricoes(String descricao) { return
	 * this.lancamentos.descricoesQueContem(descricao); }
	 */

	public void calcula() {
		// total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento,
		// pessoa);
		total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento,
				pessoa);
		// lucro = lancamentosRepository.lucroTotal(pessoa);
	}

	public void calculaLucro() throws NegocioException {
		 //FacesContext context = FacesContext.getCurrentInstance();
		//Aqui faz a busca em lancamento pela Pessoa
		this.lancamentos = lancamentosRepository.lancePessoa(usuario);

		lucro = lancamentosRepository.lucroTotal(tipoLancamento, usuario);

		total = lancamentosRepository.Lucro(tipoLancamento, usuario);

		saldoNegativos = lancamentosRepository.saldoNegativo(tipoLancamento,usuario);

	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getLucro() {
		return lucro;
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
	
	public List<Usuario> getTodosUsuarios() {
		return this.todosUsuarios;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	/**
	 * @return the pessoa
	 */

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}