package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.model.Produto;
import org.example.services.ProdutoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Api("API de Produtos")
@Path("produtos")
public class ProdutoController {

    private final ProdutoService produtoService = ProdutoService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduto(Produto produto) {
        Produto savedProduto = produtoService.createProduto(produto);
        return Response.created(URI.create(String.format("/produtos/%d", savedProduto.getId()))).entity(savedProduto).build();
    }

    @ApiOperation(
            value = "Retorna um produto a partir do seu ID",
            produces = MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Dados do produto",
                    response = Produto.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Produto não encontrado"
            )
    })
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduto(@PathParam("id") Long id) {
        Optional<Produto> produto = produtoService.getProdutoById(id);

        if (produto.isPresent()) {
            return Response.ok(produto).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduto(Produto produto) {
        return Response.ok(produtoService.updateProduto(produto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduto(@PathParam("id") Long id) {
        produtoService.deleteProdutoById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @ApiOperation(
            value = "Retorna a lista de todos os produtos",
            produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todosProdutos() {
        return Response.ok(produtoService.todosProdutos()).build();
    }

}
