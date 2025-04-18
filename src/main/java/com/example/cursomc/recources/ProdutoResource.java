package com.example.cursomc.recources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Pedido;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.dto.ProdutoDTO;
import com.example.cursomc.recources.utils.URL;
import com.example.cursomc.services.PedidoService;
import com.example.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam (value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam (value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam (value="direction", defaultValue="ASC")String direction){
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded ,ids ,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}


}
