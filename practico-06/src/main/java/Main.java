import Entidades.AppPrograma;

public class Main {
    public static void main(String[] args) {

        AppPrograma appPrograma = new AppPrograma();
        appPrograma.iniciar();

        appPrograma.logicaDelPrograma();

        appPrograma.finalizar();
    }
}