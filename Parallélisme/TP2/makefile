CC = gcc
CFLAGS = -Wall -pthread
TARGET = tp2
SRC = tp2.c tp2_pertes.c
OBJ = tp2.o tp2_pertes.o

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(OBJ) -o $(TARGET) $(CFLAGS)

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(OBJ) $(TARGET)
