package structure.UnionFind;

/**
 * 路径压缩优化：此时rank只作为参考，不作为具体树的深度
 */
public class UnionFind5 implements UF {

    private int[] id;
    //rank[i]表示以i为根的集合所表示的树的层数(树深)
    private int[] rank;

    public UnionFind5(int size) {
        this.id = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.id[i] = i;
            this.rank[i] = 1;
        }
    }

    //返回根节点 O(h)
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        //在找根节点过程中顺便压缩
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            id[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            id[qRoot] = pRoot;
        } else {
            //注意：高度相等的特殊情况
            id[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }
}


