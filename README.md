# Menu

En este repositorio se incluyen unas clases para construir un menu de opciones simple. 

Autor: Sergio Martí Torregrosa

Fecha: 22/11/2023

Version: 1.0

## Descripción

Las clases de este proyecto proporcionan una estructura flexible para la creación de menús de opciones fáciles de usar. 
La finalidad del proyecto es encapsular la lógica de los menus, permitiendo a los desarrolladores incorporar de manera 
eficiente sistemas de navegación jerárquica en sus aplicaciones. Es decir, los menús pueden contener submenús.
 
Se han tratado de seguir los principios SOLID para favorecer la máxima reutilización del proyecto.

##Contenido

Este repositorio contiene las siguientes clases:

###### Lógica interna
- **MenuItem**: representa un item simple del menu; una de las opciones. Contiene un identificador (*id*) y un nombre 
  (*name*), que es el que se muestra en el menu. Puede contener hijos, es decir, submenus. Por lo tanto, es un objeto 
  con una estructura de árbol, recursiva.
    
- **MenuManager**: es el gestor del menu. Gestiona una instancia de *MenuItem* para poder recorrer la estructura de árbol. 
  Por ejemplo, se puede recorrer el menu desde el elemento padre, a un elemento hijo, y después volver al padre.

###### Persistencia de datos

Los menus se pueden guardar como archivos *xml*.

- **MenuItemWriter**: esta clase sirve para guardar una instancia de *MenuItem* como un archivo *xml*.

- **MenuReaderDOM**: esta clase sirve para leer un archivo *xml* que contiene la información de un *MenuItem*, y 
  construir una instancia con esta información. Utiliza la tecnología de *DOM parser* (DOM son las siglas de 
  *Document Object Model*), es decir, que carga toda la información del archivo *xml* en memoria.
  
- **MenuItemXMLUtils**: esta clase contiene funciones y campos estáticos útiles para trabajar con archivos *xml*.

###### Consola

El módulo del proyecto "java-menu-item-console" contiene las clases para poder mostrar en consola el menú y que el 
usuario pueda interactuar con el menú de forma sencilla.

Se puede personalizar la forma de mostrar por pantalla el menú contando con diferentes opciones.

- **MenuConsolePrinter**: renderiza y muestra por pantalla un *MenuItem*. También se le puede proporcionar un 
  *MenuManager*. Dispone de varias opciones de personalización.
  
- **UserInputConsoleUtils**: esta clase contiene una serie de utilidades para poder manejar las entradas de usuario 
  por consola.
  
Estas son algunas capturas.

![Menu 1](C:\Users\Sergio\IdeaProjects\java-user-interface\captures\captura_01.PNG)

La imagen anterior es el estilo por defecto. Este es otro menú con otro estilo.

![Menu 2](C:\Users\Sergio\IdeaProjects\java-user-interface\captures\captura_02.PNG)

Para el menú anterior, se configuró la instancia *MenuConsolePrinter* de la siguiente forma:

![Configuración MenuConsolePrinter](C:\Users\Sergio\IdeaProjects\java-user-interface\captures\Configuracion_MenuConsolePrinter.PNG)

## Ejemplo

En este ejemplo se muestra el código para crear un programa que utiliza estas clases

```java
package org.ui.console;

import org.ui.console.printer.MenuConsolePrinter;
import org.ui.menu.MenuItem;
import org.ui.menu.MenuManager;
import org.ui.menu.io.read.MenuItemReaderDOM;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.ui.console.input.UserInputConsoleUtils.getUserInputIntBetweenBounds;

public class UserMenuTest {

    static String filename = "C:\\Users\\Sergio\\IdeaProjects\\java-user-interface\\files\\menu_magic_test.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        MenuManager mm = new MenuManager(MenuItemReaderDOM.read(filename));

        MenuConsolePrinter mcp = new MenuConsolePrinter();

        MenuItem command;
        do {
            mcp.show(mm);
            System.out.print("Introduza una opción: ");
            command = mm.onConfirm(getUserInputIntBetweenBounds(0, mm.getPeekNumChildren()) - 1);
            System.out.println("Seleccionado: " + command.getName());
            if (!command.hasItems() && mm.getStack().size() > 1) {
                mm.getStack().pop();
            }
            // manageAction(command.getId()); # <- Implementar aquí la acción de cada opción
        } while (command.getId() != -1);
    }

}
```

## Planes a futuro

En un futuro, se pretenden implementar las siguientes funcionalidades:

- Implementar funcionalidades para utilizar otras tecnologías para almacenar información. Por ejemplo: archivos *json*.
- Extender las funcionalidades de los items del menú (*MenuItem*). Por ejemplo: tablas, funciones, etc.
- Almacenar la información del estilo con que se muestra el menú en consola.
- Añadir una clase con algunos estilos predefinidos para mostrar el menú en consola.
- Implementar una forma de visualizar estos menus en una interfaz gráfica de JavaFX.