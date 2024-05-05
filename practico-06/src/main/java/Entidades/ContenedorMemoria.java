package Entidades;

import Dao.Implementacion.*;
import Dao.Interfaces.*;

import java.util.ArrayList;
import java.util.List;


public class ContenedorMemoria {
    private MedicamentoDAO medicamentoDao;
    private ObraSocialDAO obraSocialDao;
    private EspecialidadDAO especialidadDao;
    private MedicoDAO medicoDao;
    private PacienteDAO pacienteDao;


    public ContenedorMemoria() {
        this.medicamentoDao = new MedicamentoDaoImpl();
        this.obraSocialDao = new ObraSocialDaoImpl();
        this.especialidadDao = new EspecialidadDaoImpl();
        this.medicoDao = new MedicoDaoImpl();
        this.pacienteDao = new PacienteDaoImpl();


        //MEDICAMENTOS
        Medicamento paracetamol = new Medicamento(1,"Paracetamol", 10);
        Medicamento ibuprofeno = new Medicamento(2,"Ibuprofeno", 20);
        Medicamento omeprazol = new Medicamento(3,"Omeprazol", 30);
        Medicamento aspirina = new Medicamento(4,"Aspirina", 40);
        Medicamento amoxicilina = new Medicamento(5,"Amoxicilina", 50);
        Medicamento ciprofloxacino = new Medicamento(6,"Ciprofloxacino", 60);
        Medicamento metformina = new Medicamento(7,"Metformina", 70);
        Medicamento losartan = new Medicamento(8,"Losartan", 80);
        Medicamento atorvastatina = new Medicamento(9,"Atorvastatina", 90);
        Medicamento sertralina = new Medicamento(10,"Sertralina", 100);
        Medicamento loratadina = new Medicamento(11,"Loratadina", 110);
        Medicamento diazepam = new Medicamento(12,"Diazepam", 120);
        Medicamento warfarina = new Medicamento(13,"Warfarina", 130);
        Medicamento metoprolol = new Medicamento(14,"Metoprolol", 140);
        Medicamento clopidogrel = new Medicamento(15,"Clopidogrel", 150);
        Medicamento rosuvastatina = new Medicamento(16,"Rosuvastatina", 160);
        Medicamento pantoprazol = new Medicamento(17,"Pantoprazol", 170);
        Medicamento enalapril = new Medicamento(18,"Enalapril", 180);
        Medicamento losartán = new Medicamento(19,"Losartán", 190);
        Medicamento peróxidoDeBenzoilo = new Medicamento(21,"Peróxido de Benzoilo", 210);
        Medicamento tretinoína = new Medicamento(22,"Tretinoína", 220);
        Medicamento hidroquinona = new Medicamento(23,"Hidroquinona", 230);
        Medicamento ácidoSalicílico = new Medicamento(24,"Ácido Salicílico", 240);


        this.medicamentoDao.registrar(paracetamol);
        this.medicamentoDao.registrar(ibuprofeno);
        this.medicamentoDao.registrar(omeprazol);
        this.medicamentoDao.registrar(aspirina);
        this.medicamentoDao.registrar(amoxicilina);
        this.medicamentoDao.registrar(ciprofloxacino);
        this.medicamentoDao.registrar(metformina);
        this.medicamentoDao.registrar(losartan);
        this.medicamentoDao.registrar(atorvastatina);
        this.medicamentoDao.registrar(sertralina);
        this.medicamentoDao.registrar(loratadina);
        this.medicamentoDao.registrar(diazepam);
        this.medicamentoDao.registrar(warfarina);
        this.medicamentoDao.registrar(metoprolol);
        this.medicamentoDao.registrar(clopidogrel);
        this.medicamentoDao.registrar(rosuvastatina);
        this.medicamentoDao.registrar(pantoprazol);
        this.medicamentoDao.registrar(enalapril);
        this.medicamentoDao.registrar(losartán);
        this.medicamentoDao.registrar(peróxidoDeBenzoilo);
        this.medicamentoDao.registrar(tretinoína);
        this.medicamentoDao.registrar(hidroquinona);
        this.medicamentoDao.registrar(ácidoSalicílico);

        //OBRAS SOCIALES
        ObraSocial osde = new ObraSocial(1,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(2,"SanCor Salud");
        ObraSocial swissMedical = new ObraSocial(3,"Swiss Medical");
        ObraSocial galeno = new ObraSocial(4,"Galeno");

        this.obraSocialDao.registrar(osde);
        this.obraSocialDao.registrar(sanCorSalud);
        this.obraSocialDao.registrar(swissMedical);
        this.obraSocialDao.registrar(galeno);

        //ESPECIALIDADES
        Especialidad cardiología = new Especialidad(1, "Cardiología");
        Especialidad dermatología = new Especialidad(2, "Dermatología");
        Especialidad endocrinología = new Especialidad(3, "Endocrinología");
        Especialidad gastroenterología = new Especialidad(4, "Gastroenterología");
        Especialidad hematología = new Especialidad(5, "Hematología");
        Especialidad infectología = new Especialidad(6, "Infectología");
        Especialidad neumonología = new Especialidad(8, "Neumonología");
        Especialidad medicoGeneral = new Especialidad(9, null);

        this.especialidadDao.registrar(cardiología);
        this.especialidadDao.registrar(dermatología);
        this.especialidadDao.registrar(endocrinología);
        this.especialidadDao.registrar(gastroenterología);
        this.especialidadDao.registrar(hematología);
        this.especialidadDao.registrar(infectología);
        this.especialidadDao.registrar(neumonología);
        this.especialidadDao.registrar(medicoGeneral);

        //OBRAS SOCIALES ACEPTADAS POR CADA MEDICO
        List<ObraSocial> obrasSocialesAceptadasA = new ArrayList<>();
        List<ObraSocial> obrasSocialesAceptadasB = new ArrayList<>();
        List<ObraSocial> obrasSocialesAceptadasC = new ArrayList<>();
        List<ObraSocial> obrasSocialesAceptadasD = new ArrayList<>();
        List<ObraSocial> obrasSocialesAceptadasE = new ArrayList<>();

        obrasSocialesAceptadasA.add(osde);
        obrasSocialesAceptadasA.add(galeno);
        obrasSocialesAceptadasB.add(osde);
        obrasSocialesAceptadasB.add(sanCorSalud);
        obrasSocialesAceptadasC.add(osde);
        obrasSocialesAceptadasC.add(sanCorSalud);
        obrasSocialesAceptadasC.add(swissMedical);
        obrasSocialesAceptadasD.add(osde);
        obrasSocialesAceptadasD.add(galeno);
        obrasSocialesAceptadasD.add(swissMedical);
        obrasSocialesAceptadasE.add(osde);
        obrasSocialesAceptadasE.add(sanCorSalud);
        obrasSocialesAceptadasE.add(galeno);
        obrasSocialesAceptadasE.add(swissMedical);

        //MEDICOS
        Medico juanPerez = new Medico(1, "Juan","Perez", cardiología, obrasSocialesAceptadasA);
        Medico pedroGomez = new Medico(2, "Pedro","Gomez", dermatología, obrasSocialesAceptadasB);
        Medico mariaRodriguez = new Medico(3, "Maria","Rodriguez", endocrinología, obrasSocialesAceptadasC);
        Medico carlosFernandez = new Medico(4, "Carlos","Fernandez", gastroenterología, obrasSocialesAceptadasD);
        Medico lauraRamirez = new Medico(5, "Laura","Ramirez", hematología, obrasSocialesAceptadasE);
        Medico robertoMorales = new Medico(6, "Roberto","Morales", infectología, obrasSocialesAceptadasA);
        Medico carmenVargas = new Medico(7, "Carme","nVargas", neumonología, obrasSocialesAceptadasB);
        Medico albertoSuarez = new Medico(8, "Albert","Suarez", cardiología, obrasSocialesAceptadasC);
        Medico patriciaMendoza = new Medico(9, "Patricia","Mendoza", dermatología, obrasSocialesAceptadasD);
        Medico luisGuzman = new Medico(10, "Luis","Guzman", endocrinología, obrasSocialesAceptadasE);
        Medico anaCastillo = new Medico(11, "Ana","Castillo", gastroenterología, obrasSocialesAceptadasA);
        Medico jorgeTorres = new Medico(12, "Jorge","Torres", hematología, obrasSocialesAceptadasB);
        Medico teresaParedes = new Medico(13, "Teresa","Paredes", infectología, obrasSocialesAceptadasC);
        Medico guillermoPena = new Medico(14, "Guillermo","Pena", neumonología, obrasSocialesAceptadasD);
        Medico rosaNavarro = new Medico(15, "Rosa","Navarro", cardiología, obrasSocialesAceptadasE);
        Medico eduardoCordero = new Medico(16, "Eduardo","Cordero", medicoGeneral, obrasSocialesAceptadasA);

        this.medicoDao.registrar(juanPerez);
        this.medicoDao.registrar(pedroGomez);
        this.medicoDao.registrar(mariaRodriguez);
        this.medicoDao.registrar(carlosFernandez);
        this.medicoDao.registrar(lauraRamirez);
        this.medicoDao.registrar(robertoMorales);
        this.medicoDao.registrar(carmenVargas);
        this.medicoDao.registrar(albertoSuarez);
        this.medicoDao.registrar(patriciaMendoza);
        this.medicoDao.registrar(luisGuzman);
        this.medicoDao.registrar(anaCastillo);
        this.medicoDao.registrar(jorgeTorres);
        this.medicoDao.registrar(teresaParedes);
        this.medicoDao.registrar(guillermoPena);
        this.medicoDao.registrar(rosaNavarro);
        this.medicoDao.registrar(eduardoCordero);

        //PACIENTES
        Paciente juliaLopezPaciente = new Paciente(1, "Julia", "Lopez", swissMedical);
        Paciente mariaGomezPaciente = new Paciente(2, "Maria", "Gomez", sanCorSalud);
        Paciente carlosLopezPaciente = new Paciente(3, "Carlos", "Lopez", swissMedical);
        Paciente anaMartinezPaciente = new Paciente(4, "Ana", "Martinez", galeno);
        Paciente pedroRodriguezPaciente = new Paciente(5, "Pedro", "Rodriguez", osde);
        Paciente lauraGarciaPaciente = new Paciente(6, "Laura", "Garcia", sanCorSalud);
        Paciente diegoFernandezPaciente = new Paciente(7, "Diego", "Fernandez", swissMedical);
        Paciente luciaDiazPaciente = new Paciente(8, "Lucia", "Diaz", galeno);
        Paciente martinSanchezPaciente = new Paciente(9, "Martin", "Sanchez", osde);
        Paciente sofiaAlvarezPaciente = new Paciente(10, "Sofia", "Alvarez", sanCorSalud);

        this.pacienteDao.registrar(juliaLopezPaciente);
        this.pacienteDao.registrar(mariaGomezPaciente);
        this.pacienteDao.registrar(carlosLopezPaciente);
        this.pacienteDao.registrar(anaMartinezPaciente);
        this.pacienteDao.registrar(pedroRodriguezPaciente);
        this.pacienteDao.registrar(lauraGarciaPaciente);
        this.pacienteDao.registrar(diegoFernandezPaciente);
        this.pacienteDao.registrar(luciaDiazPaciente);
        this.pacienteDao.registrar(martinSanchezPaciente);
        this.pacienteDao.registrar(sofiaAlvarezPaciente);

    }
}