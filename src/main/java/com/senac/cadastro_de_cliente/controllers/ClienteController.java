package com.senac.cadastro_de_cliente.controllers;

import com.senac.cadastro_de_cliente.model.Cliente;
import com.senac.cadastro_de_cliente.repositories.ClienteRepository;
import com.senac.cadastro_de_cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listatClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping("/salvar")
    public Cliente salvarCleinte(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }
}
