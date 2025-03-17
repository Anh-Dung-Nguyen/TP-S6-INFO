CC = gcc
CFLAGS = -Wall -lpthread
TARGET = tp1

all: $(TARGET)

$(TARGET): tp1.o
	$(CC) $(CFLAGS) -o $(TARGET) tp1.o

tp1.o: tp1.c
	$(CC) $(CFLAGS) -c tp1.c

clean:
	rm -f *.o $(TARGET)
