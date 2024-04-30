//
//  factorial.c
//  MyProgram
//
//  Created by Nurzamat Nasyrkanov on 30/4/24.
//

#include <stdio.h>

// Function to find factorial of given number
unsigned int factorial(unsigned int n)
{
    if (n == 1) {
      return 1;
    }
   
    return n * factorial(n - 1);
}
 
int main(void)
{
    int num = 5;
    printf("Factorial of %d is %d", num, factorial(num));
    return 0;
}
