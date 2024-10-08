import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        /*Collection*/

        Collection<Integer> collection = new ArrayList<>();

        final HashSet<String> stringHashSet = new HashSet<>();
         collection.stream()
                .filter(s -> s > 100)
                .map(integer -> integer.toString())
//                .collect(Collectors.toCollection(HashSet::new));
                .forEach(s -> stringHashSet.add(s));


        Map<String, Integer> nameToCount = new HashMap<>();
        final Map<String, Integer> distinctNamesWithStartLetterAToCount = nameToCount
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("A"))
                .collect(Collectors.toMap(entry1 -> entry1.getKey(), entry2 -> entry2.getValue()));


        int[] nameArr = new int[0];

        final HashSet<Integer> a = Arrays.stream(nameArr)
                .mapToObj(i -> ((Integer)i).toString())
                .sorted()
                .filter(s -> s.startsWith("A"))
                .filter(s -> s.length() > 100)
                .map(s -> s.length())
                .collect(Collectors.toCollection(HashSet::new));


        List<List<String>> list  = new LinkedList<>();
        final LinkedList<String> e = new LinkedList<>();
        e.add("a");
        list.add(e);
        final LinkedList<String> e1 = new LinkedList<>();
        e1.add("b");
        list.add(e1);

        ArrayList<String> defaultAStringList = new ArrayList<>();
        final ArrayList<String> defaultAAStringList = list.stream()
                .flatMap(strings -> strings.stream())
                .filter(s -> s.startsWith("a"))
                .peek(s -> defaultAStringList.add(s))
                .filter(s -> s.startsWith("aa"))
                .collect(Collectors.toCollection(ArrayList::new));


    }
}
