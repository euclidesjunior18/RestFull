package org.example.repository;

import org.example.model.Produto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRepository {

    private static final ProdutoRepository INSTANCE = new ProdutoRepository();

    private static final Map<Long, Produto> produtos = new HashMap<>();

    public Produto salvar(Produto produto) {
        if (produtos.containsKey(produto.getId())) {
            produtos.replace(produto.getId(), produto);
        }

        produtos.put(produto.getId(), produto);
        return produto;
    }

    public Optional<Produto> getProdutoById(Long id) {
        return Optional.ofNullable(produtos.get(id));
    }

    public void deleteById(Long id) {
        produtos.remove(id);
    }

    public List<Produto> todosProdutos() {
        return produtos.values().stream().collect(Collectors.toList());
    }

    public static ProdutoRepository getInstance() {
        return INSTANCE;
    }
}
