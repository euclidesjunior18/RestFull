package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.model.Pessoa;
import org.example.services.PessoaService;
import org.example.services.ProdutoService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Api("API de Pessoas")
@Path("pessoas")
public class PessoaController {
    private final PessoaService pessoaService = PessoaService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPessoa(Pessoa pessoa) {
        Pessoa savedPessoa = pessoaService.createPessoa(pessoa);
        return Response.created(URI.create(String.format("/pessoas/%d", savedPessoa.getId()))).entity(savedPessoa).build();
    }

    @ApiOperation(
            value = "Retorna uma pessoa apartir do seu ID",
            produces = MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Dados da Pessoa",
                    response = Pessoa.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Pessoa não encontrada!"
            )
    })
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPessoa(@PathParam("id") Long id) {
        Optional<Pessoa> pessoa = pessoaService.getPessoaById(id);

        if (pessoa.isPresent()) {
            return Response.ok(pessoa).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePessoas(Pessoa pessoa) {
        return Response.ok(pessoaService.updatePessoa(pessoa)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePessoa(@PathParam("id") Long id) {
        pessoaService.deletePessoaById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @ApiOperation(
            value = "Retorna a lista de todas as pessoas",
            produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todasPessoas() {
        return Response.ok(pessoaService.todasPessoas()).build();
    }

}
