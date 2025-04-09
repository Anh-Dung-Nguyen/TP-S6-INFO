/*
// Created by nguyen-anh-dung on 4/9/25.
*/

//Version séquentielle

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

#define N 1000
#define M 1000
#define MAX 100.0
#define SEUIL 0.1

int main(){
    static double T[N+2][M+2], T1[N+2][M+2];
    double delta = 0.0;
    int i, j, compteur = 0;

    double debut = omp_get_wtime();

    for (i = 0; i < N + 2; i++){
        for (j = 0; j < M + 2; j++){
            if (i == 0 || i == N + 1 || j == 0 || j == M + 1){
                T[i][j] = MAX;
            } else {
                T[i][j] = 0.0;
            }
        }
        do {
            delta = 0;
            for (i = 0; i <= N; i++) {
                for (j = 0; j <= M; j++) {
                    T1[i][j] = (T[i][j + 1] + T[i][j - 1] + T[i + 1][j] + T[i - 1][j] + T[i][j]) / 5;
                    delta += fabs(T1[i][j] - T[i][j]);
                }
            }
            for (i = 0; i <= N; i++) {
                for (j = 0; j <= M; j++) {
                    T[i][j] = T1[i][j];
                }
            }
            compteur ++;
        } while (delta > SEUIL);
    }
    double fin = omp_get_wtime();

    printf("Terminé en %d itérations\n", compteur);
    printf("Durée: %f s\n", fin - debut);

    return 0;
}