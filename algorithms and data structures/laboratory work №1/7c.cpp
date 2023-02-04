#include <bits/stdc++.h>
#define pb push_back
#define mp make_pair
#define pp pair<int, int>
#define ppl pair<ll, ll>
#define fi first
#define se second
#define MAXN 200005

typedef long long ll;
int n, q;
int Next[MAXN + 1];
int lab[MAXN + 1];
int s[MAXN + 1];
 
int get(int u) {
    if (lab[u] < 0) {
        return u;
    }
    lab[u] = get(lab[u]);
    return lab[u];
}
 
void uni(int r1, int r2) {
    int x = lab[r1] + lab[r2];
    if (lab[r1] < lab[r2]) {
        lab[r1] = x;
        lab[r2] = r1;
    }
    else {
        lab[r2] = x;
        lab[r1] = r2;
    }
}

using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin >> n >> q;
    memset(lab, -1, sizeof(lab));
    for (int i = 1; i <= n; i++) {
        Next[i] = i + 1;
    }
    Next[n + 1] = n + 1;
    for (int i = 1; i <= q; i++) {
        int t, u, v;
        cin >> t >> u >> v;
        if (t == 3) {
            if (get(u) == get(v)) {
                puts("YES");
            }
            else {
                puts("NO");
            }
        }
        else {
            if (t == 1) {
                int r1 = get(u);
                int r2 = get(v);
                if (r1 != r2) {
                    uni(r1, r2);
                }
            }
            else {
                int pos = u;
                while (1) {
                    int m = Next[pos];
                    if (m > n || m > v) {
                        break;
                    }
                    int r1 = get(m);
                    int r2 = get(pos);
                    if (r1 == r2) {
                        Next[pos] = Next[m];
                    }
                    else {
                        uni(r1, r2);
                    }
                    pos = m;
                }
            }
        }
    }
    return 0;
}