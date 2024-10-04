Tom Bogaert
INFI-3

<div align="center">
  
# Prog. Avancée - Compte rendu

</div>

Ce rapport présente la compréhension des concepts abordés dans deux cours magistraux sur la **programmation répartie** (CM1) et la **gestion des threads et sections critiques** (CM2), ainsi que dans un **travail pratique** sur la gestion des **threads** en Java (TP1). Le but est de mettre en lumière l'importance de la programmation concurrente et parallèle, ainsi que leur lien avec les architectures.

### 1. Compréhension des Concepts

#### 1.1. Processus, Tâche, Thread

- **Processus** : Un processus est le support d'exécution d'une tâche c'est à dire une unité autonome qui exécute une tâche spécifique. Il possède ses propres ressources (mémoire, registres, etc.) et est isolé des autres processus. Dans un environnement multitâche, plusieurs processus peuvent être exécutés simultanément, mais chacun est indépendant.
  
- **Tâche** : Une tâche représente une unité de travail ou une opération spécifique effectuée par un processus. Dans le cadre de la **programmation parallèle**, une tâche peut être exécutée indépendamment des autres, permettant de traiter plusieurs opérations en même temps.

- **Thread** : Un **thread** est une subdivision d’un processus (Processus léger). Les threads partagent la même mémoire et les mêmes ressources du processus, ce qui permet une communication rapide entre eux, mais cela impose également une gestion stricte des accès concurrents pour éviter les conflits. Les threads sont utilisés pour gérer plusieurs tâches en parallèle au sein d’un même processus.

#### Exemple concret : 
Imaginons une application de traitement d'image. Un processus unique pourrait traiter l'image dans son ensemble. Si nous utilisons des **threads**, nous pouvons diviser l'image en sections et attribuer chaque section à un thread différent, permettant ainsi un traitement en parallèle et donc plus rapide.

### 2. Le Cycle de Vie d'un Thread

Le **cycle de vie d'un thread** suit plusieurs états bien définis :

1. **Création** : Le thread est créé, mais il n’est pas encore en cours d'exécution. Cet état est initié par l'instanciation de la classe `Thread` en Java, par exemple.

2. **Prêt** : Une fois que la méthode `start()` est appelée, le thread est prêt à être exécuté. Il est en file d'attente, attendant que le processeur le prenne en charge. Le processeur utilise un algorithme d'éléction (FIFO/LIFO ...) pour séléctionner les threads.

3. **En exécution** : Lorsque le thread obtient le contrôle du processeur, il entre en état d'exécution. La méthode `run()` est alors activée.

4. **En attente ou en veille** : Un thread peut être mis en attente soit pour attendre une ressource (via `wait()`) ou être mis en veille (`sleep()`) pour un certain temps. Il ne consomme pas de ressources CPU dans cet état.

5. **Terminé** : Lorsque le thread a fini d'exécuter son travail (fin de la méthode `run()`), il entre dans un état terminé. Il ne sera plus relancé.

#### Exemple dans le TP1 :
Dans le TP, un mobile est animé grâce à un thread. Ce thread suit un cycle de vie : il est créé avec `new Thread()`, prêt avec `start()`, en exécution dans `run()`, et mis en veille périodiquement avec `sleep()` pour contrôler la vitesse de déplacement.

### 3. Sections Critiques et Synchronisation des Threads

Les **sections critiques** sont des parties du code où plusieurs threads accèdent à une ressource partagée (comme une variable ou un fichier) et qui ne peuvent être exécutées que par un thread à la fois pour éviter des résultats incohérents.

#### Exemple :
Dans le cas d'une application de banque, plusieurs threads peuvent vouloir modifier le solde d’un compte en même temps. Sans synchronisation, deux threads peuvent lire la même valeur du solde et chacun peut la modifier sans prendre en compte l'autre, entraînant une incohérence. Cela peut être évité en encapsulant cette modification dans une section critique protégée par un verrou **MUTEX** grâce au mot-clé `synchronized` en Java.

### 4. Gestion des Threads : Verrous et Sémaphores

Pour gérer les conflits d'accès dans les sections critiques, il existe deux mécanismes principaux :

- **Verrou MUTEX** : C’est un verrou qui garantit qu'un seul thread accède à une section critique à la fois. En Java, il est implémenté avec le mot-clé `synchronized`. Lorsque le verrou est pris par un thread, les autres threads doivent attendre qu'il soit libéré.<br>
Le verrou MUTEX doit suivre 4 principes fondamentales :
  - À un moment donné, seul un processus peut être dans la section critique.
  - Si un processus est bloqué à l'extérieur de la section critique, un autre processus doit pouvoir y accéder.
  - Si plusieurs processus attendent d'entrer dans la section critique et qu'aucun n'y est, au moins un d'eux doit pouvoir y entrer dans un délai raisonnable.
  - La méthode utilisée doit être identique pour tous les processus.

- **Sémaphore** : Un sémaphore est un autre mécanisme de synchronisation, utile lorsqu'il y a plusieurs ressources à partager entre plusieurs threads. Un **sémaphore binaire** est équivalent à un MUTEX (1 ressource, 1 thread), tandis qu’un **sémaphore général** permet à un nombre limité de threads d'accéder simultanément à plusieurs ressources.

#### Exemple d'utilisation d’un sémaphore :
Dans un parking avec un nombre limité de places, un sémaphore pourrait être utilisé pour indiquer combien de places sont encore disponibles. Chaque voiture (thread) doit obtenir une place (ressource) avant d'entrer. Lorsqu’une voiture sort, elle libère une place, permettant à une autre d’entrer.

### 5. Lien entre l’Architecture Matérielle et la Programmation Parallèle

Le passage à des architectures matérielles de plus en plus complexes, avec des **processeurs multicœurs** et des systèmes multiprocesseurs, est directement lié à l’essor de la programmation parallèle et répartie.

- **Processeurs Multicœurs** : Aujourd’hui, les processeurs modernes possèdent plusieurs cœurs, chacun étant capable d’exécuter des threads en parallèle. Cela permet à une application d'exploiter la puissance des différents cœurs pour traiter plusieurs tâches simultanément.

- **Mémoire Partagée vs. Mémoire Distribuée** : Dans les architectures à **mémoire partagée**, plusieurs processeurs ou cœurs accèdent à une seule mémoire centrale. C’est l’approche utilisée pour la gestion des threads en Java, où les threads partagent la même mémoire. En revanche, dans les architectures à **mémoire distribuée** (comme les grappes de serveurs), chaque machine possède sa propre mémoire, et la communication se fait par le réseau, ce qui nécessite une gestion différente des données.

#### Exemple d’Architecture Parallèle :
Prenons l’exemple d'un jeu vidéo multijoueur : plusieurs cœurs peuvent être utilisés pour gérer différents aspects du jeu en parallèle (physique des objets, intelligence artificielle des ennemis, affichage graphique). Chaque tâche est exécutée en parallèle pour offrir une expérience fluide.

### 6. Conclusion

La programmation parallèle et la gestion des threads sont devenues incontournables avec les architectures matérielles modernes. La **programmation multithread** permet d’optimiser l’utilisation des processeurs multicœurs et de maximiser la performance des applications. Cependant, elle introduit des défis liés à la synchronisation des threads et à la gestion des ressources partagées. Les mécanismes de **sections critiques**, **verrous** et **sémaphores** sont essentiels pour garantir la cohérence des données.
