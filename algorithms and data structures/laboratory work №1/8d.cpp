#include <bits/stdc++.h>
 
using namespace std;
 
#define int int64_t
 
const int MAX_W = 100;
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    int n;
    cin >> n;
    vector<int> w(n);
    int sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> w[i];
        sum += w[i];
    }
    vector<vector<int>> p(n + 1, vector<int>(n * MAX_W + 1, -2));
    p[0][0] = -1;
    for (int i = 0; i < n; i++) {
        vector<vector<int>> new_p = p;
        for (int j = 0; j <= i; j++) {
            for (int s = 0; s <= i * MAX_W; s++) {
                if (p[j][s] != -2 && p[j + 1][s + w[i]] == -2) {
                    new_p[j + 1][s + w[i]] = i;
                }
            }
        }
        p = new_p;
    }
    if (n % 2 || sum % 2 || p[n / 2][sum / 2] == -2) {
        cout << "-1\n";
        return 0;
    }
    vector<bool> ans(n);
    int cur_i = n / 2, cur_j = sum / 2;
    while (p[cur_i][cur_j] != -1) {
        int ind = p[cur_i][cur_j];
        ans[ind] = true;
        cur_i--;
        cur_j -= w[ind];
    }
    for (int i = 0; i < n; i++) {
        if (ans[i]) {
            cout << i + 1 << ' ';
        }
    }
    cout << '\n';
    for (int i = 0; i < n; i++) {
        if (!ans[i]) {
            cout << i + 1 << ' ';
        }
    }
    cout << '\n';
}