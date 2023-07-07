public class PersonajeOrco extends Personajes implements AtaquePersonaje {
    public PersonajeOrco(NombresApodos nombre, NombresApodos apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }

    @Override
    public double atacar() {
        //Fórmula Original (Da números muy grandes)
        // ( ((VA*ED)-PDEF)/500)*100 ) * 1.1

        //Puede que de números negativos, los cuales paso a positivos para que puedan restarse en la salud.
        return ((((this.VA/this.ED)-this.PDEF)/500.0)*100.0)*1.1;
    }
}
