#include <bits/stdc++.h>
 
using namespace std;
 
#define int int64_t
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    int n, k;
    cin >> n >> k;
    vector<int> v(n), w(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i] >> w[i];
    }
    double l = 0, r = 1e6;
    for (int i = 0; i < 100; i++) {
        double mid = (l + r) / 2;
        vector<double> delta(n);
        for (int i = 0; i < n; i++) {
            delta[i] = v[i] - w[i] * mid;
        }
        sort(delta.rbegin(), delta.rend());
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += delta[i];
        }
        if (sum >= 0) {
            l = mid;
        } else {
            r = mid;
        }
    }
    vector<pair<double, int>> delta(n);
    for (int i = 0; i < n; i++) {
        delta[i] = {v[i] - w[i] * l, i};
    }
    sort(delta.rbegin(), delta.rend());
    for (int i = 0; i < k; i++) {
        cout << delta[i].second + 1 << ' ';
    }
}