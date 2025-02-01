package net.ensah.trackingqueryservice.repository;

import net.ensah.coreapi.enums.Location;
import net.ensah.coreapi.enums.ShipmentStatus;
import net.ensah.trackingqueryservice.entity.TrackingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class TrackingRepositoryTest {

    @Autowired
    private TrackingRepository trackingRepository;

    @BeforeEach
    void setUp() {
        TrackingView trackingView= new TrackingView(
                "tracking-id",
                 ShipmentStatus.IN_PROGRESS,
                Location.WAREHOUSE,
                true,
                LocalDate.now()
        );
        trackingRepository.save(trackingView);
    }

    @Test
    void ShouldFindTrackingViewById() {
        String trackingId = "tracking-id";
        TrackingView expected= new TrackingView(
                "tracking-id",
                ShipmentStatus.IN_PROGRESS,
                Location.WAREHOUSE,
                true,
                LocalDate.now()
        );
        Optional<TrackingView> result = trackingRepository.findById(trackingId);
        assertThat(result).isPresent();
        assertThat(result.get()).
                usingRecursiveComparison()
                .ignoringFields("archivedAt")
                .isEqualTo(expected);
    }

    @Test
    void ShouldNotFindTrackingViewById() {
        String trackingId = "tracking";
        TrackingView expected= new TrackingView(
                "tracking-id",
                ShipmentStatus.IN_PROGRESS,
                Location.WAREHOUSE,
                true,
                LocalDate.now()
        );
        Optional<TrackingView> result = trackingRepository.findById(trackingId);
        assertThat(result).isEmpty();

    }
}