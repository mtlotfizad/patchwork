package c20181125;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Q3 {
    class KeyValuePair implements Comparable<KeyValuePair> {
        int key, value;

        public KeyValuePair(int key, int value) {
            super();
            this.key = key;
            this.value = value;
        }

        public int compareTo(KeyValuePair o) {
            return key == o.key ? value - o.value : key - o.key;
        }
    }

    public int solution(String S) {
        List<Map.Entry<LocalTime, Integer>> calls = parseBill(S);
        Integer freeNumber = getFreeNumber(calls);

        int cost = getCost(calls, freeNumber, 0);


        return cost;
    }

    private int getCost(List<Map.Entry<LocalTime, Integer>> calls, Integer freeNumber, int cost) {
        for (Map.Entry call : calls) {
            if (!freeNumber.equals(call.getValue())) {
                int cullDuration = getTotalSeconds(call);
                if (cullDuration < 300)
                    cost += cullDuration * 3;
                else {
                    int minute = ((LocalTime) call.getKey()).getMinute();
                    if (((LocalTime) call.getKey()).getMinute() > 0)
                        minute += 1;
                    cost += 150 * minute;

                }
            }
        }
        return cost;
    }

    private Integer getFreeNumber(List<Map.Entry<LocalTime, Integer>> calls) {
        Map<Integer, Integer> grouped_calls = calls.stream().collect(
                Collectors.groupingBy(entry -> entry.getValue(),
                        Collectors.summingInt(entry -> getTotalSeconds(entry))));


        SortedSet<KeyValuePair> sortedSet = new TreeSet<KeyValuePair>();
        grouped_calls.forEach((k, v) -> sortedSet.add(new KeyValuePair(k, v)));

        return sortedSet.first().key;
    }

    private int getTotalSeconds(Map.Entry<LocalTime, Integer> entry) {
        return entry.getKey().getHour()
                * 3600 + entry.getKey().getMinute() * 60 + entry.getKey().getSecond();
    }


    private List<Map.Entry<LocalTime, Integer>> parseBill(String s) {
        List<Map.Entry<LocalTime, Integer>> callList = new ArrayList<>();
        List<String> calls = Arrays.asList(s.split("\n"));
        for (String c : calls) {

            String[] commaSplitted = c.split(",");
            callList.add(new AbstractMap.SimpleEntry<LocalTime, Integer>(LocalTime.parse(commaSplitted[0]),
                    Integer.parseInt(commaSplitted[1].replace("-", ""))));
        }

        return callList;
    }


    public static void main(String[] args) {
        Q3 q3 = new Q3();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(q3.solution("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n00:06:07,400-234-089"));
        System.out.println(q3.solution("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090"));
    }

}
