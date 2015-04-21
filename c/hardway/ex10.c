#include <stdio.h>

int main(int argc, char *argv[])
{
    int i = 0;
    argv[1] = "====YOU HAVE BEEN HACKED====";

    // go through each string in argv
    // why am I skipping argv[0]? lol bc thats the filename
    for( ; i++, i < argc ; ) { // the same as saying i = 1
        printf("arg %d: %s\n", i, argv[i]);
    }

    // let's make our own array of strings
    char *states[] = {
        "Utah", "Ohio", NULL, "Hawaii"
    };
    int num_states = 4;

    for(i = 0; i < num_states; i++) {
        printf("state %d: %s\n", i, states[i]);
    }

    return 0;
}
