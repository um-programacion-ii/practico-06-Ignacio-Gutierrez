package Entidades;

import Dao.Implementacion.*;
import Dao.Interfaces.*;

import java.util.ArrayList;
import java.util.List;



public class ContenedorMemoria {
    private static ContenedorMemoria instance;

    private MedicamentoDAO medicamentoDao = new MedicamentoDaoImpl();
    private ObraSocialDAO obraSocialDao = new ObraSocialDaoImpl();
    private EspecialidadDAO especialidadDao = new EspecialidadDaoImpl();
    private MedicoDAO medicoDao = new MedicoDaoImpl();
    private PacienteDAO pacienteDao = new PacienteDaoImpl();
    private TurnoDAO turnoDao = new TurnoDaoImpl();
    private RecetaDAO recetaDao = new RecetaDaoImpl();

    public ContenedorMemoria() {

        //MEDICAMENTOS
        Medicamento paracetamol = new Medicamento(1,"Paracetamol", 0);
        Medicamento ibuprofeno = new Medicamento(2,"Ibuprofeno", 0);
        Medicamento omeprazol = new Medicamento(3,"Omeprazol", 0);
        Medicamento aspirina = new Medicamento(4,"Aspirina", 0);
        Medicamento amoxicilina = new Medicamento(5,"Amoxicilina", 0);
        Medicamento ciprofloxacino = new Medicamento(6,"Ciprofloxacino", 0);
        Medicamento metformina = new Medicamento(7,"Metformina", 0);
        Medicamento losartan = new Medicamento(8,"Losartan", 0);
        Medicamento atorvastatina = new Medicamento(9,"Atorvastatina", 0);
        Medicamento sertralina = new Medicamento(10,"Sertralina", 0);
        Medicamento loratadina = new Medicamento(11,"Loratadina", 0);
        Medicamento diazepam = new Medicamento(12,"Diazepam", 0);
        Medicamento warfarina = new Medicamento(13,"Warfarina", 0);
        Medicamento metoprolol = new Medicamento(14,"Metoprolol", 0);
        Medicamento clopidogrel = new Medicamento(15,"Clopidogrel", 0);
        Medicamento rosuvastatina = new Medicamento(16,"Rosuvastatina", 0);
        Medicamento pantoprazol = new Medicamento(17,"Pantoprazol", 0);
        Medicamento enalapril = new Medicamento(18,"Enalapril", 0);
        Medicamento losartán = new Medicamento(19,"Losartán", 0);
        Medicamento peróxidoDeBenzoilo = new Medicamento(21,"Peróxido de Benzoilo", 0);
        Medicamento tretinoína = new Medicamento(22,"Tretinoína", 0);
        Medicamento hidroquinona = new Medicamento(23,"Hidroquinona", 0);
        Medicamento ácidoSalicílico = new Medicamento(24,"Ácido Salicílico", 0);


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
        ObraSocial ninguna = new ObraSocial(1,"Ninguna");
        ObraSocial osde = new ObraSocial(2,"OSDE");
        ObraSocial sanCorSalud = new ObraSocial(3,"SanCor Salud");
        ObraSocial swissMedical = new ObraSocial(4,"Swiss Medical");
        ObraSocial galeno = new ObraSocial(5,"Galeno");

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
        Especialidad medicoGeneral = new Especialidad(9, "Medico General");

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
        Paciente diegoFernandezPaciente = new Paciente(7, "Diego", "Fernandez", ninguna);
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

    public static ContenedorMemoria getInstance() {
        if (instance == null) {
            instance = new ContenedorMemoria();
        }
        return instance;
    }


    public MedicamentoDAO getMedicamentoDao() {
        return this.medicamentoDao;
    }

    public ObraSocialDAO getObraSocialDao() {
        return this.obraSocialDao;
    }

    public EspecialidadDAO getEspecialidadDao() {
        return this.especialidadDao;
    }

    public MedicoDAO getMedicoDao() {
        return this.medicoDao;
    }

    public PacienteDAO getPacienteDao() {
        return this.pacienteDao;
    }

    public TurnoDAO getTurnoDao() {
        return this.turnoDao;
    }

    public RecetaDAO getRecetaDao() {
        return this.recetaDao;
    }

}