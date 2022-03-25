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
        //如果只有一个字符，开始就是末尾，需要多深入一层，保证这个字符被比较了
        if(chars.length == 1 && index == 1 && node.nexts.size() == 0){
            //到了末尾还没返回且结点也没有子结点了，说明这个字符串依次匹配成功
            result = true;
            return result;
        } else if(chars.length == 1 && index == 1 && node.nexts.size() != 0) {
            //如果字符串到了末尾，但是字典树还没有，说明这只是个前缀，不是完整的单词，返回false
            result = false;
            return result;
        }
        if(chars.length != 1 && index == chars.length - 1 && node.nexts.size() == 0) {
            //到了末尾还没返回且结点也没有子结点了，说明这个字符串依次匹配成功
            result = true;
            return result;
        } else if(chars.length != 1 && index == chars.length - 1 && node.nexts.size() != 0) {
            //如果字符串到了末尾，但是字典树还没有，说明这只是个前缀，不是完整的单词，返回false
            result = false;
            return  result;
        } else {
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
                    if(node.nexts.size() != 0) {
                        //如果有后继结点，则从第一个后继结点进入下一层
                        result = searchHelper(node.nexts.get(0), chars, ++index);
                    } else {
                        //没有后继结点，则直接返回false
                        result = false;
                        return result;
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
        }
        return result;
    }


    boolean startsWithHelper(Trie node, char[] chars, int index) {
       boolean result;
       //如果只有一个字符，开始就是末尾，需要多深入一层，保证这个字符被比较了
        if(chars.length == 1 && index == 1){
            //到了末尾还没返回，说明前面的都已经依次找到了
            result = true;
            return result;
        }
        if(chars.length != 1 && index == chars.length - 1) {
            //到了末尾还没返回，说明前面的都已经依次找到了
            result = true;
            return result;
        } else {
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
                        //没有后继结点，则直接返回false
                        result = false;
                        return result;
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
        }
        return result;
    }

    //2. 根据字母查找当前层
        //1. 不存在，创建
        //2. 存在，进入下一层
    //todo 还要考虑插入的字符串是以前插入的字符串的前缀的情况，这种情况下，如何识别这个字符串
    void insertHelper(Trie node, char[] chars, int index) {
        if(index >= chars.length){
            return;
        }
        if(node.pre == null) {
           //如果是根结点
           if(node.nexts.size() != 0) {
               //如果有后继结点
               insertHelper(node.nexts.get(0), chars, index);
           }else {
               //如果当前层没有后继结点则创建一个以当前字母为值的结点，继续递归
                   Trie newNode2 = new Trie();
                   newNode2.val = chars[index];
                   newNode2.index = 0;
                   newNode2.pre = node;
                   node.nexts.add(newNode2);
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
                        //到达字符数组的末尾，字母已遍历完成
                        return;
                    }
                }
            } else {
                //在当前层找该字母
                if(node.index+1 < node.pre.nexts.size()) {
                    insertHelper(node.pre.nexts.get(++(node.index)), chars, index);
                } else {
                    //如果当前层不包含该字母，则创建一个值为该字母的结点，并加入当前层
                    Trie newNode1 = new Trie();
                    newNode1.val = chars[index];
                    newNode1.pre = node.pre;
                    newNode1.index = ++node.index;
                    node.pre.nexts.add(newNode1);
                    //通过新创建的结点进入下一层
                    //当前结点没有后继结点,创建一个以下一字母为值的结点，继续递归
                        if(index+1 < chars.length) {
                            Trie newNode2 = new Trie();
                            newNode2.val = chars[index+1];
                            newNode2.index = 0;
                            newNode2.pre = node;
                            newNode1.nexts.add(newNode2);
                            insertHelper(newNode2, chars, ++index);
                        } else {
                            //到达字符数组的末尾，字母已遍历完成
                            return;
                        }

                    }
                }
             }
        }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String word1 = "zhang";
        String word2 = "zhdsg";
        String word3 = "zdgabasdjf";
        //todo 多个词会有问题
        trie.insert(word1);
        trie.insert(word2);
        trie.insert(word3);
        System.out.println(trie.search("zhan"));
        System.out.println(trie.startsWith("zhan"));
        System.out.println(trie.search("zhang"));
        System.out.println("end");
    }

}
