# Loteria primitiva
> ## Programación java para practicar estructuras de datos
### Enunciado

En esta práctica vamos a realizar una versión especial del famoso juego de azar de la lotería primitiva.

#### ¿En que consiste el juego de la lotería Primitiva? 

Se trata de escoger 6 números de un total de 49. En el sorteo se sacan 6 bolas correspondientes a la combinación ganadora, una bola para el complementario y finalmente otra para el reintegro.

#### Tipos de apuestas

En el juego real existen apuestas múltiples pero nosotros sólo haremos apuestas sencillas, en las que el jugador escoge 6 números de los 49.

#### ¿Cuando se realizan los sorteos? 

Los sorteos se realizan los lunes, jueves y sábados de cada semana. No obstante, nosotros no contemplaremos fechas y lo que haremos será jugar nuestras apuestas un número arbitrario de veces, como si tuviésemos una máquina del tiempo.

#### Premios

Contemplaremos los siguientes casos:

6 aciertos
5 aciertos más el número complementario
5 aciertos
4 aciertos
3 aciertos
Reintegro

### Ejemplo de ejecución

A continuación se muestran algunos ejemplos de la ejecución de la aplicación:

#### Ejemplo 1: Caso de un boleto jugado con una única apuesta: {4,8,12,23,34,44} para tres sorteos.

![Inicio](https://github.com/dcolomer/Loteria-primitiva/blob/main/img-docs/1.png)

De la anterior imagen vemos como no hemos tenido mucha suerte, puesto que en tres sorteos que hemos jugado la apuesta sólo hemos recuperado el desembolso de uno de ellos.

Adicionalmente podríamos mostrar algunas estadísticas, por ejemplo el número de aciertos de cada categoría de premios y la frecuencia con la que aparecen los números en los sorteos. En la siguiente imagen se muestra esto:

![Inicio](https://github.com/dcolomer/Loteria-primitiva/blob/main/img-docs/2.png)

#### Ejemplo 2: Caso en el que jugamos dos apuestas en cinco sorteos

![Inicio](https://github.com/dcolomer/Loteria-primitiva/blob/main/img-docs/3.png)

De la imagen anterior apreciamos como hemos obtenido un reintegro en el segundo sorteo y tres aciertos en el tercero.

#### Ejemplo 3: Caso en el que jugamos dos apuestas en mil sorteos.

En este caso, como obtendremos bastantes premios de reintegro y de tres aciertos haremos que sólo se nos informe por pantalla de los premios a partir de cuatro aciertos. La información a mostrar será:
    • La apuesta que tiene cuatro o más aciertos
    • El sorteo donde se han producido los aciertos

![Inicio](https://github.com/dcolomer/Loteria-primitiva/blob/main/img-docs/4.png)

Bien, en este caso tenemos que de los mil sorteos en treinta ocasiones hemos acertado tres números y en una cuatro números.

#### Ejemplo 4: Caso en el que jugamos dos apuestas en cien mil sorteos

Nota: Sólo mostraremos aciertos a partir de cinco aciertos:

![Inicio](https://github.com/dcolomer/Loteria-primitiva/blob/main/img-docs/5.png) 

De la imagen anterior vemos como sólo en dos ocasiones hemos obtenido 5 aciertos.

### Sugerencias para la implementación de la práctica

Podéis crear tres clases:

    • Apuesta: Clase que modela una apuesta. Una apuesta consiste en:
        ◦ Unos números que forman la combinación 
        ◦ Un numero complementario
        ◦ Un numero de reintegro

    • Sorteo: Clase que realiza el sorteo y permite comprobar las jugadas de los usuarios con el resultado del sorteo. Tal resultado se almacena en un atributo de tipo Apuesta. Por tanto, esta clase dispondrá de los servicios sortear() y comprobarApuestas().

    • Juego: Clase principal donde se realiza el juego. Se crea un boleto con N apuestas y se juegan un cierto número de veces. Para esto se llaman a los servicios 'sortear()' y 'comprobarApuestas()' de la clase Sorteo. Si finalmente hay aciertos se muestran por pantalla.


### ¿Cómo generar números aleatorios?

El paquete java.util contiene la clase Random, la cual nos permite obtener números de forma aleatoria.

Por ejemplo, mediante el código siguiente obtenemos un número entre 1-100 (ambos inclusive):

Random rnd = new Random();
short un_numero=(short) ((short) (rnd.nextDouble() * 100)+1);

### ¿Cómo ordenar estructuras?

Para ordenar estructuras del framework Collections: Collections.sort(miLista)
Para ordenar arrays: Arrays.sort(miArray)

### ¿Cómo buscar elementos en estructuras?

Buscar un elemento en una colección: Collections.binarySearch(miLista, un_numero)
Buscar un elemento en un array: Arrays.binarySearch(miArray, un_numero)

### ¿Cómo crear una lista a partir de un array?

List<Integer> lista=Arrays.asList(miArray);

### Cómo definir y usar Mapas

Los mapas nos permiten almacenar parejas claves-valor. Por ejemplo, podríamos utilizar uno para almacenar el sueldo de cada categoría profesional:

Map<Short,Float> mapaSalarios=new TreeMap<Short,Float>(); 
mapaSalarios.put(1, 1200.50F);
mapaSalarios.put(2, 1300.40F);

// Ahora recorremos el mapa y mostramos su contenido
Iterator<?> it = mapaSalarios.entrySet().iterator();

while (it.hasNext()) {
Map.Entry<Short,Long> e = (Map.Entry<Short,Long>)it.next();
System.out.println("clave: "+e.getKey()+" valor: "+e.getValue());
}
