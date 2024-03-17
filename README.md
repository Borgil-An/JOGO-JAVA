# JOGO-JAVA
Um jogo de tabuleiro simples programado em Java

O jogo é uma adaptação direta de “O Mundo de Wumpus”, um jogo que
consiste em um labirinto onde o jogador deve encontrar o ouro, evitar armadilhas,
matar o monstro e retornar para sua posição inicial (Neste em trabalho em questão,
usamos a posição A-1 : [0][14] para ser o start point do jogador).
Neste trabalho, tivemos a introdução de mais um monstro dentro do tabuleiro,
apelidado carinhosamente de Wompers ele é um monstro que assim como
Wumpus, quando encontrar o jogador ele causa dano, porém, o seu dano é
reduzido pela metade se comparado com o primeiro monstro, o que não causa
game over caso o jogador se depare com ele, e diferentemente do wumpus, ele
anda assim como a peça cavalo do xadrez, 2 casas verticais ou horizontais e então
mais uma casa em ângulo reto (Movimento de L).
As condições de vitória do jogador permanecem iguais ao jogo original, isto é,
ter mais de 0 de energia, o ouro e retornar à posição inicial, e as de derrota é ter sua
energia reduzida a 0 (Cair em buraco, um encontro com Wumpus ou dois encontros
com Wompers).
