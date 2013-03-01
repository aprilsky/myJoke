package basic;

import java.util.*;

/**
 * @Author: caoxiao
 * @Date: 13-3-1 下午4:57
 */
public class ArrayTest {
    public static void main(String[] args) {
        testArraySort();
    }

    //数组排序
    static void testArraySort(){
        Integer[] arr = {1,2,2,3,7,7,5,5,5,6,10,10,10,5,3,2,1};
        final Map<Integer,Integer> elementNumberMap = new HashMap<Integer, Integer>();
        for (int i : arr) {
            if(elementNumberMap.containsKey(i)){
                elementNumberMap.put(i,elementNumberMap.get(i)+1);
            }else{
                elementNumberMap.put(i,1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : elementNumberMap.entrySet()) {
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }
        Arrays.sort(arr,new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                if(elementNumberMap.get(o1)!=elementNumberMap.get(o2)){
                    return elementNumberMap.get(o2)-elementNumberMap.get(o1);
                }else{
                    return o2-o1;
                }
            }
        });
        for (int i : arr) {
            System.out.print(i+"-->");
        }
    }
    /**
     * @return
     * 实现对map按照value降序排序
     */
    public static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        Map result = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
