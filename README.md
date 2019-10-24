# ER-to-AFND-to-AFD
Programa en java que acepta una expresión regular compuesta por las letras del alfabeto inglés más los operadores de unión ( | ), concatenación ( . ), estrella de Kleene ( * ) y paréntesis. Posteriormente pasa la ER a expresión polaca y lo transforma  a un autómata finito no determinístico, y finalmente del AFND lo pasa a un autómata finito determinístico. Una vez pasado al AFD, se entrega la cadena de texto (en una línea) que se quiere comparar con la ER y finalmente mostrará los índices del texto en donde termina cada ocurrencia.

PD: la ER y el texto para la ocurrencia se ingresan al ejecutar el programa, con la ER en la primera línea, y el texto en la segunda.
