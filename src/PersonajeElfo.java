public class PersonajeElfo extends Personajes implements AtaquePersonaje {
    public PersonajeElfo(NombresApodos nombre, NombresApodos apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }

    @Override
    public double atacar() {
        //Fórmula Original (Da números muy grandes)
        //( ((VA*ED)-PDEF)/500)*100 ) * 1.05

        //Puede que de números negativos, los cuales paso a positivos para que puedan restarse en la salud.
        return ((((this.VA/this.ED)-this.PDEF)/500.0)*100.0)*1.05;
    }
}
