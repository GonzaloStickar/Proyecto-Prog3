public abstract class PersonajeElfo extends Personajes {
    public PersonajeElfo(String nombre, String apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }
}
