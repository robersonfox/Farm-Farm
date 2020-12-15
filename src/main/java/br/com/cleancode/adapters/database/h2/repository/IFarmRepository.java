package br.com.cleancode.adapters.database.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleancode.adapters.database.h2.models.FarmModel;

@Repository
public interface IFarmRepository extends JpaRepository<FarmModel, Long> {

}
