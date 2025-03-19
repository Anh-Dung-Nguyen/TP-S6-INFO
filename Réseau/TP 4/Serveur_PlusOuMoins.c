/* serveur_TCP.c (serveur TCP multi-clients) */

#ifdef WIN32 /* si vous êtes sous Windows */
#include <winsock2.h>
#elif defined (linux) /* si vous êtes sous Linux */
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> /* close */
#include <netdb.h> /* gethostbyname */
#endif

#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <time.h>
#include <signal.h>

static void init(void) {
#ifdef WIN32
    WSADATA wsa;
    int err = WSAStartup(MAKEWORD(2, 2), &wsa);
    if(err < 0) {
        puts("WSAStartup failed !");
        exit(EXIT_FAILURE);
    }
#endif
}

static void end(void) {
#ifdef WIN32
    WSACleanup();
#endif
}

void handle_client(int sock_pipe) {
    char buf_read[256], buf_write[256];
    int done = 0;
    int ret;
    int random = rand() % 100;

    while (!done) {
        ret = recv(sock_pipe, buf_read, 256, 0);
        if (ret <= 0) {
            done = 1;
        } else {
            buf_read[ret] = '\0';
            int num = atoi(buf_read);
            if (num < random) {
                strcpy(buf_write, "-1\n");
            } else if (num > random) {
                strcpy(buf_write, "1\n");
            } else {
                strcpy(buf_write, "0\n");
                done = 1;
            }
            send(sock_pipe, buf_write, strlen(buf_write), 0);
        }
        sleep(2);
    }
    close(sock_pipe);
    exit(0);
}

int main(int argc, char** argv) {
    srand(time(NULL));
    init();
    struct sockaddr_in serveur, client;

    if (argc != 3) {
        fprintf(stderr, "usage: %s id port\n", argv[0]);
        exit(1);
    }

    short port = atoi(argv[2]);
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock == -1) {
        perror("socket");
        exit(1);
    }

    serveur.sin_family = AF_INET;
    serveur.sin_port = htons(port);
    serveur.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(sock, (struct sockaddr *)&serveur, sizeof(serveur)) < 0) {
        perror("bind");
        exit(1);
    }

    if (listen(sock, 5) != 0) {
        perror("listen");
        exit(1);
    }

    while (1) {
        int len = sizeof(client);
        int sock_pipe = accept(sock, (struct sockaddr *)&client, &len);
        if (sock_pipe < 0) {
            perror("accept");
            continue;
        }

        pid_t pid = fork();
        if (pid == 0) { // Processus enfant
            close(sock); // Le fils n'a pas besoin du socket d'écoute
            handle_client(sock_pipe);
        } else if (pid > 0) {
            close(sock_pipe); // Le père ferme le socket client
        } else {
            perror("fork");
        }
    }

    end();
    return 0;
}
