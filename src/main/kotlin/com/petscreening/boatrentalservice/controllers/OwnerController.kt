package com.petscreening.boatrentalservice.controllers

import com.petscreening.boatrentalservice.models.dto.OwnerDto
import com.petscreening.boatrentalservice.models.inputs.OwnerInput
import com.petscreening.boatrentalservice.services.OwnerService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class OwnerController(private val ownerService: OwnerService) {

    @QueryMapping
    fun getOwners(): List<OwnerDto> {
        return ownerService.getOwners()
    }

    @MutationMapping
    fun addOwner(@Argument ownerInput: OwnerInput): OwnerDto {
        return ownerService.addOwner(ownerInput)
    }


}