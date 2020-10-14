package edu.wofford.machiwoco;

import java.util.*; 
import java.lang.*; 
import java.io.*; 

class SortByCost implements Comparator<Landmark> 
{ 
    // Used for sorting in ascending order of 
    // landmark cost 
    public int compare(Landmark a, Landmark b) 
    { 
        return a.getCost() - b.getCost(); 
    } 
}