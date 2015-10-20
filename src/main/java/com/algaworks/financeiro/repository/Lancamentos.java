package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
						+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public List<Lancamento> todosLanc() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",
				Lancamento.class);		
		return query.getResultList();
	}
	
	
	public List<Lancamento> lancePessoa(Pessoa pessoa) {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.pessoa=:pessoa",
				Lancamento.class);
		query.setParameter("pessoa", pessoa);
		return query.getResultList();
	}
	
	/*public List<Lancamento> lancPessoa() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.pessoa = :pessoa",
				Lancamento.class);
		return query.getResultList();
	}*/

	
	public BigDecimal calculaTotalMovimentado(TipoLancamento tipo,Pessoa pessoa) {
		String jpql = "select sum(l.valor) from Lancamento "
				+ "l where l.pessoa = :pessoa and l.tipo= :tipo ";
				javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);
		query.setParameter("tipo", tipo);		
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}
	
	
	public BigDecimal Lucro(TipoLancamento tipo,Pessoa pessoa) {
		String jpql = "select sum(l.valor) from Lancamento "
				+ "l where l.pessoa = :pessoa and l.tipo='RECEITA' ";
				javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);
		//query.setParameter("tipo", tipo);		
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}
	
	public BigDecimal saldoNegativo(TipoLancamento tipo,Pessoa pessoa) {
		String jpql = "select sum(-l.valor) from Lancamento "
				+ "l where l.pessoa = :pessoa and l.tipo='DESPESA' ";
				javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);
		//query.setParameter("tipo", tipo);		
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}
	
	public BigDecimal lucroTotal(TipoLancamento tipo,Pessoa pessoa) {
		String jpql = "select sum(l.valor) - (select sum(l.valor) FROM Lancamento l where l.tipo='DESPESA' and l.pessoa= :pessoa) "
				+ "from Lancamento l WHERE l.tipo='RECEITA' and l.pessoa=:pessoa";
				javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);			
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}
	
	/*SELECT SUM(l.valor)-(SELECT SUM(l.valor) 
			FROM Lancamento WHERE l.tipo='DESPESA' AND l.pessoa=:pessoa) FROM Lancamento l 
			WHERE AND l.tipo='RECEITA' AND l.pessoa=:pessoa*/	
	
	public void adicionar(Lancamento lancamento) {
		     int count=1;	
		     for( this.manager.persist(lancamento);  count <= 10 ; count++){
		    	System.out.println(count);	     
		     }
		   }
	
	/* int  count=1;
    for(trx.begin(); count <= 10 ; count++){
        System.out.println(count);	
    } */	
	
	/*public void adiciona(Tarefa tarefa) {
		//int  count=1;
		//  for(String sql = "insert into tarefas (descricao,nomeUsuario,dataAbertura,responsavel) "
				//	+ "values (?,?,?,?)";  count <= 250 ; count++){
	          //  System.out.println(sql);
    String sql="insert into tarefas (descricao,nomeUsuario,dataAbertura,responsavel) "
				+ "values (?,?,?,?)";
	// INSERT INTO tabela(campo_da_data)VALUES(NOW())
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setString(2, tarefa.getNomeUsuario());
			stmt.setDate  (3, (java.sql.Date) new Date(tarefa.getDataAbertura().getTimeInMillis()));
            stmt.setString(4, tarefa.getResponsavel());
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
		  throw new RuntimeException(e);
		}
	 
	}*/

	public Lancamento guarda(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}

}