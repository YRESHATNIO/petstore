package org.example.userstest;

import org.example.entities.Category;
import org.example.entities.Pet;
import org.example.entities.Status;
import org.example.entities.Tag;
import org.example.steps.PetServiceSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class PetServiceTest {

    @Test
    public void getPetByIdTest() {

        int petId = getRandomPetId(1, Integer.MAX_VALUE);

        Pet expectedPet = createPet(petId);
        Pet createdPet = PetServiceSteps.createPet(expectedPet);

        Pet pet = PetServiceSteps.getPetById(createdPet.getId());
        Assert.assertEquals(pet.getId(), petId, "Expected pet doesn't have correct Id");
    }

    @Test
    public void createPetTest() {
        int petId = getRandomPetId(1, Integer.MAX_VALUE);

        Pet expectedPet = createPet(petId + 1);
        Pet createdPet = PetServiceSteps.createPet(expectedPet);
        Assert.assertEquals(createdPet.getId(), expectedPet.getId(), "Expected pet doesn't have correct Id");
    }

    @Test
    public void deletePetTest() {
        int petId = getRandomPetId(1, Integer.MAX_VALUE);

        Pet createdPet = PetServiceSteps.createPet(createPet(petId));
        PetServiceSteps.deletePetById(createdPet.getId());
    }

    private Pet createPet(Integer petId) {
        return new Pet()
                .setId(petId)
                .setName("Ressy" + petId)
                .setCategory(new Category().setId(1).setName("Dog"))
                .setTags(List.of(new Tag().setId(1).setName("Waff"), new Tag().setId(2).setName("Gaff")))
                .setPhotoUrls(List.of("http://petstore.com/photo1", "http://petstore.com/photo2"))
                .setStatus(Status.AVAILABLE);
    }

    private int getRandomPetId(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
