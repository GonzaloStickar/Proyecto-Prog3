public class PersonajeOrco extends Personajes implements AtaquePersonaje {
    public PersonajeOrco(String nombre, String apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }

    @Override
    public double atacar() {
        //Fórmula Original (Da números muy grandes)
        // ( ((VA*ED)-PDEF)/500)*100 ) * 1.1

        return ((((this.VA/this.ED)-this.PDEF)/500.0)*100.0)*1.1;
    }
}
