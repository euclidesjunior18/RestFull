package org.example.repository;

import org.example.model.Pessoa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PessoaRepository {

    private static final PessoaRepository INSTANCE = new PessoaRepository();

    private static final Map<Long, Pessoa> pessoas = new HashMap<>();

    public Pessoa salvar(Pessoa pessoa) {
        if (pessoas.containsKey(pessoa.getId())) {
            pessoas.replace(pessoa.getId(), pessoa);
        }

        pessoas.put(pessoa.getId(), pessoa);
        return pessoa;
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return Optional.ofNullable(pessoas.get(id));
    }

    public static void deleteById(Long id) {
        pessoas.remove(id);
    }

    public List<Pessoa> todasPessoas() {
        return pessoas.values().stream().collect(Collectors.toList());
    }

    public static PessoaRepository getInstance() {
        return INSTANCE;
    }

}
