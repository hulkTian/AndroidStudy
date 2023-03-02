//
// Created by ts on 2022/12/7.
//

#include "chapter_one.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <android/log.h>

//定义全局变量
char *content;

void pointAndAddress() {
    int number = 100;
    int *numberP = &number;
    content = "number的值是：%d\nnumberP的值是：%p\n*numberP的值是：%d";
    printf(content, number, numberP, *numberP);
}

int main() {
    switch (currentMethod) {
        case RUN_POINT_AND_ADDRESS:
            pointAndAddress();
            break;
        default:
            content = "Hello, World!";
            break;
    }
    return 0;//NULL == 0
}