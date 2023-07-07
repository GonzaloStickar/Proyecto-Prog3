public class PersonajeHumano extends Personajes implements AtaquePersonaje{
    public PersonajeHumano(String nombre, String apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }

    @Override
    public double atacar() {
        //((VA*ED)-PDEF)/500)*100
        return (((this.VA/this.ED)-this.PDEF)/500)*100;
    }
}
