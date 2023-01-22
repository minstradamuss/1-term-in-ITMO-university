#include <bits/stdc++.h>
 
using namespace std;
 
//#define int int64_t
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    int n = 16;
    vector<vector<pair<int, int>>> net;
    for (int len = 1; len < n; len *= 2) {
        vector<pair<int, int>> cur_layer;
        for (int cur_pair = 0; cur_pair * len * 2 < n; cur_pair++) {
            for (int j = 0; j < len; j++) {
                cur_layer.push_back({len * cur_pair * 2 + j, len * (cur_pair * 2 + 1) + len - 1 - j});
            }
        }
        net.push_back(cur_layer);
        for (int gr = len / 2; gr >= 1; gr /= 2) {
            cur_layer.clear();
            for (int cur_pair = 0; cur_pair * gr * 2 < n; cur_pair++) {
                for (int i = 0; i < gr; i++) {
                    cur_layer.push_back({gr * cur_pair * 2 + i, gr * (cur_pair * 2 + 1) + i});
                }
            }
            net.push_back(cur_layer);
        }
    }
    cin >> n;
    vector<vector<pair<int, int>>> out;
    int comp_cnt = 0;
    for (int i = 0; i < net.size(); i++) {
        vector<pair<int, int>> cur_layer;
        for (int j = 0; j < net[i].size(); j++) {
            if (net[i][j].second < n) {
                cur_layer.push_back(net[i][j]);
                comp_cnt++;
            }
        }
        if (cur_layer.size()) {
            out.push_back(cur_layer);
        }
    }
    cout << n << ' ' << comp_cnt << ' ' << out.size() << endl;
    for (int i = 0; i < out.size(); i++) {
        cout << out[i].size() << ' ';
        for (int j = 0; j < out[i].size(); j++) {
            cout << out[i][j].first + 1 << ' ' << out[i][j].second + 1 << ' ';
        }
        cout << '\n';
    }
}