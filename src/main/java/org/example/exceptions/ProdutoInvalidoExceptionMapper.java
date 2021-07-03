package org.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProdutoInvalidoExceptionMapper implements ExceptionMapper<ProdutoInvalidoException> {

    @Override
    public Response toResponse(ProdutoInvalidoException ex) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity(ex.getMessage()).
                build();
    }

}
