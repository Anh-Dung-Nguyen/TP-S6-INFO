/*
// Created by nguyen-anh-dung on 4/9/25.
*/

#include <stdio.h>
#include <omp.h>

int main(){
    #pragma omp parallel
    {
        int thread_id = omp_get_thread_num();
        printf("Thread ID = %d\n", thread_id);
    }
    printf("Fin du programme\n");
    return 0;
}