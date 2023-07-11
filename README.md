# JUnit 5

---

## Creando y configurando el proyecto con JUnit 5

Creamos un proyecto java con maven desde nuestro ide IntelliJ IDEA. Luego **agregamos la dependencia de JUnit 5:**

````xml

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
````

**NOTA**

- Al momento de seleccionar las dependencias puede que nos encontremos con las siguientes:
    - **junit-jupiter-api:** api para desarrollar los test unitarios con junit.
    - **junit-jupiter-engine:** es el plataform, el motor que se encargará de ejecutar las pruebas.
    - **junit-jupiter:** esta es la dependencia que elegimos para nuestro proyecto, ya que contiene las dos dependencias
      anteriores.
- En la dependencia de JUnit 5 en el **scope** se colocó en automático **test**, esto indica que la dependencia es
  necesaria exclusivamente para ejecutar las pruebas, pero no para usar el proyecto. Un ejemplo de dependencia con scope
  test es JUnit, que se usa para las pruebas, pero no es necesaria para que nuestro proyecto funcione.
- Estamos trabajando con la versión 17 de java.

## Creando la clase que usaremos para hacer pruebas

Crearemos nuestra clase Account (Cuenta) con los atributos person y balance (saldo):

````java
public class Account {
    private String person;
    private BigDecimal balance;

    /* getters and setters */
}
````

## Escribiendo y ejecutando primeras pruebas unitarias con Assertions

Crearemos una **clase de test** para nuestra clase **Account**, podemos hacerlo de forma manual, pero aprovecharemos el
IDE de IntelliJ IDEA para crear en automático nuestra clase de prueba, para eso debemos posicionarnos **dentro de la
clase que queremos probar** y presionar las siguientes teclas:

````
Ctrl + Shift + T
````

La combinación anterior puede hacer lo siguiente:

- Creará una clase test a partir de la clase base (el puntero del mouse debe estar dentro de la clase base).
- Si la clase test ya fue creada y tiene al menos un método de test, permite ir desde esa clase test hacia la clase
  base.
- Si estamos en la clase base, podemos ir a la clase test o crear un nuevo test para la clase base.

Al crear nuestra clase test de manera automática **(Ctrl + shift + T)** la clase de prueba creada se vería así:

````java
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

}
````

Observamos una importación estática que se hizo al crear automáticamente la clase de prueba
``import static org.junit.jupiter.api.Assertions.*;``, esta importación no permitirá utilizar los distintos métodos
estáticos que JUnit nos provee para realizar las pruebas como por ejemplo: ``assertEquals(...), assertTrue(...),
assertThrows(...), etc``.

Crearemos un método de prueba llamado **accountNameTest**, para eso presionamos:

````
Alt + insert
Seleccionamos Test Method
````

````java
class AccountTest {
    @Test
    void accountNameTest() {

    }
}
````

**NOTA**

> Por buenas prácticas tanto la clase como los métodos **deben tener un método de acceso del tipo package (default)**,
> es por eso que vemos que se escribe directamente la clase: ``class AccountTest{}`` y no un
> ``public class AccountTest{}``, lo mismo con el método. Esto es porque las pruebas deberían poder ser accedidas
> únicamente desde el contexto de ejecución de test.

## Creando nuestra primera prueba

Para crear nuestra primera prueba, vamos a agregar un constructor a nuestra clase **Account** quien recibirá dos
parámetros **person y balance**. El parámetro **balance** lo asignaremos al atributo privado de la clase, mientras que
**intencionalmente el parámetro person no será asignado al atributo person de la clase**:

````java
public class Account {
    private String person;
    private BigDecimal balance;

    public Account(String person, BigDecimal balance) {
        this.balance = balance;
    }
    /* Getters and setters */
}
````

Entonces en nuestro método test crearemos un objeto **Account("Martín", new BigDecimal("2000"))** y debemos comprobar
que el método **getPerson()** nos retorne el valor de **Martín**, porque es lo que estamos pasando por parámetro, pero
como intencionalmente **NOS HEMOS OLVIDADO** de asignar el parámetro al atributo, es que la prueba debe fallar:

````java
class AccountTest {
    @Test
    void accountNameTest() {
        Account account = new Account("Martín", new BigDecimal("2000"));

        String expected = "Martín";
        String real = account.getPerson();

        assertEquals(expected, real);
    }
}
````

Para ejecutar el test presionamos: ``Ctrl + Shift + F10``

````bash
org.opentest4j.AssertionFailedError: 
Expected :Martín
Actual   :null
````

Para corregir el error debemos ir a la clase base y ver el constructor **¡Ohh! Me olvidé de asignar el parámetro
person al atributo person**, lo corregimos y volvemos a ejecutar el test:

````java
public class Account {
    private String person;
    private BigDecimal balance;

    public Account(String person, BigDecimal balance) {
        this.person = person; // <----- ya corregido
        this.balance = balance;
    }
    /* Getters and Setters */
}
````

Ejecutamos nuevamente el test y vemos que **esta vez pasa la prueba.**

## Escribiendo test para el balance (saldo)

````java
class AccountTest {
    @Test
    void balanceAccountTets() {
        Account account = new Account("Martín", new BigDecimal("2000"));

        assertEquals(2000D, account.getBalance().doubleValue());            //(1)
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) == -1); //(2)
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) == 1);   //(3)
    }
}
````

**DONDE**

- **(1)**, verificamos que el saldo del objeto account sea igual al saldo esperado (2000). Como el tipo de dato del
  saldo es un BigDecimal, una forma de hacer la comparación es convirtiéndolo en un double.
- **(2)**, debe ser falso que el saldo de la cuenta sea menor que 0. El método **compareTo()** nos da 3 posibles
  valores, si el primer término es menor que (-1), si son iguales (0), pero si el primer término es mayor que (1).
- **(3)**, debe ser verdadero que el saldo es mayor que 0.

---

## Test Driven Development (TDD) con JUnit

Con TDD, primero creamos las pruebas luego implementamos la solución. Como primer ejemplo realizaremos un test que
compare dos objetos con los mismos valores, pero la comparación lo haremos por referencia:

````java
class AccountTest {
    @Test
    void referenceAccountTest() {
        Account account1 = new Account("Liz Gonzales", new BigDecimal("2500.00"));
        Account account2 = new Account("Liz Gonzales", new BigDecimal("2500.00"));

        assertNotEquals(account2, account1);
    }
}
````

El test anterior pasará la prueba, ya que **account1** y **account2** tienen referencias distintas, es decir, apuntan a
distintas direcciones de memoria.

Ahora, supongamos que las reglas del negocio cambian y nos piden comparar los dos objetos por valor, en esos términos,
ambos objetos son iguales, ya que contienen los mismos valores.

````java
class AccountTest {
    @Test
    void valueAccountTest() {
        Account account1 = new Account("Liz Gonzales", new BigDecimal("2500.00"));
        Account account2 = new Account("Liz Gonzales", new BigDecimal("2500.00"));

        assertEquals(account2, account1);
    }
}
````

Al hacer el test anterior, y sin haber modificado nada, la prueba va a fallar, ya que a pesar de que estamos tratando de
comparar por valor, en realidad se están comparando por referencia como en el **referenceAccountTest()**, el mensaje que
nos muestra es:

````bash
org.opentest4j.AssertionFailedError: 
Expected :org.magadiflo.junit5.app.models.Account@6a396c1e
Actual   :org.magadiflo.junit5.app.models.Account@6c3f5566
````

Entonces, la idea es que cuando hagamos las pruebas así tengamos objetos que apunten a direcciones de memorias distintas
(referencia), mientras los objetos tengan los mismos valores en sus atributos, nos deberá mostrar que son iguales. Por
defecto la comparación se hace por objeto, es decir por instancia o referencia de memoria. Si sobreescribimos el método
**equals()** veremos, como se mencionó, la comparación por objeto:

````java
public class Account {
    /* omitted code */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

````

Para poder hacer la comparación por valor, debemos modificar el método **equals()**. Podemos usar el mismo IntelliJ IDEA
para que nos genere el método implementado **equals()**:

````java
public class Account {
    /* omitted code */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(person, account.person) && Objects.equals(balance, account.balance);
    }
}
````

Como sobreescribimos el método **equals()** de la clase **Account**, ahora el test **valueAccountTest()** va a pasar,
puesto que ahora la comparación la hará **por valor** y no por referencia. **¿Y qué pasa con el test
referenceAccountTest()?**, bueno este test va a fallar, porque ahora ya no estamos comparando por referencia, sino por
valor, así que lo eliminamos del archivo de prueba y solo nos quedamos con el **valueAccountTest()**.

