/*
// Created by nguyen-anh-dung on 4/9/25.
*/
#include<stdio.h>
#include <omp.h>

int main () {
    static long nb_pas = 100000000; // 10^8
    double pas;
    long i;
    double x, pi, som = 0.0;

    double debut = omp_get_wtime();

    pas = 1.0 / (double) nb_pas;

    #pragma omp parallel for private (x) reduction(+:som)
    for (i = 1; i <= nb_pas; i++) {
        x = (i - 0.5) * pas;
        som = som + 4.0 / (1.0 + x * x);
    }

    pi = pas * som;

    double fin = omp_get_wtime();

    printf("PI = %f\n", pi);
    printf("Durée: %f secondes\n", fin - debut);

    return 0;
}