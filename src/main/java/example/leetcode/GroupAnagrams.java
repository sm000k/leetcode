package example.leetcode;


import java.util.*;

// 2.5h
//https://leetcode.com/problems/group-anagrams/submissions/1181214491/?source=submission-noac
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        Map<Map<Character, Integer>, List<String>> mapOfAnagrams = new HashMap<>();
        for (String word : strs) {
            Map<Character, Integer> wordFingerPrint = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                wordFingerPrint.putIfAbsent(word.charAt(i), 1);
                wordFingerPrint.put(word.charAt(i), wordFingerPrint.get(word.charAt(i)) + 1);
            }
            if (!mapOfAnagrams.containsKey(wordFingerPrint)) {
                mapOfAnagrams.put(wordFingerPrint,Arrays.asList(word));
            } else {
                var temp = new ArrayList<>(mapOfAnagrams.get(wordFingerPrint));
                temp.add(word);
                mapOfAnagrams.put(wordFingerPrint,temp);
            }
        }
        for (Map.Entry <Map<Character, Integer>, List<String>> entry : mapOfAnagrams.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    public static void unitTest(String[] input, List<List<String>> expected) {
        System.out.print("input = " + Arrays.toString(input));
        System.out.print(" ");
        System.out.println("expected = " + expected);
        var result = groupAnagrams(input);
        System.out.println("result = " + result);
//        System.out.println(expected.equals(groupAnagrams(input)));

    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        unitTest(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}, Arrays.asList(
                        List.of("bat"),
                        Arrays.asList("nat", "tan"),
                        Arrays.asList("ate", "eat", "tea")
                )
        );
        unitTest(new String[]{"ddddddddddg", "dgggggggggg"}, Arrays.asList(
                        List.of("dgggggggggg"),
                        List.of("ddddddddddg")
                )
        );
    }
}
