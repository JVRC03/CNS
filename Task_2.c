
/*   Write a C program that contains a string (char pointer) with a value World
The program should AND or and XOR each character in this string with 127
and display the result

*/

#include <stdio.h>
#include<stdlib.h>

int main() {
    char *str = malloc(50 * sizeof(char )); 
    
    printf("\nEnter a String : ");
    scanf("%s", str);

    printf("\n<> --> Represents empty space:: \n");

    printf("Result after XOR operation : \n");
    for(char *i = str; *i != '\0'; i++)
    {
        printf("%c", *i ^ 127);
    }
    
    printf("\nResult after AND operation: \n");
    for(char *i = str; *i != '\0'; i++)
    {
        printf("%c", *i & 127);
    }
    
    printf("\nResult after OR operation: \n");
    for(char *i = str; *i != '\0'; i++)
    {
        int ans = *i | 127;
        if (ans > 126)
        {
            printf("<>");
            continue; 
        }
        printf("%c", *i | 127);
    }
    
    
    
    return 0;
}
