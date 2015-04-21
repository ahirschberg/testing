#include <stdio.h>

int main(int argc, char *argv[]) 
{
    int numbers[4] = {0}; // sets indicies 1-3 to 0
    char name[4] = {'a'}; // sets indicies 1-3 to 0 ('\0')

    // first, print them out raw
    printf("numbers %d %d %d %d\n",
            numbers[0], numbers[1],
            numbers[2], numbers[3]);

    printf("name each: %c %c %c %c\n",
            name[0], name[1],
            name[2], name[3]);
    
    printf("name: %s\n", name);

    // setup the numbers
    int i;
    for (i = 0; i < sizeof(numbers) / sizeof(int); i++)
    {
        numbers[i] = i + 1;
    }

    name[0] = 'Z';
    name[1] = 'e';
    name[2] = 'd';
    name[3] = '\0';

    // them print them out initialized
    printf("numbers: %d %d %d %d\n",
            numbers[0], numbers[1],
            numbers[2], numbers[3]);
    
    printf("name each: %c %c %c %c\n",
            name[0], name[1],
            name[2], name[3]);

    // print the name like a string
    printf("name: %s\n", name);

    // another way to create a string
    char *another = "Zed";
    
    printf("another: %s\n", another);

    printf("another each: %c %c %c %c\n",
            another[0], another[1],
            another[2], another[3]);

    // assign characters to numbers[]
    printf("int i from old loop: %d\n", i); //=> 4
    for (i = 0; i < sizeof(numbers) / sizeof(int); i++)
    {
        name[i] = numbers[i];
    }
    
    printf("name each: %c %c %c %c\n",
            name[0], name[1],
            name[2], name[3]);

    // treat name like an integer
    printf("size int: %u, size char: %u\n", sizeof(int), sizeof(char));
    char to_int[4] = {100, 100, 100, 100};
    int string_i = (int)to_int;
    printf("string_i %d\n", string_i);
    
    return 0;
}
