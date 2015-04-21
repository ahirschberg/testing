#include <stdio.h>

/* 
 *  valid format sequences:
    %i, %d - int
    %c     - char
    %f     - float
    %s     - string

 *  valid escape characters:
    \n - newline
    \t - tab
    \b - backspace
    \v - vertical tab
    \f - new page
    \r - carriage return
 */


int main()
{
    int age = 10;
    int height = 72;
    char character = 'a';

    printf("I am %d years old.\n", age);
    printf("I am %d inches tall.\n", height);
    printf("Favorite character: %c\n", character);

    return 0;
}
