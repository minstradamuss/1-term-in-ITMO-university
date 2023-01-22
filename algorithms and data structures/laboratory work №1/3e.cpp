#include <bits/stdc++.h>
 
using namespace std;
 
#define int int64_t
 
int n, k;
vector<pair<int, int>> a;
 
double f(double x, double y) {
    vector<double> d(n);
    for (int i = 0; i < n; i++) {
        d[i] = sqrt((x - a[i].first) * (x - a[i].first) + (y - a[i].second) * (y - a[i].second));
    }
    sort(d.rbegin(), d.rend());
    double res = 0;
    for (int i = 0; i < k; i++) {
        res += d[i];
    }
    return res;
}
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    cin >> n >> k;
    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }
    double ans = 1e18;
    for (int i = 0; i < 20; i++) {
        double x = rand() * 2000.0 / RAND_MAX - 1000.0;
        double y = rand() * 2000.0 / RAND_MAX - 1000.0;
        double cur = f(x, y);
        double len = 100;
        for (int j = 0; j < 2000; j++) {
            double ang = rand() * 6.28318530718 / RAND_MAX;
            double dx = cos(ang) * len;
            double dy = sin(ang) * len;
            double new_cur = f(x + dx, y + dy);
            if (cur > new_cur) {
                cur = new_cur;
                x += dx;
                y += dy;
            }
            len *= 0.98;
        }
        ans = min(ans, cur);
    }
    cout.precision(10);
    cout << fixed << ans;
}