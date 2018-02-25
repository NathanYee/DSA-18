PQ API

peek()
enqueue(i)/offer(i)
dequeueMax()/poll()

[8, 6, 2, 5, 3, 0, 1]

child: 2i + 1, 2i + 2
parent: (i - 1) / 2 

sinking: swap node with largest child - recursive

sink(A, i):
    if A[i] >= both_child
        stop
    child_idx = idx of largest child
    swap(A, i, child_idx)
    sink(A, child_idx)

float(A, i):
    if A[i] <= parent:
        stop
    p = idx_of_parent
    swap(A, i, p)
    float(A, p)

enqueue(A):
    add to end of array
    float end of arrau

dequeue_max()
    store root
    move end to the start
    sink root

heapify([])
    # call sink starting from the first non-leaf from the bottom
    for i in (A.len/2) - 1
        sink(A, i)


heapSort(A)
    heapify(A)
    for i :len(A)
        remove A[0] by placing it at end of heap
        sink A[0]


quickSort(A, lo, hi)
    if lo >= hi
    p = partition(A, lo, hi)
    quickSort(A, lo, p-1)
    quicksort(A, p+1, hi)
