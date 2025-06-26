    package com.mover.repositories;

    import com.mover.entities.transporterrelated.TransporterAddress;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface TransporterAddressRepository extends JpaRepository<TransporterAddress, Long> {
        TransporterAddress findByTransporterId(Long transporterId);
    }
