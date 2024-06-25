package com.petscreening.boatrentalservice.services


import com.petscreening.boatrentalservice.models.dto.OwnerDto
import com.petscreening.boatrentalservice.models.inputs.OwnerInput
import com.petscreening.boatrentalservice.repositories.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {

    fun getOwners(): List<OwnerDto> {
        return ownerRepository.findAll().map { it.toDto() }
    }

    fun addOwner(ownerInput: OwnerInput): OwnerDto {
        val owner = ownerInput.toEntity()
        val newOwner = ownerRepository.save(owner)
        return newOwner.toDto()
    }
}