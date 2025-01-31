package net.ensah.trackingqueryservice.controller;

import net.ensah.coreapi.queries.GetTrackingViewById;
import net.ensah.trackingqueryservice.entity.TrackingView;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/query/track")
public class TrackingQueryController {

    private final QueryGateway queryGateway;
    private static final Logger log = LoggerFactory.getLogger(TrackingQueryController.class);

    public TrackingQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
     public CompletableFuture<ResponseEntity<TrackingView>>  getTrackingViews(
             @PathVariable(name="id") String id
    ) {
        log.info("Get tracking views");
         CompletableFuture<TrackingView> query = queryGateway.query(new GetTrackingViewById(id), ResponseTypes.instanceOf(TrackingView.class));
         return query.thenApply(ResponseEntity::ok);
     }

}
