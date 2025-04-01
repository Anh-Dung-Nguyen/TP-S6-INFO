/*
// Created by nguyen-anh-dung on 3/31/25.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <pthread.h>

#define MULTICAST_IP "224.0.0.10"
#define PORT 10000
#define BUF_SIZE 256

int sock;
struct sockaddr_in multicast_addr;
char id[50];

void *send_messages(void *arg)
{
    char message[BUF_SIZE];
    char buffer[BUF_SIZE + 50];
    while (1)
    {
        fgets(message, BUF_SIZE, stdin);
        snprintf(buffer, sizeof(buffer), "%s @ %s", id, message);
        sendto(sock, buffer, strlen(buffer), 0, (struct sockaddr *)&multicast_addr, sizeof(multicast_addr));
    }
    return NULL;
}

void *receive_messages(void *arg)
{
    struct sockaddr_in sender_addr;
    socklen_t sender_len = sizeof(sender_addr);
    char buffer[BUF_SIZE + 50];
    while (1)
    {
        int n = recvfrom(sock, buffer, sizeof(buffer) - 1, 0, (struct sockaddr *)&sender_addr, &sender_len);
        if (n > 0)
        {
            buffer[n] = '\0';
            printf("%s", buffer);
        }
    }
    return NULL;
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Usage: %s <ID>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    strcpy(id, argv[1]);

    sock = socket(AF_INET, SOCK_DGRAM, 0);
    if (sock < 0)
    {
        perror("socket");
        exit(EXIT_FAILURE);
    }

    int reuse = 1;
    setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(reuse));

    struct sockaddr_in local_addr = {0};
    local_addr.sin_family = AF_INET;
    local_addr.sin_port = htons(PORT);
    local_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(sock, (struct sockaddr *)&local_addr, sizeof(local_addr)) < 0)
    {
        perror("bind");
        exit(EXIT_FAILURE);
    }

    struct ip_mreq mreq;
    mreq.imr_multiaddr.s_addr = inet_addr(MULTICAST_IP);
    mreq.imr_interface.s_addr = htonl(INADDR_ANY);
    if (setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, &mreq, sizeof(mreq)) < 0)
    {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }

    multicast_addr.sin_family = AF_INET;
    multicast_addr.sin_port = htons(PORT);
    multicast_addr.sin_addr.s_addr = inet_addr(MULTICAST_IP);

    pthread_t send_thread, receive_thread;
    pthread_create(&send_thread, NULL, send_messages, NULL);
    pthread_create(&receive_thread, NULL, receive_messages, NULL);

    pthread_join(send_thread, NULL);
    pthread_join(receive_thread, NULL);

    close(sock);
    return 0;
}
