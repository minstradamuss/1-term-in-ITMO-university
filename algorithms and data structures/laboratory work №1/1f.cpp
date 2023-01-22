#include <bits/stdc++.h>
 
using namespace std;
 
//#define int int64_t
 
int pq[100001];
long long int sz = 0;
 
int sift_down(int v) {
    if (2 * v > sz) {
        return v;
    }
    if (2 * v + 1 > sz || pq[v] >= pq[2 * v + 1]) {
        if (pq[v] < pq[2 * v]) {
            swap(pq[v], pq[2 * v]);
            return sift_down(2 * v);
        } else {
            return v;
        }
    }
    if (pq[v] >= pq[2 * v] || pq[2 * v] < pq[2 * v + 1]) {
        swap(pq[v], pq[2 * v + 1]);
        return sift_down(2 * v + 1);
    }
    swap(pq[v], pq[2 * v]);
    return sift_down(2 * v);
}
 
int sift_up(int v) {
    if (v == 1 || pq[v] <= pq[v / 2]) {
        return v;
    }
    swap(pq[v], pq[v / 2]);
    return sift_up(v / 2);
}
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    long long int n, m;
    cin >> n >> m;
    while (m--) {
        int t;
        cin >> t;
        if (t == 1) {
            if (sz == 0) {
                cout << -1 << '\n';
                continue;
            }
            swap(pq[1], pq[sz]);
            sz--;
            cout << (sz ? sift_down(1) : 0) << ' ' << pq[sz + 1] << '\n';
        } else if (t == 2) {
            long long int x;
            cin >> x;
            if (sz == n) {
                cout << -1 << '\n';
                continue;
            }
            sz++;
            pq[sz] = x;
            cout << sift_up(sz) << '\n';
        } else {
            long long int i;
            cin >> i;
            if (i > sz || i <= 0) {
                cout << -1 << '\n';
                continue;
            }
            cout << pq[i] << '\n';
            swap(pq[i], pq[sz]);
            sz--;
            if (sz) {
                sift_up(i);
                sift_down(i);
            }
        }
    }
    for (int i = 1; i <= sz; i++) {
        cout << pq[i] << ' ';
    }
    cout << '\n';
}