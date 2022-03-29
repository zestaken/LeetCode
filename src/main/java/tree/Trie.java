package tree;
/**
 class Trie {

 public Trie() {

 }

 public void insert(String word) {

 }

 public boolean search(String word) {

 }

 public boolean startsWith(String prefix) {

 }
 }
 */

import java.sql.Array;
import java.util.ArrayList;

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

public class Trie {
    char val;
    Trie pre;
    ArrayList<Trie> nexts;
    int index;
    //当前节点是否为一个word的末尾结点的标识（区分单纯是一个前缀还是一个完整的字符）
    boolean flag = false;

    public Trie() {
        nexts = new ArrayList<>();
        pre = null;
    }

    public void insert(String word) {
        //1. 拆分字符串
        char[] chars = word.toCharArray();
        insertHelper(this, chars, 0);
    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        return searchHelper(this, chars, 0);
    }

    public boolean startsWith(String word) {
        char[] chars = word.toCharArray();
        return startsWithHelper(this, chars, 0);
    }

    boolean searchHelper(Trie node, char[] chars, int index) {
        boolean result;
        //已经越界了还没有返回，说明前面的字符全部都已经正常找到了
        if(index >= chars.length) {
          if(node.pre.flag == true) {
              result = true;
              return result;
          }else {
              result = false;
              return result;
          }
        }
        //如果是根结点
        if(node.pre == null) {
            //如果有后继结点则向下一层寻找
            if(node.nexts.size() != 0) {
                result = searchHelper(node.nexts.get(0), chars, index);
            } else {
                //如果根结点没有后继结点，直接返回false
                result = false;
                return result;
            }
        } else {
            //如果不是根结点
            if(node.val == chars[index]) {
                //如果当前结点的值匹配，则顺着当前结点进入下一层
                if(node.nexts.size() != 0 ) {
                    //如果有后继结点，则从第一个后继结点进入下一层
                    result = searchHelper(node.nexts.get(0), chars, ++index);
                } else {
                    //没有后继结点
                    if(index + 1 < chars.length) {
                        //如果字符数组还有后续，则返回false
                        result = false;
                        return result;
                    } else {
                        result = true;
                        return result;
                    }
                }
            } else {
                //如果当前结点的值不匹配，则在当前层继续寻找
                if(node.index + 1 < node.pre.nexts.size()) {
                    result = searchHelper(node.pre.nexts.get(node.index+1), chars, index);
                } else {
                    //当前层遍历完也未能匹配，返回false
                    result = false;
                    return result;
                }
            }
        }
    return result;
    }


    boolean startsWithHelper(Trie node, char[] chars, int index) {
       boolean result;
       //已经越界了还没有返回，说明前面的字符全部都已经正常找到了
        if(index >= chars.length) {
            result = true;
            return result;
        }
        //如果是根结点
        if(node.pre == null) {
            //如果有后继结点则向下一层寻找
            if(node.nexts.size() != 0) {
                result = startsWithHelper(node.nexts.get(0), chars, index);
            } else {
                //如果根结点没有后继结点，直接返回false
                result = false;
                return result;
            }
        } else {
            //如果不是根结点
            if(node.val == chars[index]) {
                //如果当前结点的值匹配，则顺着当前结点进入下一层
                if(node.nexts.size() != 0) {
                    //如果有后继结点，则从第一个后继结点进入下一层
                    result = startsWithHelper(node.nexts.get(0), chars, ++index);
                } else {
                    //没有后继结点
                    if(index + 1 < chars.length) {
                        //如果字符数组还有后续，则返回false
                        result = false;
                        return result;
                    } else {
                        result = true;
                        return result;
                    }
                }
            } else {
                //如果当前结点的值不匹配，则在当前层继续寻找
                if(node.index + 1 < node.pre.nexts.size()) {
                    result = startsWithHelper(node.pre.nexts.get(node.index+1), chars, index);
                } else {
                    //当前层遍历完也未能匹配，返回false
                    result = false;
                    return result;
                }
            }
        }
    return result;
    }

    //2. 根据字母查找当前层
        //1. 不存在，创建
        //2. 存在，进入下一层
    //todo 还要考虑插入的字符串是以前插入的字符串的前缀的情况，这种情况下，如何识别这个字符串
    void insertHelper(Trie node, char[] chars, int index) {
        if(index >= chars.length){
            node.pre.flag = true;
            return;
        }
        if(node.pre == null) {
           //如果是根结点
           if(node.nexts.size() != 0) {
               //如果有后继结点
               insertHelper(node.nexts.get(0), chars, index);
           }else {
               //如果当前层没有后继结点则创建一个以当前字母为值的结点，继续递归
                   Trie newNode = new Trie();
                   newNode.val = chars[index];
                   newNode.index = 0;
                   newNode.pre = node;
                   node.nexts.add(newNode);
                   insertHelper(node.nexts.get(0), chars, index);
           }
        } else {
            //如果不是根节点
            //当前结点值与字母匹配
            if(node.val == chars[index]) {
                if(node.nexts.size() != 0) {
                    //如果有后继结点
                    insertHelper(node.nexts.get(0), chars, ++index);
                }else {
                    //如果当前层没有后继结点则创建一个以下一字母为值的结点，继续递归
                    if(index+1 < chars.length) {
                        Trie newNode2 = new Trie();
                        newNode2.val = chars[index+1];
                        newNode2.index = 0;
                        newNode2.pre = node;
                        node.nexts.add(newNode2);
                        insertHelper(node.nexts.get(0), chars, ++index);
                    } else {
                        //该结点是一个word的结尾
                        node.flag = true;
                        //到达字符数组的末尾，字母已遍历完成
                        return;
                    }
                }
            } else {
                //在当前层找该字母
                if(node.index+1 < node.pre.nexts.size()) {
                    insertHelper(node.pre.nexts.get(node.index + 1), chars, index);
                } else {
                    //如果当前层不包含该字母，则创建一个值为该字母的结点，并加入当前层
                    Trie newNode1 = new Trie();
                    newNode1.val = chars[index];
                    newNode1.pre = node.pre;
                    newNode1.index = node.index + 1;
                    node.pre.nexts.add(newNode1);
                    //通过新创建的结点进入下一层
                    //当前结点没有后继结点,创建一个以下一字母为值的结点，继续递归
                        if(index+1 < chars.length) {
                            Trie newNode2 = new Trie();
                            newNode2.val = chars[index+1];
                            newNode2.index = 0;
                            newNode2.pre = newNode1;
                            newNode1.nexts.add(newNode2);
                            insertHelper(newNode2, chars, ++index);
                        } else {
                            //当前结点是这个word的结尾
                            newNode1.flag = true;
                            //到达字符数组的末尾，字母已遍历完成
                            return;
                        }

                    }
                }
             }
        }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String word1 = "app";
        String word2 = "apple";
        String word3 = "beer";
        String word4 = "add";
        String word5 = "jam";
        String word6 = "rental";
//["Trie","insert","insert","insert","insert","insert","insert","search","search","search","search","search","search","search","search","search","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith"]
//[[],["app"],["apple"],["beer"],["add"],["jam"],["rental"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"]]
        trie.insert(word1);
        trie.insert(word2);
        trie.insert(word3);
        trie.insert(word4);
        trie.insert(word5);
        trie.insert(word6);
        System.out.println("apps: " + trie.search("apps"));
        System.out.println("app: " + trie.search("app"));
        System.out.println("ad: " + trie.search("ad"));
        System.out.println("applepie: " + trie.search("applepie"));
        System.out.println("rest: " + trie.search("rest"));
        System.out.println("jan: " + trie.search("jan"));
        System.out.println("rent: " + trie.search("rent"));
        System.out.println("beer: " + trie.search("beer"));
        System.out.println("jam: " + trie.search("jam"));
        System.out.println("startWith----------------------");
        System.out.println("apps： " + trie.startsWith("apps")); //todo
        System.out.println("app: " + trie.startsWith("app"));
        System.out.println("ad: " + trie.startsWith("ad"));
        System.out.println("applepie: " + trie.startsWith("applepie"));
        System.out.println("rest: " + trie.startsWith("rest"));
        System.out.println("jan: " + trie.startsWith("jan")); //todo
        System.out.println("rent: " + trie.startsWith("rent"));
        System.out.println("beer: " + trie.startsWith("beer"));
        System.out.println("jam: " + trie.startsWith("jam"));
    }

}
