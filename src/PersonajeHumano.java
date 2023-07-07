public abstract class PersonajeHumano extends Personajes {

    public PersonajeHumano(String nombre, String apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }
}
