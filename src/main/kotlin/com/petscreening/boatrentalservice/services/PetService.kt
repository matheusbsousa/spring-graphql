package com.petscreening.boatrentalservice.services

import com.petscreening.boatrentalservice.excpetions.ResourceNotFoundException
import com.petscreening.boatrentalservice.models.dto.PetDto
import com.petscreening.boatrentalservice.models.entities.Pet
import com.petscreening.boatrentalservice.models.filters.GetPetsFilter
import com.petscreening.boatrentalservice.models.inputs.PetInput
import com.petscreening.boatrentalservice.repositories.OwnerRepository
import com.petscreening.boatrentalservice.repositories.PetRepository
import com.petscreening.boatrentalservice.repositories.specifications.PetSpecification
import org.springframework.stereotype.Service

private const val MAX_WEIGHT = 25.0
private const val MINIMUM_TRAINING = 3

@Service
class PetService(private val petRepository: PetRepository, private val ownerRepository: OwnerRepository) {

    fun getPets(getPetsFilter: GetPetsFilter): List<PetDto> {

        return petRepository.findAll(
            PetSpecification.isUnderMaxWeight(getPetsFilter.isUnderMaxWeight)
                .and(PetSpecification.isVaccinated(getPetsFilter.isVaccinated))
                .and(PetSpecification.isNotPoodle(getPetsFilter.isNotPoodle))
                .and(PetSpecification.isAboveMinTrainingLevel(getPetsFilter.isAboveMinTrainingLevel))
                .and(PetSpecification.isBoatRentalEligible(getPetsFilter.isBoatRentalEligible))
        )
            .map { it.toDto() }
    }

    fun addPet(petInput: PetInput): PetDto {

        val owner = ownerRepository.findById(petInput.ownerId)
            .orElseThrow { ResourceNotFoundException("Owner with id ${petInput.ownerId} not found") }

        val pet = petInput.toEntity()
        pet.isBoatRentalEligible = checkBoatRentalEligibility(pet)
        pet.owner = owner
        val newPet = petRepository.save(pet)
        return newPet.toDto()
    }

    private fun checkBoatRentalEligibility(pet: Pet): Boolean {
        return pet.weight <= MAX_WEIGHT
                && pet.isVaccinated
                && pet.breed != "Poodle"
                && pet.trainingLevel >= MINIMUM_TRAINING
    }
}