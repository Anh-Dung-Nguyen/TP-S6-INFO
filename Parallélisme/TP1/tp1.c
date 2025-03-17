/*
// Created by nguyen-anh-dung on 2/26/25.
*/
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

#define N 1000
#define NUM_THREADS 10

int V[N];

void* somme(void* arg) {
    int* range = (int*) arg;
    int debut = range[0];
    int fin = range[1];

    long sum = 0;
    for (int i = debut; i < fin; i++) {
        sum += V[i];
    }

    printf("Somme de [%d, %d[ = %ld\n", pthread_self(), debut, fin, sum);
    pthread_exit(NULL);
}

int main() {
    pthread_t p_thread[NUM_THREADS];
    int range[NUM_THREADS][2];
    int segment_size = N / NUM_THREADS;

    for (int i = 0; i < NUM_THREADS; i++) {
        range[i][0] = i * segment_size;
        range[i][1] = (i == NUM_THREADS - 1) ? N : (i + 1) * segment_size;
        pthread_create(&p_thread[i], NULL, somme, (void*) range[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(p_thread[i], NULL);
    }

    return 0;
}