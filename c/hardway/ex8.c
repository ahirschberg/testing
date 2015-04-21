#include <stdio.h>

int main(int argc, char *argv[])
{
    char full_name[] = {
        'Z', 'e', 'd',
        ' ', 'A', '.', ' ',
        'S', 'h', 'a', 'w', '\0' // removing null byte doesn't seem to change anything, even in valgrind.
    };

    int areas[] = {10, 12, 13, 14, 20};
    char name[] = "Zed";

    full_name[1] = 'i';
    areas[0] = 100;
    areas[1] = full_name[4];
        
    // Warning: on some systems you may have to change the 
    // %u in this code to a %u since it will use unsigned ints
    printf("The size of an int: %u\n", sizeof(int));
    printf("The size of areas (int[]): %u\n", sizeof(areas));
    printf("The number of ints in areas: %u.\n", sizeof(areas) / sizeof(int));
    printf("The first area is %d, the second %d.\n", areas[0], areas[1]);

    printf("The size of a char: %u\n", sizeof(char));
    printf("the size of name (char[]): %u\n", sizeof(name));
    printf("The number of chars: %u\n", sizeof(name) / sizeof(char));

    printf("The size of full_name (char[]): %u\n", sizeof(full_name));
    printf("The number of chars %u\n", sizeof(full_name) / sizeof(char));

    printf("name='%s' and full_name='%s'\n", name, full_name);

    return 0;
}
