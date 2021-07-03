package org.example.services;

import org.example.exceptions.ProdutoInvalidoException;
import org.example.model.Produto;
import org.example.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private static final ProdutoService INSTANCE = new ProdutoService();

    private ProdutoRepository produtoRepository = ProdutoRepository.getInstance();

    public Produto createProduto(Produto produto) {
        validarProduto(produto);
        if (produtoRepository.getProdutoById(produto.getId()).isPresent()){
            throw new ProdutoInvalidoException(String.format("Já existe produto com o código %d", produto.getId()));
        }

        return produtoRepository.salvar(produto);
    }

    public Optional<Produto> getProdutoById(Long id) {
        return produtoRepository.getProdutoById(id);
    }

    public Produto updateProduto(Produto produto) {
        validarProduto(produto);
        if (produtoRepository.getProdutoById(produto.getId()).isEmpty()) {
            throw new ProdutoInvalidoException(String.format("Não existe produto com o código %d", produto.getId()));
        }

        return produtoRepository.salvar(produto);
    }

    public void deleteProdutoById(Long id) {
        produtoRepository.deleteById(id);
    }

    private void validarProduto(Produto produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new ProdutoInvalidoException("A descrição do produto não foi informada");
        }
    }

    public static ProdutoService getInstance() {
        return INSTANCE;
    }

    public List<Produto> todosProdutos() {
        return produtoRepository.todosProdutos();
    }
}
