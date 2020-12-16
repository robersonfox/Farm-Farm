package br.com.cleancode.adapters.database.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleancode.adapters.database.h2.models.AnimalsModel;

@Repository
public interface IAnimalRepository extends JpaRepository<AnimalsModel, Long> {

}
