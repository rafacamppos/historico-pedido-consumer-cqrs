package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.HistoricoPedido;
import com.example.demo.dto.HistoricoPedidoDTO;
import com.example.demo.services.HistoricoPedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/historico-pedidos")
public class HistoricoPedidoResource {
	
	@Autowired
	private HistoricoPedidoService service;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HistoricoPedido> find(@PathVariable Integer id) throws ObjectNotFoundException{
		HistoricoPedido obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List <HistoricoPedidoDTO>> findAll(){
		List<HistoricoPedido> list = service.findAll();
		List<HistoricoPedidoDTO> listDTO = list.stream().map(obj -> new HistoricoPedidoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
