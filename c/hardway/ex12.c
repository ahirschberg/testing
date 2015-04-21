#include <stdio.h>

int main(int argc, char *argv[])
{
    int i = 0;

    if(argc == 1) {
        printf("You only have one command. You suck.\n");
    } else if(argc > 1 && argc < 4) {
        printf("Here's your input:\n");

        for(i = 0; i < argc; ++i) { // didn't know you could do ++i
            printf("%s ", argv[+i]); // unary + doesnt do anything afaik
        }
        printf("\n");
    } else {
        printf("You have too many arguments. You suck.\n");
    }

    return 0;
}
