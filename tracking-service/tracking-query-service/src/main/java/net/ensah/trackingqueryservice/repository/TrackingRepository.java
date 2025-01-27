package net.ensah.trackingqueryservice.repository;

import net.ensah.trackingqueryservice.entity.TrackingView;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingView,String> {

    @NotNull Optional<TrackingView> findById(String id);
}
