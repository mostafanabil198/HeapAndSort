/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

/**
 *
 * @author arabtech
 */
public class Sort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public IHeap<T> heapSort(ArrayList<T> unordered) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sortSlow(ArrayList<T> unordered) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sortFast(ArrayList<T> unordered) {
        if (unordered == null) {
            throw new NullPointerException();
        }
        mergeSort(unordered, 0, unordered.size() - 1);
    }

    private void mergeSort(ArrayList<T> arr, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergeSort(arr, first, mid);
            mergeSort(arr, mid + 1, last);
            merge(arr, first, mid, last);
        }
    }

    private void merge(ArrayList<T> arr, int first, int mid, int last) {
        int f1I = first;
        int l1I = mid;
        int f2I = mid + 1;
        int l2I = last;
        int i;
        ArrayList<T> temp = new ArrayList<>();
        for (i = f1I; f1I <= l1I && f2I <= l2I; i++) {
            if (arr.get(f1I).compareTo(arr.get(f2I)) < 0) {
                temp.add(arr.get(f1I));
                f1I++;
            } else {
                temp.add(arr.get(f2I));
                f2I++;
            }
        }
        for (i = i; i <= last && f1I <= l1I; i++) {
            temp.add(arr.get(f1I));
            f1I++;
        }
        for (i = i; i <= last && f2I <= l2I; i++) {
            temp.add(arr.get(f2I));
            f2I++;
        }
        for (i = first; i <= last; i++) {
            arr.set(i, temp.get(i - first));
        }
    }

}
