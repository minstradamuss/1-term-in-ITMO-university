#include <bits/stdc++.h>
 
using namespace std;
 
//#define int int64_t
 
struct node {
    int p, x, depth;
    map<int, int> sons;
} nodes[6'100'000];
int nodes_sz = 1;
 
int son(int v, int x) {
    if (nodes[v].sons.find(x) == nodes[v].sons.end()) {
        nodes[nodes_sz].p = v;
        nodes[nodes_sz].x = x;
        nodes[nodes_sz].depth = nodes[v].depth + 1;
        nodes[v].sons[x] = nodes_sz++;
    }
    return nodes[v].sons[x];
}
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    nodes[0].p = -1;
    nodes[0].x = -1;
    nodes[0].depth = 0;
    int k, n;
    cin >> k >> n;
    vector<int> v(k);
    while (n--) {
        int t;
        cin >> t;
        if (t == 1) {
            int i, x;
            cin >> i >> x;
            i--;
            v[i] = son(v[i], x);
        } else if (t == 2) {
            int i;
            cin >> i;
            i--;
            v[i] = nodes[v[i]].p;
        } else {
            int i, j;
            cin >> i >> j;
            i--; j--;
            if (nodes[v[i]].depth < nodes[v[j]].depth) {
                swap(i, j);
            }
            while (nodes[v[i]].depth - nodes[v[j]].depth > 1) {
                v[j] = son(v[j], nodes[v[i]].x);
                v[i] = nodes[v[i]].p;
            }
        }
    }
    cout << nodes_sz - 1 << '\n';
}