package com.vmware.questions.question1;

import java.util.ArrayList;
import java.util.List;

public class  CollectionCombiner {
    /**
     * combine two sorted collection
     */
    public Comparable[] mergeTwoCollections(Comparable[] c1, Comparable[] c2){
        // null check
        if(c1 == null && c2 == null)
        if(c1 == null || c1.length == 0){
            return c2;
        }
        if(c2 == null || c2.length == 0){
            return c1;
        }
        if(!c1[0].getClass().getName().equals(c2[0].getClass().getName())){
            throw new IllegalArgumentException("two collections have different type elements");
        }
        // do merge
        if(isAscCollection(c1) && isAscCollection(c2)) {
            return mergeAscAndAsc(c1, c2);
        } else if(isAscCollection(c1) && !isAscCollection(c2)){
            return mergeAscAndDsc(c1, c2);
        } else if(!isAscCollection(c1) && isAscCollection(c2)) {
            return mergeDscAndAsc(c1, c2);
        } else {
            return mergeDscAndDsc(c1,c2);
        }
    }

    private boolean isAscCollection(Comparable[] arr){
        if(arr[0].compareTo(arr[arr.length-1]) < 0){
            return true;
        }
        return false;
    }
    private Comparable[] mergeAscAndAsc(Comparable[] c1, Comparable[] c2) {
        Comparable[] resultArray = new Comparable[c1.length + c2.length];
        int c1Index = 0;
        int c2Index = 0;
        int resultIndex = 0;
        while (c1Index<c1.length && c2Index<c2.length){
            if(c1[c1Index].compareTo(c2[c2Index]) <= 0) {
                resultArray[resultIndex++] = c1[c1Index++];
            } else {
                resultArray[resultIndex++] = c2[c2Index++];
            }
        }

        while (c1Index < c1.length){
            resultArray[resultIndex++] = c1[c1Index++];
        }
        while (c2Index < c2.length){
            resultArray[resultIndex++] = c2[c2Index++];
        }

        return resultArray;
    }

    private Comparable[] mergeAscAndDsc(Comparable[] c1, Comparable[] c2) {
        Comparable[] resultArray = new Comparable[c1.length + c2.length];
        int c1Index = 0;
        int c2Index = c2.length-1;
        int resultIndex = 0;
        while (c1Index<c1.length && c2Index>=0){
            if(c1[c1Index].compareTo(c2[c2Index]) <= 0) {
                resultArray[resultIndex++] = c1[c1Index++];
            } else {
                resultArray[resultIndex++] = c2[c2Index--];
            }
        }

        while (c1Index < c1.length){
            resultArray[resultIndex++] = c1[c1Index++];
        }
        while (c2Index >= 0){
            resultArray[resultIndex++] = c2[c2Index--];
        }

        return resultArray;
    }

    private Comparable[] mergeDscAndAsc(Comparable[] c1, Comparable[] c2) {
        Comparable[] resultArray = new Comparable[c1.length + c2.length];
        int c1Index = c1.length-1;
        int c2Index = 0;
        int resultIndex = 0;
        while (c1Index>=0 && c2Index < c2.length){
            if(c1[c1Index].compareTo(c2[c2Index]) <= 0) {
                resultArray[resultIndex++] = c1[c1Index--];
            } else {
                resultArray[resultIndex++] = c2[c2Index++];
            }
        }

        while (c1Index >= 0){
            resultArray[resultIndex++] = c1[c1Index--];
        }
        while (c2Index < c2.length){
            resultArray[resultIndex++] = c2[c2Index++];
        }

        return resultArray;
    }

    private Comparable[] mergeDscAndDsc(Comparable[] c1, Comparable[] c2) {
        Comparable[] resultArray = new Comparable[c1.length + c2.length];
        int c1Index = c1.length-1;
        int c2Index = c2.length-1;
        int resultIndex = 0;
        while (c1Index>=0 && c2Index >= 0){
            if(c1[c1Index].compareTo(c2[c2Index]) <= 0) {
                resultArray[resultIndex++] = c1[c1Index--];
            } else {
                resultArray[resultIndex++] = c2[c2Index--];
            }
        }

        while (c1Index >= 0){
            resultArray[resultIndex++] = c1[c1Index--];
        }
        while (c2Index >= 0){
            resultArray[resultIndex++] = c2[c2Index--];
        }

        return resultArray;
    }

    /**
     * do merge n sorted collections
     * @param collections
     * @param left
     * @param right
     * @return
     */
    private Comparable[] mergeNCollections(List<Comparable[]> collections, int left, int right){

        Comparable[] result = null;
        if(left == right){
            return collections.get(left);
        } else if(right - left == 1){
            result = mergeTwoCollections(collections.get(left), collections.get(right));
        } else if (left < right){
            int mid = (left + right)/2;
            Comparable[] leftArray = mergeNCollections(collections, left, mid);
            Comparable[] rightArray = mergeNCollections(collections, mid, right);
            result = mergeTwoCollections(leftArray,rightArray);
        }
        return result;
    }

    /**
     * public method to merge n sorted collections
     * @param collections
     * @return
     */
    public Comparable[] mergeCollections(List<Comparable[]> collections){
        if(collections == null || collections.isEmpty()){
            return null;
        }
        if(collections.size() == 1){
            return collections.get(0);
        }
        return mergeNCollections(collections, 0, collections.size()-1);
    }

    public static void main(String[] args) {
        List<Comparable[]> lists = new ArrayList<Comparable[]>();
        Comparable[] arr1 = new Comparable[]{1,2,3,4,5,6,7};
        Comparable[] arr2 = new Comparable[]{100,99,66,55,44};
        Comparable[] arr3 = new Comparable[]{10,11,14,15};
        lists.add(arr1);
        lists.add(arr2);
        lists.add(arr3);
        CollectionCombiner demo = new CollectionCombiner();
        Comparable[] result = demo.mergeCollections(lists);
        for(Comparable c:result){
            System.out.println(c);
        }
    }
}
