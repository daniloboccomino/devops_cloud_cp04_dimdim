package br.com.fiap.rest;

import static br.com.fiap.rest.ResponseConstants.BAD_REQUEST;
import static br.com.fiap.rest.ResponseConstants.INTERNAL_SERVER_ERROR;
import static br.com.fiap.rest.ResponseConstants.NOT_FOUND;
import static br.com.fiap.rest.ResponseConstants.OK;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.BankSlipDAO;
import br.com.fiap.model.BankSlip;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
public class BankSlipEndpoint {
	
	private BankSlipDAO dao = new BankSlipDAO();

	@GET
	public List<BankSlip> index() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(BankSlip bankSlip) {
		if (bankSlip == null) {
			return BAD_REQUEST;
		}
		try {
			dao.save(bankSlip);
			return Response.status(Response.Status.CREATED).entity(bankSlip).build();
		} catch (Exception e) {
			return INTERNAL_SERVER_ERROR;
		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		if (id == null) {
			return BAD_REQUEST;
		}
		BankSlip bankSlip = dao.findById(id);
		if (bankSlip == null) {
			return NOT_FOUND;
		}
		return Response.status(Response.Status.OK).entity(bankSlip).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, BankSlip bankSlip) {
		if (id == null || bankSlip == null) {
			return BAD_REQUEST;
		}
		if (dao.findById(id) == null) {
			return NOT_FOUND;
		}
		bankSlip.setId(id);
		try {
			dao.update(bankSlip);
			return Response.status(Response.Status.OK).entity(bankSlip).build();
		} catch (Exception e) {
			return INTERNAL_SERVER_ERROR;
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response destroy(@PathParam("id") Long id) {
		if (id == null) {
			return BAD_REQUEST;
		}
		BankSlip bankSlip = dao.findById(id);
		if (bankSlip == null) {
			return NOT_FOUND;
		}
		try {
			dao.delete(bankSlip);
			return OK;
		} catch (Exception e) {
			return INTERNAL_SERVER_ERROR;
		}
	}
	
}
