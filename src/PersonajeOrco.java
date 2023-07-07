public abstract class PersonajeOrco extends Personajes {
    public PersonajeOrco(String nombre, String apodo) {
        super(nombre, apodo);
        this.raza = this.getClass().getName();
    }
}
