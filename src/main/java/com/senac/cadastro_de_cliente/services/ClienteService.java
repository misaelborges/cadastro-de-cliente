package com.senac.cadastro_de_cliente.services;

import com.senac.cadastro_de_cliente.model.Cliente;
import com.senac.cadastro_de_cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um usuario com o id: " + id));
    }

    public Cliente salvarCliente(Cliente cliente) {
        if (cliente.getNome() == null && cliente.getNome().isBlank()) {
            throw new RuntimeException("Cliente não pode ser salvo");
        }

        return clienteRepository.save(cliente);
    }
}
