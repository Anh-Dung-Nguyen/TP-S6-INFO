/*
// Created by nguyen-anh-dung on 4/9/25.
*/

// Version séquentielle

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <math.h>

#define N 1000000

int main(){
    int* a = malloc(sizeof(int) * N);
    int i, j, p = 0;

    for(i = 0; i < N; i++) {
        a[i] = i;
    }

    double start = omp_get_wtime();

    for (i = 2; i <= sqrt(N); i++) {
        if (a[i] > 0) {
            #pragma omp parallel for
            for (j = i * i; j < N; j += i) {
                a[j] = 0;
            }
        }
    }

    double end = omp_get_wtime();

    for (j = 0; j < N; j++) {
        if (a[j] > 0) {
            a[p] = a[j];
            p++;
        }
    }

    printf("Durée: %f\n", end - start);
    printf("Nombre total de premiers: %d\n", p);
    free(a);

    return 0;
}