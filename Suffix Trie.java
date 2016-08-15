class Node{
    Node [] nodes;
    boolean isWord;
    boolean isSuffix;
    Node (){
        nodes = new Node[26];
    }

}

static class SuffixTrie {
    Node root;
    SuffixTrie ()
    {
        root = new Node();
    }
    void insert (String input)
    {
        if (input.length() == 0) return;

        insertHelper(root , input , 0 , true);
        for (int i = 1 ; i < input.length() ; ++i)
            insertHelper(root , input.substring(i) , 0 , false);

    }
    void insertHelper (Node node , String input , int idx , boolean isWord)
    {
        char current = input.charAt(idx);
        node.nodes[current-'a'] = new Node();
        if (idx == input.length() - 1)
        {
            node.nodes[current - 'a'].isSuffix = true;
            if (isWord) node.nodes[current - 'a'].isWord = true;
        }
        else
            insertHelper(node.nodes[current - 'a'] , input , idx + 1 , isWord);
    }
    boolean isSuffix (String input)
    {
        if (input.length() == 0) return true;
        return isSuffixHelper(root , input , 0);
    }
    boolean isSuffixHelper (Node node , String input , int idx)
    {
        char current = input.charAt(idx);
        if (node.nodes[current - 'a'] == null) return false;
        if (idx == input.length() - 1)
            return node.nodes[current - 'a'].isSuffix;
        return isSuffixHelper(node.nodes[current - 'a'] , input , idx + 1);
    }

    boolean isSubstring (String input)
    {
        if (input.length() == 0) return true;
        return isSubStringHelper(root , input , 0);
    }

    boolean isSubStringHelper (Node node , String input , int idx)
    {
        char current = input.charAt(idx);
        if (node.nodes[current - 'a'] == null) return false;
        if (idx == input.length() - 1)
            return true;

        return isSubStringHelper(node.nodes[current - 'a'] , input , idx + 1);
    }
