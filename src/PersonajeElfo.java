public class PersonajeElfo extends Personajes implements AtaquePersonaje {
    public PersonajeElfo(NombresApodos nombre, NombresApodos apodo) {
        super(nombre, apodo);
        this.raza = "Elfo";
    }

    public PersonajeElfo(String nombre,
                         String apodo,
                         String fechaNacimiento,
                         int edad,
                         double salud,
                         int velocidad,
                         int destreza,
                         int fuerza,
                         int nivel,
                         int armadura) {
        super(nombre,apodo,fechaNacimiento,edad,salud,velocidad,destreza,fuerza,nivel,armadura);
        this.raza = this.getClass().getName();
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.salud = salud;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
    }

    @Override
    public double atacar() {
        //Fórmula Original (Da números muy grandes)
        //( ((VA*ED)-PDEF)/500)*100 ) * 1.05

        //Puede que de números negativos, los cuales paso a positivos para que puedan restarse en la salud.
        return ((((this.VA/this.ED)-this.PDEF)/500.0)*100.0)*1.05;
    }
}
