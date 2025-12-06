package com.devops.metrics.rest;

import com.devops.metrics.exceptions.MetricsException;
import com.devops.metrics.k8s.Cluster.Environment;
import com.devops.metrics.models.tempoEntrega.TempoEntregaEstatisticas;
import com.devops.metrics.models.tempoEntrega.TempoEntregaPerformance;
import com.devops.metrics.models.tempoEntrega.TempoEntregaPerformanceDescricao;
import com.devops.metrics.service.tempoEntrega.TempoEntregaPerformanceDescricaoService;
import com.devops.metrics.service.tempoEntrega.TempoEntregaPerformanceService;
import com.devops.metrics.service.tempoEntrega.TempoEntregaService;
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
@Path("tempoEntrega")
@Tag(name = "TempoEntrega", description = "Cálculo do tempo de entrega de uma ou mais aplicações")
@TracedWithRequestID
public class TempoEntregaResource {

    @Inject
    TempoEntregaService tempoEntregaService;

    @Inject
    TempoEntregaPerformanceService tempoEntregaPerformanceService;

    @Inject
    TempoEntregaPerformanceDescricaoService tempoEntregaPerformanceDescricaoService;

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/namespace/{service}")
    @APIResponse(responseCode = "200",
        description = "Cálculo do tempo de entrega",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = TempoEntregaEstatisticas.class)))
    public Response tempoEntrega(@PathParam("service") String service,
                                 @DefaultValue("") @QueryParam("data_inicio") String dataInicio,
                                 @DefaultValue("") @QueryParam("data_fim") String dataFim,
                                 @DefaultValue("") @HeaderParam("X-Access-Fontes-Token") String token) {

        var estatisticas = tempoEntregaService
            .criarEstatisticasTempoEntrega(service, token, dataInicio, dataFim);

        return Response.ok(estatisticas).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/lista")
    public Response lista() {
        return Response.ok(tempoEntregaService.namespacesFromCluster(Environment.PRD)).build();
    }

    @GET
    @Path("/apps")
    public Response tempoEntregaMs(@DefaultValue("") @QueryParam("data_inicio") String dataInicio,
                                       @DefaultValue("") @QueryParam("data_fim") String dataFim) {
        var estatiscasGroupMap = tempoEntregaService.criarEstatisticaMs(dataInicio, dataFim);
        return Response.ok(estatiscasGroupMap).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/app/{app}")
    public Response tempoEntregaSiglaUnica(@DefaultValue("") @QueryParam("data_inicio") String dataInicio,
                                           @DefaultValue("") @QueryParam("data_fim") String dataFim,
                                           @DefaultValue("") @PathParam("app") String app) throws MetricsException {
        var estatiscasGroupMap = tempoEntregaService.criarEstatisticaSiglaUnica(app, dataInicio, dataFim);
        return Response.ok(estatiscasGroupMap).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/todos")
    public Response tempoEntregaTodos(@DefaultValue("") @QueryParam("data_inicio") String dataInicio,
                                      @DefaultValue("") @QueryParam("data_fim") String dataFim) {
        var estatisticasGeral = tempoEntregaService.criarEstatisticasGeral(dataInicio, dataFim);
        return Response.ok(estatisticasGeral).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/performance/{service}")
    @APIResponse(responseCode = "200",
        description = "Performance do tempo de entrega do serviço",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = TempoEntregaPerformance.class)))
    public Response rating(@PathParam("service") String service,
                           @DefaultValue("") @HeaderParam("X-Access-Fontes-Token") String token) {
        return Response.ok(tempoEntregaPerformanceService.calculoTempoEntregaPerformance(service, token)).build();
    }

    @GET
    @GZIP
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/performance_regras")
    @APIResponse(responseCode = "200",
        description = "Descrição e regras da performance do tempo de entrega",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = SchemaType.OBJECT, implementation = TempoEntregaPerformanceDescricao.class)))
    public Response ratingRegras() {
        return Response.ok(tempoEntregaPerformanceDescricaoService.gerarTempoEntregaPerformanceDescricao()).build();
    }

}
