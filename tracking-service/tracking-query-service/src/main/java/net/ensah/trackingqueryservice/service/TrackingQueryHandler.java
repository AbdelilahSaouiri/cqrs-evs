package net.ensah.trackingqueryservice.service;


import net.ensah.coreapi.queries.GetTrackingViewById;
import net.ensah.trackingqueryservice.entity.TrackingView;
import net.ensah.trackingqueryservice.repository.TrackingRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class TrackingQueryHandler {

    private final TrackingRepository trackingRepository;
    private static final Logger log = LoggerFactory.getLogger(TrackingQueryHandler.class);

    public TrackingQueryHandler(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @QueryHandler
    public TrackingView getTrackingViews(GetTrackingViewById query){
        log.info("getTrackingViews by Id {}", query.getId());
       return trackingRepository.findById(query.getId()).orElseThrow(() -> new RuntimeException("No tracking view found"));
    }
}
