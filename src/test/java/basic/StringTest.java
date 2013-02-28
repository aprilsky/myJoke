package basic;

/**
 * @Author: caoxiao
 * @Date: 13-2-27 上午10:53
 */
public class StringTest {
    /**
     *
     * @param storedFileId
     * @param s
     * @return s 在storedFileId 第二个位置的下标
     */
    private static int secondIndexOf(String storedFileId, String s) {
        char[] surrogates = storedFileId.toCharArray();
        int j = 2;
        int postion = -1;
        for (int i = 0; i < surrogates.length; i++) {
            postion ++;
            if(s.toCharArray()[0]==(surrogates[i])){
                j--;
                if(j==0){
                    break;
                }
            }
        }
        //storedFileId.lastIndexOf(0,postion);
        return postion;
    }
}
