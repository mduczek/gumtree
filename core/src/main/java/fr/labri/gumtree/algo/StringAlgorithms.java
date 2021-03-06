package fr.labri.gumtree.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.labri.gumtree.tree.Tree;

public final class StringAlgorithms {

    private StringAlgorithms() {};

    public static List<int[]> lcss(String s0, String s1) {
            int[][] lengths = new int[s0.length() + 1][s1.length() + 1];
            for (int i = 0; i < s0.length(); i++)
                    for (int j = 0; j < s1.length(); j++)
                            if (s0.charAt(i) == (s1.charAt(j)))
                                    lengths[i+1][j+1] = lengths[i][j] + 1;
                            else
                                    lengths[i+1][j+1] = Math.max(lengths[i+1][j], lengths[i][j+1]);

            List<int[]> indexes = new ArrayList<>();

            for (int x = s0.length(), y = s1.length(); x != 0 && y != 0; ) {
                    if (lengths[x][y] == lengths[x - 1][y]) x--;
                    else if (lengths[x][y] == lengths[x][y - 1]) y--;
                    else {
                            indexes.add(new int[] {x - 1, y - 1});
                            x--;
                            y--;
                    }
            }
            Collections.reverse(indexes);
            return indexes;
    }

    public static List<int[]> hunks(String s0, String s1) {
            List<int[]> lcs = lcss(s0 ,s1);
            List<int[]> hunks = new ArrayList<int[]>();
            int inf0 = -1;
            int inf1 = -1;
            int last0 = -1;
            int last1 = -1;
            for (int i = 0; i < lcs.size(); i++) {
                    int[] match = lcs.get(i);
                    if (inf0 == -1 || inf1 == -1) {
                            inf0 = match[0];
                            inf1 = match[1];
                    } else if (last0 + 1 != match[0] || last1 + 1 != match[1]) {
                            hunks.add(new int[] {inf0, last0 + 1, inf1, last1 + 1});
                            inf0 = match[0];
                            inf1 = match[1];
                    } else if (i == lcs.size() - 1) {
                            hunks.add(new int[] {inf0, match[0] + 1, inf1, match[1] + 1});
                            break;
                    }
                    last0 = match[0];
                    last1 = match[1];
            }
            return hunks;
    }

    public static String lcs(String S1, String S2) {
            int Start = 0;
            int Max = 0;
            for (int i = 0; i < S1.length(); i++)
            {
                    for (int j = 0; j < S2.length(); j++)
                    {
                            int x = 0;
                            while (S1.charAt(i + x) == S2.charAt(j + x))
                            {
                                    x++;
                                    if (((i + x) >= S1.length()) || ((j + x) >= S2.length())) break;
                            }
                            if (x > Max)
                            {
                                    Max = x;
                                    Start = i;
                            }
                    }
            }
            return S1.substring(Start, (Start + Max));
    }

    public static List<int[]> lcss(List<Tree> s0, List<Tree> s1) {
            int[][] lengths = new int[s0.size() + 1][s1.size() + 1];
            for (int i = 0; i < s0.size(); i++)
                    for (int j = 0; j < s1.size(); j++)
                            if (s0.get(i).isSimilar(s1.get(j)))
                                    lengths[i+1][j+1] = lengths[i][j] + 1;
                            else
                                    lengths[i+1][j+1] = Math.max(lengths[i+1][j], lengths[i][j+1]);

            List<int[]> indexes = new ArrayList<>();

            for (int x = s0.size(), y = s1.size(); x != 0 && y != 0; ) {
                    if (lengths[x][y] == lengths[x - 1][y]) x--;
                    else if (lengths[x][y] == lengths[x][y - 1]) y--;
                    else {
                            indexes.add(new int[] {x - 1, y - 1});
                            x--;
                            y--;
                    }
            }
            Collections.reverse(indexes);
            return indexes;
    }

}
