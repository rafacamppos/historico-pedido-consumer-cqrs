package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.demo.domain.HistoricoPedido;
import com.example.demo.repository.HistoricoPedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
@EnableBinding(Sink.class)
public class HistoricoPedidoService {
	
	@Autowired
	private HistoricoPedidoRepository repo;
	
	public List<HistoricoPedido> findAll(){
		return repo.findAll();
	}
	
	public HistoricoPedido find(Integer id) throws ObjectNotFoundException {
		Optional<HistoricoPedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + HistoricoPedido.class.getName()));
	}
	
	@StreamListener(target = Sink.INPUT)
	public void consumerProductEvent(@Payload HistoricoPedido obj) {
		System.out.println("id produto: " + obj.getId());
		System.out.println("id instante: " + obj.getInstante());
		System.out.println("id nome produto: " + obj.getNomeProduto());
		System.out.println("id preco: " + obj.getValorProduto());
		repo.save(obj);
		}
	
	
	public HistoricoPedido insert(HistoricoPedido obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
