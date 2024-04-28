//
//  fib.c
//  MyProgram
//
//  Created by Nurzamat Nasyrkanov on 28/4/24.
//

#include <stdio.h>

int fib(int n){
    if(n < 2)
        return n;

    return (fib(n-1) + fib(n-2));
}
