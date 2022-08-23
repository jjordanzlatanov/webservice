package com.company.webservice.resources;

import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/receive")
public class BlockResource {
    private final Jdbi jdbi;

    public BlockResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
}
