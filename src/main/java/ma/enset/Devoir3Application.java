package ma.enset;

import ma.enset.entities.Patient;
import ma.enset.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Devoir3Application implements CommandLineRunner {
    //injection des dependances
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Devoir3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<100;i++){
            patientRepository.save(new Patient(null,"Najwa",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        }
       /* patientRepository.save(new Patient(null,"Awjan",new Date(),false,10));
        patientRepository.save(new Patient(null,"Najwa",new Date(),true,45));
        patientRepository.save(new Patient(null,"Hania",new Date(),false,78));
       */ //liste des patients
        //pagination pagerequest
        Page<Patient> patients=patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("Total des pages : "+patients.getTotalPages());
        System.out.println("Total des elemets : "+patients.getTotalElements());
        System.out.println("N° de page  : "+patients.getNumber());
        List<Patient> patientList=patients.getContent();
        //List<Patient> byMalade=patientRepository.findByMalade(true);
        Page<Patient> byMalade=patientRepository.findByMalade(true,PageRequest.of(0,4));
        List<Patient> patientList1=patientRepository.chercherPatients("%N%",780);
        System.out.println("Liste des patients");
        byMalade.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.isMalade());
        });
        System.out.println("**************************");
        Patient patient=patientRepository.findById(1L).orElseThrow(()->new RuntimeException("Patient n'existe pas"));
        // or Patient patient=patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        //update
        patient.setScore(870);
        patientRepository.save(patient);
        //suppression
        patientRepository.deleteById(1L);
        System.out.println("Liste des patients avec filtrage :");
        byMalade.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
        });
    }
}
