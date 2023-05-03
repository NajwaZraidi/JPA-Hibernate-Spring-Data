package ma.enset.repositories;

import ma.enset.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

//Mapping objet relationel
public interface PatientRepository extends JpaRepository<Patient,Long> {
public List<Patient> findByMalade(boolean m);
Page<Patient> findByMalade(boolean m, Pageable pageable);
List<Patient> findByMaladeAndScoreLessThan(boolean m,int score);
List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);
List<Patient> findByDateNaissanceBetween(Date d1, Date d2);
List<Patient> findByDateNaissanceBetweenAndMaladeIsTrue(Date d1, Date d2);
List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2,String mc);
//2eme methode pour l'affichage
@Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
List<Patient> chercherPatients(@Param("x") Date d1,@Param("y") Date d2,@Param("x") String mc);
@Query("select p from Patient p where p.nom like :N and p.score<:S ")
List<Patient> chercherPatients(@Param("N") String nom,@Param("S") int scoreMin);


}
