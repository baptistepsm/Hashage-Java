# Exercice n°1

## Question 1 : 

En utilisant le mot "MessageHashe", voici le hashé obtenu en utilisant la fonction de hashage SHA-1 : 8a6e2e7934501471c1c5284bfc11b7dadcb1fc32

## Question 2 :

### Pour 5 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 69 mots sur le mot "abandonnais"

## Question 3 :

### Pour 6 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 83 mots sur le mot "abandonnateur"

### Pour 7 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 83 mots sur le mot "abandonnateur"

### Pour 8 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 83 mots sur le mot "abandonnateur"

### Pour 9 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 293 mots sur le mot "abcedes"

### Pour 10 bits : 
En utilisant le mot "MessageHashe", j'obtiens une collision au bout de 1636 mots sur le mot "absolusses"

## Question 4 :
Nous sommes en train de casser la propriété de résistance à la collision.

## Question 5 :

### Pour 5 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 21 mots sur le mot "abaissant"

## Question 6 :

### Pour 6 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 159 mots sur le mot "abatardir"

### Pour 7 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 172 mots sur le mot "abatardis"

### Pour 8 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 172 mots sur le mot "abatardis"

### Pour 9 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 843 mots sur le mot "abonnerions"

### Pour 10 bits : 
En utilisant le mot "poisson", j'obtiens une collision au bout de 1371 mots sur le mot "abrier"


# Exercice n°2 :

## Question 4 :
En utilisant la phrase "Ceci est mon premier HMAC SHA1" ainsi que la clé "CleCompliquee", j'obtiens [ce résultat](https://github.com/baptistepsm/Hashage-Java/resources/q4_ex2.png)

## Question 5 :
En effet, en utilisant la classe HexFormat, il est assez simple de convertir un tableau de bytes en hexadécimal. J'obtiens donc [ce résultat](https://github.com/baptistepsm/Hashage-Java/resources/q5_ex2.png)

## Question 6 :
Une fois l'algorithme passé de SHA-1 à SHA-512, j'obtiens [ce résultat](https://github.com/baptistepsm/Hashage-Java/resources/q6_ex2.png). On observe que le résultat est beaucoup plus long que en SHA-1.