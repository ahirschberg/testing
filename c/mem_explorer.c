#include <stdio.h>

int main() {
    int i = 1;
    int *ptr;

    ptr = i;
    while(1) {
        printf("%x\n", ptr);
        ptr+=1024;
    }

    return 0;
}
