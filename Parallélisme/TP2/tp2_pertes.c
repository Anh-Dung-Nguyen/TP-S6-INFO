/*
// Created by nguyen-anh-dung on 3/26/25.
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include<unistd.h>

#define N 10

int buffer[N];
int in = 0, out = 0, count = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void ecrire(int t){
    pthread_mutex_lock(&mutex);
    if (count == N){
        in = (in - 1 + N) % N;
        count--;
    }
    buffer[in] = t;
    in = (in + 1) % N;
    count++;
    pthread_mutex_unlock(&mutex);
}

int lire(){
    int v;
    pthread_mutex_lock(&mutex);
    v = buffer[out];
    out = (out + 1) % N;
    count--;
    pthread_mutex_unlock(&mutex);
    return v;
}

void* producer(void* arg) {
    int id = *(int*)arg;
    int trame = 1;
    while(1){
        ecrire(trame);
        printf("Producteur %d a produit %d\n", id, trame);
        trame++;
        sleep(1);
    }
}

void* consumer(void* arg) {
    int id = *(int*)arg;
    while(1){
        int v = lire();
        printf("Consommateur %d a consume %d\n", id, v);
        sleep(1);
    }
}

int main() {
    pthread_t producteur_thread, consommateur_thread;
    int id = 0;

    pthread_create(&producteur_thread, NULL, producer, &id);
    pthread_create(&consommateur_thread, NULL, consumer, &id);

    pthread_join(producteur_thread, NULL);
    pthread_join(consommateur_thread, NULL);

    return 0;
}