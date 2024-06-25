package com.petscreening.boatrentalservice.controllers

import com.petscreening.boatrentalservice.models.dto.PetDto
import com.petscreening.boatrentalservice.models.filters.GetPetsFilter
import com.petscreening.boatrentalservice.models.inputs.PetInput
import com.petscreening.boatrentalservice.services.PetService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class PetController(private val petService: PetService) {


    @QueryMapping
    fun getPets(@Argument getPetsFilter: GetPetsFilter): List<PetDto> {
        return petService.getPets(getPetsFilter)
    }

    @MutationMapping
    fun addPet(@Argument petInput: PetInput): PetDto {
        return petService.addPet(petInput)
    }

}