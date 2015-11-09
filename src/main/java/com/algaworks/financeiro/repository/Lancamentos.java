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
import com.algaworks.financeiro.model.Usuario;

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

	public List<Lancamento> lancamentoUsuario(Usuario usuario) {
		TypedQuery<Lancamento> query = manager
				.createQuery("from Lancamento l where l.usuario=:usuario ",
						Lancamento.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	
	public List<Lancamento> lancamentos(Usuario usuario) {										
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.usuario=:usuario ",
				Lancamento.class);
        query.setParameter("usuario", usuario);       
		return query.getResultList();		
	}

	// SELECT * FROM Lancamento l INNER JOIN l.usuario u WHERE
	// l.usuario=:usuario

	/*public List<Lancamento> lancePessoa(Usuario usuario) {
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento l where l.usuario=:usuario", Lancamento.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}*/
	
	public List<Lancamento> lancePessoa(Usuario usuario) {
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento l where l.usuario=:usuario"
				, Lancamento.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
		/*
select usr.id, usr.name
from User as usr
left join usr.messages as msg
group by usr.id, usr.name
order by count(msg)
		*/
	}
	

	public List<Lancamento> todosLancamento(Usuario usuario) {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.usuario=:usuario ",
				//SELECT * FROM lancamento AS l LEFT JOIN usuario_autorizacao AS u ON u.id=l.id  
				//INNER JOIN usuario s ON(s.id=l.usuario_id) WHERE l.usuario_id=3
				Lancamento.class);
		query.setParameter("usuario", usuario);
		 
		return query.getResultList();		
	}
	
	//Select * from lançamento l join l.pessoa p where p.nome...
	//SELECT * FROM lancamento l JOIN usuario u ON(u.id=l.id) WHERE u.id=3
	

	public BigDecimal calculaTotalMovimentado(TipoLancamento tipo, Pessoa pessoa) {
		String jpql = "select sum(l.valor) from Lancamento "
				+ "l where l.pessoa = :pessoa and l.tipo= :tipo ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);
		query.setParameter("tipo", tipo);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal Lucro(TipoLancamento tipo, Usuario usuario) {
		String jpql = "select sum(l.valor) from Lancamento "
				+ "l where l.usuario = :usuario and l.tipo='RECEITA' ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("usuario", usuario);
		// query.setParameter("tipo", tipo);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal saldoNegativo(TipoLancamento tipo, Usuario usuario) {
		String jpql = "select sum(-l.valor) from Lancamento "
				+ "l where l.usuario = :usuario and l.tipo='DESPESA' ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("usuario", usuario);
		// query.setParameter("tipo", tipo);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal lucroTotal(TipoLancamento tipo, Usuario usuario) {
		String jpql = "select sum(l.valor) - (select sum(l.valor) "
				+ "FROM Lancamento l where l.tipo='DESPESA' and l.usuario= :usuario) "
				+ "from Lancamento l WHERE l.tipo='RECEITA' and l.usuario=:usuario";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("usuario", usuario);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public void adicionar(Lancamento lancamento) {
		int count = 1;
		for (this.manager.persist(lancamento); count <= 10; count++) {
			System.out.println(count);
		}
	}

	public Lancamento guarda(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}
}