#include <stdio.h>
#include <string.h>

char toLower(char c)
{
    int LOWERCASE_OFFSET = 32; // http://www.asciitable.com/index/asciifull.gif
    if(c <= 'Z' && c >= 'A') {
        c += LOWERCASE_OFFSET;
    }

    return c;
}
int main(int argc, char *argv[])
{
    if(argc != 2) {
        printf("ERROR: You need one argument.\n");
        // this is how you abort a program
        return 1;
    }

    int i;
    
    /* Eventually figure out why this doesn't work */
    //int arg1_length = sizeof(argv[1]) / sizeof(char);
    //printf("length with sizeof: %d | length with strlen: %d\n", arg1_length, strlen(argv[1]));
    
    int  arg1_len = strlen(argv[1]);
    char lowered_arg[arg1_len + 1];// = {'\0'};
    lowered_arg[arg1_len] = '\0';

    char letter; 
    for (i = 0; letter = argv[1][i], letter != '\0'; ++i) {
        char lower_letter = toLower(letter);
        /*switch(lower_letter) {
            case 'a':
                printf("%d: 'A'\n", i);
                break;
            case 'e':
                printf("%d: 'E'\n", i);
            case 'i':
                printf("%d: 'I'\n", i);
                break;
            case 'o':
                printf("%d: 'O'\n", i);
                break;
            case 'u':
                printf("%d: 'U'\n", i);
                break;
            case 'y':
                if(i > 2) {
                    // it's only sometimes Y
                    printf("%d: 'Y'\n", i);
                }
                break;

            default:
                printf("%d: %c is not a vowel\n", i, letter);
        }*/

        //printf("letter %c has int %i\n", letter, letter);

        // switch letters to lower case
        lowered_arg[i] = lower_letter;
    }
    lowered_arg[strlen(argv[1])] = '\0'; // valgrind doesn't catch this when lowered_arg is made too small (or any assigns out of bounds for that matter)
    printf("Lowercase argument: %s\n", lowered_arg);

    return 0;
}


