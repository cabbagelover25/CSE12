#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "stack.h"

// Edit only after this line //

// pop function for stack
Element* pop(Stack* s){
    /**If s is empty, return*/
    if(isEmpty(s)){
        return NULL;
    }
    /**If top/capacity is less then the shrinkFactor, divide capacity by
    two and make a new stack with this capacity that replaces the old one.*/
    if((float)s->top / (float)s->capacity <= s->shrinkFactor){
        s->capacity = (s->capacity / 2);
        s->elements = (struct Element**)realloc(s->elements, 
        sizeOf(struct Element** * capacity));
        //free(s); //Do I need to free it?
    }
    /**No matter what, the top item in elements is returned, and it's previous
    place is set to null, as well as top being decreased by one. */
    Element* topItem = stack->elements[top-1];
    stack->elements[top-1] = NULL;
    s->top = s->top - 1;
    return topItem;
}

// multipop: pop multiple elements from stack
Element** multiPop(Stack* s, int k){
  if(s->top == 0){
    return NULL;
  }
  Element** returnList = (struct Element**)malloc(sizeOf(struct Element** * 
  k));
  
  if(k >= currentSize()){
    returnList = s->elements;
    s->elements
  }

  for(int i = 0; i < returnList.length(); i++){
    returnList[i] = pop(s);
  }
  return returnList;
  
}

// Utility function: add element for push
// This function is a hint, you may ignore its implementation
void addE(Stack* s, int k, char* v) {

}

// Utility function: expandCapacity for push
// This function is a hint, you may ignore its implementation
void expandCapacity(Stack* s) {

}

// push function for stack
bool push(Stack* s, int k, char* v){
    if(isFull(s)){
        return false;
    }
    Element* newItem = (struct Element*)malloc(sizeof(struct Element*))
    newItem->value = v;
    newItem->key = k;
    if((float)s->top / (float)s->capacity >= s->growthFactor){
      s->capacity = (s->capacity * 2);
      s->elements = (struct Element**)realloc(sizeOf(struct Element** * capacity));
    }
    s->elements[s->top] = newItem;
    s->top += 1;
    return true;
}

// push unique function for stack
bool pushUnique(Stack* s, int k, char* v){
  if(isFull(s)){
      return false;
  }
  Element* newItem = (struct Element*)malloc(sizeof(struct Element*))
  newItem->value = v;
  newItem->key = k;
  s->elements[s->top] = newItem;
  if(!((s->elements[s->top-1]->value == v) && (s->elements[s->top-1]->key == k))){
    return false;
  }
  s->top += 1;
  return true;
  if((float)s->top / (float)s->capacity >= s->growthFactor){
    s->capacity = (s->capacity * 2);
    s->elements = (struct Element**)realloc(sizeOf(struct Element** * capacity));
  }
  if(s->elements)
  s->elements[s->top] = newItem;
  s->top += 1;
  return true;
}

// reverse the elements in stack
void reverse(Stack* s){

}

// peek function for stack
Element* peek(Stack* s){
  if(isEmpty(s)){
    return NULL;
  }
  return s->elements[top-1];
}

// search for element from top of stack
int search(Stack* s, int k, char* v){

}

// get current capacity of stack
int getCapacity(Stack* s){
    return stack->cap;
}

//check if stack is full
bool isFull(Stack* s){
  return stack->top == stack->capacity; 
}

// clear the stack
void clear(Stack *s) {
  stack->elements = ;
}

// clean the stack
void cleanStack(Stack *s) {
  for(int i = 0; i < s->capacity; i++) {
    free(s->elements[i]);
  }
	free(s->elements);
	free(s);
}

// current size of stack
int currentSize(Stack* s){
  return stack->top;
}

// check if stack empty
bool isEmpty(Stack* s){
  return stack->top == 0;
}

// Stack with fixed capacity
Stack* makeStack(int cap){
  struct Stack* = (struct Stack*) malloc(sizeof(struct Stack));
  stack->elements = (struct Element**)malloc(sizeof(struct Element * cap));
  stack->capacity = cap;
  stack->top = 0;
  stack->growthFactor = 1.0f;
  stack->shrinkFactor = 0.0f;
  return stack; 
}

// Stack that can grow
Stack* makeStackG(int cap, float growF){
    struct Stack* = (struct Stack*) malloc(sizeof(struct Stack));
    stack->elements = (struct Element**)malloc(sizeof(struct Element * cap));
    stack->capacity = cap;
    stack->top = 0;
    stack->growthFactor = growF;
    stack->shrinkFactor = 0.0f;
    return stack;
}

// Stack that can grow and shrink
Stack* makeStackGnS(int cap, float growF, float shrinkF){
    struct Stack* = (struct Stack*) malloc(sizeof(struct Stack));
    stack->elements = (struct Element**)malloc(sizeof(struct Element* * cap));
    stack->capacity = cap;
    stack->top = 0;
    stack->growthFactor = growF;
    stack->shrinkFactor = shrinkF;
    return stack;
}
