/*
// Created by nguyen-anh-dung on 4/9/25.
*/

#include <stdio.h>
#include <omp.h>

int main(){
    int nb_threads = 10;
    int taille_boucle = 100;

    omp_set_num_threads(nb_threads);

    #pragma omp parallel
    {
        int id = omp_get_thread_num();
        int debut = id * (taille_boucle / nb_threads);
        int fin = (id + 1) * (taille_boucle / nb_threads);

        for (int i = debut; i < fin; i ++){
            printf("Thread %d exécute l'itération %d\n", id, i);
        }
    }
    return 0;
}