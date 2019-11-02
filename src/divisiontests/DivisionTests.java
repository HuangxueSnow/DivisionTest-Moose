/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package divisiontests;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class DivisionTests {

    /**
     * @param args the command line arguments
     */
   static long n, m;
    static ArrayList<ArrayList<Integer>> factorSets = new ArrayList<ArrayList<Integer>>();
    static Integer[] e1 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextLong();
        initFactorSets();
        long total = 0;

        boolean add = true;
        for (ArrayList<Integer> factorSet : factorSets) {
            for (int factor : factorSet) {
                if (add) {
                    total += m / factor;
                } else {
                    total -= m / factor;
                }
            }
            add = !add;
        }
        System.out.println(total);
    }

    private static void initFactorSets() {
        ArrayList<Integer> list = new ArrayList();
        for (int ele : e1) {
            if (ele <= n) {
                list.add(ele);
            } else {
                break;
            }
        }
        factorSets.add(list);
        for (int i = 2; i <= list.size(); i++) {
            factorSets.add(getFactorSet(list, 0, i));
        }
    }

    private static ArrayList<Integer> getFactorSet(ArrayList<Integer> list, int startIndex, int num) {
        ArrayList<Integer> ret = new ArrayList();
        int product=1;
        if (list.size()==startIndex+num) {
            for (int i=startIndex;i<list.size();i++) {
                product*=list.get(i);
            }
            ret.add(product);
            return ret;
        }
        if (num==1) {
            for (int i=startIndex;i<list.size();i++)
                ret.add(list.get(i));
            return ret;
        }

        for (int first = startIndex; first < list.size() - num + 1; first++) {
            int firstValue=list.get(first);
            ArrayList<Integer> al = getFactorSet(list, startIndex + 1, num - 1);
            for (int ele:al) {
                if(firstValue!=ele)
                    ret.add(firstValue*ele);
            }
        }
        return ret;
    }
}