
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapEasyProblems {

    // counting frequence of a string using hashmap
    public static Map<Character, Integer> countCharacters(String str) {

        Map<Character, Integer> characterMap = new HashMap<>();

        for (char ch : str.toCharArray()) {
            if (characterMap.containsKey(ch)) {
                characterMap.put(ch, characterMap.get(ch) + 1);
            } else {
                characterMap.put(ch, 1);
            }

        }

        return characterMap;
    }

    // Count frequency of words in a sentence
    public static Map<String, Integer> countWords(String sentence) {

        Map<String, Integer> words = new HashMap<>();

        String[] wordsArr = sentence.split(" ");

        for (String word : wordsArr) {
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }

        return words;
    }

    // finding first non repeative character
    public static Map<Character, Integer> firstNonRepeatingCharacter(String word) {
        Map<Character, Integer> characters = new LinkedHashMap<>();

        for (char ch : word.toCharArray()) {
            if (characters.containsKey(ch)) {
                characters.put(ch, characters.get(ch) + 1);
            } else {
                characters.put(ch, 1);
            }
        }

        Map<Character, Integer> calculatedMap = new LinkedHashMap<>();

        for (Map.Entry<Character, Integer> entry : characters.entrySet()) {
            Character ch = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1 || value < 2) {
                calculatedMap.put(ch, value);
            }

        }
        return calculatedMap;

    }

    public static boolean checkAnagrams(String wordOne, String wordTwo) {

        HashMap<Character, Integer> firstWordCount = new HashMap<>();

        HashMap<Character, Integer> secondWordCount = new HashMap<>();

        for (char word : wordOne.toCharArray()) {
            firstWordCount.put(word, firstWordCount.getOrDefault(word, 0) + 1);
        }

        for (char word : wordTwo.toCharArray()) {
            secondWordCount.put(word, secondWordCount.getOrDefault(word, 0) + 1);
        }

        // checking if both words have equal number of same character counts
        for (Map.Entry<Character, Integer> entry : firstWordCount.entrySet()) {
            Character ch = entry.getKey();
            Integer value = entry.getValue();
            // If the second map doesn't have this character or count differs
            if (!secondWordCount.containsKey(ch) || !secondWordCount.get(ch).equals(value)) {
                return false;
            }

        }
        return true;

    }

    public static void main(String[] args) {

        String str = "Hello";
        Map<Character, Integer> count = HashMapEasyProblems.countCharacters(str);
        System.out.println("Count of the string: " + count);

        String sentence = "This is a sentence sentence";
        System.out.println(countWords(sentence));

        Map<Character, Integer> word = firstNonRepeatingCharacter("swiss");
        System.out.println(word.entrySet().iterator().next());

        System.out.println(checkAnagrams("listen", "silent"));
        System.out.println(checkAnagrams("hello", "world"));
        System.out.println(checkAnagrams("a gentleman", "elegant man"));

    }
}