package com.devops.metrics.rest;

import com.devops.metrics.models.frequenciaEntrega.FrequenciaEntregaPerformance;
import com.devops.metrics.models.frequenciaEntrega.FrequenciaEntregaPerformanceDescricao;
import com.devops.metrics.models.frequenciaEntrega.FrequenciasEntregaEstatisticas;
import com.devops.metrics.service.frequenciaEntrega.FrequenciaEntregaPerformanceDescricaoService;
import com.devops.metrics.service.frequenciaEntrega.FrequenciaEntregaPerformanceService;
import com.devops.metrics.service.frequenciaEntrega.FrequenciaEntregaService;
import com.devops.metrics.tracer.TracedWithRequestID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.GZIP;

@ApplicationScoped
@Path("Entrega")
@Tag(name = "Frequência de entrega", description = "Cálculo da frequência de entrega")
@TracedWithRequestID
public class FrequenciaEntregaResource {

    @Inject
    FrequenciaEntregaService frequenciaEntregaService;

    @Inject
    FrequenciaEntregaPerformanceService frequenciaEntregaPerformanceService;

    @Inject
    FrequenciaEntregaPerformanceDescricaoService frequenciaEntregaPerformanceDescricaoService;

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/namespace/{service}")
    @APIResponse(responseCode = "200",
        description = "Frequência de entrega",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = FrequenciasEntregaEstatisticas.class)))
    public Response frequenciaEntrega(@PathParam("service") String service,
                                      @DefaultValue("") @HeaderParam("X-Access-Fontes-Token") String token) {
        return Response.ok(frequenciaEntregaService.calculoFrequenciaEntrega(service, token)).build();
    }

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/frequencia_rating/{service}")
    @APIResponse(responseCode = "200",
        description = "Rating da frequência de entrega",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = FrequenciaEntregaPerformance.class)))
    public Response rating(@PathParam("service") String service,
                           @DefaultValue("") @HeaderParam("X-Access-Fontes-Token") String token) {
        return Response.ok(frequenciaEntregaPerformanceService.calculoFrequenciaEntregaPerformance(service, token)).build();
    }

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/frequencia_rating_regras")
    @APIResponse(responseCode = "200",
        description = "Descrição e regras do rating da frequência de entrega",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = FrequenciaEntregaPerformanceDescricao.class)))
    public Response ratingRegras() {
        return Response.ok(frequenciaEntregaPerformanceDescricaoService.gerarFrequenciaEntregaPerformanceDescricao()).build();
    }

}
