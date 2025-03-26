/*
// Created by nguyen-anh-dung on 3/26/25.
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define N 10
int buffer[N];
int in = 0, out = 0, count = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t not_full = PTHREAD_COND_INITIALIZER;
pthread_cond_t not_empty = PTHREAD_COND_INITIALIZER;

void put(int v) {
    pthread_mutex_lock(&mutex);
    while (count == N){
        pthread_cond_wait(&not_full, &mutex);
    }
    buffer[in] = v;
    in = (in + 1) % N;
    count++;
    pthread_cond_signal(&not_empty);
    pthread_mutex_unlock(&mutex);
}

int get() {
    pthread_mutex_lock(&mutex);
    while (count == 0) {
        pthread_cond_wait(&not_empty, &mutex);
    }
    int v = buffer[out];
    out = (out - 1) % N;
    count--;
    pthread_cond_signal(&not_full);
    pthread_mutex_unlock(&mutex);
    return v;
}

void* producer(void* arg) {
    int id = *(int*)arg;
    for (int i = 0; i < N/2; i++) {
        put(i + id * 100);
        printf("Producteur %d a produit: %d\n", id, i + id * 100);
    }
}

void* consumer(void* arg) {
    int id = *(int*)arg;
    for (int i = 0; i < N/2; i++) {
        int v = get();
        printf("Consommateur %d consumer: %d\n", id, i + id * 100);
  }
  return NULL;
}

int main() {
  pthread_t producers[2], consumers[2];
  int ids[2] = {0, 1};

  pthread_create(&producers[0], NULL, producer, &ids[0]);
}