Tom Bogaert
INFI-3

<div align="center">
  
# Prog. Avancée - Compte rendu

</div>

Ce rapport présente ma compréhension des concepts abordés dans les deux cours magistraux sur la **programmation répartie** (CM1) et la **gestion des threads et sections critiques** (CM2), ainsi que dans les **travaux pratiques** sur la gestion des **threads** en Java (TP1) et l'utilisation des **sémaphores** (TP2 et modification du TP1) et l'utilisation des moniteur (TP3). 

Le but est de mettre en lumière l'importance de la programmation concurrente et parallèle, ainsi que leur lien avec les architectures.

## 1. Compréhension des Concepts

### 1.1. Processus, Tâche, Thread

- **Processus** : Un processus est le support d'exécution d'une tâche c'est à dire une unité autonome qui exécute une tâche spécifique. Il possède ses propres ressources (mémoire, registres, etc.) et est isolé des autres processus. Dans un environnement multitâche, plusieurs processus peuvent être exécutés simultanément, mais chacun est indépendant.
  
- **Tâche** : Une tâche représente une unité de travail ou une opération spécifique effectuée par un processus. Dans le cadre de la **programmation parallèle**, une tâche peut être exécutée indépendamment des autres, permettant de traiter plusieurs opérations en même temps.

- **Thread** : Un **thread** est une subdivision d’un processus (Processus léger). Les threads partagent la même mémoire et les mêmes ressources du processus, ce qui permet une communication rapide entre eux, mais cela impose également une gestion stricte des accès concurrents pour éviter les conflits. Les threads sont utilisés pour gérer plusieurs tâches en parallèle au sein d’un même processus.

### Exemple concret : 
Imaginons une application de traitement d'image. Un processus unique pourrait traiter l'image dans son ensemble. Si nous utilisons des **threads**, nous pouvons diviser l'image en sections et attribuer chaque section à un thread différent, permettant ainsi un traitement en parallèle et donc plus rapide.

## 2. Le Cycle de Vie d'un Thread

Le **cycle de vie d'un thread** suit plusieurs états bien définis :

1. **Création** : Le thread est créé, mais il n’est pas encore en cours d'exécution. Cet état est initié par l'instanciation de la classe `Thread` en Java, par exemple.

2. **Prêt** : Une fois que la méthode `start()` est appelée, le thread est prêt à être exécuté. Il est en file d'attente, attendant que le processeur le prenne en charge. Le processeur utilise un algorithme d'éléction (FIFO/LIFO ...) pour séléctionner les threads.

3. **En exécution** : Lorsque le thread obtient le contrôle du processeur, il entre en état d'exécution. La méthode `run()` est alors activée.

4. **En attente ou en veille** : Un thread peut être mis en attente soit pour attendre une ressource (via `wait()`) ou être mis en veille (`sleep()`) pour un certain temps. Il ne consomme pas de ressources CPU dans cet état.

5. **Terminé** : Lorsque le thread a fini d'exécuter son travail (fin de la méthode `run()`), il entre dans un état terminé. Il ne sera plus relancé.

### Exemple dans le TP1 :
Dans le TP, un mobile est animé grâce à un thread. Ce thread suit un cycle de vie : il est créé avec `new Thread()`, prêt avec `start()`, en exécution dans `run()`, et mis en veille périodiquement avec `sleep()` pour contrôler la vitesse de déplacement.

Nous avons essayé de mettre en place un bouton qui permettait de stopper les mobiles en utilisant la méthode `suspend()`, qui permet de mettre en pause les threads et de les relancers en appuyant de nouveau sur sur ce même bouton pour appeler la méthode `resume()` pour remettre en exécution les threads. Mais je n'ai pas réussis à mettre en place cette fonctionnalité, car les méthodes `suspend()` et `resume()` sont obsolètes de la classe Thread en Java.

## 3. Sections Critiques et Synchronisation des Threads

Les **sections critiques** sont des parties du code où plusieurs threads accèdent à une ressource partagée (comme une variable ou un fichier) et qui ne peuvent être exécutées que par un thread à la fois pour éviter des résultats incohérents.

### Exemple :
Dans le cas d'une application de banque, plusieurs threads peuvent vouloir modifier le solde d’un compte en même temps. Sans synchronisation, deux threads peuvent lire la même valeur du solde et chacun peut la modifier sans prendre en compte l'autre, entraînant une incohérence. Cela peut être évité en encapsulant cette modification dans une section critique protégée par un verrou **MUTEX** grâce au mot-clé `synchronized` en Java.

## 4. Gestion des Threads : Verrous et Sémaphores

Pour gérer les conflits d'accès dans les sections critiques, il existe deux mécanismes principaux :

- **Verrou MUTEX** : C’est un verrou qui garantit qu'un seul thread accède à une section critique à la fois. En Java, il est implémenté avec le mot-clé `synchronized`. Lorsque le verrou est pris par un thread, les autres threads doivent attendre qu'il soit libéré.<br>
Le verrou MUTEX doit suivre 4 principes fondamentales :
  - À un moment donné, seul un processus peut être dans la section critique.
  - Si un processus est bloqué à l'extérieur de la section critique, un autre processus doit pouvoir y accéder.
  - Si plusieurs processus attendent d'entrer dans la section critique et qu'aucun n'y est, au moins un d'eux doit pouvoir y entrer dans un délai raisonnable.
  - La méthode utilisée doit être identique pour tous les processus.

- **Sémaphore** : Un sémaphore est un autre mécanisme de synchronisation, utile lorsqu'il y a plusieurs ressources à partager entre plusieurs threads. Un **sémaphore binaire** est équivalent à un MUTEX (1 ressource, 1 thread), tandis qu’un **sémaphore général** permet à un nombre limité de threads d'accéder simultanément à plusieurs ressources.

---
### Interprétation des cycles de vie et utilisation des sémaphores à travers le TP2 :

### 1. Cycle de Vie des Threads dans le TP2

Les threads dans ce programme sont gérés par la classe `Affichage`, qui hérite de la classe `Thread`. Voici une analyse détaillée des étapes du cycle de vie des threads.

### Création des Threads

Dans la classe `Main.java`, quatre objets de la classe `Affichage` sont créés. Chaque objet correspond à un thread.

```java
Affichage TA = new Affichage("AAAAAAA\n", sem);
Affichage TB = new Affichage("BBBBB\n", sem);
Affichage TC = new Affichage("CCC\n", sem);
Affichage TD = new Affichage("DDDDDD\n", sem);
```

Chaque thread est dans l'état **Nouveau** (`New`) lors de sa création. À ce stade, ils ne sont pas encore prêts à être exécutés.

### Lancement des Threads

Les threads sont démarrés en appelant la méthode `start()` :

```java
TB.start();
TA.start();
TC.start();
TD.start();
```

Cela change leur état de **Nouveau** à **Prêt à être exécuté** (`Runnable`), signifiant qu'ils peuvent maintenant être exécutés par l'OS. L'OS décide quel thread sera exécuté en fonction de la priorité ou d'autres critères.

### Exécution des Threads

Une fois qu’un thread est choisi par le planificateur, sa méthode `run()` est appelée. Voici les étapes principales :

1. Le thread tente d'acquérir le sémaphore en appelant `sem.syncWait()`. Si le sémaphore est disponible, il entre dans la section critique (l'affichage). Sinon, il attend que le sémaphore soit libéré.
2. Le thread exécute la méthode `run()`, affichant la chaîne de caractères caractère par caractère avec un délai de 100 ms entre chaque caractère.
3. Une fois l'affichage terminé, le thread libère le sémaphore avec `sem.syncSignal()`.

À ce stade, le thread est dans l'état **En Exécution** (`Running`).

### Blocage et Attente

Si un autre thread tente d'acquérir le sémaphore pendant qu'un thread est en exécution, il est bloqué et passe à l'état **En Attente** (`Blocked`). Cela se produit lorsque la méthode `wait()` est appelée dans `syncWait()`. Le thread reste bloqué jusqu'à ce que le sémaphore soit libéré.

### Terminaison des Threads

Après avoir terminé l'exécution de la méthode `run()`, le thread passe à l'état **Mort** (`Terminated`). À ce stade, il ne peut plus être relancé.


## 2. Fonctionnement des Sémaphores dans le TP2

Le **sémaphore binaire** dans ce programme est implémenté dans la classe `SemaphoreTPBin`. Son rôle est de gérer l'accès à la ressource partagée (la console pour l'affichage). Voici comment il fonctionne.

### Initialisation du Sémaphore

Le sémaphore est initialisé avec une valeur de 1, ce qui signifie qu’un seul thread peut accéder à la ressource à la fois :

```java
SemaphoreTPBin sem = new SemaphoreTPBin(1);
```

### Acquisition du Sémaphore : `syncWait()`

Chaque thread appelle `sem.syncWait()` pour tenter d'acquérir le sémaphore avant d'entrer dans la section critique.

```java
sem.syncWait();
```

- Si la valeur du sémaphore est 1 (ressource libre), le thread entre dans la section critique, et la valeur du sémaphore est décrémentée à 0 pour indiquer que la ressource est occupée.
- Si la valeur du sémaphore est 0 (ressource occupée), le thread est bloqué et attend que la ressource soit libérée. Il entre en **état d'attente** grâce à la méthode `wait()`.

Cette approche garantit une **exclusion mutuelle**, permettant à un seul thread d’accéder à la ressource à la fois.

### Libération du Sémaphore : `syncSignal()`

Après avoir terminé son exécution dans la section critique, le thread appelle `syncSignal()` pour libérer la ressource :

```java
sem.syncSignal();
```

- La valeur du sémaphore est réinitialisée à 1, signalant que la ressource est libre.
- La méthode `notify()` réveille un thread bloqué qui attendait la libération du sémaphore. Ce thread peut alors entrer dans la section critique.

### Pourquoi les threads ne sont-ils pas exécutés dans le même ordre à chaque exécution ?

Lorsque le programme est exécuté plusieurs fois, l'ordre d'exécution des threads varie en raison du fonctionnement du système d'exploitation (OS) qui gère le planificateur de threads. Ce planificateur détermine l’ordre d’exécution des threads en fonction de son algorithmes d'éléctions, et n'est donc pas déterministe. 

## 3. Conclusion

Dans ce programme, le **cycle de vie des threads** est contrôlé par un **sémaphore binaire**, qui assure la synchronisation des accès à la console pour l'affichage. Chaque thread passe par les étapes de création, exécution, blocage (si nécessaire), et terminaison, tandis que le sémaphore garantit qu’un seul thread à la fois accède à la ressource partagée.

Le sémaphore permet d'éviter que les lettres ne soit afficher mélanger.  


---

### Gestion des Sections Critiques dans le Déplacement des Mobiles
Dans la version du TP1 où nous avons ajouté une section critique sur la fenêtre à l'aide d'un sémaphore général, cela permet de créer une zone dans laquelle un nombre limité de mobiles peut se déplacer simultanément. Ce sémaphore général impose une limite sur le nombre de threads (ou de mobiles dans ce cas) pouvant entrer dans cette zone, assurant ainsi que seuls quelques mobiles y circulent à la fois.

La section critique s'applique aux boucles gérant le déplacement des mobiles dans la zone centrale de la fenêtre. Étant donné que les mobiles effectuent un aller-retour, il est nécessaire de définir deux sections critiques distinctes : une pour la boucle qui gère l'aller, et une autre pour celle qui gère le retour.

Remarque importante : bien que nous ayons deux sections critiques distinctes, elles utilisent le même sémaphore général pour gérer l'accès à la ressource partagée (la zone centrale de la fenêtre). Cela garantit qu'un nombre fixe de mobiles puisse être dans la zone à tout moment, que ce soit lors de l'aller ou du retour.

---

### Les Moniteurs et leur Fonctionnement
Les moniteurs sont des mécanismes de synchronisation utilisés pour contrôler l'accès concurrent à des ressources partagées par plusieurs threads. Ils assurent deux fonctions principales :

- **Exclusion Mutuelle** :
Un moniteur garantit qu’un seul thread à la fois peut accéder à une section critique (une ressource partagée). Cela évite les conflits lorsqu’un thread modifie ou lit une donnée, empêchant les autres threads d’interférer pendant cette opération. En Java, cela est géré avec les méthodes synchronisées.

- **Synchronisation Conditionnelle** :
Les moniteurs permettent aux threads de se mettre en attente lorsqu'une condition n’est pas remplie, et de se réveiller lorsque cette condition change. Cela se fait avec les méthodes wait(), notify(), et notifyAll() :

  - **wait()** met un thread en pause jusqu’à ce qu'une condition soit remplie.
  - **notify()** réveille un thread en attente.
  - **notifyAll()** réveille tous les threads bloqués sur une même condition.
  
Dans l'exercice 1 du TD3, un scénario de producteur/consommateur, le moniteur empêche le producteur et le consommateur d'accéder à la ressource partagée en même temps (exclusion mutuelle), dans le TD la lettre, et synchronise leur accès en fonction de l’état du buffer (le producteur attend si le buffer est plein, le consommateur attend s’il est vide).

---
## 5. Lien entre l’Architecture Matérielle et la Programmation Parallèle

Le passage à des architectures matérielles de plus en plus complexes, avec des **processeurs multicœurs** et des systèmes multiprocesseurs, est directement lié à l’essor de la programmation parallèle et répartie.

- **Processeurs Multicœurs** : Aujourd’hui, les processeurs modernes possèdent plusieurs cœurs, chacun étant capable d’exécuter des threads en parallèle. Cela permet à une application d'exploiter la puissance des différents cœurs pour traiter plusieurs tâches simultanément.

- **Mémoire Partagée vs. Mémoire Distribuée** : Dans les architectures à **mémoire partagée**, plusieurs processeurs ou cœurs accèdent à une seule mémoire centrale. C’est l’approche utilisée pour la gestion des threads en Java, où les threads partagent la même mémoire. En revanche, dans les architectures à **mémoire distribuée** (comme les grappes de serveurs), chaque machine possède sa propre mémoire, et la communication se fait par le réseau, ce qui nécessite une gestion différente des données.

### Exemple d’Architecture Parallèle :
Prenons l’exemple d'un jeu vidéo multijoueur : plusieurs cœurs peuvent être utilisés pour gérer différents aspects du jeu en parallèle (physique des objets, intelligence artificielle des ennemis, affichage graphique). Chaque tâche est exécutée en parallèle pour offrir une expérience fluide.

## 6. Conclusion

La programmation parallèle et la gestion des threads sont devenues incontournables avec les architectures matérielles modernes. La **programmation multithread** permet d’optimiser l’utilisation des processeurs multicœurs et de maximiser la performance des applications. Cependant, elle introduit des défis liés à la synchronisation des threads et à la gestion des ressources partagées. Les mécanismes de **sections critiques**, **verrous** et **sémaphores** sont essentiels pour garantir la cohérence des données.
