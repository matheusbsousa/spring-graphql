package com.petscreening.boatrentalservice.repositories

import com.petscreening.boatrentalservice.models.entities.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet>