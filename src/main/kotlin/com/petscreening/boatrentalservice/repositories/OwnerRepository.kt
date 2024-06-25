package com.petscreening.boatrentalservice.repositories

import com.petscreening.boatrentalservice.models.entities.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : JpaRepository<Owner, Long>