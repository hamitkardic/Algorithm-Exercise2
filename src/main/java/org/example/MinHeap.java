package org.example;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void insert(int value) {
        if (size >= capacity) {
            System.out.println("Heap is full. Cannot insert " + value);
            return;
        }

        heap[size] = value;
        int current = size;
        size++;

        while (current > 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public boolean contains(int value) {
        int current = 0;
        while (current < size) {
            if (heap[current] == value) {
                System.out.println(value + " in the heap.");
                return true;
            }
            System.out.print(heap[current] + " -> ");
            current++;
        }
        System.out.println(value + " not found in the heap.");
        return false;
    }

    public void delete(int value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(value + " not found in the heap. Cannot delete.");
            return;
        }

        heap[index] = heap[size - 1];
        size--;

        heapify(index);
    }

    private void heapify(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;

        if (left < size && heap[left] < heap[index]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(15);
        minHeap.insert(40);
        minHeap.insert(30);
        minHeap.insert(50);
        minHeap.insert(10);
        minHeap.insert(100);
        minHeap.insert(40);

        minHeap.printHeap();

        int valueToFind = 40;
        minHeap.contains(valueToFind);

        minHeap.delete(10);
        minHeap.printHeap();
    }
}
