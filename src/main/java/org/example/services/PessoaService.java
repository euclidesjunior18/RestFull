package org.example.services;


import org.example.exceptions.PessoaInvalidaException;
import org.example.model.Pessoa;
import org.example.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

public class PessoaService {

    private static final PessoaService INSTANCE = new PessoaService();

    private PessoaRepository pessoaRepository = PessoaRepository.getInstance();

    public Pessoa createPessoa(Pessoa pessoa) {
        validarPessoa(pessoa);
        if (pessoaRepository.getPessoaById(pessoa.getId()).isPresent()){
            throw new PessoaInvalidaException(String.format("Já existe pessoa com o código %d", pessoa.getId()));
        }

        return pessoaRepository.salvar(pessoa);
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.getPessoaById(id);
    }

    public Pessoa updatePessoa(Pessoa pessoa) {
        validarPessoa(pessoa);
        if (pessoaRepository.getPessoaById(pessoa.getId()).isPresent()) {
            throw new PessoaInvalidaException(String.format("Não existe pessoa com o código %d", pessoa.getId()));
        }

        return pessoaRepository.salvar(pessoa);
    }

    public void deletePessoaById(Long id) {
        PessoaRepository.deleteById(id);
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new PessoaInvalidaException("O nome da pessoa não foi informado");
        }
    }

    public static PessoaService getInstance() {
        return INSTANCE;
    }

    public List<Pessoa> todasPessoas() {
        return pessoaRepository.todasPessoas();
    }
}
