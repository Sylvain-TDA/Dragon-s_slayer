## try

Le bloc try contient le code qui peut lever une exception. Il est toujours suivi d’un ou plusieurs blocs catch ou d’un bloc finally.

## catch

Le bloc catch permet de capturer et de traiter une exception levée dans le bloc try. Il spécifie le type d’exception à attraper et le code à exécuter en cas d’erreur.

## HeroOufOfTheBoard e

Dans un bloc catch, e est une variable qui représente l’exception capturée. Elle permet d’accéder aux informations de l’exception (comme son message avec e.getMessage()).

## e.getMessage()

La méthode getMessage() est héritée de la classe Throwable (dont Exception hérite). Elle retourne le message d’erreur associé à l’exception.

##  public int startingAGame() throws HeroOutOfTheBoardException

Cette signature indique que la méthode startingAGame peut lever une exception de type HeroOutOfTheBoardException. Les appelants de cette méthode doivent soit gérer cette exception avec un try-catch, soit la propager avec throws.

## throws

Le mot-clé throws est utilisé dans la signature d’une méthode pour indiquer que celle-ci peut lever une ou plusieurs exceptions. Il informe les appelants de la méthode qu’ils doivent gérer ces exceptions. 

##  throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");

Cette instruction lève une nouvelle instance de l’exception personnalisée HeroOutOfTheBoardException avec un message spécifique.

## throw

Le mot-clé throw est utilisé pour lever explicitement une exception dans un bloc de code.