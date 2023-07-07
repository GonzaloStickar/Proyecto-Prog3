import java.util.Random;

public abstract class Personajes {

    //Datos
    public String raza; // (humanos/orcos/elfos)
    public NombresApodos nombre;
    public NombresApodos apodo;
    public String fechaNacimiento;
    public int edad; //entre 0 a 300
    public double salud; //100

    //Características
    public int velocidad;// 1 a 10
    public int destreza; //1 a 5
    public int fuerza;//1 a 10
    public int nivel; //1 a 10
    public int armadura; //1 a 10

    public double PD;
    //Poder de Disparo
    //Haga el producto de Destreza * Fuerza * Nivel del personaje que ataca.

    public double ED;
    //Efectividad de Disparo
    //Genere un valor aleatorio de 1 a 100. Considerarlo como valor porcentual.

    public double VA;
    //Valor de Ataque
    //Al Poder de Disparo lo multiplico por la Efectividad de Disparo.

    public double PDEF;
    //Poder de Defensa
    //Haga el producto de Armadura * Velocidad del personaje que defiende.

    public Personajes(NombresApodos nombre, NombresApodos apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNacimiento = crearValorEntreRangoRandom(1, 30) + "/" + crearValorEntreRangoRandom(1, 12) + "/" + crearValorEntreRangoRandom(-2000, 2000) + "/";
        this.edad = crearValorEntreRangoRandom(0, 300);
        this.salud = 100.0;
        this.velocidad = crearValorEntreRangoRandom(1, 10);
        this.destreza = crearValorEntreRangoRandom(1, 5);
        this.fuerza = crearValorEntreRangoRandom(1, 10);
        this.nivel = crearValorEntreRangoRandom(1, 10);
        this.armadura = crearValorEntreRangoRandom(1, 10);
        this.PD = this.destreza * this.fuerza * this.nivel;
        this.ED = crearValorEntreRangoRandom(1, 100);
        this.VA = this.PD * ED;
        this.PDEF = this.armadura * this.velocidad;
    }



    public void setSalud(double ataque) {
        this.salud = this.salud - (Math.abs(ataque));
    }

    public static int crearValorEntreRangoRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}