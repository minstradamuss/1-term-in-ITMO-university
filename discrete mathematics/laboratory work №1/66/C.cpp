#include <bits/stdc++.h>

using namespace std;

vector <vector <int> > zn;
vector <int> table;
vector <int> index_value;
vector <int> depth;

int dfs(int v) {
	if (depth[v] > 0) {
		return depth[v];
	}
	else if (depth[v] == 0) {
		return depth[v];
	}
	else {
		depth[v] = 0;
		for (auto& i : zn[v])
			depth[v] = max(depth[v], dfs(i) + 1);
		return depth[v];
	}
}


int main() {

	int n;
	cin >> n;

	zn.resize(n);
	table.resize(n);
	index_value.resize(n, -1);

	int index = 0;

	for (int i = 0; i < n; i++) {
		int sz;
		cin >> sz;

		zn[i].resize(sz);

		for (auto& x : zn[i]) {
			cin >> x, x--;
		}
		if (sz == 0) {
			index_value[i] = index++;
		}
		else {
			for (int j = 0; j < (1 << sz); j++) {
				int k;
				cin >> k;
				if (k) {
					table[i] = table[i] ^ (1 << j);
				}
			}
		}
	}

	depth.resize(n, -1);
	cout << dfs(n - 1) << "\n";

	for (int check = 0; check < (1 << index); check++) {

		vector<bool> dp(n);

		for (int i = 0; i < n; ++i) {
			if (zn[i].empty()) {
				dp[i] = (check >> (index - 1 - index_value[i])) & 1;
				continue;
			}
			int check_sec = 0;
			for (int j = 0; j < zn[i].size(); ++j) {

				check_sec <<= 1;
				check_sec += dp[zn[i][j]];
			}

			dp[i] = (table[i] >> check_sec) & 1;
		}

		cout << dp[n - 1];
	}
	return 0;
}