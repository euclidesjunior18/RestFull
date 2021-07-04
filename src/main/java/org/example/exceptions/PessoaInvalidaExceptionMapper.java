package org.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PessoaInvalidaExceptionMapper implements ExceptionMapper<PessoaInvalidaException> {
    @Override
    public Response toResponse(PessoaInvalidaException ex) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity(ex.getMessage()).
                build();
    }
}
